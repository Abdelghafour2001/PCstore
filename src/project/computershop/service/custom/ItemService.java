package project.computershop.service.custom;

import project.computershop.dto.ItemDTO;
import project.computershop.service.SuperService;
import project.computershop.service.exception.DuplicateException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemService extends SuperService {
    public void deleteItem(String itemCode) throws SQLException;

    public ArrayList<ItemDTO> getAllItem() throws SQLException;

    public void saveItem(ItemDTO item) throws SQLException, DuplicateException;

    public String getNextItemCode() throws SQLException;

    public void updateItem(ItemDTO item) throws SQLException, DuplicateException ;

    public ItemDTO getItemByCode(String itemCode) throws SQLException;

    public ItemDTO getItemByDescription(String description) throws SQLException;
}
