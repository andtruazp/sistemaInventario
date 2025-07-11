package com.app.inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.inventario.model.Permiso;
import com.app.inventario.model.Rol;

public interface PermisoRepository extends JpaRepository<Permiso, Integer> {
	List<Permiso> findByRol(Rol rol);
}
