package com.app.inventario.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.inventario.dto.ProductoDto;
import com.app.inventario.exception.InventarioException;
import com.app.inventario.model.Producto;
import com.app.inventario.service.InventarioService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Controller
@RequestMapping("/inventario")
@PreAuthorize("hasAuthority('ver_inventario')")
public class InventarioController {

	@Autowired
	private InventarioService inventarioService;

	@GetMapping
	public String verInventario(Model model) {
		List<Producto> productos;
		productos = inventarioService.listarTodosProductos();

		model.addAttribute("productos", productos);
		return "inventario/lista";

	}
	

	@GetMapping("/nuevo")
	@PreAuthorize("hasAuthority('agregar_producto')")
	public String mostrarFormularioNuevo(Model model) {
		model.addAttribute("productoDTO", new ProductoDto());
		return "inventario/formulario";
	}
	

	@PostMapping("/guardar")
	@PreAuthorize("hasAuthority('agregar_producto')")
	public String guardarProducto(@Valid ProductoDto productoDTO, BindingResult result, Principal principal) {
		if (result.hasErrors()) {
			return "inventario/formulario";
		}

		inventarioService.agregarProducto(productoDTO, principal.getName());
		return "redirect:/inventario";
	}

	@PostMapping("/aumentar/{id}")
	@PreAuthorize("hasAuthority('aumentar_inventario')")
	public String aumentarInventario(@PathVariable Integer id, @RequestParam @Min(1) Integer cantidad,
			Principal principal, RedirectAttributes redirectAttributes) {
		try {
			inventarioService.aumentarInventario(id, cantidad, principal.getName());
		} catch (InventarioException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
		}
		return "redirect:/inventario";
	}
	
	@PostMapping("/cambiar-estatus/{id}")
    @PreAuthorize("hasAuthority('dar_baja_producto')")
    public String cambiarEstatusProducto(@PathVariable Integer id, @RequestParam Boolean activo, Principal principal) {
        inventarioService.cambiarEstatusProducto(id, activo, principal.getName());
        return "redirect:/inventario";
    }

}
