package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.domain.Department;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
public interface DepartmentService {
    List<Department> findAllDepartments();

    Department findOneDepartment(Long id);

    void deleteOneDepartment(Long id);

    Department saveDepartment(Department department);

    Department editDepartment(Department department);
}
