package ro.teamnet.zth.appl.controller;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.service.DepartmentService;
import ro.teamnet.zth.appl.service.DepartmentServiceImpl;

import java.util.List;

/**
 * Created by user on 7/14/2016.
 */
@MyController(urlPath = "/departments")
public class DepartmentController {
    private final DepartmentService departmentService = new DepartmentServiceImpl();

    /**
     * @return
     */
    @MyRequestMethod(urlPath = "/all")
    public List<Department> getAllDepartments() {
        return departmentService.findAllDepartments();
    }

    /**
     * @return
     */
    @MyRequestMethod(urlPath = "/one")
    public Department getOneDepartment(@MyRequestParam(paramName = "id") Long id) {
//        Department department = new Department();
        return departmentService.findOneDepartment(id);
    }

    @MyRequestMethod(urlPath = "/delete", methodType = "DELETE")
    public String deleteOneDepartment(@MyRequestParam(paramName = "id") Long id) {
        departmentService.deleteOneDepartment(id);
        return "Delete done";
    }

    @MyRequestMethod(urlPath = "/save", methodType = "POST")
    public Department saveOneDepartment(@MyRequestParam(paramName = "department.json") JSONObject jsonObject) {
        Gson gson = new Gson();
        Department department = gson.fromJson(jsonObject.toJSONString(), Department.class);

        return departmentService.saveDepartment(department);
    }
}
