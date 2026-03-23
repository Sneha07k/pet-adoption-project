package com.petadoption.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetResponse {

    private Long id;
    private String name;
    private String type;
    private String breed;
    private int age;
    private String description;
    private String location;
    private String ownerName;
    private String ownerContact;
    private LocalDateTime createdAt;
    
}
