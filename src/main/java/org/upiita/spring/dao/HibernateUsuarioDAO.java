package org.upiita.spring.dao;


import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.upiita.spring.entidades.Usuario;

@Component(value="UsuarioDAO")
@Transactional
public class HibernateUsuarioDAO implements UsuarioDAO {

	//Inyectamos la instancia del ssesionFactory con spring
	@Autowired
	@Qualifier(value="sessionFactory")
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see org.upiita.spring.dao.PostDAO#buscaPorId(java.lang.Integer)
	 */
	@Override
	public Usuario buscaPorId(Integer id)
	{
		Usuario user = null;

		System.out.println("BUSCANDO USUARIO CON ID:" + id);
		
		//Transaccion programatica
		//Session session = sessionFactory.openSession();
		//session.beginTransaction();
		Session session = sessionFactory.getCurrentSession();
		
		//Una ve iniciada la transaccion podemos hacer consulta o modificar la bd
		user = (Usuario) session.get(Usuario.class, id);
		
		Hibernate.initialize(user.getPosts());
		
		//terminado todo lo necesitado en la bd, cerramos la session de hibernate
		//session.close();
		
		return user;
	}

	@Override
	public Integer guardar(Usuario user) {//Transaccion programatica
		//Session session = sessionFactory.openSession();
		//session.beginTransaction();
		Session session = sessionFactory.getCurrentSession();
		
		//Una ve iniciada la transaccion podemos hacer consulta o modificar la bd
		session.saveOrUpdate(user);
		
		//Si cambiamos datos hacemos commit
		//session.getTransaction().commit();
		
		//terminado todo lo necesitado en la bd, cerramos la session de hibernate
		//session.close();
		
		//Retorno el id, si se hizo un insert hibernate actualiza el objeto post en tal caso el id cambiara
		//si se hizo un update el id del objeto no cambiara
		return user.getId();
	}
	
	public Usuario buscaPorEmailYPassword(String email, String password)
	{
		Session session = sessionFactory.getCurrentSession();

		Criteria criterio = session.createCriteria(Usuario.class);
		criterio.add(Restrictions.and(Restrictions.eq("email", email), Restrictions.eq("password", password)));
		
		Usuario usuarioEncontrado = (Usuario) criterio.uniqueResult();
		
		return usuarioEncontrado;
	}
}
