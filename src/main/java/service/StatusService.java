package service;

import java.util.List;

import model.Status;

public interface StatusService {
    
    void save(Status status);

    void edit(Status status);

    boolean delete(long id);

    List<Status> getAll();

    Status find(long id);
}