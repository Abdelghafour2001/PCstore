package project.computershop.dao.custom;

import project.computershop.dao.CrudDAO;
import project.computershop.entity.Employee;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<Employee,String> {
    boolean existsByNic(String nic) throws SQLException;
}
