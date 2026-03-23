package com.petadoption.config;

import com.petadoption.dto.PetRequest;
import com.petadoption.dto.PetResponse;
import com.petadoption.model.Pet;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {

    /**
     * Converts a PetRequest DTO to a Pet entity.
     */
    public Pet toEntity(PetRequest request) {
        return Pet.builder()
                .name(request.getName())
                .type(request.getType())
                .breed(request.getBreed())
                .age(request.getAge())
                .description(request.getDescription())
                .location(request.getLocation())
                .ownerName(request.getOwnerName())
                .ownerContact(request.getOwnerContact())

                .build();
    }

   
    public PetResponse toResponse(Pet pet) {
        return PetResponse.builder()
                .id(pet.getId())
                .name(pet.getName())
                .type(pet.getType())
                .breed(pet.getBreed())
                .age(pet.getAge())
                .description(pet.getDescription())
                .location(pet.getLocation())
                .ownerName(pet.getOwnerName())
                .ownerContact(pet.getOwnerContact())
                .createdAt(pet.getCreatedAt())
                .build();
    }

    /**
     * Updates an existing Pet entity with values from a PetRequest DTO.
     */
    public void updateEntityFromRequest(Pet pet, PetRequest request) {
        pet.setName(request.getName());
        pet.setType(request.getType());
        pet.setBreed(request.getBreed());
        pet.setAge(request.getAge());
        pet.setDescription(request.getDescription());
        pet.setLocation(request.getLocation());
        pet.setOwnerName(request.getOwnerName());
        pet.setOwnerContact(request.getOwnerContact());
    }
}
