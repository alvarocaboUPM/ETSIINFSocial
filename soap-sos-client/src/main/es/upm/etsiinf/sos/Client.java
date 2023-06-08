package main.es.upm.etsiinf.sos;

import java.rmi.RemoteException;

import main.es.upm.etsiinf.sos.ws.*;

public class Client {

	static final int NumTest = 10;
	static ETSIINFSocialStub[] clientes;

	public static void main(String[] args) throws RemoteException {

		clientes = new ETSIINFSocialStub[NumTest];
		for (int i = 0; i < NumTest; i++) {
			clientes[i] = new ETSIINFSocialStub();
			// mantenemos las conexiones
			clientes[i]._getServiceClient().engageModule("addressing");
			clientes[i]._getServiceClient().getOptions().setManageSession(true);
		}

		Thread[] threads = new Thread[NumTest];
		for (int i = 0; i < NumTest; i++) {
			threads[i] = new ClientThread(i);
		}

		//Diccionario que mapea ID con tarea
		Tests.fillCaseMap();

		// Iniciar los hilos secuencialmente (sin login)
		for (int i = 1; i < NumTest; i++) {
			threads[i].run();
		}


	}

	private static class ClientThread extends Thread {
		int id;
		Ops op;

		ClientThread(int id) {
			this.id = id;
			op = new Ops(clientes[id]);
		}

		@Override
		public void run() {
			try {
				op.login("admin", "admin");
				Tests.realizarOperacionesAleatorias(id, op);
				op.logout();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
}