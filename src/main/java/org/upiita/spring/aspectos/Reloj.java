package org.upiita.spring.aspectos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.aspectj.lang.ProceedingJoinPoint;

public class Reloj {
	
	public Object tomaTiempo(ProceedingJoinPoint joinPoint) throws Throwable {
		Object resultado = null;

			//obtenemos el tiempo inicial
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss_SSS");
			Calendar tiempoInicial = Calendar.getInstance();
			System.out.println("tiempo inicial:" + dateFormat.format(tiempoInicial.getTime()));


			// invocamos al metodo asociado con este aspecto
			resultado = joinPoint.proceed();


			Calendar tiempoActual = Calendar.getInstance();
			System.out.println("tiempo actual:" + dateFormat.format(tiempoActual.getTime()));
			
			//terminando imprimimos el tiempo actual y el tiempo que le llevo invocar a dicho metodo
			long tiempoTotal = tiempoActual.getTimeInMillis() - tiempoInicial.getTimeInMillis() ;
			System.out.println("tiempo empleado (mili segundos):" + tiempoTotal);
		

		return resultado;
	}

}