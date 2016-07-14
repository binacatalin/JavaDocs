package ro.teamnet.zth;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.appl.controller.DepartmentController;
import ro.teamnet.zth.appl.controller.EmployeeController;
import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by user on 7/14/2016.
 */
public class MyDispatcherServlet extends HttpServlet {
    /**
     * key: urlPath
     * value: info abouth methiod
     */
    private Map<String, MethodAttributes> methodRegister;

    /**
     * @param req
     * @param resp
     */
    private void dispatchReply(String type, HttpServletRequest req, HttpServletResponse resp) {
//        r is the response
        Object dispatchResp = null;

        try {
            dispatchResp = dispatch(req, resp);
        } catch (Exception e) {
            sendExceptionError(e, req, resp);
        }
//
        try {
            reply(dispatchResp, req, resp);
        } catch (IOException e) {
            sendExceptionError(e, req, resp);
        }
    }

    /**
     * @param e
     * @param req
     * @param resp
     */
    private void sendExceptionError(Exception e, HttpServletRequest req, HttpServletResponse resp) {
    }


    /**
     * @param dispatchResp
     * @param req
     * @param resp
     */
    private void reply(Object dispatchResp, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter pw = resp.getWriter();
        pw.printf(dispatchResp.toString());
}

    /**
     * @param req
     * @param resp
     * @return
     */
    private Object dispatch(HttpServletRequest req, HttpServletResponse resp) {
        String pathInfo = req.getPathInfo();
        Object ret = "Hello";

////        TODO - "/mvc" nu face parte din pathInfo
////        exemplu de path: "/mvc/employees"
//        if (pathInfo.startsWith("/employees")) {
//            EmployeeController employeeController = new EmployeeController();
//            return employeeController.getAllEmployees();
//        } else if (pathInfo.startsWith("/departments")) {
//            DepartmentController departmentController = new DepartmentController();
//            return departmentController.getAllDepartments();
//        }
        MethodAttributes attribute = methodRegister.get(pathInfo);
        if (attribute != null) {
            String controllerName = attribute.getControllerClass();
            ret = "banana !!!";
            try {
                Class<?> controllerClass = Class.forName(controllerName);
                Object controllerInstance = controllerClass.newInstance();
                Method method = controllerClass.getMethod(attribute.getMethodName());
                return method.invoke(controllerInstance);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        instructiuni de delegare
        dispatchReply("GET", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        instructiuni de delegare
        dispatchReply("POST", req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        methodRegister = new HashMap<String, MethodAttributes>();
        Iterable<Class> controllersList = null;

        try {
            controllersList = AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.controller");
            for (Class controller : controllersList) {
                if (controller.isAnnotationPresent(MyController.class)) {
                    MyController annController = (MyController) controller.getAnnotation(MyController.class);
                    String controllerUrlPath = annController.urlPath();
                    Method[] methods = controller.getMethods();
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(MyRequestMethod.class)) {
                            MyRequestMethod myRequestMethod = method.getAnnotation(MyRequestMethod.class);
                            String methodUrlPath = myRequestMethod.urlPath();
                            String urlPath = controllerUrlPath + methodUrlPath;

                            MethodAttributes attribute = new MethodAttributes();
                            attribute.setControllerClass(controller.getName());
                            attribute.setMethodName(method.getName());
                            attribute.setMethodType(myRequestMethod.methodType());

                            methodRegister.put(urlPath, attribute);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
