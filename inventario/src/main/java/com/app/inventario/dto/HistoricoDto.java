package com.app.inventario.dto;


import java.time.LocalDateTime;

import com.app.inventario.model.Historico.TipoMovimiento;


public class HistoricoDto {
	
	private String usuario;
    private String producto;
    private TipoMovimiento movimiento;
    private LocalDateTime fecha;
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public TipoMovimiento getMovimiento() {
		return movimiento;
	}
	public void setMovimiento(TipoMovimiento movimiento) {
		this.movimiento = movimiento;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
    
   
      
    

}
