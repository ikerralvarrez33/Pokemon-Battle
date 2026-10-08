package clase;

import java.util.List;

public class Pokemon {

	private String nombre;
	private Tipo tipo;
	private int puntosVidaMaximos;
	private int puntosVidaActuales;
	private int defensa;
	private Estado estado;
	private List<Habilidad> habilidades;

	public void recibirDanno(int puntos) {

		if (puntos <= 0) {
			throw new IllegalArgumentException("Error en los puntos.");
		}

		puntosVidaActuales = puntosVidaActuales - puntos;

		if (puntosVidaActuales <= 0) {
			puntosVidaActuales = 0;
			estado = Estado.DEBILITADO;
		}

	}

	public void curarse(int puntos) {

		if (puntos <= 0) {
			throw new IllegalArgumentException("Error en los puntos");
		}

		puntosVidaActuales = puntosVidaActuales + puntos;

		if (puntosVidaActuales > puntosVidaMaximos) {
			puntosVidaActuales = puntosVidaMaximos;
		}
	}

	public boolean comprobarEstado() {
		return this.estado == Estado.ACTIVO;
	}

	public void reiniciarEstado() {

		this.estado = Estado.ACTIVO;

		if (this.puntosVidaActuales == 0) {
			this.puntosVidaActuales = this.puntosVidaMaximos;

		}

	}

	public Pokemon(String nombre, Tipo tipo, int puntosVidaMaximos, int puntosVidaActuales, int defensa, Estado estado,
			List<Habilidad> habilidades) {
		super();

		if (nombre == null || nombre.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede ser null ni estar vacío.");
		}

		if (tipo == null) {
			throw new IllegalArgumentException("El tipo del Pokémon no puede ser null.");
		}

		if (puntosVidaMaximos < 0 || puntosVidaMaximos > 255) {
			throw new IllegalArgumentException("La vida máxima debe estar entre 0 y 255.");
		}

		if (puntosVidaActuales < 0) {
			throw new IllegalArgumentException("La vida actual no puede ser negativa.");
		}
		if (puntosVidaActuales > puntosVidaMaximos) {
			throw new IllegalArgumentException("La vida actual no puede superar a la vida máxima.");
		}

		if (defensa < 0) {
			throw new IllegalArgumentException("La defensa no puede ser negativa.");
		}

		if (estado == null) {
			throw new IllegalArgumentException("El estado no puede ser null.");
		}

		if (habilidades == null) {
			throw new IllegalArgumentException("La lista de habilidades no puede ser null.");
		}
		if (habilidades.size() > 4) {
			throw new IllegalArgumentException("Un Pokémon no puede tener más de 4 habilidades.");
		}

		this.nombre = nombre;
		this.tipo = tipo;
		this.puntosVidaMaximos = puntosVidaMaximos;
		this.puntosVidaActuales = puntosVidaActuales;
		this.defensa = defensa;
		this.estado = estado;
		this.habilidades = habilidades;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public int getPuntosVidaMaximos() {
		return puntosVidaMaximos;
	}

	public void setPuntosVidaMaximos(int puntosVidaMaximos) {
		this.puntosVidaMaximos = puntosVidaMaximos;
	}

	public int getPuntosVidaActuales() {
		return puntosVidaActuales;
	}

	public void setPuntosVidaActuales(int puntosVidaActuales) {
		this.puntosVidaActuales = puntosVidaActuales;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Habilidad> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<Habilidad> habilidades) {
		this.habilidades = habilidades;
	}

	@Override
	public String toString() {
		return "Pokemon [nombre=" + nombre + ", tipo=" + tipo + ", puntosVidaMaximos=" + puntosVidaMaximos
				+ ", puntosVidaActuales=" + puntosVidaActuales + ", defensa=" + defensa + ", estado=" + estado
				+ ", habilidades=" + habilidades + "]";
	}

}
