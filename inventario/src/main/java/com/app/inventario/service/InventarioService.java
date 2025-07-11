package com.app.inventario.service;

import java.util.List;

import com.app.inventario.dto.ProductoDto;
import com.app.inventario.exception.InventarioException;
import com.app.inventario.model.Producto;

public interface InventarioService {
	List<Producto> listarTodosProductos();
    List<Producto> listarProductosPorEstatus(boolean activos);
    Producto buscarProductoPorId(Integer id);
    void agregarProducto(ProductoDto productoDTO, String username);
    void aumentarInventario(Integer idProducto, Integer cantidad, String username) throws InventarioException;
    void cambiarEstatusProducto(Integer idProducto, boolean activo, String username);
    List<Producto> buscarProductosPorNombre(String nombre);
    void restarInventario(Integer idProducto,int cantidad, String username) throws InventarioException;

}
