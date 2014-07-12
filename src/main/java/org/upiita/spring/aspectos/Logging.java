package org.upiita.spring.aspectos;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


/*
		EN EL ARCHIVO DONDE CONFIGURAMOS LOS ASPECTOS en este caso (dao-context-testing.xml)
		(PARA LA CONFIGURACION POR ANOTACIONES) AGREGAMOS
		 org.upiita.spring.aspectos 
		<context:component-scan base-package="org.upiita.spring.dao,org.upiita.spring.aspectos" />
		
		ademas agregamos (1), (2) y (3)

 */

@Component //(1)
@Aspect //(2)
public class Logging {

	@Before("execution(* org.upiita.spring.dao.UsuarioDAO.buscaPorId(..))")//(3)
	public void antesDeInvocar(JoinPoint joinPoint) {
		System.out.println("ANTES DE INVOCAR!!");
		System.out.println("ARGUMENTOS: " + Arrays.asList(joinPoint.getArgs()));
	}

	public void despuesDeInvocar() {
		System.out.println("DESPUES DE INVOCAR!!");
	}

	//metodo alrededor se ejecuta antes y despues del punto de corte
	public Object alrededor(ProceedingJoinPoint joinPoint) throws Throwable {

		Object resultado;

		//esta linia executa el punto de corte
		resultado = joinPoint.proceed();

		return resultado;
	}

}
