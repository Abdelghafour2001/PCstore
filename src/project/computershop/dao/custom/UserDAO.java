package project.computershop.dao.custom;

import project.computershop.dao.CrudDAO;
import project.computershop.entity.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDAO extends CrudDAO<User,String> {
    Optional<User> findByName(String userName) throws SQLException;

    boolean existsByNic(String nic) throws SQLException;
}
