package hu.example.idleintransactiondemo.service;

import hu.example.idleintransactiondemo.entity.FooBar;
import hu.example.idleintransactiondemo.repository.FooBarRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FooBarService {

    private final FooBarRepository repository;

    public Mono<Void> insertDummyFooBars(Integer size) {
        return Flux.fromIterable(IntStream.rangeClosed(1, size).boxed().collect(Collectors.toList()))
                .log()
                .flatMap(i -> this.repository.save(FooBar.builder().foo("test-" + i).build()))
                .log()
                .concatMap(i -> this.repository.findAll())
                .then();
    }

    public Flux<FooBar> findAll() {
        return this.repository.findAll();
    }

    public Mono<Void> deleteAll() {
        return this.repository.deleteAll();
    }

}
