package entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "team")
public class Equipo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String nombre;

	@Column(name = "points")
	private Integer puntos;

	@Column(name = "players_count")
	private Integer numeroJugadores;

	@OneToMany(mappedBy = "equipo")
	private Set<Jugador> jugadores = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "team_sponsor", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "sponsor_id"))
	private Set<Patrocinador> patrocinadores = new HashSet<>();

	@ManyToMany(mappedBy = "equipos")
	private Set<Competicion> competiciones = new HashSet<>();

	@OneToOne(mappedBy = "equipo", cascade = CascadeType.ALL)
	private Entrenador entrenador;

	public Equipo() {
	}

	public Equipo(String nombre, Integer puntos, Integer numeroJugadores) {
		super();
		this.nombre = nombre;
		this.puntos = puntos;
		this.numeroJugadores = numeroJugadores;
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

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public Integer getNumeroJugadores() {
		return numeroJugadores;
	}

	public void setNumeroJugadores(Integer numeroJugadores) {
		this.numeroJugadores = numeroJugadores;
	}

	public Set<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(Set<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Set<Patrocinador> getPatrocinadores() {
		return patrocinadores;
	}

	public void setPatrocinadores(Set<Patrocinador> patrocinadores) {
		this.patrocinadores = patrocinadores;
	}

	public Set<Competicion> getCompeticiones() {
		return competiciones;
	}

	public void setCompeticiones(Set<Competicion> competiciones) {
		this.competiciones = competiciones;
	}

	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	@Override
	public String toString() {
		return "Equipo [id=" + id + ", nombre=" + nombre + ", puntos=" + puntos + ", numeroJugadores=" + numeroJugadores
				+ ", jugadores=" + jugadores + ", patrocinadores=" + patrocinadores + ", competiciones=" + competiciones
				+ ", entrenador=" + entrenador + "]";
	}
}