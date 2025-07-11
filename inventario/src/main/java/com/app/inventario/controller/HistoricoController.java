package com.app.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.inventario.model.Historico;
import com.app.inventario.model.Historico.TipoMovimiento;
import com.app.inventario.service.HistoricoService;

@Controller
@RequestMapping("/historico")
@PreAuthorize("hasAuthority('ver_historico')")
public class HistoricoController {
	
	@Autowired
	private HistoricoService historicoService;
	
	@GetMapping
	public String verHistorico(@RequestParam(required=false) String tipo, Model model) {
		List<Historico> historico;
		
		if(tipo != null && !tipo.isEmpty()) {
			try {
                TipoMovimiento tipoMovimiento = TipoMovimiento.valueOf(tipo.toUpperCase());
                historico = historicoService.filtrarPorMovimineto(tipoMovimiento);
                model.addAttribute("tipoSeleccionado", tipoMovimiento);
            } catch (IllegalArgumentException e) {
                historico = historicoService.listarTodo();
            }
		}else {
			historico = historicoService.listarTodo();
		}

		model.addAttribute("historico", historico);
		model.addAttribute("tiposMovimiento", TipoMovimiento.values());
		return "historico/lista";

	}

}
