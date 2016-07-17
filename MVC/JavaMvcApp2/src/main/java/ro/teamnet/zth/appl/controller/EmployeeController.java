package ro.teamnet.zth.appl.controller;

import org.json.simple.JSONObject;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.service.EmployeeService;
import ro.teamnet.zth.appl.service.EmployeeServiceImpl;
import org.json.*;
import java.util.List;

/**
 * Created by user on 7/14/2016.
 */
@MyController(urlPath = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    /**
     * @return
     */
    @MyRequestMethod(urlPath = "/all")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
//        return "allEmployees";
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
        return "Delete done";
    }

//    TODO primire ca parametru un JSON
    // http://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java
    // http://crunchify.com/how-to-read-json-object-from-file-in-java/
    // get json file from url in java
    @MyRequestMethod(urlPath = "/save", methodType = "POST")
    public Employee saveOneEmployee(@MyRequestParam(paramName = "id") Long id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName("");
        employee.setLastName("");

//        JSONObject jsonObj = new JSONObject();
//        jsonObj.toString();

        return employeeService.saveEmployee(employee);
    }
}
