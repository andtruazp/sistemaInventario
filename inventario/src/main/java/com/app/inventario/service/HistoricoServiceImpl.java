package com.app.inventario.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.inventario.model.Historico;
import com.app.inventario.model.Historico.TipoMovimiento;
import com.app.inventario.model.Producto;
import com.app.inventario.model.Usuario;
import com.app.inventario.repository.HistoricoRepository;
import com.app.inventario.repository.ProductoRepository;
import com.app.inventario.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HistoricoServiceImpl implements HistoricoService {
	
	@Autowired
	private HistoricoRepository historicoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public List<Historico> listarTodo() {
		return historicoRepository.findAll();
	}

	@Override
	public List<Historico> filtrarPorMovimineto(TipoMovimiento tipoMovimiento) {
		return historicoRepository.findByMovimiento(tipoMovimiento);
	}

	@Override
	public void crearRegistro(String username, Integer idProducto, TipoMovimiento tipoMovimiento) {
		Historico historico = new Historico();
		Producto producto = productoRepository.findById(idProducto)
				.orElseThrow(() -> new RuntimeException("Producot no encontrado"));
		Usuario usuario = usuarioRepository.findByCorreo(username)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		
		historico.setUsuario(usuario);
		historico.setProducto(producto);
		historico.setMovimiento(tipoMovimiento);
		historico.setFecha(LocalDateTime.now());
		
		historicoRepository.save(historico);
		
	}

}
