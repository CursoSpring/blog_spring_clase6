package org.upiita.spring.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity(name = "categorias")
public class Categoria {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "categoriaIdSecuencia", sequenceName = "categoria_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoriaIdSecuencia")
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	@ManyToMany
	@JoinTable(name = "categorias_posts", inverseJoinColumns = {@JoinColumn(name="post_id")}, joinColumns = {@JoinColumn(name="categoria_id")})
	private List<Post> posts;
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public String toString() {

		StringBuilder builder = new StringBuilder();

		builder.append("{");
		builder.append("id:").append(this.id).append(",");
		builder.append("nombre:").append(this.nombre);
		builder.append("}");

		return builder.toString();
	}

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

}
