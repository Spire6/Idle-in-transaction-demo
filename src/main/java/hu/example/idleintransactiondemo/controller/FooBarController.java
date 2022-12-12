package hu.example.idleintransactiondemo.controller;

import hu.example.idleintransactiondemo.entity.FooBar;
import hu.example.idleintransactiondemo.service.FooBarService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class FooBarController {

    private final FooBarService service;

    @PostMapping("/saveDummyFooBars")
    @Operation(summary = "Insert dummy FooBars", description = "Insert dummy FooBars")
    public Mono<Void> saveDummyFooBars(@RequestParam Integer size) {
        return this.service.insertDummyFooBars(size);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get all FooBars", description = "Get all FooBars")
    public Flux<FooBar> getAll() {
        return this.service.findAll();
    }

    @DeleteMapping("/deleteAll")
    @Operation(summary = "Delete all FooBars", description = "Delete all FooBars")
    public Mono<Void> deleteAll() {
        return this.service.deleteAll();
    }

}
