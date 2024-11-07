package com.hostmonitoring.hostmonitoring.repository;

import com.hostmonitoring.hostmonitoring.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert,Long> {
}
