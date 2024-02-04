package entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "competition")
public class Competicion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String nombre;

	@Column(name = "creation_date")
	private LocalDate fechaCreacion;

	@Column(name = "matchdays_count")
	private Integer cantidadJornadas;

	@Column(name = "teams_count")
	private Integer cantidadEquipos;

	@ManyToMany
	@JoinTable(name = "competition_team", joinColumns = @JoinColumn(name = "competition_id"), inverseJoinColumns = @JoinColumn(name = "team_id"))
	private Set<Equipo> equipos = new HashSet<>();

	public Competicion() {
	}

	public Competicion(String nombre, LocalDate fechaCreacion, Integer cantidadJornadas, Integer cantidadEquipos) {
		super();
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.cantidadJornadas = cantidadJornadas;
		this.cantidadEquipos = cantidadEquipos;
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

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getCantidadJornadas() {
		return cantidadJornadas;
	}

	public void setCantidadJornadas(Integer cantidadJornadas) {
		this.cantidadJornadas = cantidadJornadas;
	}

	public Integer getCantidadEquipos() {
		return cantidadEquipos;
	}

	public void setCantidadEquipos(Integer cantidadEquipos) {
		this.cantidadEquipos = cantidadEquipos;
	}

	public Set<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}

	@Override
	public String toString() {
		return "Competicion [nombre=" + nombre + ", fechaCreacion=" + fechaCreacion + ", cantidadJornadas="
				+ cantidadJornadas + ", cantidadEquipos=" + cantidadEquipos + "]";
	}
}
