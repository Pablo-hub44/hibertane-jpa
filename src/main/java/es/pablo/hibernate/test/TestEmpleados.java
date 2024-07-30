package es.pablo.hibernate.test;

import java.time.LocalDate;
import java.time.Month;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import es.pablo.hibernate.modelo.Direccion;
import es.pablo.hibernate.modelo.Empleado;

public class TestEmpleados {
	
	//esto es para un gestor para hacer consulta sql, entity manager global
	//private static EntityManager manager;
	
	//una forma
	//@PersistenceUnit()
	//instancais, aunque estitymanagerfactory tambien es una interfaz
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");//asi como le pusimos al xml;
	
	//notaacion para crear nuestro gestor de dependencia rapido
	//@PersistenceContext(unitName = "Persistencia")
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//otra forma,la clasica,  asi creamos el gestor de persistencia (em)
		//emf = emf.createEntityManager()  ya no pk lo instanciamos arriba
		
		//manager.cre
		//EntityManagerFactory
		//1 crear el gestor de persistencia (EB)
		//manager = emf.createEntityManager();
		
		//		creamos un objeto
		//Empleado  e = new Empleado(10L, "perez", "jose", new GregorianCalendar(1999,6,6).getTime());
		
		insertInicial();
//		manager.persist(e);
		
		//List<Empleado> empleados = (List<Empleado>)manager.createQuery("FROM Empleado").getResultList(); //no es necesario poner el select * pk ya esta tomando encuenta que queremos todos
		//System.out.println("en esta DB hay "+empleados.size()+ " empleados");
		
//		manager.getTransaction().begin();//metodo para iniciar trasacciones
		//find permite obtener elementos a partir de un identificador
//		Empleado asesor = manager.find(Empleado.class, 10L);
//		asesor.setNombre("David");
//		asesor.setApellidos("lopez");
		
		//manager.merge("Lopez"); //merge the state of the given entity into the * current persistence context. util para ahorra recursos, 
		//para modifica; podemos obtener una entidad administrada a partir de otra entidad que no sea administrada
		
//		manager.getTransaction().commit();		
//		imprmirtTodo();
		
//		manager.close();//cerramos la entitymanager
		
		EntityManager enty = emf.createEntityManager();
		
		//Empleado  e = new Empleado(10L, "perez", "jose", new GregorianCalendar(1999,6,6).getTime());  antes con gregorian porque era Date
		Empleado  e = new Empleado(10L, "perez", "jose", LocalDate.of(1971,Month.AUGUST,15));
		
		e.setDireccion(new Direccion(15L, "Calle san juan del rio", "pachuca de soto", "hidalgo", "MEXICO"));
		enty.getTransaction().begin();
		enty.persist(e);
		
		
		enty.getTransaction().commit();
		enty.close();
		
		imprmirtTodo();
		
		enty = emf.createEntityManager();
		enty.getTransaction().begin();
		e = enty.merge(e);//lo convertimos de una entidad no gestionada a una gestionada, se convierte en manage
		e.setNombre("Dani");
		enty.remove(e);//coiin remove quita la entidad del sistema
		enty.getTransaction().commit();
		enty.close();
		
		imprmirtTodo();
		
		
	}


	/**
	 * @param 
	 * agaramos un pedaso de codigo que estaba en la parte de arriba hicimos stract method y creamos un a funcion que la invocamos arriba
	 */
	private static void insertInicial() {
		EntityManager enty = emf.createEntityManager();
		Empleado  e2 = new Empleado(11L, "perez", "martin", LocalDate.of(1971,Month.AUGUST,30));
		enty.getTransaction().begin();//metodo para iniciar trasacciones
		//va a enviar el objeto con el nuevo empleado
		
		enty.persist(e2);
		enty.getTransaction().commit();
		
		enty.close();
	}
	
	
	
	
	
	
	
	
	
	private static void imprmirtTodo() {
		EntityManager enty = emf.createEntityManager();
		
		@SuppressWarnings("unchecked")
		List<Empleado> empleado = (List<Empleado>)enty.createQuery("FROM Empleado").getResultList();
		System.out.println("Hay "+ empleado.size()+ " empleados en el sistema.");
		
		for (Empleado emp : empleado) {
			System.out.println(emp.toString());
		}
	
		//sin el casteo lo traeria el objeto es.pablo.hibernate.modelo.Empleado@3a1706e1
		//es.pablo.hibernate.modelo.Empleado@1d901f20
		
		enty.close();
	}

}
