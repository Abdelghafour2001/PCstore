package project.computershop.service.custom.impl;

import project.computershop.dao.DaoFactory;
import project.computershop.dao.DaoTypes;
import project.computershop.dao.custom.LoginRecordDAO;
import project.computershop.dao.custom.QueryDAO;
import project.computershop.dao.custom.UserDAO;
import project.computershop.dto.LoginDTO;
import project.computershop.dto.UserDTO;
import project.computershop.entity.LoginRecord;
import project.computershop.entity.User;
import project.computershop.service.custom.UserService;
import project.computershop.service.exception.DuplicateException;
import project.computershop.service.exception.NotFoundException;
import project.computershop.service.util.Convertor;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final LoginRecordDAO loginRecordDAO;
    private final QueryDAO queryDAO;
    private final Convertor convertor;
    public UserServiceImpl() {
        userDAO = DaoFactory.getInstance().getDAO(DaoTypes.USER);
        loginRecordDAO = DaoFactory.getInstance().getDAO(DaoTypes.LOGINRECORD);
        queryDAO = DaoFactory.getInstance().getDAO(DaoTypes.QUERY);
        convertor = new Convertor();
    }

    @Override
    public UserDTO userValidation(String userName, String userPassword, String rank) throws SQLException, NotFoundException {
        Optional<User> optional = userDAO.findByName(userName);
        if (optional.isPresent() && optional.get().getUserPassword().equals(userPassword) && optional.get().getRank().equals(rank)) {
            return convertor.fromUser(optional.get());
        }
        throw new NotFoundException("No such user is registered !");
    }

    @Override
    public String getNextUserId() throws SQLException {
        Optional<String> lastPk = userDAO.getLastPk();
        if(lastPk.isPresent()){
            String pk=lastPk.get().substring(1);
            pk=String.format("U%03d", Integer.parseInt(pk)+1);
            return pk;
        }
        return "U001";
    }

    @Override
    public void saveLogin(UserDTO user) throws SQLException {
        if (!loginRecordDAO.save(new LoginRecord(getNextLoginId(), Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), user.getUserId())))
            throw new SQLException("Login details save fail !");
    }

    @Override
    public ArrayList<LoginDTO> getAllUserLogin() throws SQLException {
        return queryDAO.findAllLoginRecord();
    }

    @Override
    public ArrayList<UserDTO> getAllUser() throws SQLException {
        return (ArrayList<UserDTO>) userDAO.getAll().stream().map(user -> convertor.fromUser(user)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String userId) throws SQLException {
        if (!userDAO.deleteByPk(userId))
            throw new SQLException("User deleting fail !");
    }

    @Override
    public void saveUser(UserDTO userDTO) throws SQLException, DuplicateException {
        if (userDAO.existsByNic(userDTO.getNic())){
            throw new DuplicateException("Duplicate user nic\nUser already saved ! ");
        }
        if (userDAO.findByName(userDTO.getUserName()).isPresent())
            throw new DuplicateException("Duplicate user name\nUser already saved ! ");
        if (!userDAO.save(convertor.toUser(userDTO)))
            throw new SQLException("Fail to save the user !");
    }

    @Override
    public void updateUser(UserDTO userDTO) throws SQLException, DuplicateException {
        User user = userDAO.findByPk(userDTO.getUserId()).get();
        if (!user.getNic().equals(userDTO.getNic()))
            if (userDAO.existsByNic(userDTO.getNic()))
                throw new DuplicateException("Another user nic is duplicated !");
        if (!user.getUserName().equals(userDTO.getUserName()))
            if (userDAO.findByName(userDTO.getUserName()).isPresent())
                throw new DuplicateException("Another user name is duplicated !");
        if (!userDAO.update(convertor.toUser(userDTO)))
            throw new SQLException("Fail to save the user !");
    }

    private String getNextLoginId() throws SQLException {
        Optional<String> lastPk = loginRecordDAO.getLastPk();
        if(lastPk.isPresent()){
            String pk=lastPk.get().substring(1);
            pk=String.format("L%03d", Integer.parseInt(pk)+1);
            return pk;
        }
        return "L001";
    }
}
