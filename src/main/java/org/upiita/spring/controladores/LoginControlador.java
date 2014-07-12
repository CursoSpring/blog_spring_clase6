package org.upiita.spring.controladores;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.upiita.spring.formas.FormaSession;

@Controller
public class LoginControlador {

	@Autowired
	private FormaSession formaSession;
	
	@RequestMapping(value="/login")
	public String login(Boolean errorLogin, Model modelo, HttpSession session)
	{
		
		formaSession.setItemsComprados(100);

		//agregamos un atributo a la session
		//lo podemos obttener en otro cualquier controlador con getAttribute
		//para recuperar valores de variables de session
		//quiza no sea necesario usar variable de session ya que tenemos Authentication authentication
		session.setAttribute("variable", 10);
		
		modelo.addAttribute("errorLogin",errorLogin);
		return "login";
	}
	
	//HttpSession session es para tener la session
	
	@RequestMapping(value="/error-403")
	public String error403(Authentication authentication, Model modelo)
	{
		System.out.println("Nombre: "+authentication.getName());//usuario
		System.out.println("Password: "+authentication.getCredentials());//passsword
		System.out.println("Roles: "+authentication.getAuthorities());//roles
		
		modelo.addAttribute("email", authentication.getName());
		
		return "error-403";
	}
}
