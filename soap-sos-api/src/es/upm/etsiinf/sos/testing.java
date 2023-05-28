package es.upm.etsiinf.sos;

import java.rmi.RemoteException;

import es.upm.etsiinf.sos.model.xsd.PasswordPair;
import es.upm.etsiinf.sos.model.xsd.User;
import es.upm.etsiinf.sos.model.xsd.Username;

public class testing {

    public static void main(String [] args) throws RemoteException {

        ETSIINFSocialSkeleton skeleton = new ETSIINFSocialSkeleton();

        /*
         * PRUEBAS login
         */
        Login prueba_login = new Login();
        User aux = new User();
        aux.setName("pabloso");
        aux.setPwd("pablitoclavounclabito22");
        prueba_login.setArgs0(aux);
        System.out.println("\n");
        skeleton.login(prueba_login).get_return().getResponse();
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");

        /*
         * PRUEBAS addUser
         */
        es.upm.etsiinf.sos.AddUser user1 = new AddUser();
        Username prueba = new Username();
        prueba.setUsername("pabloso");
        user1.setArgs0(prueba);
        System.out.println("\n");
        skeleton.addUser(user1);
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");
        
        /*
         * PRUEBAS logout
         */
        es.upm.etsiinf.sos.Logout logout = new Logout();
        System.out.println("\n");
       //skeleton.logout(logout);
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");


        /*
         * PRUEBAS removeUser
         */
        RemoveUser remove = new RemoveUser();
        remove.setArgs0(prueba);
        System.out.println("\n");
        //skeleton.removeUser(remove);
        //aux2.setPwd("pruebita3230");
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");
        
        
        /*
         * PRUEBAS changePassword
         */
        es.upm.etsiinf.sos.ChangePassword pwd = new es.upm.etsiinf.sos.ChangePassword();
        PasswordPair pwd_pair = new PasswordPair();
        pwd_pair.setNewpwd("pablitoclavounclabito2234");
        pwd_pair.setOldpwd("pablitoclavounclabito22");
        pwd.setArgs0(pwd_pair);

        skeleton.changePassword(pwd);
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");
    }
    


        
        
}
