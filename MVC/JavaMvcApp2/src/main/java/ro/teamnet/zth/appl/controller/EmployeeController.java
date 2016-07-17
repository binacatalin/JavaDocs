package ro.teamnet.zth.appl.controller;

import com.google.gson.Gson;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.service.EmployeeService;
import ro.teamnet.zth.appl.service.EmployeeServiceImpl;
import org.json.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
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
// maybe paramName = "employee.json"
    @MyRequestMethod(urlPath = "/save", methodType = "POST")
    public Employee saveOneEmployee(@MyRequestParam(paramName = "employee") JSONObject jsonObject) {
//        ObjectMapper objectMapper = new ObjectMapper();

        Gson gson = new Gson();
        Employee employee = gson.fromJson(jsonObject.toJSONString(), Employee.class);

//        employee.setId(objectMapper.convertValue(jsonObj.get("id"), Long.class));
//        employee.setFirstName(objectMapper.convertValue(jsonObj.get("firstName"), String.class));
//        employee.setLastName(objectMapper.convertValue(jsonObj.get("lastName"), String.class));
//        employee.setEmail(objectMapper.convertValue(jsonObj.get("email"), String.class));
//        employee.setPhoneNumber(objectMapper.convertValue(jsonObj.get("phoneNumber"), String.class));
//
//        String hireDateString = objectMapper.convertValue(jsonObj.get("hireDate"), String.class);
//        DateFormat dateFormat = new SimpleDateFormat("DD-MM-YYYY");
//        Date hireDate = null;
//
//        try {
//            hireDate = (Date) dateFormat.parse(hireDateString);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        employee.setHireDate(hireDate);
//
//        employee.setJobId(objectMapper.convertValue(jsonObj.get("jobId"), String.class));
//        employee.setSalary(objectMapper.convertValue(jsonObj.get("salary"), BigDecimal.class));
//        employee.setCommissionPct(objectMapper.convertValue(jsonObj.get("commissionPct"), BigDecimal.class));
//        employee.setManagerId(objectMapper.convertValue(jsonObj.get("managerId"), Long.class));
//        employee.setDepartmentId(objectMapper.convertValue(jsonObj.get("departmentId"), Long.class));

        return employeeService.saveEmployee(employee);
    }
}
