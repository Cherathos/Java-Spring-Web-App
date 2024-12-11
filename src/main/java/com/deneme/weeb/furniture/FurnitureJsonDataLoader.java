package com.deneme.weeb.furniture;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class FurnitureJsonDataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(FurnitureJsonDataLoader.class);
    private final FurnitureRepository furnitureRepository;
    private final ObjectMapper objectMapper;

    public FurnitureJsonDataLoader(FurnitureRepository furnitureRepository, ObjectMapper objectMapper){
        this.furnitureRepository = furnitureRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (furnitureRepository.count() == 0) {
            try (InputStream inputStream = getClass().getResourceAsStream("/data/furnitures.json")) {
                if (inputStream == null) { // Handle missing file
                    logger.error("Could not find '/data/furnitures.json' on the classpath.");
                    return;
                }

                Furnitures allFurnitures = objectMapper.readValue(inputStream, Furnitures.class);
                List<Furniture> furnitureList = allFurnitures.furnitures();
                if (furnitureList == null || furnitureList.isEmpty()) {
                    logger.warn("No furniture data found in the JSON file.");
                    return;
                }

                furnitureRepository.saveAll(furnitureList);
                logger.info("Furniture data successfully loaded into the database.");
            } catch (IOException e) {
                logger.error("Error reading the furniture JSON file: " + e.getMessage(), e);
                throw new RuntimeException("Failed to load furniture data.", e);
            }
        } else {
            logger.info("Furniture data already exists in the database. Skipping JSON data load.");
        }
    }
}