package com.example.MyApp.Repository;

import com.example.MyApp.Entity.UserList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserListRepository extends CrudRepository<UserList, Integer> {
    UserList findById (int id);
}
