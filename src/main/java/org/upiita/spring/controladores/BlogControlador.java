package org.upiita.spring.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.upiita.spring.dao.PostDAO;
import org.upiita.spring.entidades.Post;
import org.upiita.spring.formas.FormaSession;
import org.upiita.spring.formas.PostForma;

//la anotacion controller le indica a spring que es una clase controladora
//el concepto anotation no es de spring es una interfaz que permite marcar a una clase con
//cierta configuracion, uno mismo puede hacer sus anotaciones
//esta anotacion si es de spring
@Controller
public class BlogControlador {


	@Autowired
	private FormaSession formaSession;
	
	
	//Aggarra una instancia del contexto de spring y la asigna a esta propiedad, la inicializa pues, para evitar nullpointerexception
	//Es la famosa inyeccion de dependencias

	//@Qualifier(value="PostDAO") con esto pido la instancia llamada PostDAO 
	@Autowired
	@Qualifier(value="PostDAO")
	private PostDAO postDAO;
	
	//Esta anotacion le dice a spring que este metodo sera una url
	//{postId:{[0-9]+} indica que la url llevara un parametro llamado postId
	//despues de los : indica una exp. reg. para definir el tipo de valor del parametro postId (en este caso entero)
	//{parametro:expresion reg}
	@RequestMapping(value="/blog/{postId:[0-9]+}")
	public String mostrarInicio(@PathVariable(value="postId") Integer postId, @RequestParam(required=false) Integer limite,@RequestParam(required=false) String nombre, Model modelo) {
		
		//@@PathVariable le dice a spring que postId tendra el valor de postId de la url 
		//@@PathVariable(value="varUrl") tipo de dato var java } (es un mapeo o una asociacion)
		
		//En este metodo vendria toda la conf necesaria para mapear la url(calculos, base etc)
		
		System.out.println("limite: "+ limite);
		System.out.println("despachando url /blog/" + postId);
		
		Post elPost = postDAO.buscaPorId(postId);
		
		//Enviamos el objeto a la vista(en este caso post.jsp)
		modelo.addAttribute("var",elPost);
		modelo.addAttribute("nombre",nombre);
		
		System.out.println("Items comprados: "+formaSession.getItemsComprados());
		
		//Es el archivo (web) donde se va a enviar el contenido de este metodo
		return "post";
	}

	@RequestMapping(value="/blog/{postId:[0-9]+}/editar")
	public String mostrarEditor(@RequestParam(required=false) Boolean actualizado, @PathVariable Integer postId, Model modelo)
	{
		
		//si hubo errores al guardar
		if(modelo.containsAttribute("post")){
			System.out.println("Hubo errores, Modelo: "+modelo);
		}
		
		//si todo salio bien o si es la 1era vez que entra
		else{
			Post post = postDAO.buscaPorId(postId);
			modelo.addAttribute("post",post);
			modelo.addAttribute("actualizado", actualizado);		
		}
		
		return "post_editar";
	}

	//Por default las ur son del tipo get (ya con method=RequestMethod.POST indicamos )
	@RequestMapping(value="/blog/guardar",method=RequestMethod.POST)
	//public String guardarPost(Integer id, @RequestParam(value="titulo") String titulo_, String contenido)
	
	///El @Valid para validar ya que en la clase PostForma ya estamos usando anotaciones de hibernate validator.
	public String guardarPost(@Valid @ModelAttribute("post") PostForma forma, BindingResult validacion, RedirectAttributes atributos)
	{
		String urlRedirect = "";
		//@RequestParam(value="titulo") String titulo_ en este caso lo pusimos por que en el jsp del formulario se envia el titulo como name="titulo_" y como lo resivimos 
		//en java como titulo es para mapear o asociar en caso de que tengan nombre distinton la var en jsp y en java, en el caso de la var contenido es llamada de igual modo en el jsp y en java
		//por eso no ponemos la anotacion
		
		System.out.println("id: " + forma.getId() + " titulo_: " +  forma.getTitulo() + ", contenido: " + forma.getContenido());
		
		//Si hay errorres de validadcion
		if(validacion.hasErrors()){
			//etso esta disponible desde la version 3.1 de spring 
			//es para preservar datos en un redirect
			atributos.addFlashAttribute("post", forma);
			atributos.addFlashAttribute("org.springframework.validation.BindingResult.post", validacion);
			
			urlRedirect = "redirect:/blog/" + forma.getId() + "/editar";
		}
		
		//si no hay errorres de validacion
		else{

			//@TODO: mas adelante guardamos estos datos en la BD
			Post post =  new Post();
			post.setId(forma.getId());
			post.setTitulo(forma.getTitulo());
			post.setContenido(forma.getContenido());
			
			Integer postIdGuardado = postDAO.guardar(post);
			
			//@TODO PATRON POST - REDIRECT - GET
			urlRedirect =  "redirect:/blog/" + postIdGuardado + "/editar?actualizado=true";	
		}
		
		return urlRedirect;
	}
	
}
