package project.computershop.dao.custom;

import project.computershop.dao.CrudDAO;
import project.computershop.entity.ItemTransactionDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemTransactionDetailsDAO extends CrudDAO<ItemTransactionDetails,String> {
    ArrayList<ItemTransactionDetails> findAllById(String id) throws SQLException;
}
