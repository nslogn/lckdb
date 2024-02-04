package entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "player")
public class Jugador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String nombre;

	@Column(name = "nationality")
	private String nacionalidad;

	@Column(name = "birthdate")
	private LocalDate fechaDeNacimiento;

	@Column(name = "mvp_count")
	private Integer cantidadMVP;

	@Column(name = "date_signed")
	private LocalDate fechaFirma;

	@Column(name = "contract_endDate")
	private LocalDate fechaFinContrato;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Equipo equipo;

	public Jugador() {
	}

	public Jugador(String nombre, String nacionalidad, LocalDate fechaDeNacimiento, Integer cantidadMVP,
			LocalDate fechaFirma, LocalDate fechaFinContrato) {
		super();
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.cantidadMVP = cantidadMVP;
		this.fechaFirma = fechaFirma;
		this.fechaFinContrato = fechaFinContrato;
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

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public LocalDate getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public Integer getCantidadMVP() {
		return cantidadMVP;
	}

	public void setCantidadMVP(Integer cantidadMVP) {
		this.cantidadMVP = cantidadMVP;
	}

	public LocalDate getFechaFirma() {
		return fechaFirma;
	}

	public void setFechaFirma(LocalDate fechaFirma) {
		this.fechaFirma = fechaFirma;
	}

	public LocalDate getFechaFinContrato() {
		return fechaFinContrato;
	}

	public void setFechaFinContrato(LocalDate fechaFinContrato) {
		this.fechaFinContrato = fechaFinContrato;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	@Override
	public String toString() {
		return "Jugador [id=" + id + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", fechaDeNacimiento="
				+ fechaDeNacimiento + ", cantidadMVP=" + cantidadMVP + ", fechaFirma=" + fechaFirma
				+ ", fechaFinContrato=" + fechaFinContrato + ", equipo=" + equipo + "]";
	}
}
