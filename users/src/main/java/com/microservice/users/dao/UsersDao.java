package com.microservice.users.dao;


import com.microservice.commonsusers.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "users")
public interface UsersDao extends PagingAndSortingRepository<User,Long> {

    @RestResource(path = "search-username")  //
    public User findByUsername(@Param("name") String username); //conectando con operadores logicos podemosm agregar mas de un filtro
    //ejemplo findByUsernameAndEmail
}
