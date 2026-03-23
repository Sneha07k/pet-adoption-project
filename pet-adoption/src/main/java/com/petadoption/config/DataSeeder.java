package com.petadoption.config;

import com.petadoption.model.Pet;
import com.petadoption.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final PetRepository petRepository;

    public DataSeeder(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public void run(String... args) {
        if (petRepository.count() == 0) {
            log.info("Seeding sample pet data...");
            seedPets();
            log.info("Sample data seeded successfully.");
        } else {
            log.info("Database already has data. Skipping seed.");
        }
    }

    private void seedPets() {
        List<Pet> samplePets = List.of(

            Pet.builder()
                .name("Buddy")
                .type("Dog")
                .breed("Golden Retriever")
                .age(2)
                .description("Buddy is a friendly and energetic dog who loves to play fetch. " +
                             "Great with kids and other dogs. Fully vaccinated and neutered.")
                .location("New York, NY")
                .ownerName("John Smith")
                .ownerContact("john.smith@email.com")
                .build(),

            Pet.builder()
                .name("Whiskers")
                .type("Cat")
                .breed("Persian")
                .age(3)
                .description("Whiskers is a calm and affectionate indoor cat. " +
                             "She loves to cuddle and enjoys quiet environments.")
                .location("Los Angeles, CA")
                .ownerName("Emily Johnson")
                .ownerContact("+1-555-234-5678")
                .build(),

            Pet.builder()
                .name("Tweety")
                .type("Bird")
                .breed("Canary")
                .age(1)
                .description("Tweety is a cheerful yellow canary who loves to sing. " +
                             "Perfect for apartment living.")
                .location("Chicago, IL")
                .ownerName("Michael Brown")
                .ownerContact("m.brown@gmail.com")
                .build(),

            Pet.builder()
                .name("Max")
                .type("Dog")
                .breed("German Shepherd")
                .age(4)
                .description("Max is an intelligent and loyal dog. " +
                             "He is well-trained, obedient, and great for families or singles.")
                .location("Houston, TX")
                .ownerName("Sarah Davis")
                .ownerContact("sarah.davis@email.com")
                .build(),

            Pet.builder()
                .name("Luna")
                .type("Cat")
                .breed("Siamese")
                .age(2)
                .description("Luna is a playful and talkative Siamese cat. " +
                             "She bonds quickly with her owners and loves interactive toys.")
                .location("Phoenix, AZ")
                .ownerName("Chris Wilson")
                .ownerContact("+1-555-876-5432")
                .build(),

            Pet.builder()
                .name("Nibbles")
                .type("Rabbit")
                .breed("Holland Lop")
                .age(1)
                .description("Nibbles is a gentle and curious rabbit. " +
                             "Loves to be petted and enjoys fresh vegetables.")
                .location("New York, NY")
                .ownerName("Karen Martinez")
                .ownerContact("karen.m@email.com")
                .build()
        );

        petRepository.saveAll(samplePets);
    }
}
