package me.dio.bagApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double unitPrice;
    @Builder.Default
    private boolean available = true;
    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;
}
