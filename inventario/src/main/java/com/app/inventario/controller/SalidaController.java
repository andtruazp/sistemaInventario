package com.app.inventario.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.inventario.model.Producto;
import com.app.inventario.service.InventarioService;

@Controller
@RequestMapping("/salida")
@PreAuthorize("hasAuthority('salida_productos')")
public class SalidaController {
	
	@Autowired
	private InventarioService inventarioService;
	
	@GetMapping
	public String mostrarSalida(Model model) {
		List<Producto> productosActivos = inventarioService.listarProductosPorEstatus(true);
        model.addAttribute("productos", productosActivos);
        return "salida/formulario";
	}
	
	@PostMapping("/disminuir")
	@PreAuthorize("hasAuthority('sacar_inventario')")
	public String salidaProductos(@RequestParam Integer idProducto, @RequestParam Integer cantidad, Principal principal, RedirectAttributes redirectAttributes) {
		Producto producto = inventarioService.buscarProductoPorId(idProducto);
		
		if (producto == null || producto.getEstatus() != 1) {
	        redirectAttributes.addFlashAttribute("error", "Producto no válido o inactivo.");
	        return "redirect:/salida";
	    }

	    if (cantidad <= 0 || cantidad > producto.getCantidad()) {
	        redirectAttributes.addFlashAttribute("error", "Cantidad inválida. No puede exceder el inventario disponible.");
	        return "redirect:/salida";
	    }

	    inventarioService.restarInventario(idProducto, cantidad, principal.getName());
	    return "redirect:/salida";
	}

}
