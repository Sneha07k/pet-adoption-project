package com.petadoption.controller;

import com.petadoption.dto.ApiResponse;
import com.petadoption.dto.PetRequest;
import com.petadoption.dto.PetResponse;
import com.petadoption.service.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "*")
public class PetController {

    private final PetService petService;

    // Constructor-based dependency injection
    public PetController(PetService petService) {
        this.petService = petService;
    }

    /**
     * POST /api/pets
     * Add a new pet listing for adoption.
     */
    @PostMapping
    public ResponseEntity<ApiResponse<PetResponse>> addPet(
            @Valid @RequestBody PetRequest request) {

        PetResponse response = petService.addPet(request);
        return new ResponseEntity<>(
                ApiResponse.success("Pet added successfully", response),
                HttpStatus.CREATED);
    }

    /**
     * GET /api/pets
     * Retrieve all available pets.
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<PetResponse>>> getAllPets() {
        List<PetResponse> pets = petService.getAllPets();
        return ResponseEntity.ok(
                ApiResponse.success("Pets retrieved successfully", pets));
    }

    /**
     * GET /api/pets/{id}
     * Retrieve details of a specific pet by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PetResponse>> getPetById(
            @PathVariable Long id) {

        PetResponse pet = petService.getPetById(id);
        return ResponseEntity.ok(
                ApiResponse.success("Pet retrieved successfully", pet));
    }

    /**
     * PUT /api/pets/{id}
     * Update an existing pet listing.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PetResponse>> updatePet(
            @PathVariable Long id,
            @Valid @RequestBody PetRequest request) {

        PetResponse updated = petService.updatePet(id, request);
        return ResponseEntity.ok(
                ApiResponse.success("Pet updated successfully", updated));
    }

    /**
     * DELETE /api/pets/{id}
     * Remove a pet listing.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePet(
            @PathVariable Long id) {

        petService.deletePet(id);
        return ResponseEntity.ok(
                ApiResponse.success("Pet deleted successfully", null));
    }

    /**
     * GET /api/pets/type/{type}
     * Filter pets by type (e.g. dog, cat, bird).
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<ApiResponse<List<PetResponse>>> getPetsByType(
            @PathVariable String type) {

        List<PetResponse> pets = petService.getPetsByType(type);
        return ResponseEntity.ok(
                ApiResponse.success("Pets by type retrieved successfully", pets));
    }

    /**
     * GET /api/pets/location/{location}
     * Filter pets by location.
     */
    @GetMapping("/location/{location}")
    public ResponseEntity<ApiResponse<List<PetResponse>>> getPetsByLocation(
            @PathVariable String location) {

        List<PetResponse> pets = petService.getPetsByLocation(location);
        return ResponseEntity.ok(
                ApiResponse.success("Pets by location retrieved successfully", pets));
    }

    /**
     * GET /api/pets/age/younger-than/{age}
     * Get pets younger than a given age.
     */
    @GetMapping("/age/younger-than/{age}")
    public ResponseEntity<ApiResponse<List<PetResponse>>> getPetsYoungerThan(
            @PathVariable int age) {

        List<PetResponse> pets = petService.getPetsYoungerThan(age);
        return ResponseEntity.ok(
                ApiResponse.success("Pets younger than " + age + " retrieved", pets));
    }



    
}

