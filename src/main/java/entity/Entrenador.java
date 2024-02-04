package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "coach")
public class Entrenador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String nombre;

	@OneToOne
	@JoinColumn(name = "team_id")
	private Equipo equipo;

	public Entrenador() {
	}

	public Entrenador(String nombre) {
		this.nombre = nombre;
	}

	public Entrenador(String nombre, Equipo equipo) {
		this.nombre = nombre;
		this.equipo = equipo;
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

	public void setNombre(String name) {
		this.nombre = name;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	@Override
	public String toString() {
		return "Entrenador [nombre=" + nombre + ", equipo=" + equipo + "]";
	}
}
