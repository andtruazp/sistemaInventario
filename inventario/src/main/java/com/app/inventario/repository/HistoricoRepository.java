package com.app.inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.inventario.model.Historico;
import com.app.inventario.model.Historico.TipoMovimiento;

public interface HistoricoRepository extends JpaRepository<Historico, Integer>{
	List<Historico> findByMovimiento(TipoMovimiento tipoMovimiento);

}
