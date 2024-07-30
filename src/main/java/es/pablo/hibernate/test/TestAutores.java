package es.pablo.hibernate.test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.pablo.hibernate.modelo.Autor;
import es.pablo.hibernate.modelo.Libro;

public class TestAutores {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
	
	
	public static void main(String[] args) {
		crearDatos();
		
		imprimirDatos();
		
		
	}
	
	static void crearDatos() {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();//siempre el begin primero
		
//		Autor autor1 = new Autor(1L, "Pablo Peres", "Española");
//		Autor autor2 = new Autor(2L, "Elena gomez", "Mexicana");
//		Autor autor3 = new Autor(3L, "Miguel Peres", "Chileno");
//		em.persist(autor1);
//		em.persist(autor2);
//		em.persist(autor3);
//		
//		
//		
//		Libro libro1 = new Libro(1L, "programar java facil", autor2); 
//		Libro libro2= new Libro(2L, "Como vestirse con estilo", autor3); 
//		Libro libro3 = new Libro(3L, "como coninar sin quemar la cocina", autor1); 
//		Libro libro4 = new Libro(4L, "Programar en cobol es divertido", autor2); 
//		Libro libro5 = new Libro(5L, "programar en cobol no es divertido", autor2); 
//		
//		em.persist(libro1);
//		em.persist(libro2);
//		em.persist(libro3);
//		em.persist(libro4);
//		em.persist(libro5);
		
		Libro L1 = new Libro();
		L1.setId(1L);
		L1.setTitulo("jpa e hibernate");
		em.persist(L1);//asi lo guardamos
		
		Autor a1 = new Autor(1L, "Dani", "Española");
//		a1.setLibros(Arrays.asList(L1));  una forma de asignar los libros
		a1.addLibro(L1);
		System.out.println("libros escritos (pre-save):"+ a1.getLibros().size());
		em.persist(a1);
		
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	
	static void imprimirDatos() {
		EntityManager em = emf.createEntityManager();
		
		Autor autor2 = em.find(Autor.class, 2L);
		System.out.println(autor2);
		
		List<Libro> libros = autor2.getLibros();
//		libros.size();//como un hack
//		em.close();
		
		System.out.println("libros escritos (pre-save):"+ libros.size());
		
		for (Libro libro : libros) {
			System.out.println("* "+ libro.toString());
		}
		em.close();
	}
}
