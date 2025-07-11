package com.app.inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.inventario.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	List<Producto> findByEstatus(Integer estatus);
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

}
