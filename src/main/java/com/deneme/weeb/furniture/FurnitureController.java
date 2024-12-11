package com.deneme.weeb.furniture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/a")
public class FurnitureController {

    private final FurnitureRepository furnitureRepository;

    public FurnitureController(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    @GetMapping("/all")
    List<Furniture> findAll(){
        return furnitureRepository.findAll();
    }

    @GetMapping("/{id}")
    Furniture findById(@PathVariable int id){
        Optional<Furniture> furniture = furnitureRepository.findById(id);
        if(furniture.isEmpty()){
            throw new FurnitureNotFoundException();
        }
        return furniture.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void createFurniture(@RequestBody Furniture furniture){
        furnitureRepository.createFurniture(furniture);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void updateFurniture(@RequestBody Furniture furniture, @PathVariable int id){
        furnitureRepository.updateFurniture(furniture, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteFurniture(@PathVariable int id){
        furnitureRepository.deleteFurniture(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/name/{furnitureName}")
    List<Furniture> findByName(@PathVariable String furnitureName){
        return furnitureRepository.findByName(furnitureName);
    }
}
