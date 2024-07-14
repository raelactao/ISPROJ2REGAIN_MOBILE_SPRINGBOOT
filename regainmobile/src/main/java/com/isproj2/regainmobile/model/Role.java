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
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleID;

    // column name = 'role_type' must match FK column
    @Column(name = "role_type", columnDefinition = "ROLE_USER")
    private String name;

    // mappedBy = 'role' must match with a property in User class
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private Collection<User> users;

    public Role(String role) {
        this.name = role;
    }

}
