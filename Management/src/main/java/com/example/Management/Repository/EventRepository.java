package com.example.Management.Repository;

import com.example.Management.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Long> {
    Optional<Event> findByHost(String hostName);
}
