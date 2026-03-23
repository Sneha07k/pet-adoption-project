package com.petadoption.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Pet name is required")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Pet type is required")
    @Column(nullable = false, length = 50)
    private String type; // dog, cat, bird, etc.

    @Column(length = 100)
    private String breed;

    @Min(value = 0, message = "Age must be a non-negative number")
    @Column(nullable = false)
    private int age;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotBlank(message = "Location is required")
    @Column(nullable = false, length = 150)
    private String location;

    @NotBlank(message = "Owner name is required")
    @Column(nullable = false, length = 100)
    private String ownerName;

    @NotBlank(message = "Owner contact is required")
    @Column(nullable = false, length = 100)
    private String ownerContact;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(length = 500)
    private String imageUrl;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
