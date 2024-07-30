package es.pablo.hibernate.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "autores")
public class Autor {

	@Id
	@Column(name = "autor_id")
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "nacionalidad")
	private String nacionalidad;

	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER) //el nombre del campo q esta en la otra tabla de la relacion
	private List<Libro> libros = new ArrayList<>();
	//constructor
	public Autor() {
		
	}

	public Autor(Long id, String nombre, String nacionalidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}

	
	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
		for (Libro libro : libros) {
			libro.setAutor(this);
		}
	}
	
	//metodo para crear el libro
	public void addLibro(Libro libro) {
		//		validacion
		if (!libros.contains(libro)) {
			libros.add(libro);
			libro.setAutor(this);
		}
	}
	
	//metodo para borrar un libro
	public void removeLibro(Libro libro) {
		//		validacion
		 if (libros.contains(libro)) {
		        libros.remove(libro);
		        libro.setAutor(null); // Desasociar el libro del autor
		    }
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

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nacionalidad, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		return Objects.equals(id, other.id) && Objects.equals(nacionalidad, other.nacionalidad)
				&& Objects.equals(nombre, other.nombre);
	}
	
	
	
}
