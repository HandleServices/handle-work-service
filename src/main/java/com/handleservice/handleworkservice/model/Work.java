package com.handleservice.handleworkservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "works")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "worker_id", nullable = false)
    private UUID workerId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal value;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private boolean enable = true;

    @Column(name = "created_at", insertable = false, updatable = false, nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;

}
