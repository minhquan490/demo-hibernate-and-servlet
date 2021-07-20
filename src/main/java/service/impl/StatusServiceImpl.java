package service.impl;

import java.util.List;

import dao.StatusDao;
import dao.impl.StatusDaoImpl;
import model.Status;
import service.StatusService;

public class StatusServiceImpl implements StatusService {

    StatusDao statusDao = new StatusDaoImpl();

    @Override
    public void save(Status status) {
        statusDao.save(status);
    }

    @Override
    public void edit(Status newStatus) {
        Status oldStatus = statusDao.find(newStatus.getId());
        oldStatus.setCart(newStatus.getCart());
        oldStatus.setApprovalStatus(newStatus.getApprovalStatus());
        statusDao.edit(oldStatus);
    }

    @Override
    public boolean delete(long id) {
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
    public List<Status> getListStatusOfUser(long id) {
        return statusDao.getListStatusOfUser(id);
    }
}