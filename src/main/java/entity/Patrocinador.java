package entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

/**
 * Represents a sponsor entity mapped to the "sponsor" table. Contains
 * attributes such as id, name, offered budget, and associated teams.
 * 
 * @author Sirpa_Jesus
 */
@Entity
@Table(name = "sponsor")
public class Patrocinador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String nombre;

	@Column(name = "offered_budget")
	private BigDecimal dineroOfrecido;

	@ManyToMany(mappedBy = "patrocinadores")
	private Set<Equipo> equipos = new HashSet<>();

	public Patrocinador() {
	}

	public Patrocinador(String nombre, BigDecimal dineroOfreciodo) {
		super();
		this.nombre = nombre;
		this.dineroOfrecido = dineroOfreciodo;
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

	public Set<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}

	public BigDecimal getDineroOfrecido() {
		return dineroOfrecido;
	}

	public void setDineroOfrecido(BigDecimal dineroOfrecido) {
		this.dineroOfrecido = dineroOfrecido;
	}

	@Override
	public String toString() {
		return "Patrocinador [nombre=" + nombre + ", equipos=" + equipos + "]";
	}
}
