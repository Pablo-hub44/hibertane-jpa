package es.pablo.hibernate.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "direccion")
public class Direccion {
	
	@Id
	@Column(name = "id_direccion")
	private Long id;
	@Column(name = "direccion")
	private String direccion;
	@Column(name = "localidad")
	private String localidad;
	@Column(name = "provincia")
	private String provincia;
	@Column(name = "pais")
	private String pais;
	
	//relacion con empleado
	@OneToOne(mappedBy = "direccion",fetch = FetchType.LAZY)//se pone el atributo del que lleva la relacion aca en java
	private Empleado empleado;
	
	//constructor vacio
	public Direccion() {
		
	}
	//constructor con todo
	public Direccion(Long id, String direccion, String localidad, String provincia, String pais) {
		super();
		this.id = id;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.pais = pais;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	//los set y gets de la relacion con empleado
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	@Override
	public String toString() {
		return "Direccion [id=" + id + ", direccion=" + direccion + ", localidad=" + localidad + ", provincia="
				+ provincia + ", pais=" + pais + ", empleado=" + empleado.getCodigo() + "]";
	}
	
	
	
}
