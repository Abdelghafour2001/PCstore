package project.computershop.service.custom;

import project.computershop.dto.CustomerTransactionDTO;
import project.computershop.dto.RepairTransactionDetailsDTO;
import project.computershop.dto.TransactionDTO;
import project.computershop.service.SuperService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface TransactionService extends SuperService {
    public long findTransactionCountByDate(LocalDate date) throws SQLException;

    public long transactionCount() throws SQLException;

    public double getIncomeByDate(LocalDate now) throws SQLException;

    public String getNextTransactionId() throws SQLException;

    public void saveTransaction(TransactionDTO transactionDTO) throws SQLException;

    public RepairTransactionDetailsDTO getRepairTransactionDetails(String repairId) throws SQLException;

    public TransactionDTO getTransaction(String transactionId) throws SQLException;

    public ArrayList<TransactionDTO> getAllFullTransaction() throws SQLException;

    public ArrayList<CustomerTransactionDTO> getAllCustomerTransaction() throws SQLException;
}
