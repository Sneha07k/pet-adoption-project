package com.petadoption.service;

import com.petadoption.dto.PetRequest;
import com.petadoption.dto.PetResponse;

import java.util.List;

public interface PetService {

    PetResponse addPet(PetRequest request);

    List<PetResponse> getAllPets();

    PetResponse getPetById(Long id);

    PetResponse updatePet(Long id, PetRequest request);

    void deletePet(Long id);

    List<PetResponse> getPetsByType(String type);

    List<PetResponse> getPetsByLocation(String location);

    List<PetResponse> getPetsYoungerThan(int age);
}
