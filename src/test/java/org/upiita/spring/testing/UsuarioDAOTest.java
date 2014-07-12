package org.upiita.spring.testing;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.dao.UsuarioDAO;
import org.upiita.spring.entidades.DatosUsuario;
import org.upiita.spring.entidades.Post;
import org.upiita.spring.entidades.Usuario;

public class UsuarioDAOTest {

	private static ApplicationContext contexto;
	
	private static UsuarioDAO usuarioDAO;
	
	@BeforeClass
	public static void inicializar()
	{
		//Creamos el contexto de srping
		contexto = new ClassPathXmlApplicationContext("dao-context-testing.xml");
		usuarioDAO = (UsuarioDAO) contexto.getBean("UsuarioDAO");
	}	
	
	@Test
	public void buscaUsuarioTest()
	{
		//Consulta a la bd
		Usuario post = usuarioDAO.buscaPorId(1);
		
		//Checha que el objeto que consultamos nop  venga null
		Assert.assertNotNull("El metodo para buscaUsuarioTest regresa datos vacios ",post);
	}
	
	@Test
	public void guardarUsuarioTest() 
	{
		Usuario u = new Usuario();
		//u.setId(1);
		u.setEmail("email");
		u.setNombre("vic");
		u.setPassword("pass");
		
		Integer id = usuarioDAO.guardar(u);
		
		Usuario pBd = usuarioDAO.buscaPorId(id);
		
		Assert.assertNotNull("El metodo para guardarUsuarioTest regresa datos vacios ",pBd);
				
		Assert.assertNotNull(pBd.getId());
		Assert.assertEquals(u.getId(), pBd.getId());
		 
		Assert.assertNotNull(pBd.getEmail()); 
		Assert.assertEquals(u.getEmail(), pBd.getEmail());

		Assert.assertNotNull(pBd.getNombre());
		Assert.assertEquals(u.getNombre(), pBd.getNombre());

		Assert.assertNotNull(pBd.getPassword());
		Assert.assertEquals(u.getPassword(), pBd.getPassword());
	}
	
	@Test
	public void buscaPorEmailYPassword()
	{
		Usuario u = usuarioDAO.buscaPorEmailYPassword("juan@email.com", "1234");
		System.out.println("El usuario es: " + u);
	}
	
	@Test
	public void mapeoUsuarioDatosUsuarioTest()
	{
		Usuario u = usuarioDAO.buscaPorId(1);
		System.out.println("Usuario nombre: " +  u.getNombre());
		System.out.println("Datos usuario:" +  u.getDatosUsuario());
		
		DatosUsuario datosUsuario = u.getDatosUsuario();
		System.out.println("Usuario nombre(desde datos usuario): "+ datosUsuario.getUsuario().getNombre());
	}
	
	@Test
	public void mapeoUsuarioPostsTest()
	{
		Usuario u = usuarioDAO.buscaPorId(1);
		System.out.println("Usuario nombre: " + u.getNombre());
		System.out.println("Datos posts:" + u.getPosts());
		
		List<Post> posts = u.getPosts();
		System.out.println("El usuario" + u.getNombre() + " tiene " + posts.size() + " posts.");
	}
	
}
