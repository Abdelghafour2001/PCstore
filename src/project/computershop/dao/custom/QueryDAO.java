package project.computershop.dao.custom;

import project.computershop.dao.SuperDAO;
import project.computershop.dto.CustomerTransactionDTO;
import project.computershop.dto.LoginDTO;
import project.computershop.dto.RepairDTO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface QueryDAO extends SuperDAO {

    public ArrayList<RepairDTO> getAllReturnedRepair() throws SQLException;

    public ArrayList<RepairDTO> getAllNonReturnedRepair() throws SQLException;

    public ArrayList<RepairDTO> findNonReturnRepairByCustomerId(String customerId) throws SQLException;

    public ArrayList<LoginDTO> findAllLoginRecord() throws SQLException;

    public double getIncomeByDate(Date date) throws SQLException;

    public Optional<CustomerTransactionDTO> getAllCustomerTransaction(String transactionId) throws SQLException;
}
