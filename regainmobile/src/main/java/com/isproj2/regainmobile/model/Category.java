package com.isproj2.regainmobile.model;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryID;

    // column name = 'category_name' must match FK column
    @Column(name = "category_name")
    private String name;

    // mappedBy = 'category' must match with a property in Product class
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Collection<Product> products;

}
