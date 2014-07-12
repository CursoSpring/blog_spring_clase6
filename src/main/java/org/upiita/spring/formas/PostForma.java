package org.upiita.spring.formas;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class PostForma {

	private Integer id;
	
	//anotacion de hibernate validator valida que una cadena o coleccion de java tenga almenos un elemento
	@NotEmpty
	private String titulo;
	//size es para cadenas o coleciones en este caso de 5 en adelante
	@Size(min=5)
	private String contenido;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	
}
