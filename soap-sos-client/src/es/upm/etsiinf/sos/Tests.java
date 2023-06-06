package es.upm.etsiinf.sos;

import java.rmi.RemoteException;
import java.util.Random;

public class Tests {
    static boolean rootAccess;
    static Random random = new Random();

    static String[] users = {
            "admin",
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
            "admin",
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

    public static void realizarOperacionesAleatorias(int thdID) throws RemoteException {
        rootAccess = false;
        Client.operaciones[thdID] = new Ops(Client.clientes[thdID]);
        System.out.println(" 	--------------------------------------");
        System.out.printf("			cliente %d			  \n", thdID);
        System.out.println(" 	--------------------------------------");
        realizarOperacion(thdID + 1);
    }

    private static void realizarOperacion(int tipoOperacion) throws RemoteException {
		String tmpUser, tmpPass;

		switch (tipoOperacion) {

			case 0:
				System.out.println("Loging out");
				Client.operaciones[tipoOperacion].logout();
				break;
			case 1:
				System.out.println("Loging in");
				tmpUser = generarNombre();
				tmpPass = generarContraseña();
				rootAccess = tmpUser.equals("admin") && tmpPass.equals("admin");
				Client.operaciones[tipoOperacion].login("admin", "admin");
				break;
			case 2: {
				System.out.println("Adding user");
				String addingUser = generarNombre();
				if (rootAccess) {
					Client.operaciones[tipoOperacion].addUser(addingUser);
					break;
				} else {

					System.out.println("Accion reservada para el usuario Administrador");
					System.out.print("¿Iniciar Sesion Como Administrador? (yes/no): ");
					boolean randomDecision = random.nextInt(2) == 1;
					System.out.print(randomDecision ? "yes\n" : "no\n");

					if (randomDecision) {
						System.out.println("Introduce las credenciales del Administrador:\n");
						tmpUser = generarNombre();
						tmpPass = generarContraseña();
						System.out.println("User: " + tmpUser);
						System.out.println("Password: " + tmpPass);
						if (tmpUser.equals("admin") && tmpPass.equals("admin")) {
							Client.operaciones[tipoOperacion].login(tmpUser, tmpPass);
							Client.operaciones[tipoOperacion].addUser(addingUser);
						} else
							System.out.println("Error al Iniciar sesion como Administrador");
					} else {
						System.out.println("Cancelando operacion...");
					}
				}

			}
			case 3:
				System.out.println("Removing user");
				Client.operaciones[tipoOperacion].removeUser(generarNombre());
				break;
			case 4:
				System.out.println("Chaning user password");

				Client.operaciones[tipoOperacion].changePassword(Client.operaciones[tipoOperacion].contraseña, generarContraseña());
				break;
			case 5:
				System.out.println("Adding a friend");
				Client.operaciones[tipoOperacion].addFriend(generarNombre());
				break;
			case 6:
				System.out.println("Getting friends list");
				Client.operaciones[tipoOperacion].getMyFriends();
				break;
			case 7:
				System.out.println("Removing a friend state");
				Client.operaciones[tipoOperacion].removeFriend(generarNombre());
				break;
			case 8:
				System.out.println("Publishing state");
				Client.operaciones[tipoOperacion].publishState(generarEstado());
				break;
			case 9:
				System.out.println("Getting states list");
				Client.operaciones[tipoOperacion].getMyStates();
				break;
			case 10:
				System.out.println("Getting my friends states list");
				Client.operaciones[tipoOperacion].getMyFriendStates(Client.operaciones[tipoOperacion].usuario);
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
}
