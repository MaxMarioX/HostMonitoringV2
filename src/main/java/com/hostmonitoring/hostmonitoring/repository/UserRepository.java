package com.hostmonitoring.hostmonitoring.repository;

import com.hostmonitoring.hostmonitoring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String username);
}
