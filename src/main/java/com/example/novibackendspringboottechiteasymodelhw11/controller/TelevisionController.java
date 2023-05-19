package com.example.novibackendspringboottechiteasymodelhw11.controller;

import com.example.novibackendspringboottechiteasymodelhw11.exeptions.RecordNotFoundException;
import com.example.novibackendspringboottechiteasymodelhw11.model.Television;
import com.example.novibackendspringboottechiteasymodelhw11.repository.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {
//    ArrayList<String> televisions = new ArrayList<>();
    @Autowired
    private TelevisionRepository repos;

    @GetMapping
    public ResponseEntity <Iterable<Television>> getTelevisions(){
        return ResponseEntity.ok(repos.findAll());
    }

    @PostMapping
    public ResponseEntity<Television> createTelevision(@RequestBody Television t){
        repos.save(t);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + t.getId()).toUriString());
        return ResponseEntity.created(uri).body(t);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevision(@PathVariable("id") Long id) {

        Optional<Television> television = repos.findById(id);

        if (television.isEmpty()){
            throw new RecordNotFoundException("No television found with id: " + id );

        } else {
            Television television1 = television.get();
            return ResponseEntity.ok().body(television1);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable("id") Long id){
       repos.deleteById(id);
       return ResponseEntity.noContent().build();
    }

}
