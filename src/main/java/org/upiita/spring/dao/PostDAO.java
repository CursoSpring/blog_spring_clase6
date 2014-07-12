package org.upiita.spring.dao;

import java.util.List;

import org.upiita.spring.entidades.Post;

public interface PostDAO {

	public abstract Post buscaPorId(Integer id);

	public abstract Integer guardar(Post post);

	public abstract List<Post> buscaPorTitulo(String titulo);

	public abstract List<Post> buscaDiferentesDeTitulo(String titulo);

}