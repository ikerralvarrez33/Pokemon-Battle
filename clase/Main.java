package clase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);

		System.out.println("==== POKEMON BATTLE ====");

		System.out.println("Introduzca el nombre del entrenador A :");
		String nombreEntrenadorA = teclado.nextLine();

		System.out.println("Introduzca el nombre del entrenador B:");
		String nombreEntrenadorB = teclado.nextLine();

		List<Pokemon> equipoEntrenadorA = new ArrayList<Pokemon>();
		List<Pokemon> equipoEntrenadorB = new ArrayList<Pokemon>();

		Pokemon pokemonCreadoA = crearPokemon(teclado);
		equipoEntrenadorA.add(pokemonCreadoA);

		Pokemon pokemonCreadoB = crearPokemon(teclado);
		equipoEntrenadorB.add(pokemonCreadoB);

		Entrenador entrenadorA = new Entrenador(nombreEntrenadorA, equipoEntrenadorA, pokemonCreadoA, Rango.Z);
		Entrenador entrenadorB = new Entrenador(nombreEntrenadorB, equipoEntrenadorB, pokemonCreadoB, Rango.Z);

		System.out.println("======================");
		System.out.println("¡Dato! : Los puntos de vida siempre empiezan en 255.");
		System.out.println("Los de defensa y potencia son valores aleatorios entre 1 y 100 o 1 y 50 respectivamente.");
		System.out.println("======================");

		int opcion;
		do {

			System.out.println();
			System.out.println("Bienvenido!");
			System.out.println("====MENU====");
			System.out.println("0. Salir");
			System.out.println("1. Informacion de los entrenadores");
			System.out.println("2. Jugar un combate");
			System.out.println("3. Curar a pokemons debilitados");
			System.out.println("4. Añadir a más pokémons");
			System.out.println("5. Modificar movimientos de  los pokemons de tu equipo.");
			opcion = teclado.nextInt();

			switch (opcion) {
			case 0:
				System.out.println("¡Gracias por jugar!");
				System.out.println("Saliendo.....");
				break;
			case 1:
				System.out.println("Información del entrenador A: ");
				imprimirDatosEntrenadores(entrenadorA);
				System.out.println("Información del entrenador B: ");
				imprimirDatosEntrenadores(entrenadorB);
				break;
			case 2:
				System.out.println("----------------------");
				System.out.println("Ambos al ser nuevos empezais en el rango Z. ");
				System.out.println("Buena suerte!");
				System.out.println("----------------------");
				System.out.println();

				Combate combate = new Combate(entrenadorA, entrenadorB);
				do {

					Entrenador atacante = combate.getTurnoActual();
					Pokemon pokemonAtacante = atacante.getPokemonActivo();

					System.out.println("Turno de " + atacante.getNombre() + " con " + pokemonAtacante.getNombre());
					System.out.println("Elige : ");
					System.out.println("1. Atacar ");
					System.out.println("2. Cambiar pokemon");

					int accionEntrenador = teclado.nextInt();

					teclado.nextLine();

					if (accionEntrenador == 2) {

						System.out.println("Elige un pokemon activo:");
						List<Pokemon> equipo = atacante.getEquipoPokemon();
						int i = 1;
						for (Pokemon p : equipo) {
							if (p.getEstado() != Estado.DEBILITADO) {
								System.out.println(i + ". " + p.getNombre());
							}
							i++;
						}
						int eleccion = teclado.nextInt();
						teclado.nextLine();
						atacante.cambiarPokemon(equipo.get(eleccion - 1));
						System.out.println("Pokemon cambiado a " + atacante.getPokemonActivo().getNombre());
						combate.alternarTurno();

					} else {

						System.out.println("Elige algún movimiento :");

						List<Habilidad> listaMovimientosCombate = pokemonAtacante.getHabilidades();
						int i = 1;

						for (Habilidad habilidad : listaMovimientosCombate) {
							System.out.println(i + " " + habilidad.getNombre());
							i++;

						}

						int movimientoElegido = teclado.nextInt();

						Habilidad habilidadElegida = listaMovimientosCombate.get(movimientoElegido - 1);

						combate.combateATurnos(habilidadElegida);
						Entrenador defensor;
						if (atacante == entrenadorA) {
							defensor = entrenadorB;
						} else {
							defensor = entrenadorA;

						}
						comprobarDebilitado(teclado, defensor);
					}
				} while (!combate.determinarFinCombate());

				Entrenador ganador = combate.determinarGanador();

				System.out.println("¡Fin del combate!");
				System.out.println("Ganador: " + ganador.getNombre());
				ganador.subirRango();

				break;
			case 3:

				teclado.nextLine();

				System.out.println("Introduzca el nombre del entrenador que quieres curar todos los pokémons");
				String nombre = teclado.nextLine();

				if (nombre.equalsIgnoreCase(entrenadorA.getNombre())) {
					entrenadorA.curarPokemons();
					System.out
							.println("Pokemons del entrenador " + entrenadorA.getNombre() + " curados correctamente!");
				}

				if (nombre.equalsIgnoreCase(entrenadorB.getNombre())) {
					entrenadorB.curarPokemons();
					System.out
							.println("Pokemons del entrenador " + entrenadorB.getNombre() + " curados correctamente!");
				}

				break;
			case 4:

				int opcionAnnadirPokemon;
				do {
					System.out.println("¿Quién quiere añadir pokemons a su equipo?");
					System.out.println("0. Volver al menú");
					System.out.println("1. " + entrenadorA.getNombre());
					System.out.println("2. " + entrenadorB.getNombre());

					opcionAnnadirPokemon = teclado.nextInt();
					teclado.nextLine();

					switch (opcionAnnadirPokemon) {
					case 0:
						System.out.println("Volviendo al menú.");
						break;

					case 1:
						int continuarA;
						do {
							if (entrenadorA.getEquipoPokemon().size() >= 6) {
								System.out.println("¡Equipo completo!");
								break;
							}

							Pokemon pokemonCreado = crearPokemon(teclado);
							entrenadorA.getEquipoPokemon().add(pokemonCreado);

							System.out.println("Pokémon añadido correctamente.");
							System.out.println("1. Añadir otro Pokémon");
							System.out.println("0. Volver al menú");

							continuarA = teclado.nextInt();
							teclado.nextLine();
						} while (continuarA != 0);
						break;

					case 2:
						int continuarB;
						do {
							if (entrenadorB.getEquipoPokemon().size() >= 6) {
								System.out.println("¡Equipo completo!");
								break;
							}

							Pokemon pokemonCreado = crearPokemon(teclado);
							entrenadorB.getEquipoPokemon().add(pokemonCreado);

							System.out.println("Pokémon añadido correctamente.");
							System.out.println("1. Añadir otro Pokémon");
							System.out.println("0. Volver al menú");

							continuarB = teclado.nextInt();
							teclado.nextLine();
						} while (continuarB != 0);
						break;

					default:
						System.out.println("Error en la opción.");
						break;
					}
				} while (opcionAnnadirPokemon != 0);
				break;

			case 5:

				int opcionPokemon;
				do {

					System.out.println("¿Quién quiere modificar o cambiar movimientos de los pokemons de su equipo?");
					System.out.println("0. Volver al menú");
					System.out.println("1. " + entrenadorA.getNombre());
					System.out.println("2. " + entrenadorB.getNombre());

					opcionPokemon = teclado.nextInt();
					teclado.nextLine();

					switch (opcionPokemon) {
					case 0:
						System.out.println("Volviendo.....");
						break;
					case 1:

						ModificarMovimientosPokemons(teclado, entrenadorA);
						break;

					case 2:

						ModificarMovimientosPokemons(teclado, entrenadorB);

						break;

					default:
						System.out.println("Error en la opción");
						break;
					}

				} while (opcionPokemon != 0);
				break;

			default:
				System.out.println("Error en la opción. Inténtelo de nuevo.");
				break;
			}

		} while (opcion != 0);

	}

	public static void imprimirDatosEntrenadores(Entrenador entrenador) {

		System.out.println("Nombre " + entrenador.getNombre());
		System.out.println("Rango " + entrenador.getRango());
		System.out.println("Pokemon activo " + entrenador.getPokemonActivo());

		System.out.println("Equipo ");
		for (Pokemon pokemon : entrenador.getEquipoPokemon()) {
			System.out.println("- " + pokemon.getNombre());
		}
	}

	public static void comprobarDebilitado(Scanner Teclado, Entrenador defensor) {
		defensor.getPokemonActivo().comprobarEstado();

		if (defensor.getPokemonActivo().getEstado() == Estado.DEBILITADO
				&& defensor.saberPokemonActivosParaCombatir()) {
			System.out.println("¡El pokemon de " + defensor.getNombre() + " ha sido debilitado!!!");
			System.out.println("Elige un nuevo pokemon:");
			List<Pokemon> equipo = defensor.getEquipoPokemon();

			List<Pokemon> disponibles = new ArrayList<>();
			int i = 1;
			for (Pokemon pokemon : equipo) {
				if (pokemon.getEstado() == Estado.ACTIVO) {
					System.out.println(i + ". " + pokemon.getNombre());
					disponibles.add(pokemon);
					i++;
				}
			}

			int eleccion = Teclado.nextInt();
			Teclado.nextLine();

			defensor.cambiarPokemon(disponibles.get(eleccion - 1));
			System.out.println(defensor.getNombre() + " ha sacado a " + defensor.getPokemonActivo().getNombre());
		}
	}

	public static void ModificarMovimientosPokemons(Scanner Teclado, Entrenador entrenador) {

		int opcionModificar;

		do {

			System.out.println("¿Qué pokemon quieres añadir/modificar movimientos?");
			System.out.println("Pulsa del 1 al 6.");
			System.out.println("0. Nadie, salir al menu.");
			int i = 1;
			for (Pokemon pokemon : entrenador.getEquipoPokemon()) {
				System.out.println(i + " " + pokemon.getNombre());
				i++;
			}
			opcionModificar = Teclado.nextInt();
			if (opcionModificar == 0) {
				System.out.println("Volviendo...");
			} else if (opcionModificar < 1 || opcionModificar > entrenador.getEquipoPokemon().size()) {
				System.out.println("La opción no es válida.");
				System.out.println("No hay pokemons en esa posición.");
			} else {
				Pokemon pokemonSeleccionado = entrenador.getEquipoPokemon().get(opcionModificar - 1);
				modificarStatsPokemon(Teclado, pokemonSeleccionado);

			}

		} while (opcionModificar != 0);

	}

	public static void modificarStatsPokemon(Scanner Teclado, Pokemon pokemon) {

		int opcionMovimientoPokemon;

		do {

			System.out.println("Pokemon: " + pokemon.getNombre());
			int i = 1;
			for (Habilidad habilidad : pokemon.getHabilidades()) {
				System.out.println(i + " " + habilidad.getNombre());
				i++;

			}
			System.out.println();
			System.out.println("===============");
			System.out.println("0. Volver al menú.");
			System.out.println("1. Añadir movimientos.");
			System.out.println("2. Eliminar movimientos.");

			opcionMovimientoPokemon = Teclado.nextInt();
			Teclado.nextLine();

			switch (opcionMovimientoPokemon) {
			case 0:
				System.out.println("Saliendo....");
				break;
			case 1:

				if (pokemon.getHabilidades().size() >= 4) {
					System.out.println("No puedes tener más de 4 movimientos por pokemon.");
				} else {
					Habilidad nuevaHabilidad = crearHabilidad(Teclado);
					pokemon.getHabilidades().add(nuevaHabilidad);
					System.out.println("Movimiento creado correctamente!");
				}

				break;
			case 2:

				System.out.println("¿Qué movimiento quieres eliminar?");
				int posicionMovimiento = Teclado.nextInt();
				Teclado.nextLine();

				pokemon.getHabilidades().remove(posicionMovimiento - 1);
				System.out.println("Movimiento eliminado correctamente");

				break;
			default:
				System.out.println("Error en la opción");
				break;
			}

		} while (opcionMovimientoPokemon != 0);

	}

	public static Habilidad crearHabilidad(Scanner Teclado) {
		System.out.println("Movimiento principal del pokemon activo del entrenador:");
		String movimientoNombre = Teclado.nextLine();

		Tipo tipoPokemon = null;
		boolean tipoCorrecto = false;

		while (!tipoCorrecto) {

			System.out.println("¿Tipo del movimiento?");
			String tipoMovimiento = Teclado.nextLine();

			tipoMovimiento = tipoMovimiento.toUpperCase();

			try {

				tipoPokemon = Tipo.valueOf(tipoMovimiento);

				System.out.println("Tipo correcto : " + tipoPokemon);

				tipoCorrecto = true;

			} catch (IllegalArgumentException e) {

				System.out.println("Tipo no válido, elige entre estos:");

				for (Tipo tipo : Tipo.values()) {

					System.out.print(tipo + "\t");
				}

				System.out.println();
			}
		}

		int puntosPotencia = (int) (Math.random() * 100) + 1;

		Habilidad habilidadPokemonCreado = new Habilidad(movimientoNombre, tipoPokemon, puntosPotencia);

		List<Habilidad> listaMovimientos = new ArrayList<Habilidad>();
		listaMovimientos.add(habilidadPokemonCreado);
		return habilidadPokemonCreado;

	}

	public static Pokemon crearPokemon(Scanner Teclado) {
		System.out.println("Movimiento principal del pokemon activo del entrenador:");
		String movimientoNombre = Teclado.nextLine();

		Tipo tipoPokemon = null;
		boolean tipoCorrecto = false;

		while (!tipoCorrecto) {

			System.out.println("¿Tipo del movimiento?");
			String tipoMovimiento = Teclado.nextLine();

			tipoMovimiento = tipoMovimiento.toUpperCase();

			try {

				tipoPokemon = Tipo.valueOf(tipoMovimiento);

				System.out.println("Tipo correcto : " + tipoPokemon);

				tipoCorrecto = true;

			} catch (IllegalArgumentException e) {

				System.out.println("Tipo no válido, elige entre estos:");

				for (Tipo tipo : Tipo.values()) {

					System.out.print(tipo + "\t");
				}

				System.out.println();
			}
		}

		Habilidad habilidadPokemonCreado = new Habilidad(movimientoNombre, tipoPokemon, 50);

		List<Habilidad> listaMovimientos = new ArrayList<Habilidad>();
		listaMovimientos.add(habilidadPokemonCreado);

		System.out.println("Nombre del pokemon del entrenador:");
		String pokemonNombre = Teclado.nextLine();

		Tipo tipoPokemonUnico1 = null;
		boolean tipoPokemonCorrecto = false;

		while (!tipoPokemonCorrecto) {
			System.out.println("¿Tipo del Pokemon? ");
			String tipoPokemonUnico = Teclado.nextLine();

			tipoPokemonUnico = tipoPokemonUnico.toUpperCase();

			try {
				tipoPokemonUnico1 = Tipo.valueOf(tipoPokemonUnico);
				System.out.println("Tipo correcto: " + tipoPokemonUnico1);
				tipoPokemonCorrecto = true;
			} catch (IllegalArgumentException e) {
				System.out.println("Tipo no válido, elige entre estos:");
				for (Tipo tipo : Tipo.values()) {
					System.out.print(tipo + "\t");
				}
				System.out.println();
			}
		}
		int puntosDefensa = (int) (Math.random() * 50) + 1;

		Pokemon pokemonCreado = new Pokemon(pokemonNombre, tipoPokemonUnico1, 255, 255, puntosDefensa, Estado.ACTIVO,
				listaMovimientos);

		return pokemonCreado;

	}
}
