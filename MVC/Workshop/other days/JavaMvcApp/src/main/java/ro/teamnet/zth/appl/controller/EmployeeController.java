package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.InjectService;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.service.EmployeeService;
import ro.teamnet.zth.appl.service.impl.EmployeeServiceImpl;

import java.util.List;

/**
 * Created by user on 7/14/2016.
 */
@MyController(urlPath = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

//      TODO - abstractizare
//    @InjectService
//    public EmployeeController(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }

    /**
     * @return
     */
    @MyRequestMethod(urlPath = "/all")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    /**
     * @return
     */
    @MyRequestMethod(urlPath = "/one")
    public Employee getOneEmployee(@MyRequestParam(paramName = "id") Long id) {
//        Employee employee = new Employee();
        return employeeService.findOneEmployee(id);
//        return employee.toString();
//        return "oneRandomEmployee";
    }

    @MyRequestMethod(urlPath = "/delete", methodType = "DELETE")
    public String deleteOneEmployee(@MyRequestParam(paramName = "id") Long id) {
        employeeService.deleteOneEmployee(id);
        Employee employee = employeeService.findOneEmployee(id);

        return employee.toString();
    }

    @MyRequestMethod(urlPath = "/save", methodType = "POST")
    public Employee saveOneEmployee(@MyRequestParam(paramName = "id") Long id, @MyRequestParam(paramName = "firstName")
            String firstName, @MyRequestParam(paramName = "lastName") String lastName) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);

        return employeeService.saveEmployee(employee);
    }
}
