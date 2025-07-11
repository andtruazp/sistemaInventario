package com.app.inventario.service;

import java.util.List;

import com.app.inventario.model.Historico;
import com.app.inventario.model.Historico.TipoMovimiento;

public interface HistoricoService {
	List<Historico> listarTodo();
	List<Historico> filtrarPorMovimineto(TipoMovimiento tipoMovimiento);
	void crearRegistro(String username, Integer idProducto, TipoMovimiento tipoMovimiento);

}
