package com.graphicstore.product.domain.agregate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.graphicstore.product.domain.agregate.audit.AuditObject;
import com.graphicstore.product.domain.agregate.enums.ColorBySide;
import com.graphicstore.product.domain.agregate.enums.ProductCategory;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity(name = "product")
@Getter
@Setter
@Table(name = "product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor @NoArgsConstructor
public class Product extends AuditObject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_product")
    @SequenceGenerator(name = "seq_product", sequenceName = "seq_product", allocationSize = 1)
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ProductCategory category; //enum
    private String substrate;
    @Enumerated(EnumType.STRING)
    private ColorBySide color;
    private Integer height;
    private Integer width;
    @ElementCollection
    private List<String> finishings;
    private Integer quantity;
    private BigDecimal price;


    public Product(String name, ProductCategory category, String substrate, ColorBySide color, Integer height,
                   Integer width, List<String> finishings, Integer quantity, BigDecimal price) {
        this.name = name;
        this.category = category;
        this.substrate = substrate;
        this.color = color;
        this.height = height;
        this.width = width;
        this.finishings = finishings;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
