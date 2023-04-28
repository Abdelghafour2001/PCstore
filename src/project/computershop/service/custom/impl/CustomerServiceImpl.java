package project.computershop.service.custom.impl;

import project.computershop.dao.DaoFactory;
import project.computershop.dao.DaoTypes;
import project.computershop.dao.SuperDAO;
import project.computershop.dao.custom.*;
import project.computershop.dto.CustomerDTO;
import project.computershop.dto.CustomerTransactionDTO;

import project.computershop.entity.Customer;
import project.computershop.service.custom.CustomerService;
import project.computershop.service.exception.DuplicateException;
import project.computershop.service.exception.InUseException;
import project.computershop.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerDAO customerDAO;
    private final QueryDAO queryDAO;
    private final Convertor convertor;
    public CustomerServiceImpl() {
        customerDAO = DaoFactory.getInstance().getDAO(DaoTypes.CUSTOMER);
        queryDAO = DaoFactory.getInstance().getDAO(DaoTypes.QUERY);
        convertor = new Convertor();
    }

    @Override
    public void saveCustomer(CustomerDTO customerDTO) throws  SQLException,DuplicateException {
        if (customerDAO.existsByNic(customerDTO.getNic()))
            throw new DuplicateException("Duplicate cin\nCustomer already saved !");
        if (!customerDAO.save(convertor.toCustomer(customerDTO)))
            throw new SQLException("Fail to save the customer !");
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) throws SQLException, DuplicateException {
        if (!customerDAO.findByPk(customerDTO.getCustomerId()).get().getNic().equals(customerDTO.getNic())){
            if (customerDAO.existsByNic(customerDTO.getNic()))
                throw new DuplicateException("Another customer nic is duplicated !");
        }
        if (!customerDAO.update(convertor.toCustomer(customerDTO)))
            throw new SQLException("Fail to update the customer !");
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException {
//        ArrayList<CustomerDTO>arrayList=new ArrayList<>();
//        for (Customer customer:customerDAO.getAll())
//            arrayList.add(convertor.fromCustomer(customer));
//        return arrayList;
        return (ArrayList<CustomerDTO>) customerDAO.getAll().stream().map(customer -> convertor.fromCustomer(customer)).collect(Collectors.toList());
    }

    @Override
    public void deleteCustomer(String customerId) throws SQLException, InUseException {
        if(queryDAO.findNonReturnRepairByCustomerId(customerId).size() > 0)
            throw new InUseException("This Customer has a repair that has not been returned !");
        if (!customerDAO.deleteByPk(customerId))
            throw new SQLException("Fail to delete the customer !");
    }

    @Override
    public String getNextCustomerId() throws SQLException {
        Optional<String> lastPk = customerDAO.getLastPk();
        if(lastPk.isPresent()){
            String pk=lastPk.get().substring(1);
            pk=String.format("C%03d", Integer.parseInt(pk)+1);
            return pk;
        }
        return "C001";
    }

    @Override
    public long getCustomerCount() throws SQLException {
        return customerDAO.count();
    }

    @Override
    public CustomerDTO getCustomer(String cusId) throws SQLException {
        Optional<Customer> optional = customerDAO.findByPk(cusId);
        if (optional.isPresent())
            return convertor.fromCustomer(optional.get());
        throw new SQLException("Customer Not found !");
    }

    @Override
    public CustomerDTO getCustomerByName(String name) throws SQLException {
        Optional<Customer> optional = customerDAO.findByName(name);
        if (optional.isPresent())
            return convertor.fromCustomer(optional.get());
        throw new SQLException("Customer Not found !");
    }
}
