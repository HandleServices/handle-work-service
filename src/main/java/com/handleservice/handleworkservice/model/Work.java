package com.handleservice.handleworkservice.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "works")
public class Work {

    public Work() {
    }

    public Work(Long id, UUID workerId, BigDecimal value, String name, @Nullable String description, LocalTime estimatedTime, boolean enable) {
        this.id = id;
        this.workerId = workerId;
        this.value = value;
        this.name = name;
        this.description = description;
        this.estimatedTime = estimatedTime;
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

    @Column(name = "estimated_time", nullable = false)
    private LocalTime estimatedTime;

    @Column(nullable = false)
    private boolean enable = true;

    @CreationTimestamp
    @Column(name = "created_at", insertable = false, updatable = false, nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", insertable = false, updatable = false)
    private Instant updatedAt = null;

}
