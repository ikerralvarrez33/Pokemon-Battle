package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import clase.Entrenador;
import clase.Estado;
import clase.Habilidad;
import clase.Pokemon;
import clase.Rango;
import clase.Tipo;

class EntrenadorTest {
	@Test
	void testComprobarDatosCorrectos() {
		Pokemon pokemonActivo = new Pokemon("Charizard", Tipo.FUEGO, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		Entrenador entrenador = new Entrenador("Ash", new ArrayList<Pokemon>(), pokemonActivo, Rango.Z);
		assertNotNull(entrenador);

	}

	@Test
	void testNombreNull() {

		Pokemon pokemonActivo = new Pokemon("Charizard", Tipo.FUEGO, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		assertThrows(IllegalArgumentException.class, () -> {
			new Entrenador(null, new ArrayList<Pokemon>(), pokemonActivo, Rango.A);
		});

	}

	@Test
	void testEquipoSuperaLimitePokemon() {
		Pokemon pokemonActivo = new Pokemon("Pikachu", Tipo.ELÉCTRICO, 255, 50, 40, Estado.ACTIVO, new ArrayList<>());

		ArrayList<Pokemon> equipoDe7 = new ArrayList<>(java.util.Collections.nCopies(7, pokemonActivo));

		assertThrows(IllegalArgumentException.class, () -> {
			new Entrenador("Ash", equipoDe7, pokemonActivo, Rango.A);
		});
	}

	@Test
	void testNoValidoEquipoVacio() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Entrenador("Ash", new ArrayList<Pokemon>(), null, Rango.A);
		});
	}

	@Test
	void testPokemonActivoEnEstadoDebilitado() {
		Pokemon pokemonDebilitado = new Pokemon("Pikachu", Tipo.ELÉCTRICO, 255, 0, 40, Estado.DEBILITADO,
				new ArrayList<>());

		assertThrows(IllegalArgumentException.class, () -> {
			new Entrenador("Ash", new ArrayList<Pokemon>(), pokemonDebilitado, Rango.A);
		});

	}

	@Test
	void testPokemonActivoEsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Entrenador("Ash", new ArrayList<Pokemon>(), null, Rango.A);
		});
	}

	@Test
	void testComprobarRangoCorrecto() {

		Pokemon pokemonActivo = new Pokemon("Charizard", Tipo.FUEGO, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		Entrenador entrenador = new Entrenador("Ash", new ArrayList<Pokemon>(), pokemonActivo, Rango.I);
		assertEquals(Rango.I, entrenador.getRango());

	}

	@Test
	void testRangoFueraDelEnum() {

		Pokemon pokemonActivo = new Pokemon("Charizard", Tipo.FUEGO, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		assertThrows(IllegalArgumentException.class, () -> {
			new Entrenador("Ash", new ArrayList<Pokemon>(), pokemonActivo, Rango.valueOf("@"));
		});
	}

	@Test
	void testNoValidoRangoConDatosNoValidosParaEnum() {
		Pokemon pokemonActivo = new Pokemon("Lucario", Tipo.LUCHA, 255, 160, 100, Estado.ACTIVO,
				new ArrayList<Habilidad>());
		Entrenador entrenador = new Entrenador("Brock", new ArrayList<Pokemon>(), pokemonActivo, Rango.C);

		assertNotEquals(123, entrenador.getRango());
	}

	@Test
	void testValidoAnnadirPokemon() {
		Pokemon pokemonActivo = new Pokemon("Charizard", Tipo.FUEGO, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		Entrenador entrenador = new Entrenador("Ash", new ArrayList<Pokemon>(), pokemonActivo, Rango.Z);

		Pokemon pokemonNuevo = new Pokemon("Gengar", Tipo.FANTASMA, 255, 150, 80, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		entrenador.annadirPokemon(pokemonNuevo);

		assertEquals(1, entrenador.getEquipoPokemon().size());
	}

	@Test
	void testAnnadirPokemonEnEquipoMayorASeis() {
		Pokemon pokemonActivo = new Pokemon("Charizard", Tipo.FUEGO, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		ArrayList<Pokemon> equipoLleno = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			equipoLleno.add(
					new Pokemon("Pikachu", Tipo.ELÉCTRICO, 255, 50, 40, Estado.ACTIVO, new ArrayList<Habilidad>()));
		}

		Entrenador entrenador = new Entrenador("Ash", equipoLleno, pokemonActivo, Rango.Z);
		Pokemon nuevo = new Pokemon("Pidgeot", Tipo.VOLADOR, 255, 100, 50, Estado.ACTIVO, new ArrayList<Habilidad>());

		assertFalse(entrenador.annadirPokemon(nuevo));
	}

	@Test
	void testCambiarPokemonEnEquipo() {
		Pokemon pokemonActivo = new Pokemon("Charizard", Tipo.FUEGO, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		ArrayList<Pokemon> equipo = new ArrayList<>();
		Pokemon pokemonEnEquipo = new Pokemon("Gengar", Tipo.FANTASMA, 255, 150, 80, Estado.ACTIVO,
				new ArrayList<Habilidad>());
		equipo.add(pokemonEnEquipo);

		Entrenador entrenador = new Entrenador("Ash", equipo, pokemonActivo, Rango.Z);

		entrenador.cambiarPokemon(pokemonEnEquipo);
		assertEquals(pokemonEnEquipo, entrenador.getPokemonActivo());
	}

	@Test
	void testCambiarPokemonDebilitado() {
		Pokemon pokemonActivo = new Pokemon("Charizard", Tipo.FUEGO, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		ArrayList<Pokemon> equipo = new ArrayList<>();

		Pokemon pokemonDebilitado = new Pokemon("Charizard", Tipo.FUEGO, 255, 0, 50, Estado.DEBILITADO,
				new ArrayList<Habilidad>());

		Entrenador entrenador = new Entrenador("Ash", equipo, pokemonActivo, Rango.Z);

		assertThrows(IllegalArgumentException.class, () -> {
			entrenador.cambiarPokemon(pokemonDebilitado);
		});

	}

	@Test
	void testCambiarPokemon() {
		Pokemon pokemonActivo = new Pokemon("Charizard", Tipo.FUEGO, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		ArrayList<Pokemon> equipo = new ArrayList<>();
		Pokemon pokemonEnEquipo = new Pokemon("Gengar", Tipo.FANTASMA, 255, 150, 80, Estado.ACTIVO,
				new ArrayList<Habilidad>());
		equipo.add(pokemonEnEquipo);

		Entrenador entrenador = new Entrenador("Ash", equipo, pokemonActivo, Rango.Z);

		entrenador.cambiarPokemon(pokemonEnEquipo);
		assertEquals(pokemonEnEquipo, entrenador.getPokemonActivo());
	}

	@Test
	void testCambiarPokemonPorunString() {

		Entrenador entrenador = new Entrenador("Ash", new ArrayList<>(),
				new Pokemon("Charizard", Tipo.FUEGO, 255, 100, 50, Estado.ACTIVO, new ArrayList<>()), Rango.Z);
		Object parametroIncorrecto = "Pikachu";

		assertThrows(Exception.class, () -> {
			entrenador.cambiarPokemon((Pokemon) parametroIncorrecto);
		});

	}

	@Test
	void testSaberPokemonDisponibles() {
		Pokemon pokemonActivo = new Pokemon("Charizard", Tipo.FUEGO, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		ArrayList<Pokemon> equipo = new ArrayList<>();
		equipo.add(pokemonActivo);

		Entrenador entrenador = new Entrenador("Brock", equipo, pokemonActivo, Rango.Z);

		assertTrue(entrenador.saberPokemonActivosParaCombatir());
	}

	@Test
	void testComprobarPokemonDisponiblesNoValidos() {
		Pokemon pokemonActivo = new Pokemon("Charizard", Tipo.FUEGO, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		ArrayList<Pokemon> equipoDebilitado = new ArrayList<>();
		equipoDebilitado
				.add(new Pokemon("Pikachu", Tipo.ELÉCTRICO, 255, 0, 40, Estado.DEBILITADO, new ArrayList<Habilidad>()));

		Entrenador entrenador = new Entrenador("Brock", equipoDebilitado, pokemonActivo, Rango.Z);

		assertFalse(entrenador.tienePokemonDisponibles());
	}

	@Test
	void testNumeroPokemonDebilitados() {
		Pokemon pokemonActivo = new Pokemon("Charizard", Tipo.FUEGO, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		ArrayList<Pokemon> equipo = new ArrayList<>();
		equipo.add(new Pokemon("Pikachu", Tipo.ELÉCTRICO, 255, 0, 40, Estado.DEBILITADO, new ArrayList<Habilidad>()));
		equipo.add(new Pokemon("Bulbasaur", Tipo.PLANTA, 255, 50, 40, Estado.ACTIVO, new ArrayList<Habilidad>()));

		Entrenador entrenador = new Entrenador("Ash", equipo, pokemonActivo, Rango.Z);

		assertEquals(1, entrenador.numeroPokemonDebilitados());
	}

	@Test
	void testSubirRango() {
		Pokemon pokemonActivo = new Pokemon("Charizard", Tipo.FUEGO, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());
		Entrenador entrenador = new Entrenador("Ash", new ArrayList<Pokemon>(), pokemonActivo, Rango.Z);

		entrenador.subirRango();
		assertEquals(Rango.Y, entrenador.getRango());
	}

	@Test
	void testSubirRangoNoValido() {

		Pokemon pokemonActivo = new Pokemon("Mewtwo", Tipo.PSÍQUICO, 255, 255, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());
		Entrenador entrenador = new Entrenador("Brock", new ArrayList<Pokemon>(), pokemonActivo, Rango.A);

		assertThrows(IllegalStateException.class, () -> {
			entrenador.subirRango();
		});

	}

	@Test
	void testBajarRango() {
		Pokemon pokemonActivo = new Pokemon("Charizard", Tipo.FUEGO, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());
		Entrenador entrenador = new Entrenador("Ash", new ArrayList<Pokemon>(), pokemonActivo, Rango.A);

		entrenador.bajarRango();
		assertEquals(Rango.B, entrenador.getRango());
	}

	@Test
	void testBajarRangoNoValido() {
		Pokemon pokemonActivo = new Pokemon("Mewtwo", Tipo.PSÍQUICO, 255, 255, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());
		Entrenador entrenador = new Entrenador("Brock", new ArrayList<Pokemon>(), pokemonActivo, Rango.Z);

		assertThrows(IllegalStateException.class, () -> {
			entrenador.bajarRango();
		});

	}

}
