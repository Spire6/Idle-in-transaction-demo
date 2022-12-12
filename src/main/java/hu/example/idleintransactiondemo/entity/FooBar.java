package hu.example.idleintransactiondemo.entity;

import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.Id;

@Data
@Builder
public class FooBar {

    @Id
    private Long id;
    private String foo;

}
