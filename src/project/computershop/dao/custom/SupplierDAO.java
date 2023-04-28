package project.computershop.dao.custom;

import project.computershop.dao.CrudDAO;
import project.computershop.entity.Supplier;

import java.sql.SQLException;
import java.util.Optional;

public interface SupplierDAO extends CrudDAO<Supplier,String> {
    Optional<Supplier> findByName(String name) throws SQLException;
}
