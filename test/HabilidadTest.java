package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import clase.Habilidad;
import clase.Tipo;

class HabilidadTest {

	@Test
	void testComprobandoDatos() {
		Habilidad habilidadNueva = new Habilidad("lanzallamas", Tipo.FUEGO, 100);
		assertNotNull(habilidadNueva);
	}

	@Test
	void testComprobarConstructorHabilidadNoValidaNombreVacio() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Habilidad("", Tipo.FUEGO, 50);
		});
	}

	@Test
	void testComprobarConstructorHabilidadNombreIgualANULL() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Habilidad(null, Tipo.FUEGO, 100);
		});
	}

	@Test
	void testComprobarConstructorHabilidadTipoIgualANULL() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Habilidad("bolaSombra", null, 80);
		});
	}

	@Test
	void testConstructorHabilidadNoValidadPuntosDePotenciaNegativa() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Habilidad("lanzallamas", Tipo.FUEGO, -100);
		});
	}

	@Test
	void testConstructorNoValidoPuntosDePotenciaIgualACero() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Habilidad("ascuas", Tipo.FUEGO, 0);
		});
	}

	@Test
	void testObtenerDatosNombreCorrecto() {
		Habilidad habilidadNueva = new Habilidad("lanzallamas", Tipo.FUEGO, 100);
		assertEquals("lanzallamas", habilidadNueva.getNombre());
	}

	@Test
	void testObtenerDatosTipoCorrecto() {
		Habilidad habilidadNueva = new Habilidad("lanzallamas", Tipo.FUEGO, 100);
		assertEquals(Tipo.FUEGO, habilidadNueva.getTipo());
	}

	@Test
	void testObtenerDatosPotenciaCorrecto() {
		Habilidad habilidadNueva = new Habilidad("lanzallamas", Tipo.FUEGO, 100);
		assertEquals(100, habilidadNueva.getPuntosPotencia());
	}

}
