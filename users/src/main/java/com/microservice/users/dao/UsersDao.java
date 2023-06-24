package com.microservice.users.dao;


import com.microservice.commonsusers.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "users")
public interface UsersDao extends PagingAndSortingRepository<User,Long> {

    @RestResource(path = "search-username")  //
    public User findByUsername(@Param("name") String username);//search-username es algo mas customizado y reemplza a findByUserName
    //lo mismo el param es para reemplazar en la ruta de url el name por el username
    //conectando con operadores logicos podemosm agregar mas de un filtro
    //ejemplo findByUsernameAndEmail

    @Query("select u from users_dev u where u.username?1") // es como lo de arriba pero mas personalizado
    public User obtenerUserQuery(String username);
}
