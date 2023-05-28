package com.example.Role.Based.Task.Repositery;

import com.example.Role.Based.Task.Entity.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
    @Query(value = "select * from user_info where emailid = :emailID",nativeQuery = true)
    User loginApi(String emailID);
}
