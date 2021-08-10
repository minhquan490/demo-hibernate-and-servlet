package service.impl;

import java.sql.SQLException;
import java.util.List;

import dao.StatusDao;
import dao.impl.StatusDaoImpl;
import model.Status;
import service.StatusService;

public class StatusServiceImpl implements StatusService {

    StatusDao statusDao = new StatusDaoImpl();

    @Override
    public void save(Status status) throws SQLException {
        statusDao.save(status);
    }

    @Override
    public void edit(Status newStatus) throws SQLException {
        Status oldStatus = statusDao.find(newStatus.getId());
        oldStatus.setApprovalStatus(newStatus.getApprovalStatus());
        statusDao.edit(oldStatus);
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return statusDao.delete(id);
    }

    @Override
    public List<Status> getAll() {
        return statusDao.getAll();
    }

    @Override
    public Status find(long id) {
        return statusDao.find(id);
    }

    @Override
    public List<Status> getListStatusOfUser(String id) {
        return statusDao.getListStatusOfUser(id);
    }
}