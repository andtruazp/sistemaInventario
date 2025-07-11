package com.app.inventario.exception;

public class InventarioException extends RuntimeException{
	public InventarioException(String message) { 
        super(message);
    }

    
    public InventarioException() {
        super("Ocurrió un error en el inventario."); 
    }

}
