package com.example.PepsiKolya.dao;



import com.example.PepsiKolya.model.User;

import java.util.List;

public interface UserDao {

    public User getUserById(long  id);
    void save(User user);
    void removeById (long id);

    public List<User> getAll();

}
