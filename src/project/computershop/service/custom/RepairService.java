package project.computershop.service.custom;

import project.computershop.dto.RepairDTO;
import project.computershop.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepairService extends SuperService {
    public ArrayList<RepairDTO> getNonReturnRepair() throws SQLException;

    public RepairDTO getRepairById(String repairId) throws SQLException;

    public ArrayList<RepairDTO> getAllReturnedRepair() throws SQLException;

    public void deleteRepair(String repairId) throws SQLException;

    public void saveRepair(RepairDTO repairDTO) throws SQLException;

    public void updateRepair(RepairDTO repairDTO) throws SQLException;

    public String getNextRepairId() throws SQLException;
}
