package clase;

import java.util.ArrayList;
import java.util.List;

public class Entrenador {

	private String nombre;
	private List<Pokemon> equipoPokemon = new ArrayList<Pokemon>();
	private Pokemon pokemonActivo;
	private Rango rango;

	public boolean annadirPokemon(Pokemon pokemonNuevo) {

		if (pokemonNuevo == null || equipoPokemon.size() >= 6) {
			return false;
		}

		return equipoPokemon.add(pokemonNuevo);

	}

	public void cambiarPokemon(Pokemon pokemonNuevo) {

		if (pokemonNuevo == null) {
			throw new IllegalArgumentException("El Pokémon no puede ser null.");

		}

		if (pokemonNuevo.getEstado() == Estado.DEBILITADO) {
			throw new IllegalArgumentException("El pokemon no esta disponible para combatir.");
		}

		if (!equipoPokemon.contains(pokemonNuevo)) {
			throw new IllegalArgumentException("El Pokémon no está en el equipo");

		}

		this.pokemonActivo = pokemonNuevo;

	}

	public boolean tienePokemonDisponibles() {
		for (Pokemon pokemon : equipoPokemon) {

			if (pokemon.getEstado() != Estado.DEBILITADO) {
				return true;
			}

		}

		return false;

	}

	public boolean saberPokemonActivosParaCombatir() {

		return tienePokemonDisponibles();

	}

	public int numeroPokemonDebilitados() {
		int cantidadPokemons = 0;

		for (Pokemon pokemon : equipoPokemon) {
			if (pokemon.getEstado() == Estado.DEBILITADO) {
				cantidadPokemons++;

			}
		}

		return cantidadPokemons;
	}

	public void subirRango() {
		if (rango == Rango.A) {
			throw new IllegalStateException("El entrenador no puede subir más rangos, está en el máximo.");

		}

		int posicion = rango.ordinal();

		rango = Rango.values()[posicion + 1];
	}

	public void bajarRango() {

		if (rango == Rango.Z) {
			throw new IllegalStateException("El entrenador no puede bajar más rangos, está en el mínimo.");

		}

		int posicion = rango.ordinal();

		rango = Rango.values()[posicion - 1];

	}

	public void curarPokemons() {

		for (Pokemon pokemon : equipoPokemon) {

			pokemon.reiniciarEstado();
		}
	}

	public Entrenador(String nombre, List<Pokemon> equipoPokemon, Pokemon pokemonActivo, Rango rango) {
		super();

		if (nombre == null || nombre.isEmpty()) {
			throw new IllegalArgumentException("Error en el nombre.");

		}

		if (equipoPokemon == null || equipoPokemon.size() > 6) {
			throw new IllegalArgumentException("Error en el equipo Pokemon.");

		}

		if (pokemonActivo == null || pokemonActivo.getEstado() == Estado.DEBILITADO) {
			throw new IllegalArgumentException("Error en el pokemon activo.");

		}

		if (rango == null) {
			throw new IllegalArgumentException("Error en el rango.");

		}

		this.nombre = nombre;
		this.equipoPokemon = new ArrayList<>(equipoPokemon);
		this.pokemonActivo = pokemonActivo;
		this.rango = rango;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Pokemon> getEquipoPokemon() {
		return equipoPokemon;
	}

	public void setEquipoPokemon(List<Pokemon> equipoPokemon) {
		this.equipoPokemon = equipoPokemon;
	}

	public Pokemon getPokemonActivo() {
		return pokemonActivo;
	}

	public void setPokemonActivo(Pokemon pokemonActivo) {
		this.pokemonActivo = pokemonActivo;
	}

	public Rango getRango() {
		return rango;
	}

	public void setRango(Rango rango) {
		this.rango = rango;
	}

	@Override
	public String toString() {
		return "Entrenador [nombre=" + nombre + ", equipoPokemon=" + equipoPokemon + ", pokemonActivo=" + pokemonActivo
				+ ", rango=" + rango + "]";
	}
}
