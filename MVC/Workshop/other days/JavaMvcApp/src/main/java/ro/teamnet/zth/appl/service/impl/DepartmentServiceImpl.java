package ro.teamnet.zth.appl.service.impl;

import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.service.DepartmentService;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentDao departmentDao = new DepartmentDao();

    @Override
    public List<Department> findAllDepartments() {
        return departmentDao.findAllDepartments();
    }

    @Override
    public Department findOneDepartment(Long id) {
        return departmentDao.findDepartmentById(id);
    }

    @Override
    public void deleteOneDepartment(Long id) {
        departmentDao.deleteDepartment(departmentDao.findDepartmentById(id));
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentDao.insertDepartment(department);
    }
}
