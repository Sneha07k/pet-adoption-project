package com.petadoption.repository;

import com.petadoption.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    /**
     * Find all pets by type (e.g. "dog", "cat", "bird").
     */
    List<Pet> findByTypeIgnoreCase(String type);

    /**
     * Find all pets by location (case-insensitive).
     */
    List<Pet> findByLocationContainingIgnoreCase(String location);

    /**
     * Find all pets younger than a given age.
     */
    List<Pet> findByAgeLessThan(int age);

    /**
     * Find all pets by type and location.
     */
    List<Pet> findByTypeIgnoreCaseAndLocationContainingIgnoreCase(String type, String location);
}
