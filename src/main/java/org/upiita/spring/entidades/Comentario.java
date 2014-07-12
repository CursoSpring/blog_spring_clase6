package org.upiita.spring.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity(name = "comentarios")
public class Comentario {

	@Id
	@SequenceGenerator(name="comentarioIdSecuencia", sequenceName="comentario_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="comentarioIdSecuencia")
	@Column(name = "id")	
	private Integer id;

	@Column(name = "comentario")
	private String comentario;

	@Column(name = "comentarista")
	private String comentarista;

	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	
	
	//A muchos comentarios le corresponde un post
	@ManyToOne
	//Como Comentario es la entidad due;a ponemos el @JoinColumn
	@JoinColumn(name="post_id")
	private Post post;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String toString() {

		StringBuilder builder = new StringBuilder();

		builder.append("{");
		builder.append("id:").append(this.id).append(",");
		builder.append("comentario:").append(this.comentario).append(",");
		builder.append("comentarista:").append(this.comentarista).append(",");
		builder.append("fechaCreacion:").append(this.fechaCreacion);
		builder.append("}");

		return builder.toString();
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getComentarista() {
		return comentarista;
	}

	public void setComentarista(String comentarista) {
		this.comentarista = comentarista;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

}
