package ro.teamnet.zth;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestObject;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.controller.DepartmentController;
import ro.teamnet.zth.appl.controller.EmployeeController;
import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * Created by user on 7/14/2016.
 */
public class MyDispatcherServlet extends HttpServlet {
    /**
     * key: urlPath
     * value: info abouth methiod
     * modif chie ca sa mearga si DELETE, POST, ...
     * Trebuie verificat tipul de request http
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
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter pw = resp.getWriter();

//        pw.printf(dispatchResp.toString());
        objectMapper.writeValueAsString(dispatchResp);
        pw.printf(objectMapper.writeValueAsString(dispatchResp));
    }

    /**
     * @param req
     * @param resp
     * @return
     */
    private Object dispatch(HttpServletRequest req, HttpServletResponse resp) {
        String pathInfo = req.getPathInfo();
        Object ret = "Hello";

//        TODO
        Long arg = null;
//        String val = req.getParameter("id");
//        if (val != null)
//            arg = Long.valueOf(val);

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

//                   use registerMethod
                Method method = controllerClass.getMethod(attribute.getMethodName(), attribute.getParameterTypes());
                Parameter[] parameters = method.getParameters();
                List<Object> paramValueList = new ArrayList<>();

                for (Parameter parameter : parameters) {
                    Class<?> type = parameter.getType();
                    if (parameter.isAnnotationPresent(MyRequestParam.class)) {
                        MyRequestParam requestParam = parameter.getAnnotation(MyRequestParam.class);
                        String name = requestParam.paramName();
                        String reqParamValue = req.getParameter(name);

                        Object reqParamObj = (type.equals(String.class)) ? reqParamValue : new ObjectMapper().readValue(reqParamValue, type);
                        paramValueList.add(reqParamObj);

                    } else if (parameter.isAnnotationPresent(MyRequestObject.class)) {
                        BufferedReader br = req.getReader();
                        Object reqBody = new ObjectMapper().readValue(br, type);

                        paramValueList.add(reqBody);
                    }
                }
                return method.invoke(controllerInstance, paramValueList.toArray());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("DELETE", req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("PUT", req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        methodRegister = new HashMap<String, MethodAttributes>();
        Iterable<Class> controllersList = null;
        Iterable<Class> servicesList = null;

        try {
            controllersList = AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.controller");
            servicesList = AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.service.impl");

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
                            attribute.setParameterTypes(method.getParameterTypes());

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
