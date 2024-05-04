package com.workintech.s17d1.controller;
import com.workintech.s17d1.entity.Animal;
import com.workintech.s17d1.utills.ValidationUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "workintech/animal")
public class AnimalController {
    private Map<Integer, Animal>animals;

    @Value("${project.owner}")
    private String projectOwner;

    @Value("${project.name}")
    private String projectName;

    @PostConstruct
    public void loadAll(){
        this.animals=new HashMap<>();
        System.out.println("project owner deger="+projectOwner);
        System.out.println("project name deger="+projectName);

        this.animals.put(1,new Animal(1,"maymun"));
    }

    @GetMapping
    public List<Animal> getAnimals() {
        System.out.println("---animals list get triggerd---");

        return new ArrayList<>(this.animals.values());
    }
    @GetMapping("/{id}")
    public Animal getAnimal(@PathVariable("id") Integer id){
        ValidationUtils.checkId(id);
        System.out.println("---Animals get by id triggerd");
        return this.animals.get(id);
    }

    @PostMapping
    public void AddAnimal(@RequestBody Animal animal){
        ValidationUtils.checkAnimal(animal);
        this.animals.put(animal.getId(),animal);

    }
    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable("id") Integer id,@RequestBody Animal newAnimal){
        ValidationUtils.checkId(id);
        this.animals.replace(id,newAnimal);
        ValidationUtils.checkAnimal(newAnimal);
        return this.animals.get(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable("id") Integer id){
        this.animals.remove(id);
    }

}
