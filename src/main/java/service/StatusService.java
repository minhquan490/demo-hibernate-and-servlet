package service;

import java.sql.SQLException;
import java.util.List;

import model.Status;

public interface StatusService {

    void save(Status status) throws SQLException;

    void edit(Status status) throws SQLException;

    boolean delete(long id) throws SQLException;

    List<Status> getAll();

    List<Status> getListStatusOfUser(String id);

    Status find(long id);
}