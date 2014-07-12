package org.upiita.spring.seguridad;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

//extendemos de la clase que ocupa spring secirity para login fallido
public class LoginFallidoManejador extends SimpleUrlAuthenticationFailureHandler{

	//Sobrescribimos authentication-failure-url ya que no toma la propiedad del xml
	//por usar onAuthenticationFailure, por lo que la tenemos que poner a manita
	@Override
	public void setDefaultFailureUrl(String defaultFailureUrl) {
		// TODO Auto-generated method stub
		super.setDefaultFailureUrl(defaultFailureUrl);
	}
	
	//Vamos a sobrescribir este metodo
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		
		// TODO Auto-generated method stub
		
		//Obtenemos la variable de session loginsFallidos (en caso de k exista)
		Integer loginsFallidos = (Integer) request.getSession().getAttribute("loginsFallidos");
		
		//primera vez que se equivoco el usuario(aun noexiste la variable de session loginsFallidos)
		if(loginsFallidos == null){
			loginsFallidos = 1;
		}
		
		//si se equivoco ya mas de 1 vez(si ya existe la var de session loginsFallidos)
		else{
			loginsFallidos++;
		}
		
		//guardamos la var de session 
		request.setAttribute("loginsFallidos", loginsFallidos);
		
		super.onAuthenticationFailure(request, response, exception);
	}
	
}
