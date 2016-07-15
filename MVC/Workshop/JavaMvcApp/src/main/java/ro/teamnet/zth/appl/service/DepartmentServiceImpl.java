package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.domain.Department;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
public class DepartmentServiceImpl implements DepartmentService {
    @Override
    public List<Department> findAllDepartments() {
        DepartmentDao departmentDao = new DepartmentDao();
        return departmentDao.findAllDepartments();
    }
}
