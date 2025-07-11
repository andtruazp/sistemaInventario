package com.app.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.inventario.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {

}
