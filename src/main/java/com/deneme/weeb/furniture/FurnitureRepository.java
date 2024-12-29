package com.deneme.weeb.furniture;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface FurnitureRepository extends ListCrudRepository<Furniture, Integer> {

    @Query
    List<Furniture> findAllByFurnituretype(String furnituretype);

    @Query
    List<Furniture> findAllByFurniturename(@NotEmpty String furniturename);

}
