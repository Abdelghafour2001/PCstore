package project.computershop.service.custom;

import project.computershop.dto.LoginDTO;
import project.computershop.dto.UserDTO;
import project.computershop.service.SuperService;
import project.computershop.service.exception.DuplicateException;
import project.computershop.service.exception.NotFoundException;
import project.computershop.view.tm.UserTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserService extends SuperService {
    public UserDTO userValidation(String userName, String userPassword, String rank) throws SQLException, NotFoundException;

    public String getNextUserId() throws SQLException;

    public void saveLogin(UserDTO user) throws SQLException;

    public ArrayList<LoginDTO> getAllUserLogin() throws SQLException;

    public ArrayList<UserDTO> getAllUser() throws SQLException;

    public void deleteUser(String userId) throws SQLException;

    public void saveUser(UserDTO userDTO) throws SQLException, DuplicateException;

    public void updateUser(UserDTO userDTO) throws SQLException, DuplicateException;
}
