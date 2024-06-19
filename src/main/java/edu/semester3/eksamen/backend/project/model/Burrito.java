package edu.semester3.eksamen.backend.project.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "burrito")
public class Burrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String name;
    private String description;
    private double price;
    private String ingredients;
    private String image;

    public Burrito() {
    }

    public Burrito(String name, String description, double price, String ingredients, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.ingredients = ingredients;
        this.image = image;
    }
}
