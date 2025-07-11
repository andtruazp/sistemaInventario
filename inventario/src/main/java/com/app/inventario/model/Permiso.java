package com.app.inventario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "permisos")
public class Permiso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_permiso")
	private Integer idPermiso;
	
	@Column(name = "permiso")
    private String permiso;
    
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;
    
   
    public Permiso() {}


	public Integer getIdPermiso() {
		return idPermiso;
	}


	public void setIdPermiso(Integer idPermiso) {
		this.idPermiso = idPermiso;
	}


	public String getPermiso() {
		return permiso;
	}


	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}


	public Rol getRol() {
		return rol;
	}


	public void setRol(Rol rol) {
		this.rol = rol;
	}

	
	
	

}
