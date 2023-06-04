package es.upm.etsiinf.sos;
import es.upm.etsiinf.sos.ws.*;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.Response;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Client {
    static Response response;
	static ETSIINFSocialStub[] clientes; 
	static Ops[] operaciones;
	
	static String[] users = {
		"admin",
		"admin",
		"admin",
		"admin",
		"admin",
		"admin",
		"admin",
		"admin",
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
		"admin",
		"admin",
		"admin",
		"admin",
		"admin",
		"admin",
		"admin",
		"admin",
		"admin",
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
	  
	static int NumTest = 1;
	static int RanClk = 0;
	static int RanOp = 0;
	static int nCliente;
	static Random random = new Random();
	static BlockingQueue<String> messageQueue;
	public static void main(String[] args) throws RemoteException {
		
		//String log4jConfPath = "log4j.properties";
		//PropertyConfigurator.configure(log4jConfPath);

		boolean res;
		
		
		clientes = new ETSIINFSocialStub[NumTest];
		operaciones = new Ops[NumTest];
		for (int i = 0; i < NumTest; i++) {
			clientes[i] = new ETSIINFSocialStub();
				// mantenemos las conexiones
			clientes[i]._getServiceClient().engageModule("addressing");
			clientes[i]._getServiceClient().getOptions().setManageSession(true);
		}
	
		
		// 	--------------------------------------
		// |			  Cliente 1				  |
		//  --------------------------------------
		operaciones[0] = new Ops(clientes[0]);
		operaciones[0].login("admin", "admin");
        operaciones[0].addUser("dummyass");
		
		//operaciones[0].login("admin", "admin");
		operaciones[0].login("pabloso", "pablitoclavounclabito2234");
		operaciones[0].login("asg", "ahadfsld");
       
               // ...
	

        // Crear hilos para los clientes
		/* 
        Thread[] threads = new Thread[NumTest];
        for (int i = 0; i < NumTest; i++) {
            threads[i] = new ClientThread(i);
        }

        // Iniciar los hilos
        for (int i = 0; i < NumTest; i++) {
            threads[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (int i = 0; i < NumTest; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		*/
		
	}
	static boolean rootAccess;
	private static void realizarOperacionesAleatorias(int thdID) throws RemoteException {
		boolean logged = true;
		rootAccess = false;
		//RanClk = random.nextInt(NumTest);
		RanOp  = thdID;
		operaciones[RanOp] = new Ops(clientes[RanOp]);
        //while(logged){
			int tmp = random.nextInt(11);
			System.out.println(" 	--------------------------------------");
			System.out.printf("			cliente %d			  \n",thdID);
			System.out.println(" 	--------------------------------------");
			realizarOperacion(1);
			logged = tmp != 0;
		//}
	
    }

 

    private static void realizarOperacion(int tipoOperacion) throws RemoteException {
		String tmpUser, tmpPass;

        switch (tipoOperacion) {
			
			case 0:
				operaciones[RanOp].logout();
                break;
            case 1:
				tmpUser = generarNombre();
				tmpPass = generarContraseña();
				rootAccess = tmpUser.equals("admin") && tmpPass.equals("admin");
				operaciones[RanOp].login("admin", "admin");
                break;
			case 2:{
				String addingUser = generarNombre();
				if(rootAccess){
                operaciones[RanOp].addUser(addingUser);
				break;
				}else{
				
					System.out.println("Accion reservada para el usuario Administrador");
					System.out.print("¿Iniciar Sesion Como Administrador? (yes/no): ");	
					boolean randomDecision = random.nextInt(2) == 1;
					System.out.print(randomDecision ? "yes\n":"no\n");
					
					if(randomDecision){
						System.out.println("Introduce las credenciales del Administrador:\n");
						tmpUser = generarNombre();
						tmpPass = generarContraseña();
						System.out.println("User: " + tmpUser);
						System.out.println("Password: " + tmpPass);
						if(tmpUser.equals("admin") && tmpPass.equals("admin")){ 
							operaciones[RanOp].login(tmpUser, tmpPass);
							operaciones[RanOp].addUser(addingUser);
						}
						else System.out.println("Error al Iniciar sesion como Administrador");
					}
					else{
						System.out.println("Cancelando operacion...");
					}
				}
                
			}
            case 3:
				operaciones[RanOp].removeUser(generarNombre());
                break;
            case 4:
				operaciones[RanOp].changePassword( operaciones[RanOp].contraseña, generarContraseña());
                break;
            case 5:
				operaciones[RanOp].addFriend( generarNombre());
                break;
            case 6:
				operaciones[RanOp].getMyFriends();
                break;
			case 7:
				operaciones[RanOp].publishState(generarEstado());
                break;
			case 8:
				operaciones[RanOp].removeFriend (generarNombre());
                break;
			case 9:
				operaciones[RanOp].getMyStates();
                break;
			case 10:
				operaciones[RanOp].getMyFriendStates(operaciones[RanOp].usuario);
                break;
			default: System.out.println("operacion Invalida");
				break;

        }
    }

    private static String generarNombre() {
        return users[random.nextInt(users.length)];
    }

    private static String generarContraseña() {
        return pass [random.nextInt(pass.length)];
    }
	private static String generarEstado() {
        return states[random.nextInt(states.length)];
    }

	private static class ClientThread extends Thread {
		int id;
		ClientThread(int id){ this.id = id;}
		@Override
        public void run() {
			try {
				realizarOperacionesAleatorias(id);
			} catch (RemoteException e) {
				e.printStackTrace();
			} 
 		}
    }
}
	


//cp $AXIS2_HOME/repository/modules/addressing-1.6.2.mar. /addressing.jar