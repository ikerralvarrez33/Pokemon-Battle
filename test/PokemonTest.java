package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import clase.Estado;
import clase.Habilidad;
import clase.Pokemon;
import clase.Tipo;

class PokemonTest {

	@Test
	void testConstructorPokemonNoValido() {
		Pokemon nuevoPokemon = new Pokemon("Charizard", Tipo.FUEGO, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		assertNotNull(nuevoPokemon);

	}

	@Test
	void testConstructorPokemonNoValidoNombreVacio() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon("", Tipo.FUEGO, 100, 100, 50, Estado.ACTIVO, new ArrayList<Habilidad>());
		});

	}

	@Test
	void testConstructorPokemonNoValidoNombreIgualNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon(null, Tipo.FUEGO, 100, 100, 50, Estado.ACTIVO, new ArrayList<Habilidad>());
		});
	}

	@Test
	void testConstructorNoValidoTipoIgualNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon("Charizard", null, 100, 100, 50, Estado.ACTIVO, new ArrayList<Habilidad>());
		});
	}

	@Test
	void testValidoVidaMaximaDentroValores() {
		Pokemon nuevoPokemon = new Pokemon("Charizard", Tipo.FUEGO, 255, 100, 50, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		assertEquals(255, nuevoPokemon.getPuntosVidaMaximos());

	}

	@Test
	void testNoValidoVidaMaximaFueraValores() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon("Charizard", Tipo.FUEGO, 256, 100, 50, Estado.ACTIVO, new ArrayList<Habilidad>());
		});
	}

	@Test
	void testNoValidoVidaMaximaNegativa() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon("Charizard", Tipo.FUEGO, -10, 100, 50, Estado.ACTIVO, new ArrayList<Habilidad>());
		});
	}

	@Test
	void testVidaActualValida() {
		Pokemon nuevoPokemon = new Pokemon("Charizard", Tipo.FUEGO, 255, 5, 5, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		assertEquals(5, nuevoPokemon.getPuntosVidaActuales());

	}

	@Test
	void testNoValidoVidaActualMenorACero() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon("Charizard", Tipo.FUEGO, 255, -10, 50, Estado.ACTIVO, new ArrayList<Habilidad>());
		});
	}

	@Test
	void testNoValidoVidaActualSuperiorALaMaxima() {

		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon("Charizard", Tipo.FUEGO, 255, 300, 50, Estado.ACTIVO, new ArrayList<Habilidad>());
		});

	}

	@Test
	void testPuntosDefensaIgualACero() {
		Pokemon nuevoPokemon = new Pokemon("Charizard", Tipo.FUEGO, 255, 5, 0, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		assertEquals(0, nuevoPokemon.getDefensa());
	}

	@Test
	void testNoValidoPuntosDefensaNegativo() {

		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon("Charizard", Tipo.FUEGO, 255, 100, -50, Estado.ACTIVO, new ArrayList<Habilidad>());
		});

	}

	@Test
	void testNoValidoEstadoIgualNull() {

		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon("Charizard", Tipo.FUEGO, 255, 200, 50, null, new ArrayList<Habilidad>());
		});

	}

	@Test
	void testNoValidoMasCuatroHabilidades() {

		assertThrows(IllegalArgumentException.class, () -> {
			Pokemon nuevoPokemon = new Pokemon("Charizard", Tipo.FUEGO, 255, 100, 10, Estado.ACTIVO,
					new ArrayList<>(Arrays.asList(new Habilidad("1", Tipo.AGUA, 1), new Habilidad("2", Tipo.AGUA, 1),
							new Habilidad("3", Tipo.AGUA, 1), new Habilidad("4", Tipo.AGUA, 1),
							new Habilidad("5", Tipo.AGUA, 1))));
		});

	}

	@Test
	void testNoValidoListaHabildadesIgualANull() {

	}

	@Test
	void testReducirVida() {
		Pokemon nuevoPokemon = new Pokemon("Charizard", Tipo.FUEGO, 100, 100, 10, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		nuevoPokemon.recibirDanno(50);
		assertEquals(50, nuevoPokemon.getPuntosVidaActuales());
	}

	@Test
	void testRecibirDannoNoValido() {
		Pokemon nuevoPokemon = new Pokemon("Charizard", Tipo.FUEGO, 100, 100, 10, Estado.ACTIVO,
				new ArrayList<Habilidad>());
		assertThrows(IllegalArgumentException.class, () -> {
			nuevoPokemon.recibirDanno(0);
		});

	}

	@Test
	void testValidoCurarse() {

		Pokemon nuevoPokemon = new Pokemon("Charizard", Tipo.FUEGO, 100, 40, 10, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		nuevoPokemon.curarse(50);

		assertEquals(90, nuevoPokemon.getPuntosVidaActuales());
	}

	@Test
	void testCurarseNoValido() {

		Pokemon nuevoPokemon = new Pokemon("Charizard", Tipo.FUEGO, 100, 40, 10, Estado.ACTIVO,
				new ArrayList<Habilidad>());
		assertThrows(IllegalArgumentException.class, () -> {
			nuevoPokemon.curarse(0);
		});

	}

	@Test
	void testComprobarEstado() {
		Pokemon nuevoPokemon = new Pokemon("Charizard", Tipo.FUEGO, 100, 40, 10, Estado.ACTIVO,
				new ArrayList<Habilidad>());

		assertTrue(nuevoPokemon.comprobarEstado());

	}

	@Test
	void testComprobarEstadoConUnPokemonDebilitado() {

		Pokemon nuevoPokemon = new Pokemon("Charizard", Tipo.FUEGO, 100, 0, 10, Estado.DEBILITADO,
				new ArrayList<Habilidad>());

		assertFalse(nuevoPokemon.comprobarEstado());

	}

	@Test
	void testReiniciarEstado() {

		Pokemon nuevoPokemon = new Pokemon("Charizard", Tipo.FUEGO, 100, 40, 10, Estado.DEBILITADO,
				new ArrayList<Habilidad>());

		nuevoPokemon.reiniciarEstado();

		assertEquals(Estado.ACTIVO, nuevoPokemon.getEstado());

		assertTrue(nuevoPokemon.getPuntosVidaActuales() > 0);

	}

}
