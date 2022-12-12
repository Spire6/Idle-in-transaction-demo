package hu.example.idleintransactiondemo.repository;

import hu.example.idleintransactiondemo.entity.FooBar;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface FooBarRepository extends ReactiveCrudRepository<FooBar, Long> {
}
