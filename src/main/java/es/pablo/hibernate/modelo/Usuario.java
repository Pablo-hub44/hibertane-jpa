package es.pablo.hibernate.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

@Entity
@Table
public class Usuario {

	@Id
	@Column
	private Long id;
	
	@Column
	private String nombre;
	
	
	
	//la relacion con publicaciones, el objeto mapeado del lado inverso es autor
	//un usuario tiene muchas publicaciones
	@OneToMany(mappedBy = "autor")//, cascade = CascadeType.ALL)
	private List<Publicacion> publicaciones = new ArrayList<Publicacion>();
	
	
	
	public Usuario() {
		
	}
	
	@PreRemove //notacion para que borre algo antes de
	public void nullificarPublicaciones() {
		publicaciones.forEach(pub -> pub.setAutor(null));
	}
	



	public Usuario(Long id, String nombre, List<Publicacion> publicaciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.publicaciones = publicaciones;
	}

	public Usuario(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}



	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}
	
	
//	public void agregarUsuario(Long id, String nombre) {
//		this.id = id;
//		this.nombre = nombre;
//	}

//	public void insertarUsuario(Usuario user1) {
//		user1.add();
//		
//	}
	
}
