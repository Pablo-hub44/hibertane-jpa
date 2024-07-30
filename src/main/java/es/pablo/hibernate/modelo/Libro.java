package es.pablo.hibernate.modelo;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Libros")
public class Libro {
	
	@Id
	@Column(name = "libro_id")
	private Long id;
	
	
	@Column(name = "titulo")
	private String titulo;
	
	//objeto q hara referencia a la relacion con autor aca en libros
	@ManyToOne(fetch = FetchType.LAZY) //Esta estrategia se utiliza para optimizar el rendimiento y evitar cargar datos innecesarios en la memoria.
	@JoinColumn(name = "autor_id")//el nombre que va a tener en la db la relacion, este seria el FK
	private Autor autor;

	public Libro() {
		
	}
	
	public Libro(Long id, String titulo, Autor autor) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autor, id, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(id, other.id)
				&& Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + "]";
	}
	
	
	
	
}
