package es.pablo.hibernate.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

//import jakarta.persistence.JoinColumn;
import javax.persistence.OneToOne;


//notacion importante para que sepa que esta clase es una entidad
@Entity
@Table(name = "Empleado") //para cuando se cree con ese nombre
public class Empleado implements Serializable{
	//id es el identificador
	@Id
	@Column(name = "Cod_empleado")
	private Long codigo;
	
	@Column(name = "apellidos")
	private String apellidos;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "fecha_nacimiento")
//	private Date fechaNacimiento; antes, date guarda tambien la hora y la fecha
	private LocalDate	fechaNacimiento;
	
//	private List<factura> facturas;
//	private List<Cliente> clientes;  //lazy relaciones perezosas y eager relaciones tempranas
	
	
	
	//la relacion de uno a uno
	@OneToOne(cascade = {CascadeType.ALL}) //Defines the set of cascadable operations that are propagatedto the associated entity.The value cascade=ALL is equivalent to cascade={PERSIST, MERGE, REMOVE, REFRESH, DETACH
	@JoinColumn(name = "id_direccion")//seria el atributo q esta en la tabla empleados, esta seria el fk
	private Direccion direccion;
//	
	//	constructor vacio
	public Empleado() {
		
	}
	
	//	constructor
	public Empleado(Long codigo, String apellidos, String nombre, LocalDate fechaNacimiento) {
		this.codigo = codigo;
		this.apellidos = apellidos;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
	}
	 
	
	
	public Direccion getDireccion() {
		return direccion;
	}


	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	@Override
	public String toString() {
		return "Empleado [codigo=" + codigo + ", apellidos=" + apellidos + ", nombre=" + nombre + ", fechaNacimiento="
				+ fechaNacimiento + ", direccion=" + direccion + "]";
	}
	
	
	
}
