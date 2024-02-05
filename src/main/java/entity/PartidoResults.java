package entity;

import jakarta.persistence.*;

/**
 * Represents match results entity mapped to the "matchGame_results" table.
 * Contains attributes such as id, scores for local and visiting teams, and an
 * associated match entity.
 * 
 * @author Sirpa_Jesus
 */
@Entity
@Table(name = "matchGame_results")
public class PartidoResults {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "score_local")
	private Integer golesLocal;

	@Column(name = "score_away")
	private Integer golesVisitante;

	@OneToOne
	@JoinColumn(name = "partido_id", referencedColumnName = "id")
	private Partido partido;

	public PartidoResults() {
	}

	public PartidoResults(Integer golesLocal, Integer golesVisitante, Partido partido) {
		super();
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
		this.partido = partido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(Integer golesLocal) {
		this.golesLocal = golesLocal;
	}

	public Integer getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(Integer golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	@Override
	public String toString() {
		return "PartidoResults [id=" + id + ", golesLocal=" + golesLocal + ", golesVisitante=" + golesVisitante
				+ ", partido=" + partido + "]";
	}
}
