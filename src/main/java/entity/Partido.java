package entity;

import jakarta.persistence.*;

/**
 * Represents a match entity mapped to the "matchGame" table. Contains
 * attributes such as id, names of local and visiting teams, the best player's
 * name, and associated entities for match results and teams.
 * 
 * @author Sirpa_Jesus
 */
@Entity
@Table(name = "matchGame")
public class Partido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(name = "local_team_name")
	private String equipoLocal;

	@Column(name = "visit_team_name")
	private String equipoVisitante;

	@Column(name = "match_day")
	private Integer jornada;
	
	@OneToOne(mappedBy = "partido", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private PartidoResults partidoResult;
	
	@Column(name = "mvp_name")
	private String mejorJugadorNombre;

	@ManyToOne
	@JoinColumn(name = "home_team_id", referencedColumnName = "id")
	private Equipo equipoLocalEntity;

	@ManyToOne
	@JoinColumn(name = "away_team_id", referencedColumnName = "id")
	private Equipo equipoVisitanteEntity;

	public Partido() {
	}

	public Partido(String equipoLocal, String equipoVisitante) {
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(String equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public String getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipoVisitante(String equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public PartidoResults getPartidoResult() {
		return partidoResult;
	}

	public void setPartidoResult(PartidoResults partidoResult) {
		this.partidoResult = partidoResult;
	}

	public String getMejorJugadorNombre() {
		return mejorJugadorNombre;
	}

	public void setMejorJugadorNombre(String mejorJugadorNombre) {
		this.mejorJugadorNombre = mejorJugadorNombre;
	}

	public Equipo getEquipoLocalEntity() {
		return equipoLocalEntity;
	}

	public void setEquipoLocalEntity(Equipo equipoLocalEntity) {
		this.equipoLocalEntity = equipoLocalEntity;
	}

	public Equipo getEquipoVisitanteEntity() {
		return equipoVisitanteEntity;
	}

	public void setEquipoVisitanteEntity(Equipo equipoVisitanteEntity) {
		this.equipoVisitanteEntity = equipoVisitanteEntity;
	}

	public Integer getJornada() {
		return jornada;
	}

	public void setJornada(Integer jornada) {
		this.jornada = jornada;
	}

	@Override
	public String toString() {
		return "Partido [Id=" + Id + ", equipoLocal=" + equipoLocal + ", equipoVisitante=" + equipoVisitante
				+ ", mejorJugadorNombre=" + mejorJugadorNombre + ", equipoLocalEntity=" + equipoLocalEntity
				+ ", equipoVisitanteEntity=" + equipoVisitanteEntity + "]";
	}
}