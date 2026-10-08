package clase;

public class Combate {

	private Entrenador entrenador1;
	private Entrenador entrenador2;
	private Entrenador turnoActual;
	private Entrenador ganador;

	public void combateATurnos(Habilidad habilidad) {

		Entrenador entrenadorAtacante = turnoActual;

		Entrenador entrenadorDefensor;

		if (entrenadorAtacante.getPokemonActivo().getEstado() == Estado.DEBILITADO) {
			throw new IllegalStateException("Error!, El Pokémon activo está debilitado.");
		}

		if (turnoActual == entrenador1) {
			entrenadorDefensor = entrenador2;

		} else {
			entrenadorDefensor = entrenador1;

		}

		int dannio = calcularDanno(habilidad, entrenadorDefensor.getPokemonActivo());

		entrenadorDefensor.getPokemonActivo().recibirDanno(dannio);

		boolean estado = entrenadorDefensor.getPokemonActivo().comprobarEstado();

		if (estado == true) {
			boolean tienePokemonTodavia = entrenadorDefensor.saberPokemonActivosParaCombatir();

			if (tienePokemonTodavia == false) {
				determinarGanador();
			}

		}
		System.out.println("Daño realizado: " + dannio);

		System.out.println("Vida restante de " + entrenadorDefensor.getPokemonActivo().getNombre() + ": "
				+ entrenadorDefensor.getPokemonActivo().getPuntosVidaActuales());
		alternarTurno();
	}

	public int calcularDanno(Habilidad habilidad, Pokemon pokemonDefensor) {

		int dannio = 0;

		dannio = habilidad.getPuntosPotencia() - pokemonDefensor.getDefensa();
		if (dannio < 1) {
			dannio = 1;
		}

		return dannio;
	}

	public void alternarTurno() {

		if (turnoActual == entrenador1) {
			turnoActual = entrenador2;
		} else {
			turnoActual = entrenador1;
		}

	}

	public boolean determinarFinCombate() {

		return !entrenador1.saberPokemonActivosParaCombatir() || !entrenador2.saberPokemonActivosParaCombatir();

	}

	public Entrenador determinarGanador() {

		if (!entrenador1.saberPokemonActivosParaCombatir()) {
			ganador = entrenador2;

		} else if (!entrenador2.saberPokemonActivosParaCombatir()) {
			ganador = entrenador1;

		} else {
			throw new IllegalStateException("El combate sigue!, aún no hay ganador");
		}
		return ganador;

	}

	public Combate(Entrenador entrenador1, Entrenador entrenador2) {
		super();

		if (entrenador1 == null || entrenador2 == null) {
			throw new IllegalArgumentException("Los entrenadores no pueden ser null");

		}

		this.entrenador1 = entrenador1;
		this.entrenador2 = entrenador2;

		this.turnoActual = entrenador1;

		this.ganador = null;

	}

	public Entrenador getEntrenador1() {
		return entrenador1;
	}

	public void setEntrenador1(Entrenador entrenador1) {
		this.entrenador1 = entrenador1;
	}

	public Entrenador getEntrenador2() {
		return entrenador2;
	}

	public void setEntrenador2(Entrenador entrenador2) {
		this.entrenador2 = entrenador2;
	}

	public Entrenador getTurnoActual() {
		return turnoActual;
	}

	public void setTurnoActual(Entrenador turnoActual) {
		this.turnoActual = turnoActual;
	}

	public Entrenador getGanador() {
		return ganador;
	}

	public void setGanador(Entrenador ganador) {
		this.ganador = ganador;
	}

	@Override
	public String toString() {
		return "Combate [entrenador1=" + entrenador1 + ", entrenador2=" + entrenador2 + ", turnoActual=" + turnoActual
				+ ", ganador=" + ganador + "]";
	}

}
