package org.upiita.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.upiita.spring.entidades.Post;

//Indica que spring crea una instancia y la guarda en memoria (una instancia en el contexto de spring), esto es para solo tener una sola instancia de esta clas "un singleton"
//(value="PostDAO") => le da nombre a la instancia puede o no llevarlo pero es reco
@Component(value="PostDAO")

/*Con esto permitimos el uso de transacciones declarativas
  => que spring establece una session con hibernate
  cirra session, hace comit en caso de que aplique ect.
  por eso ya comente lo de la transaccion programatica, le delegue a spring eso
  
  NOTA: la anotacion @Transactional en este caso la pues antes de declarar la clase
   		por lo tanto se aplicara @Transactional a todos los metodos de la calse
   		si quiero que solo se aplique a algunos metodos entonces la pongo en la 
   		cabecera de los metos que quiero que sean @Transactional (pero no en la cabecera
   		de la clase)
*/
@Transactional

public class HibernatePostDAO implements PostDAO {

	//Inyectamos la instancia del ssesionFactory con spring
	@Autowired
	@Qualifier(value="sessionFactory")
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see org.upiita.spring.dao.PostDAO#buscaPorId(java.lang.Integer)
	 */
	@Override
	public Post buscaPorId(Integer id)
	{
		Post elPost = null;
		
		//Transaccion programatica
		//Session session = sessionFactory.openSession();
		//session.beginTransaction();
	
		//Transaccion delcarativo
		Session session = sessionFactory.getCurrentSession();
		
		//Una ve iniciada la transaccion podemos hacer consulta o modificar la bd
		elPost = (Post) session.get(Post.class, id);

		/*
		 * Le indica a hibernate que queremos sus comentarios asociados
		 *  fetch=FetchType.LAZY que es el de pordefault el que usa hibernate
		 *  
		 */
		//Hibernate.initialize(elPost.getComentarios());
		Hibernate.initialize(elPost.getCategorias());
		
		
		//terminado todo lo necesitado en la bd, cerramos la session de hibernate
		//session.close();
		
		return elPost;
	}

	/* (non-Javadoc)
	 * @see org.upiita.spring.dao.PostDAO#guardar(org.upiita.spring.entidades.Post)
	 */
	@Override
	public Integer guardar(Post post)
	{
		//Transaccion programatica
		//Session session = sessionFactory.openSession();
		//session.beginTransaction();
		
		Session session = sessionFactory.getCurrentSession();
		
		//Una ve iniciada la transaccion podemos hacer consulta o modificar la bd
		session.saveOrUpdate(post);
		
		//Si cambiamos datos hacemos commit
		//session.getTransaction().commit();
		
		//terminado todo lo necesitado en la bd, cerramos la session de hibernate
		//session.close();
		
		//Retorno el id, si se hizo un insert hibernate actualiza el objeto post en tal caso el id cambiara
		//si se hizo un update el id del objeto no cambiara
		return post.getId();
	}

	
	@Override
	public List<Post> buscaPorTitulo(String titulo){
		
		Session session = sessionFactory.getCurrentSession();
		//Creamos el criterio usando el .class que representa a la tabla
		Criteria criterio = session.createCriteria(Post.class);
		//agregamos criterios de busqueda
		//primer argumento(en general de la restriccion es la propiedad de la entidad "de la clase" no de la tabla)
		//segundo argumento
		criterio.add(Restrictions.like("titulo", "%" + titulo + "%"));
		//le decimos a hibernate que busque y obtenga una lista de resultados
		List<Post> postEncontrados = criterio.list();
		
		return postEncontrados;
	}
	
	@Override
	public List<Post> buscaDiferentesDeTitulo(String titulo){
		
		Session session = sessionFactory.getCurrentSession();
		//Creamos el criterio usando el .class que representa a la tabla
		Criteria criterio = session.createCriteria(Post.class);
		//agregamos criterios de busqueda
		//primer argumento(en general de la restriccion es la propiedad de la entidad "de la clase" no de la tabla)
		//segundo argumento
		criterio.add(Restrictions.not(Restrictions.like("titulo", "%" + titulo + "%")));
		//le decimos a hibernate que busque y obtenga una lista de resultados
		List<Post> postEncontrados = criterio.list();
		
		return postEncontrados;
	}
	
}
