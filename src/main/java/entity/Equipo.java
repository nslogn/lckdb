package entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

	@Column(name = "budget")
	private BigDecimal presupuesto;

	@Column(name = "expenses_initial")
	private BigDecimal gastosIniciales;

	@Column(name = "budget_from_sponsors")
	private BigDecimal ingresosPatrocinios;

	@Column(name = "last_season_position")
	private Integer posicionPrevia;

//	@Column(name = "current_position", columnDefinition = "INTEGER DEFAULT 0")
	private Integer posicionActual;

	@OneToMany(mappedBy = "equipo")
	private Set<Jugador> jugadores = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "team_sponsor", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "sponsor_id"))
	private Set<Patrocinador> patrocinadores = new HashSet<>();

	@ManyToMany(mappedBy = "equipos")
	private Set<Competicion> competiciones = new HashSet<>();

	@OneToOne(mappedBy = "equipo", cascade = CascadeType.ALL)
	private Entrenador entrenador;

	@OneToMany(mappedBy = "equipoVisitanteEntity")
	private List<Partido> partidosVisitante = new ArrayList<>();

	@OneToMany(mappedBy = "equipoLocalEntity")
	private List<Partido> partidosLocal = new ArrayList<>();

	public Equipo() {
	}

	public Equipo(String nombre, Integer puntos, Integer numeroJugadores) {
		super();
		this.nombre = nombre;
		this.puntos = puntos;
		this.numeroJugadores = numeroJugadores;
		this.presupuesto = new BigDecimal(10000);
		this.gastosIniciales = new BigDecimal(1000 * numeroJugadores);
		this.posicionPrevia = (int) (Math.random() * 10) + 1;
		this.ingresosPatrocinios = new BigDecimal(0);
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

	public BigDecimal getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(BigDecimal presupuesto) {
		this.presupuesto = presupuesto;
	}

	public BigDecimal getGastosIniciales() {
		return gastosIniciales;
	}

	public void setGastosIniciales(BigDecimal gastosIniciales) {
		this.gastosIniciales = gastosIniciales;
	}

	public BigDecimal getIngresosPatrocinios() {
		return ingresosPatrocinios;
	}

	public void setIngresosPatrocinios(BigDecimal ingresosPatrocinios) {
		this.ingresosPatrocinios = ingresosPatrocinios;
	}

	public Integer getPosicionPrevia() {
		return posicionPrevia;
	}

	public void setPosicionPrevia(Integer posicionPrevia) {
		this.posicionPrevia = posicionPrevia;
	}

	public List<Partido> getPartidosVisitante() {
		return partidosVisitante;
	}

	public void setPartidosVisitante(List<Partido> partidosVisitante) {
		this.partidosVisitante = partidosVisitante;
	}

	public List<Partido> getPartidosLocal() {
		return partidosLocal;
	}

	public void setPartidosLocal(List<Partido> partidosLocal) {
		this.partidosLocal = partidosLocal;
	}

	public Integer getPosicionActual() {
		return posicionActual;
	}

	public void setPosicionActual(Integer posicionActual) {
		this.posicionActual = posicionActual;
	}

	@Override
	public String toString() {
		return "Equipo [id=" + id + ", nombre=" + nombre + ", puntos=" + puntos + ", numeroJugadores=" + numeroJugadores
				+ ", jugadores=" + jugadores + ", patrocinadores=" + patrocinadores + ", competiciones=" + competiciones
				+ ", entrenador=" + entrenador + "]";
	}
}