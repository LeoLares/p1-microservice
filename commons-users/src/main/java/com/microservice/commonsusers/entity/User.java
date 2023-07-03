package com.microservice.commonsusers.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users_dev")
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true , length = 20)
    private String username;
    private String name;
    private String lastname;
    @Column(length = 60)
    private String password;

    @Column(unique = true , length = 100)
    private String email;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.LAZY) //anotacion de spring para cardinalidad es decir la relacion
    //lazy es una busqueda mas especifica y con eager trae  todo junto, lazy es carga peresoza es decir solo trae el users
    // los roles los traes con el get y eager trae todo juntois users y roles
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = { "user_id", "role_id" }) })
    private List<Role> roles; //como en este caso solo queremos aplicar una sola direccion de relacion ubicamos esto en la clase user,
    //en caso que la relacion sea bidireccional debemos aplicar lo mismo pero a la inversa en la clase roles
    //agregando despues del fetchTyope.Lazy , el mappedBy=Roles para lo tomer a l inversa

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellido() {
        return lastname;
    }

    public void setApellido(String apellido) {
        this.lastname = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    private static final long serialVersionUID = 4112221912401133094L;
}
