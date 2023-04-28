package project.computershop.dao.custom;

import project.computershop.dao.CrudDAO;
import project.computershop.entity.RepairTransactionDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface RepairTransactionDetailsDAO extends CrudDAO<RepairTransactionDetails,String> {
    public ArrayList<RepairTransactionDetails> findAllByTransactionId(String transactionId) throws SQLException;

    public Optional<RepairTransactionDetails> findByRepairId(String repairId) throws SQLException;
}
