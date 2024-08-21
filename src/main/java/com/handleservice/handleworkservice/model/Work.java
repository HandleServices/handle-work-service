package com.handleservice.handleworkservice.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "works")
public class Work {

    public Work() {
    }

    public Work(long id, UUID workerId, BigDecimal value, String name, @Nullable String description, boolean enable) {
        this.id = id;
        this.workerId = workerId;
        this.value = value;
        this.name = name;
        this.description = description;
        this.enable = enable;
    }

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
