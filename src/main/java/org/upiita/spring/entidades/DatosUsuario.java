package org.upiita.spring.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity(name = "datos_usuarios")
public class DatosUsuario {

	@Id
	@SequenceGenerator(name="datosUsuarioIdSecuencia", sequenceName="datos_usuarios_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="datosUsuarioIdSecuencia")
	@Column(name = "id")
	private Integer id;

	@Column(name = "biografia")
	private String biografia;

	@Column(name = "fecha_registro")
	private Date fechaRegistro;
	
	//En la entidad que NO es dueña va la propiedad mappedBy
	//mappedByse llena con el nombre de la propiedad o atributo (java) de la otra calse 
	//con la que estamos mapeando a esta clase
	@OneToOne(mappedBy="datosUsuario")
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// override del metodo String para que esta clase se imprima bonito :)
	public String toString() {

		StringBuilder builder = new StringBuilder();

		builder.append("{");
		builder.append("id:").append(this.id).append(",");
		builder.append("biografia:").append(this.biografia).append(",");
		builder.append("fechaRegistro:").append(this.fechaRegistro);
		builder.append("}");

		return builder.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

}
