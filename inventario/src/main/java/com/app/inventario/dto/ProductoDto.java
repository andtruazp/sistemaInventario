package com.app.inventario.dto;

import jakarta.validation.constraints.NotBlank;

public class ProductoDto {
	private Integer idProducto;
    
    @NotBlank
    private String nombre;

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    
    

}
