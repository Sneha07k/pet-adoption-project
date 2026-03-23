package com.petadoption;

import com.petadoption.config.PetMapper;
import com.petadoption.dto.PetRequest;
import com.petadoption.dto.PetResponse;
import com.petadoption.exception.ResourceNotFoundException;
import com.petadoption.model.Pet;
import com.petadoption.repository.PetRepository;
import com.petadoption.service.PetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceImplTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetMapper petMapper;

    @InjectMocks
    private PetServiceImpl petService;

    private Pet samplePet;
    private PetRequest sampleRequest;
    private PetResponse sampleResponse;

    @BeforeEach
    void setUp() {
        samplePet = Pet.builder()
                .id(1L)
                .name("Buddy")
                .type("Dog")
                .breed("Golden Retriever")
                .age(2)
                .description("Friendly dog")
                .location("New York, NY")
                .ownerName("John Smith")
                .ownerContact("john@email.com")
                .createdAt(LocalDateTime.now())
                .build();

        sampleRequest = PetRequest.builder()
                .name("Buddy")
                .type("Dog")
                .breed("Golden Retriever")
                .age(2)
                .description("Friendly dog")
                .location("New York, NY")
                .ownerName("John Smith")
                .ownerContact("john@email.com")
                .build();

        sampleResponse = PetResponse.builder()
                .id(1L)
                .name("Buddy")
                .type("Dog")
                .breed("Golden Retriever")
                .age(2)
                .description("Friendly dog")
                .location("New York, NY")
                .ownerName("John Smith")
                .ownerContact("john@email.com")
                .createdAt(samplePet.getCreatedAt())
                .build();
    }

    @Test
    void addPet_shouldReturnPetResponse() {
        when(petMapper.toEntity(sampleRequest)).thenReturn(samplePet);
        when(petRepository.save(samplePet)).thenReturn(samplePet);
        when(petMapper.toResponse(samplePet)).thenReturn(sampleResponse);

        PetResponse result = petService.addPet(sampleRequest);

        assertNotNull(result);
        assertEquals("Buddy", result.getName());
        verify(petRepository, times(1)).save(samplePet);
    }

    @Test
    void getPetById_shouldReturnPetWhenFound() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(samplePet));
        when(petMapper.toResponse(samplePet)).thenReturn(sampleResponse);

        PetResponse result = petService.getPetById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void getPetById_shouldThrowWhenNotFound() {
        when(petRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> petService.getPetById(99L));
    }

    @Test
    void getAllPets_shouldReturnList() {
        when(petRepository.findAll()).thenReturn(List.of(samplePet));
        when(petMapper.toResponse(samplePet)).thenReturn(sampleResponse);

        List<PetResponse> result = petService.getAllPets();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void deletePet_shouldDeleteSuccessfully() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(samplePet));
        doNothing().when(petRepository).delete(samplePet);

        assertDoesNotThrow(() -> petService.deletePet(1L));
        verify(petRepository, times(1)).delete(samplePet);
    }

    @Test
    void getPetsByType_shouldReturnFilteredList() {
        when(petRepository.findByTypeIgnoreCase("Dog")).thenReturn(List.of(samplePet));
        when(petMapper.toResponse(samplePet)).thenReturn(sampleResponse);

        List<PetResponse> result = petService.getPetsByType("Dog");

        assertEquals(1, result.size());
        assertEquals("Dog", result.get(0).getType());
    }
}
