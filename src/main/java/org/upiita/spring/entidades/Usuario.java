package org.upiita.spring.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity(name="usuarios")
public class Usuario {

	@Id	
	@SequenceGenerator(name="idUsuarioSecuencia", sequenceName="usuarios_id_seq", allocationSize=1)
	@GeneratedValue(generator="idUsuarioSecuencia", strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Integer id;
	
	@Column(name="password")
	private String password;

	@Column(name="nombre")
	private String nombre;
	
	@Column(name="email")
	private String email;

	@OneToOne
	//Esta anotacion va en la entidad dueña(la entidad de la tabla que tiene la llave foranea)
	//name = nombre real de la columna de la llave foranea en la tabla
	@JoinColumn(name="datos_autor_id")
	private DatosUsuario datosUsuario;
	
	
	@OneToMany(mappedBy="usuario")
	private List<Post> posts;
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public DatosUsuario getDatosUsuario() {
		return datosUsuario;
	}

	public void setDatosUsuario(DatosUsuario datosUsuario) {
		this.datosUsuario = datosUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
