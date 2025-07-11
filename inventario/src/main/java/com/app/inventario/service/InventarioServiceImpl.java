package com.app.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.inventario.dto.ProductoDto;
import com.app.inventario.exception.InventarioException;
import com.app.inventario.model.Historico.TipoMovimiento;
import com.app.inventario.model.Producto;
import com.app.inventario.repository.ProductoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class InventarioServiceImpl implements InventarioService {
	
	@Autowired
    private ProductoRepository productoRepository;

	@Autowired
	private HistoricoService historicoService;

	@Override
	public List<Producto> listarTodosProductos() {
		return productoRepository.findAll();
	}

	@Override
	public List<Producto> listarProductosPorEstatus(boolean activos) {
		return productoRepository.findByEstatus(activos ? 1 : 0);
	}

	@Override
	public Producto buscarProductoPorId(Integer id) {
		return productoRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
	}

	@Override
	public void agregarProducto(ProductoDto productoDTO, String username) {
		Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setCantidad(0);
        producto.setEstatus(1);
        
        productoRepository.save(producto);
        
        historicoService.crearRegistro(username, producto.getIdProducto(), TipoMovimiento.ENTRADA);
		
	}

	@Override
	public void aumentarInventario(Integer idProducto, Integer cantidad, String username) throws InventarioException {
		if (cantidad <= 0) {
            throw new InventarioException("La cantidad debe ser mayor que cero");
        }
        
        Producto producto = productoRepository.findById(idProducto)
            .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        
        producto.setCantidad(producto.getCantidad() + cantidad);
        productoRepository.save(producto);
        
        historicoService.crearRegistro(username, idProducto, TipoMovimiento.ENTRADA);
		
	}

	@Override
	public void cambiarEstatusProducto(Integer idProducto, boolean activo, String username) {
		Producto producto = productoRepository.findById(idProducto)
	            .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
	        
	        producto.setEstatus(activo ? 1 : 0);
	        productoRepository.save(producto);
		
	}

	@Override
	public List<Producto> buscarProductosPorNombre(String nombre) {
		return productoRepository.findByNombreContainingIgnoreCase(nombre);

	}

	@Override
	public void restarInventario(Integer idProducto, int cantidad, String username) throws InventarioException {
		Producto producto = productoRepository.findById(idProducto)
				.orElseThrow(() -> new InventarioException("Producto no encontrado"));
		if(producto.getCantidad() < cantidad) {
			throw new InventarioException("No hay suficiente inventario");
		}
		
		producto.setCantidad(producto.getCantidad() - cantidad);
		productoRepository.save(producto);
		
		historicoService.crearRegistro(username, idProducto, TipoMovimiento.SALIDA);
		
	}
	

}
