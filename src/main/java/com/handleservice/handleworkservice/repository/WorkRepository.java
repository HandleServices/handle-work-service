package com.handleservice.handleworkservice.repository;

import com.handleservice.handleworkservice.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkRepository extends JpaRepository<Work, Long> {
    Optional<Work> findByIdAndWorkerId(Long id, UUID workerId);

    List<Work> findAllByWorkerId(UUID workerId);

    Boolean existsByIdAndWorkerId(Long id, UUID workerId);
}
