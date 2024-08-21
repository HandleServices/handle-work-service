package com.handleservice.handleworkservice.repository;

import com.handleservice.handleworkservice.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work, Long> {
}
