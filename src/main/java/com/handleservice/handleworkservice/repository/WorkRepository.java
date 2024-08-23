package com.handleservice.handleworkservice.repository;

import com.handleservice.handleworkservice.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkRepository extends JpaRepository<Work, Long> {
    Optional<Work> findByIdAndWorkerId(Long id, UUID workerId);

    List<Work> findAllByWorkerId(UUID workerId);

    @Modifying
    @Query("UPDATE Work w SET w.name = :#{#newWork.name}, w.description = :#{#newWork.description}, w.enable = :#{#newWork.enable} WHERE w.id = :id AND w.workerId = :workerId ")
    Work updateWorkByIdAndWorkerId(
            @Param("id") Long id,
            @Param("workerId") UUID workerId,
            @Param("newWork") Work newWork
    );

    Boolean existsByIdAndWorkerId(Long id, UUID workerId);
}
