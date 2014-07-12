package org.upiita.spring.testing;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.dao.UsuarioDAO;

public class LoggingAspectoDemostracion {

	private static ApplicationContext contexto;

	private static UsuarioDAO usuarioDAO;
	
	public static void main(String args[]) {

		contexto = new ClassPathXmlApplicationContext("dao-context-testing.xml");
		usuarioDAO = (UsuarioDAO) contexto.getBean("UsuarioDAO");
		
		usuarioDAO.buscaPorId(1);
		
	}

}
