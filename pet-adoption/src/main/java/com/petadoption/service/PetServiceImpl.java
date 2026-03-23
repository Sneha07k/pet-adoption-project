package com.petadoption.service;

import com.petadoption.config.PetMapper;
import com.petadoption.dto.PetRequest;
import com.petadoption.dto.PetResponse;
import com.petadoption.exception.ResourceNotFoundException;
import com.petadoption.model.Pet;
import com.petadoption.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final PetMapper petMapper;

    // Constructor-based dependency injection
    public PetServiceImpl(PetRepository petRepository, PetMapper petMapper) {
        this.petRepository = petRepository;
        this.petMapper = petMapper;
    }

    @Override
    public PetResponse addPet(PetRequest request) {
        log.info("Adding new pet: {}", request.getName());
        Pet pet = petMapper.toEntity(request);
        Pet savedPet = petRepository.save(pet);
        log.info("Pet saved with id: {}", savedPet.getId());
        return petMapper.toResponse(savedPet);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PetResponse> getAllPets() {
        log.info("Fetching all pets");
        return petRepository.findAll()
                .stream()
                .map(petMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PetResponse getPetById(Long id) {
        log.info("Fetching pet with id: {}", id);
        Pet pet = findPetOrThrow(id);
        return petMapper.toResponse(pet);
    }

    @Override
    public PetResponse updatePet(Long id, PetRequest request) {
        log.info("Updating pet with id: {}", id);
        Pet existingPet = findPetOrThrow(id);
        petMapper.updateEntityFromRequest(existingPet, request);
        Pet updatedPet = petRepository.save(existingPet);
        log.info("Pet updated successfully: {}", updatedPet.getId());
        return petMapper.toResponse(updatedPet);
    }

    @Override
    public void deletePet(Long id) {
        log.info("Deleting pet with id: {}", id);
        Pet pet = findPetOrThrow(id);
        petRepository.delete(pet);
        log.info("Pet deleted successfully: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PetResponse> getPetsByType(String type) {
        log.info("Fetching pets by type: {}", type);
        return petRepository.findByTypeIgnoreCase(type)
                .stream()
                .map(petMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PetResponse> getPetsByLocation(String location) {
        log.info("Fetching pets by location: {}", location);
        return petRepository.findByLocationContainingIgnoreCase(location)
                .stream()
                .map(petMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PetResponse> getPetsYoungerThan(int age) {
        log.info("Fetching pets younger than: {}", age);
        return petRepository.findByAgeLessThan(age)
                .stream()
                .map(petMapper::toResponse)
                .collect(Collectors.toList());
    }

    // -------------------------
    // Private helper methods
    // -------------------------

    private Pet findPetOrThrow(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet", id));
    }
}
