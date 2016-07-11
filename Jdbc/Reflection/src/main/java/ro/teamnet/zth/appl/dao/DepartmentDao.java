package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Department;

import java.util.List;


/**
 * Created by user on 7/11/2016.
 */

public class DepartmentDao {

    EntityManager emi = new EntityManagerImpl();

    public Department getDepartmentById(Long id){
        return (Department) emi.findById(Department.class, id);
    }

    public List<Department> getAllDepartments(){
        return emi.findAll(Department.class);
    }

    public Department insertDepartment(Department d){
        return (Department)emi.insert(d);
    }

    public Department updateDepartment(Department d){
        return emi.update(d);
    }

    public void deleteDepartment(Department d){
        emi.delete(d);
    }
}