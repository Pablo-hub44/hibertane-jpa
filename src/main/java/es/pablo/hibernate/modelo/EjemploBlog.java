package es.pablo.hibernate.modelo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EjemploBlog {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
	public static void main(String[] args) {
		insertar();
		
		imprimirDatos();
		borrarUsuario(1L);
		borrar(3L);
		imprimirDatos();
		emf.close();
		
	}
	
	private static void borrarUsuario(Long id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Usuario u = em.find(Usuario.class,	 id);
		if (u != null) {
			
			em.remove(u);
		}else {
			System.out.println("id llega nulo");
		}
		em.getTransaction().commit();
		em.close();
		
	}

	static void insertar() {
		
		Publicacion p = new Publicacion(1L, "Hoy hace sol");
		Comentario c1 = new Comentario(1L, "Aqui llueve");
		Comentario c2 = new Comentario(2L, "Buenas tardes");
		Comentario c3 = new Comentario(3L, "Buenas tardes don juancho");
		
		Usuario user = new Usuario();
		Usuario user1 = new Usuario(1L, "Pancho villa");
		Usuario user2 = new Usuario(2L, "Pancho villa");
		
		p.insertarComentaraio(c1);
		p.insertarComentaraio(c2);
		p.insertarComentaraio(c3);
		
//		user.insertarUsuario(user1);
//		user.insertarUsuario(user2);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();//siempre el begin primero
		
		em.persist(p);
		em.persist(user1);
		em.persist(user2);
		
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	static void borrar(Long comentario) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Comentario c = em.find(Comentario.class, comentario);
//		Publicacion pub  =c.getPublicacion();
//		pub.eliminarComentario(c);
		em.remove(c.getPublicacion());//borrar no solo un comentario, sino TODos los comentarios
		em.getTransaction().commit();
		em.close();
	}
	
	
	static void imprimirDatos() {
		EntityManager em = emf.createEntityManager();
		
		System.out.println("Entradas de blog");
		List<Publicacion> pubs = em.createQuery("FROM Publicacion", Publicacion.class).getResultList();
		
		for (Publicacion publicacion : pubs) {
			System.out.println("\n "+ publicacion);
			if (publicacion.getComentarios().isEmpty()) {
				System.out.println("Sin comentarios");
			}
		}
		List<Usuario> usuarios = em.createQuery("FROM Usuario", Usuario.class).getResultList();
		
		for (Usuario user : usuarios) {
			System.out.println("\n "+ user);
		}
		em.close();
	}
}
