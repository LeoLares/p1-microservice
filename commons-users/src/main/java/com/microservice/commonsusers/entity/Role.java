package com.microservice.commonsusers.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles_dev")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_role",unique = true,length = 30)
    private String nameRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    private static final long serialVersionUID = 4462330911801172710L;
}
