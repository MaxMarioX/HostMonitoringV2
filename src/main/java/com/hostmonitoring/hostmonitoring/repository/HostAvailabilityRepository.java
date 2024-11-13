package com.hostmonitoring.hostmonitoring.repository;

import com.hostmonitoring.hostmonitoring.entity.HostAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostAvailabilityRepository extends JpaRepository<HostAvailability,Long> {
}
