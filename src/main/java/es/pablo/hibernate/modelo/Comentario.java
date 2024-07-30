package es.pablo.hibernate.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Comentario {
	
	@Id
	@Column
	private Long id;
	
	@Column
	private String mensaje;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Publicacion publicacion;
	
	public Comentario() {
		
	}

	public Comentario(Long id, String mensaje, Publicacion publicacion) {
		super();
		this.id = id;
		this.mensaje = mensaje;
		this.publicacion = publicacion;
	}

	public Comentario(Long id, String mensaje) {
		super();
		this.id = id;
		this.mensaje = mensaje;
		
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public Publicacion getPublicacion() {
		return publicacion;
	}


	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	

}
