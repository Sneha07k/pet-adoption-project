package com.petadoption.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetRequest {

    @NotBlank(message = "Pet name is required")
    private String name;

    @NotBlank(message = "Pet type is required")
    private String type;

    private String breed;

    @Min(value = 0, message = "Age must be 0 or greater")
    private int age;

    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Owner name is required")
    private String ownerName;

    

    @NotBlank(message = "Owner contact is required")
    private String ownerContact;
}
