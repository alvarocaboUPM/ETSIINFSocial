package main.es.upm.etsiinf.sos;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Random;

public class Tests {
	static boolean rootAccess = false;
	static Random random = new Random();
	static HashMap<Integer, String> caseMap = new HashMap<>();
	
	static String[] users = {
			"Alejandro",
			"Sofía",
			"Javier",
			"María",
			"Daniel",
			"Laura",
			"Adrián",
			"Lucía",
			"Pablo",
			"Andrea",
			"Carlos",
			"Carmen",
			"Diego",
			"Natalia",
			"Fernando",
			"Elena",
			"Rubén",
			"Marta",
			"Sergio"
	};

	static String[] pass = {
			"Contraseña1",
			"Pass123",
			"SecurePwd",
			"Secret@123",
			"P@ssw0rd",
			"MyPassword",
			"StrongPwd",
			"12345678",
			"Qwerty123",
			"Welcome1",
			"Pa$$w0rd",
			"Password123!",
			"SecurePass",
			"123456",
			"Abcd1234",
			"Passw0rd",
			"Password!",
			"SecretPassword",
			"Admin123"
	};

	static String[] states = {
			"¡Feliz de estar aquí!",
			"Disfrutando de un día soleado.",
			"Deseando que llegue el fin de semana.",
			"Emocionado por el concierto de esta noche.",
			"Compartiendo una foto de mis vacaciones.",
			"Reflexionando sobre la vida.",
			"Probando una nueva receta.",
			"Viendo mi serie favorita.",
			"Celebrando un hito personal.",
			"De vuelta al trabajo después de las vacaciones.",
			"Apoyando a mi equipo en el partido de hoy.",
			"Compartiendo una nueva canción que me encanta.",
			"Planeando una escapada de fin de semana.",
			"Haciendo ejercicio en el gimnasio.",
			"Conectando con viejos amigos.",
			"Explorando nuevos lugares en la ciudad.",
			"Compartiendo una cita inspiradora.",
			"Leyendo un libro fascinante.",
			"Disfrutando de una tarde relajante en casa.",
			"Agradecido por todo lo bueno en mi vida."
	};

	public static void realizarOperacionesAleatorias(int thdID, Ops op) throws RemoteException {
		rootAccess = false;
		System.out.println("\n---------------------------------------------------");
		System.out.printf("\t running: '%s'\n", caseMap.get(thdID));
		System.out.println("---------------------------------------------------");
		realizarOperacion(thdID, op);
	}

	private static void realizarOperacion(int tipoOperacion, Ops op) throws RemoteException {
		String tmpUser, tmpPass;

		switch (tipoOperacion) {
			case 0:
				tmpUser = generarNombre();
				tmpPass = generarContraseña();
				rootAccess = tmpUser.equals("admin") && tmpPass.equals("admin");
				rootAccess = true;
				op.login("admin", "admin");
				break;
			case 1:
				String addingUser = generarNombre();
				if (rootAccess) {
					op.addUser(addingUser);
					break;
				}

				// System.out.println("Accion reservada para el usuario Administrador");
				// System.out.print("¿Iniciar Sesion Como Administrador? (yes/no): ");

				// boolean randomDecision = random.nextInt(2) == 1;
				// System.out.print(randomDecision ? "yes\n" : "no\n");
				// if (!randomDecision) {
				// 	System.out.println("Cancelando operacion...");
				// 	break;
				// }

				// System.out.println("Introduce las credenciales del Administrador:\n");
				// tmpUser = generarNombre();
				// tmpPass = generarContraseña();
				// System.out.println("User: " + tmpUser);
				// System.out.println("Password: " + tmpPass);

				// if (tmpUser.equals("admin") && tmpPass.equals("admin")) {
				// 	
				// 	
				// } else
				// 	System.out.println("Error al Iniciar sesion como Administrador");
				op.addUser(addingUser);
				break;
			case 2:
				String username = generarNombre();

				op.removeUser(username);
				break;
			case 3:


				op.changePassword(op.contraseña,
						generarContraseña());
				break;
			case 4:

				op.addFriend(generarNombre());
				break;
			case 5:

				op.getMyFriends();
				break;
			case 6:

				op.removeFriend(generarNombre());
				break;
			case 7:

				op.publishState(generarEstado());
				break;
			case 8:

				op.getMyStates();
				break;
			case 9:

				op.getMyFriendStates(op.usuario);
				break;
			case 10:

				op.logout();
				break;
			default:
				System.out.println("operacion Invalida");
				break;

		}
	}

	private static String generarNombre() {
		return users[random.nextInt(users.length)];
	}

	private static String generarContraseña() {
		return pass[random.nextInt(pass.length)];
	}

	private static String generarEstado() {
		return states[random.nextInt(states.length)];
	}

	public static void fillCaseMap(){
		caseMap = new HashMap<Integer, String>();

		caseMap.put(0, "Loging in");
        caseMap.put(1, "Adding user");
        caseMap.put(2, "Removing user");
        caseMap.put(3, "Chaning user password");
        caseMap.put(4, "Adding a friend");
        caseMap.put(5, "Getting friends list");
        caseMap.put(6, "Removing a friend state");
        caseMap.put(7, "Publishing state");
        caseMap.put(8, "Getting states list");
        caseMap.put(9, "Getting my friends states list");
        caseMap.put(10, "Loging out");
		
	}
}
