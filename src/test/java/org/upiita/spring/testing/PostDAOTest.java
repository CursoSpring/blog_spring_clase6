package org.upiita.spring.testing;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
 * Permite agarra una clase y si los metodos son estatics permite tomar 
 * los metodos sin instanciar la clase
*/
import static org.springframework.util.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.dao.PostDAO;
import org.upiita.spring.entidades.Post;

public class PostDAOTest {

	//Hay 2 tipos de pruebas 
	// Prueba unitaria (checa la logica de un metodo)
	// Prueba de integracion (cehca bd, checar que la aplicacion web funcione)
	
	private static ApplicationContext contexto;
	
	private static PostDAO postDAO;
	
	/*
	 * Anotation de JUni que hace todo lo de este metodo este inicializado desde el inicio
	 * Este metodo se ejecuta antes de todos los test, se usa para inicializar los elementos
	 * necesarios para probar los test, @BeforeClass indica que se ejecute al inicio.
	 */
	@BeforeClass
	public static void inicializar()
	{
		//Creamos el contexto de srping
		contexto = new ClassPathXmlApplicationContext("dao-context-testing.xml");
		postDAO = (PostDAO) contexto.getBean("PostDAO");
	}
	

	
	/*
	 * NOTIN: 	En JUnit no se tiuene la garantia de el orden de ejecucion de los metodos(@Test), por lo que nunca hay que hacer metodos 
	 * 			@Test que dependan unos de otros 
	 * 
	 * */
	
	
	
	/*
	 * Anotation de JUni que hace todo este inicializado
	 */
	@Test
	public void buscaPostTest()
	{
		//Consulta a la bd
		Post post = postDAO.buscaPorId(1);
	
		System.out.println("post titulo " + post.getTitulo());
		System.out.println("comentarios " + post.getComentarios());
		System.out.println("categorias " + post.getCategorias());
		
		//Checha que el objeto que consultamos nop  venga null
		Assert.assertNotNull("El metodo para buscaPostTest regresa datos vacios ",post);
	}
	
	@Test
	public void guardarPostTest() 
	{
		Post p = new Post();
		//p.setId(3);
		p.setTitulo("Clase 3");
		p.setContenido("Contenido Clase 3");
		p.setFechaCreacion(new Date());
		
		Integer id = postDAO.guardar(p);
		
		Post pBd = postDAO.buscaPorId(id);
		
		Assert.assertNotNull("El metodo para guardarPostTest regresa datos vacios ",pBd);
				
		//Validamos que no sea null ...(1)
		
		//El primer parametro es el valor que espero y el segundo es el valor obtenido,
		//Esperamos que el obtenido sea igual al esperado  ...(2)
		
		Assert.assertNotNull(pBd.getId());
		Assert.assertEquals(p.getId(), pBd.getId());
		 
		Assert.assertNotNull(pBd.getContenido()); 
		Assert.assertEquals(p.getContenido(), pBd.getContenido());

		Assert.assertNotNull(pBd.getTitulo());
		Assert.assertEquals(p.getTitulo(), pBd.getTitulo());

		Assert.assertNotNull(pBd.getFechaCreacion());
		//En caso de fecha fallaria el tes ya que en el equals en date la base y java no manejan el mismo formato, por lo que para que no falle 
		//necesitamos formatear las fechas al mismo formato
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Assert.assertEquals(formato.format (p.getFechaCreacion()), formato.format (pBd.getFechaCreacion()));
	}
	
	@Test
	public void buscaPorTituloTest()
	{
		List<Post> postEncontrados = postDAO.buscaPorTitulo("post");
		System.out.println("postEncontrados = " + postEncontrados);
		
		//usamos esta assert de spring para verificar si la coleccion es no null y no venga vacia
		//estos assertion de spring no vienen en los de junit la idea es combinarlos para facilitarnos
		//las cosas
		
		//Ver el import de como se importo el paquete de este metodo
		notEmpty(postEncontrados);
	}
	
	
	/*@Test
	public void buscaDiferentesDeTituloTest()
	{
		List<Post> postEncontrados = postDAO.buscaDiferentesDeTitulo("post");
		System.out.println("postEncontrados = " + postEncontrados);
		
		//usamos esta assert de spring para verificar si la coleccion es no null y no venga vacia
		//estos assertion de spring no vienen en los de junit la idea es combinarlos para facilitarnos
		//las cosas
		
		//Ver el import de como se importo el paquete de este metodo
		notEmpty(postEncontrados);
	}*/
	
}
