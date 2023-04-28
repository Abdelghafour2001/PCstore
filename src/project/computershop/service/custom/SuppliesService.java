package project.computershop.service.custom;

import project.computershop.dto.SuppliesDTO;
import project.computershop.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SuppliesService extends SuperService {
    public String getNextSuppliesId() throws SQLException;

    public void saveSupplies(SuppliesDTO suppliesDTO) throws SQLException;

    public ArrayList<SuppliesDTO> getAllFullSupplies() throws SQLException;
}
