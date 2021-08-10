package dao;

import java.util.List;

import model.Status;

public interface StatusDao {
    
    void save(Status status);

    void edit(Status status);

    boolean delete(long id);

    List<Status> getAll();

    List<Status> getListStatusOfUser(String id);

    Status find(long id);
}