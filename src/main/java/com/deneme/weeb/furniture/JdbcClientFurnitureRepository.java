package com.deneme.weeb.furniture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcClientFurnitureRepository {
    private static final Logger logger = LoggerFactory.getLogger(JdbcClientFurnitureRepository.class);
    private final JdbcClient jdbcClient;

    public JdbcClientFurnitureRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Furniture> findAll() {
        return jdbcClient.sql("select * from Furniture")
                .query(Furniture.class)
                .list();
    }

    public Optional<Furniture> findById(Integer id) {
        return jdbcClient.sql("select id, price, quantity, furniturename, furnituretype, description, image from Furniture where id = :id")
                .param(id)
                .query(Furniture.class)
                .optional();
    }

    public void createFurniture(Furniture furniture){
        var updated = jdbcClient.sql("INSERT INTO Furniture(id, price, quantity, furniturename, furnituretype, description, image) VALUES (?,?,?,?,?,?,?)")
                .params(List.of(furniture.id(), furniture.price(), furniture.quantity(), furniture.furniturename(), furniture.furnituretype(), furniture.description(), furniture.image()))
                .update();

        Assert.state(updated == 1, "Failed to create furniture" + furniture.furniturename());
    }

    public void updateFurniture(Furniture furniture, Integer id){
        var updated = jdbcClient.sql("UPDATE Furniture SET price = ?, quantity = ?, furniturename = ?, furnituretype = ?, description = ?, image = ? WHERE id = ?")
                .params(List.of(furniture.price(), furniture.quantity(), furniture.furniturename(), furniture.furnituretype(), furniture.description(), furniture.image(), furniture.id()))
                .update();

        Assert.state(updated == 1, "Failed to create furniture" + furniture.furniturename());
    }

    public void deleteFurniture (Integer id){
        var updated = jdbcClient.sql("DELETE FROM Furniture WHERE id = ?")
                .param(id)
                .update();

        Assert.state(updated == 1, "Failed to create furniture" + id);
    }

    public void saveAll(List<Furniture> furnitures){
        furnitures.stream().forEach(this::createFurniture);
    }


    public List<Furniture> findByName(String furniturename){
        return jdbcClient.sql("select * from Furniture where furniturename = :furniturename")
                .param(furniturename)
                .query(Furniture.class)
                .list();
    }

    public Integer count(){
        return jdbcClient.sql("select * from Furniture")
                .query()
                .listOfRows()
                .size();
    }
}