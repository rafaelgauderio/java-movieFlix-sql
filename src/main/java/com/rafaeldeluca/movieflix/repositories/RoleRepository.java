package com.rafaeldeluca.movieflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafaeldeluca.movieflix.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{

}
