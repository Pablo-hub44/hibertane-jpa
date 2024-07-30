package es.pablo.hibernate.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Publicacion {

	@Id
	@Column
	private Long id;
	
	@Column
	private String titulo;
	
	
	@OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL)//, orphanRemoval = true)
	private List<Comentario> comentarios = new ArrayList<>();
	
	//relacion inversa
	@ManyToOne
	@JoinColumn
	private Usuario autor;
	
	
	
	
	
	
	
	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	//constructores
	public Publicacion() {}

	public Publicacion(long id, String titulo) {
		this.id = id;
		this.titulo = titulo;
	}
	
	

	public Publicacion(Long id, String titulo, List<Comentario> comentarios) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.comentarios = comentarios;
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

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public void insertarComentaraio(Comentario c1) {
		comentarios.add(c1);
		c1.setPublicacion(this);
		
	}
	
	public void eliminarComentario(Comentario c) {
		comentarios.remove(c);
		c.setPublicacion(null);
	}

	@Override
	public int hashCode() {
		return Objects.hash(comentarios, id, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Publicacion other = (Publicacion) obj;
		return Objects.equals(comentarios, other.comentarios) && Objects.equals(id, other.id)
				&& Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Publicacion [id=" + id + ", titulo=" + titulo + ", comentarios=" + comentarios + "]";
	}


	
	
	
}