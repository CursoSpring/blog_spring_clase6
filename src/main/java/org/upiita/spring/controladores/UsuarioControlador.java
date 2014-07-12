package org.upiita.spring.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.upiita.spring.dao.UsuarioDAO;
import org.upiita.spring.entidades.Usuario;
import org.upiita.spring.formas.UsuarioForma;

@Controller
public class UsuarioControlador {
	//@Qualifier(value="PostDAO") con esto pido la instancia llamada PostDAO 
	@Autowired
	@Qualifier(value="UsuarioDAO")
	private UsuarioDAO usuarioDAO;
	
	@RequestMapping(value="/usuario/{userId:[0-9]+}")
	public String mostrar(@PathVariable(value="userId") Integer userId, Model modelo)
	{
		Usuario user = usuarioDAO.buscaPorId(userId);
		modelo.addAttribute("usuario_", user);
		
		return "usuario";
	}
	
	@RequestMapping(value="/usuario/{userId:[0-9]+}/editar")
	public String mostrarEditor(@RequestParam(required=false) Boolean actualizado, @PathVariable Integer userId, Model modelo)
	{
		//si hubo errores al guardar
		if(modelo.containsAttribute("usuario")){
			System.out.println("Hubo errores, Modelo: "+modelo);
		}
		
		//si todo salio bien o si es la 1era vez que entra
		else{
			Usuario usuario = usuarioDAO.buscaPorId(userId);
			modelo.addAttribute("usuario", usuario);
			modelo.addAttribute("actualizado", actualizado);
		}
		
		return "usuario_editar";
	}

	@RequestMapping(value="/usuario/guardar",method=RequestMethod.POST)
	//public String guardarUsuario(Integer id, String password, String nombre, String email)
	public String guardarUsuario(@Valid @ModelAttribute("usuario") UsuarioForma forma, BindingResult validacion, RedirectAttributes atributos)

	{	
		String urlRedirect = "";
		
		System.out.println("nombre: " +  forma.getNombre() + ", email: " + forma.getEmail());
		
		if(validacion.hasErrors()){
			atributos.addFlashAttribute("usuario", forma);
			atributos.addFlashAttribute("org.springframework.validation.BindingResult.usuario", validacion);
			
			urlRedirect = "redirect:/usuario/" + forma.getId() + "/editar";
		}
		
		else
		{			
			Usuario u = new Usuario();
			u.setId(forma.getId());
			u.setNombre(forma.getNombre());
			u.setPassword(forma.getPassword());
			u.setEmail(forma.getEmail());
			Integer usuarioIdGuardado = usuarioDAO.guardar(u);

			urlRedirect =  "redirect:/usuario/" + usuarioIdGuardado + "/editar?actualizado=true";
		}
		//@TODO PATRON POST - REDIRECT - GET
		return urlRedirect;
	}
}
