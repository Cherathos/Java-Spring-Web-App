package com.deneme.weeb.furniture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class FurnitureController {

    private final FurnitureRepository furnitureRepository;

    public FurnitureController(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    @GetMapping("/all")
    List<Furniture> findAll(){
        return furnitureRepository.findAll();
    }

    @GetMapping("/find/{id}")
    Furniture findById(@PathVariable Integer id){
        Optional<Furniture> furniture = furnitureRepository.findById(id);
        if(furniture.isEmpty()){
            throw new FurnitureNotFoundException();
        }
        return furniture.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    void createFurniture(@RequestBody Furniture furniture){
        furnitureRepository.save(furniture);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/update/{id}")
    void updateFurniture(@RequestBody Furniture furniture, @PathVariable Integer id){
        furnitureRepository.save(furniture);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    void deleteFurniture(@PathVariable Integer id){
        furnitureRepository.delete(furnitureRepository.findById(id).get());
    }

    @GetMapping("/type/{furnituretype}")
    List<Furniture> findByFurnitureType(@PathVariable String furnituretype){
        return furnitureRepository.findAllByFurnituretype(furnituretype);
    }

    @GetMapping("/name/{furniturename}")
    List<Furniture> findByFurnitureName(@PathVariable String furniturename){
        return furnitureRepository.findAllByFurniturename(furniturename);
    }
}
