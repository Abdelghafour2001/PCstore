package project.computershop.dao.custom;

import project.computershop.dao.CrudDAO;
import project.computershop.entity.SuppliesDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SuppliesDetailsDAO extends CrudDAO<SuppliesDetails,String> {
    double findTotalByPk(String pk) throws SQLException;

    ArrayList<SuppliesDetails> findAllByPk(String pk) throws SQLException;
}
