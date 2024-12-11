package com.deneme.weeb.furniture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class FurnitureRepository {
    private static final Logger logger = LoggerFactory.getLogger(FurnitureRepository.class);
    private final JdbcClient jdbcClient;

    public FurnitureRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Furniture> findAll() {
        return jdbcClient.sql("select * from Furniture")
                .query(Furniture.class)
                .list();
    }

    public Optional<Furniture> findById(int id) {
        return jdbcClient.sql("select furnitureID, furniturePrice, furnitureQuantity, furnitureName, furnitureType, furnitureDescription, furnitureImage from Furniture where furnitureID = :id")
                .param(id)
                .query(Furniture.class)
                .optional();
    }

    public void createFurniture(Furniture furniture){
        var updated = jdbcClient.sql("INSERT INTO Furniture(furnitureID, furniturePrice, furnitureQuantity, furnitureName, furnitureType, furnitureDescription, furnitureImage) VALUES (?,?,?,?,?,?,?)")
                .params(List.of(furniture.furnitureID(), furniture.furniturePrice(), furniture.furnitureQuantity(), furniture.furnitureName(), furniture.furnitureType(), furniture.furnitureDescription(), furniture.furnitureImage()))
                .update();

        Assert.state(updated == 1, "Failed to create furniture" + furniture.furnitureName());
    }

    public void updateFurniture(Furniture furniture, int id){
        var updated = jdbcClient.sql("UPDATE Furniture SET furniturePrice = ?, furnitureQuantity = ?, furnitureName = ?, furnitureType = ?, furnitureDescription = ?, furnitureImage = ? WHERE furnitureID = ?")
                .params(List.of(furniture.furniturePrice(), furniture.furnitureQuantity(), furniture.furnitureName(), furniture.furnitureType(), furniture.furnitureDescription(), furniture.furnitureImage(), furniture.furnitureID()))
                .update();

        Assert.state(updated == 1, "Failed to create furniture" + furniture.furnitureName());
    }

    public void deleteFurniture (int id){
        var updated = jdbcClient.sql("DELETE FROM Furniture WHERE furnitureID = ?")
                .param(id)
                .update();

        Assert.state(updated == 1, "Failed to create furniture" + id);
    }

    public void saveAll(List<Furniture> furnitures){
        furnitures.stream().forEach(this::createFurniture);
    }


    public List<Furniture> findByName(String furnitureName){
        return jdbcClient.sql("select * from Furniture where furnitureName = :furnitureName")
                .param(furnitureName)
                .query(Furniture.class)
                .list();
    }

    public int count(){
        return jdbcClient.sql("select * from Furniture")
                .query()
                .listOfRows()
                .size();
    }
}