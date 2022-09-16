package com.example.springSecCrudUser.Repositories;

import com.example.springSecCrudUser.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository  extends JpaRepository<Users,Integer> {
    @Query(value = "SELECT * FROM USERS as u WHERE u.email= :email",nativeQuery = true)
    Users findByEmail(@Param("email") String email);
    @Query(value = "DELETE FROM USERS as u WHERE u.email= :email",nativeQuery = true)
    void deleteByEmail(@Param("email") String email) throws Exception;
}
