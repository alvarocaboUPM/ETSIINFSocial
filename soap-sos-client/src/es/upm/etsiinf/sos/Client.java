package es.upm.etsiinf.sos;

import es.upm.etsiinf.sos.ETSIINFSocialStub.*;
import java.rmi.RemoteException;
import org.apache.axis2.AxisFault;

public class Client {
    static ETSIINFSocialStub stub;

    public static void main(String[] args) throws RemoteException {
        stub = new ETSIINFSocialStub();
        stub._getServiceClient().engageModule("addressing");
        stub._getServiceClient().getOptions().setManageSession(true);

        // Create admin user
        User admin = new User();
        admin.setName("admin");
        admin.setPwd("admin");
        Login mo = new Login();
        mo.setArgs0(admin);
        System.out.println("null");

        try {
			LoginResponse r = stub.login(mo);
			// System.out.println("SALIDA LOGIN ADMIN EN CLIENTE: " +
			// r.get_return().getResponse());
			System.out.println(r.get_return().getResponse());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(false);
		}
        //System.out.println("First login: " + stub.login(admin));
    }
}
//cp $AXIS2_HOME/repository/modules/addressing-1.6.2.mar. /addressing.jar