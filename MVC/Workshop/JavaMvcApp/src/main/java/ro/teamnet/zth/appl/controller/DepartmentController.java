package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
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
}
