package project.computershop.dao.custom;

import project.computershop.dao.CrudDAO;
import project.computershop.entity.Customer;

import java.sql.SQLException;
import java.util.Optional;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    public Optional<Customer> findByName(String name) throws SQLException;

    public boolean existsByNic(String nic) throws SQLException;
}
