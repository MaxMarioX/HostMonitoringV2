package com.hostmonitoring.hostmonitoring.repository;

import com.hostmonitoring.hostmonitoring.entity.EmailConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<EmailConfig,Long> {
    @Query("select x from EmailConfig x where x.Id = ?1")
    Optional<EmailConfig> findFirstById(String Id);
}
