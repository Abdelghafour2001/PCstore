package project.computershop.dao.custom;

import project.computershop.dao.CrudDAO;
import project.computershop.entity.Repair;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepairDAO extends CrudDAO<Repair,String> {
    public ArrayList<Repair> findAllByCustomerId(String customerId) throws SQLException;

    public boolean updateStatus(String repairId, String status) throws SQLException;
}
