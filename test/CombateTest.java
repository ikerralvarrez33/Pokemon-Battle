package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import clase.Combate;
import clase.Entrenador;
import clase.Estado;
import clase.Habilidad;
import clase.Pokemon;
import clase.Rango;
import clase.Tipo;

class CombateTest {

	@Test
	void testComprobarCombate() {

		Pokemon pokemonActivoEntrenadorA = new Pokemon("Charizard", Tipo.FUEGO, 255, 120, 60, Estado.ACTIVO,
				new ArrayList<Habilidad>());
		Entrenador entrenadorA = new Entrenador("Ash", new ArrayList<Pokemon>(), pokemonActivoEntrenadorA, Rango.Z);

		Pokemon pokemonActivoEntrenadorB = new Pokemon("Blastoise", Tipo.AGUA, 255, 130, 40, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		Entrenador entrenadorB = new Entrenador("Gary", new ArrayList<Pokemon>(), pokemonActivoEntrenadorB, Rango.A);

		Combate combate = new Combate(entrenadorA, entrenadorB);

		assertNotNull(combate);
	}

	@Test
	void testComprobarCombateConNull() {

		Pokemon pokemonActivoEntrenadorA = new Pokemon("Emboar", Tipo.FUEGO, 255, 135, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		Entrenador entrenadorA = new Entrenador("Ash", new ArrayList<Pokemon>(), pokemonActivoEntrenadorA, Rango.B);

		assertThrows(IllegalArgumentException.class, () -> {
			new Combate(entrenadorA, null);
		});
	}

	@Test
	void testComprobarCombateConDosNulls() {

		assertThrows(IllegalArgumentException.class, () -> {
			new Combate(null, null);
		});
	}

	@Test
	void testAlternarTurno() {

		Pokemon pokemonActivoEntrenadorA = new Pokemon("Infernape", Tipo.FUEGO, 255, 178, 20, Estado.ACTIVO,
				new ArrayList<Habilidad>());
		Entrenador entrenadorA = new Entrenador("Ash", new ArrayList<Pokemon>(), pokemonActivoEntrenadorA, Rango.Z);

		Pokemon pokemonActivoEntrenadorB = new Pokemon("Empoleon", Tipo.AGUA, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		Entrenador entrenadorB = new Entrenador("Gary", new ArrayList<Pokemon>(), pokemonActivoEntrenadorB, Rango.Z);

		Combate combate = new Combate(entrenadorA, entrenadorB);

		combate.alternarTurno();

		assertEquals(entrenadorB, combate.getTurnoActual());
	}

	@Test
	void testRealizarTurnoConPokemonDebilitado() {

		Pokemon pokemonActivoEntrenadorA = new Pokemon("Delphox", Tipo.FUEGO, 255, 100, 30, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		ArrayList<Pokemon> equipoA = new ArrayList<>();
		equipoA.add(pokemonActivoEntrenadorA);

		Entrenador entrenadorA = new Entrenador("Ash", equipoA, pokemonActivoEntrenadorA, Rango.Z);

		Pokemon pokemonActivoEntrenadorB = new Pokemon("Greninja", Tipo.AGUA, 255, 150, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		ArrayList<Pokemon> equipoB = new ArrayList<>();
		equipoB.add(pokemonActivoEntrenadorB);

		Entrenador entrenadorB = new Entrenador("Gary", equipoB, pokemonActivoEntrenadorB, Rango.Z);

		Combate combate = new Combate(entrenadorA, entrenadorB);

		pokemonActivoEntrenadorA.recibirDanno(255);
		pokemonActivoEntrenadorA.comprobarEstado();
		Habilidad habilidad = new Habilidad("FuegoFatuo", Tipo.FUEGO, 10);

		assertThrows(IllegalStateException.class, () -> {
			combate.combateATurnos(habilidad);
		});
	}

	@Test
	void testCombateFinalizadoConTodosDebilitados() {

		Pokemon pokemonActivoEntrenadorA = new Pokemon("Crocalor", Tipo.FUEGO, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		ArrayList<Pokemon> equipoA = new ArrayList<Pokemon>();
		equipoA.add(pokemonActivoEntrenadorA);

		Pokemon pokemonActivoEntrenadorB = new Pokemon("Bulbasaur", Tipo.PLANTA, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		ArrayList<Pokemon> equipoDebilitado = new ArrayList<Pokemon>();
		equipoDebilitado.add(pokemonActivoEntrenadorB);

		Entrenador entrenadorA = new Entrenador("Ash", equipoA, pokemonActivoEntrenadorA, Rango.Z);
		Entrenador entrenadorB = new Entrenador("Gary", equipoDebilitado, pokemonActivoEntrenadorB, Rango.Z);

		Combate combate = new Combate(entrenadorA, entrenadorB);

		pokemonActivoEntrenadorB.recibirDanno(255);
		pokemonActivoEntrenadorB.comprobarEstado();

		Entrenador ganador = combate.determinarGanador();

		assertEquals(entrenadorA, ganador);
	}

	@Test
	void testRealizarTurnoDannioNormal() {

		Pokemon pokemonActivoEntrenadorA = new Pokemon("Charizard", Tipo.FUEGO, 255, 113, 30, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		Entrenador entrenadorA = new Entrenador("Ash", new ArrayList<Pokemon>(), pokemonActivoEntrenadorA, Rango.Z);

		Pokemon pokemonActivoEntrenadorB = new Pokemon("Blastoise", Tipo.AGUA, 255, 255, 3, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		ArrayList<Pokemon> equipoB = new ArrayList<>();
		equipoB.add(pokemonActivoEntrenadorB);
		Entrenador entrenadorB = new Entrenador("Gary", equipoB, pokemonActivoEntrenadorB, Rango.Z);

		Combate combate = new Combate(entrenadorA, entrenadorB);

		Habilidad habilidad = new Habilidad("Llamarada", Tipo.FUEGO, 10);

		combate.combateATurnos(habilidad);

		assertEquals(248, pokemonActivoEntrenadorB.getPuntosVidaActuales());
	}

	@Test
	void testCombateContinua() {

		Pokemon pokemonActivoEntrenadorA = new Pokemon("Charizard", Tipo.FUEGO, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		Pokemon pokemonActivoEntrenadorB = new Pokemon("Blastoise", Tipo.AGUA, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		ArrayList<Pokemon> equipoA = new ArrayList<>();
		equipoA.add(pokemonActivoEntrenadorA);
		ArrayList<Pokemon> equipoB = new ArrayList<>();
		equipoB.add(pokemonActivoEntrenadorB);

		Entrenador entrenadorA = new Entrenador("Ash", equipoA, pokemonActivoEntrenadorA, Rango.Z);
		Entrenador entrenadorB = new Entrenador("Gary", equipoB, pokemonActivoEntrenadorB, Rango.Z);

		Combate combate = new Combate(entrenadorA, entrenadorB);

		assertThrows(IllegalStateException.class, () -> {
			combate.determinarGanador();
		});
	}

	@Test
	void testRealizarTurnoDannioMinimo() {
		Pokemon pokemonActivoEntrenadorA = new Pokemon("Pikachu", Tipo.ELÉCTRICO, 255, 100, 10, Estado.ACTIVO,
				new ArrayList<Habilidad>());
		Entrenador entrenadorA = new Entrenador("Ash", new ArrayList<>(), pokemonActivoEntrenadorA, Rango.Z);

		Pokemon pokemonActivoEntrenadorB = new Pokemon("Onix", Tipo.ROCA, 255, 100, 15, Estado.ACTIVO,
				new ArrayList<Habilidad>());
		ArrayList<Pokemon> equipoB = new ArrayList<>();
		equipoB.add(pokemonActivoEntrenadorB);
		Entrenador entrenadorB = new Entrenador("Gary", equipoB, pokemonActivoEntrenadorB, Rango.Z);

		Combate combate = new Combate(entrenadorA, entrenadorB);

		Habilidad habilidadDebil = new Habilidad("Placaje", Tipo.NORMAL, 5);

		combate.combateATurnos(habilidadDebil);

		assertEquals(99, pokemonActivoEntrenadorB.getPuntosVidaActuales());
	}

	@Test
	void testRealizarTurnoFuerzaDebilitamiento() {
		Pokemon pokemonActivoEntrenadorA = new Pokemon("Mewtwo", Tipo.PSÍQUICO, 255, 200, 10, Estado.ACTIVO,
				new ArrayList<Habilidad>());
		Entrenador entrenadorA = new Entrenador("Ash", new ArrayList<>(), pokemonActivoEntrenadorA, Rango.Z);

		Pokemon pokemonActivoEntrenadorB = new Pokemon("Blastoise", Tipo.AGUA, 255, 5, 2, Estado.ACTIVO,
				new ArrayList<Habilidad>());
		ArrayList<Pokemon> equipoB = new ArrayList<>();
		equipoB.add(pokemonActivoEntrenadorB);
		Entrenador entrenadorB = new Entrenador("Gary", equipoB, pokemonActivoEntrenadorB, Rango.Z);

		Combate combate = new Combate(entrenadorA, entrenadorB);

		Habilidad habilidad = new Habilidad("Psíquico", Tipo.PSÍQUICO, 10);

		combate.combateATurnos(habilidad);

		assertTrue(pokemonActivoEntrenadorB.getPuntosVidaActuales() <= 0);
		assertEquals(Estado.DEBILITADO, pokemonActivoEntrenadorB.getEstado());
	}
}
