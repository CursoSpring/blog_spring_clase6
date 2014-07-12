package org.upiita.spring.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "departamentos")
public class Departamento {

	@Id
	@SequenceGenerator(name = "departamentoIdSecuencia", sequenceName = "departamento_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departamentoIdSecuencia")
	@Column(name = "id")
	private Integer id;

	@Column(name = "nombre")
	private String nombre;	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String toString() {

		StringBuilder builder = new StringBuilder();

		builder.append("{");
		builder.append("id:").append(this.id).append(",");
		builder.append("nombre:").append(this.nombre);
		builder.append("}");

		return builder.toString();
	}

}
