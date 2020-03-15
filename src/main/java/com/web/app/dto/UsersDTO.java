package com.web.app.dto;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class UsersDTO {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "EMAIL")
    private String email;

    // Getters and Setters methods
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
