package clase;

public class Habilidad {

	private String nombre;
	private Tipo tipo;
	private int puntosPotencia;

	public Habilidad(String nombre, Tipo tipo, int puntosPotencia) {
		super();

		if (nombre == null || nombre.isEmpty()) {
			throw new IllegalArgumentException("Error en el nombre.");

		} else {
			this.nombre = nombre;

		}

		if (tipo == null) {
			throw new IllegalArgumentException("Error en el tipo.");

		} else {
			this.tipo = tipo;

		}

		if (puntosPotencia <= 0) {
			throw new IllegalArgumentException("Error en los puntos de potencia.");

		} else {
			this.puntosPotencia = puntosPotencia;

		}

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

	public int getPuntosPotencia() {
		return puntosPotencia;
	}

	public void setPuntosPotencia(int puntosPotencia) {
		this.puntosPotencia = puntosPotencia;
	}

	@Override
	public String toString() {
		return "Habilidad [nombre=" + nombre + ", tipo=" + tipo + ", puntosPotencia=" + puntosPotencia + "]";
	}

}
