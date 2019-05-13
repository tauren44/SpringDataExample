package com.example.springdata.repository;

import com.example.springdata.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users " +
            "WHERE email IS NOT NULL " +
            "AND extract(MONTH FROM birthday) = :m " +
            "AND extract(DAY FROM birthday) = :d",
            nativeQuery = true)
    List<User> findByMatchMonthAndMatchDay(@Param("m") int month, @Param("d") int day);

    @Query(value = "SELECT * FROM users WHERE email = :e", nativeQuery = true)
    User findOneByEmail(@Param("e") String email);
}
