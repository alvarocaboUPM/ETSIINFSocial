package es.upm.etsiinf.sos;

import es.upm.etsiinf.sos.ws.*;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.Response;
import java.rmi.RemoteException;
import java.util.concurrent.BlockingQueue;

public class Client {
	static Response response;
	static ETSIINFSocialStub[] clientes;
	static Ops[] operaciones;

	static int NumTest = 10;
	static int RanClk = 0;
	static int RanOp = 0;
	static int nCliente;
	
	static BlockingQueue<String> messageQueue;

	public static void main(String[] args) throws RemoteException {

		clientes = new ETSIINFSocialStub[NumTest];
		operaciones = new Ops[NumTest];
		for (int i = 0; i < NumTest; i++) {
			clientes[i] = new ETSIINFSocialStub();
			// mantenemos las conexiones
			clientes[i]._getServiceClient().engageModule("addressing");
			clientes[i]._getServiceClient().getOptions().setManageSession(true);
			
		}

		// --------------------------------------
		// | Cliente 1 |
		// --------------------------------------
		operaciones[0] = new Ops(clientes[0]);
		operaciones[0].login("admin", "admin");
		
		operaciones[0].addUser("dummyass");

	}

	
	// private static class ClientThread extends Thread {
	// 	int id;

	// 	ClientThread(int id) {
	// 		this.id = id;
	// 	}

	// 	@Override
	// 	public void run() {
	// 		try {
	// 			realizarOperacionesAleatorias(id);
	// 		} catch (RemoteException e) {
	// 			e.printStackTrace();
	// 		}
	// 	}
	// }
}