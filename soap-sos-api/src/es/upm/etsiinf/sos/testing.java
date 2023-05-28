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
        aux.setPwd("pablitoclavounclabito2234");
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
        prueba.setUsername("Jaime");
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

        //skeleton.changePassword(pwd);
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");
        
        
        /*
         * PRUEBAS addFriend
         */
        AddFriend friend = new AddFriend();
        Username user_friend = new Username();
        user_friend.setUsername("Jaime");
        friend.setArgs0(user_friend);

        AddFriendResponse res_friend = new AddFriendResponse();
        res_friend = skeleton.addFriend(friend);
        System.out.println(res_friend.get_return().getResponse());

        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");

        /*
         * PRUEBAS removeFriend
         */

        RemoveFriend removeF = new RemoveFriend();
        RemoveFriendResponse responseFriend = new RemoveFriendResponse();
        Username userRemoveF = new Username();
        userRemoveF.setUsername("Julito");
        removeF.setArgs0(userRemoveF);
        responseFriend = skeleton.removeFriend(removeF);
        System.out.println(responseFriend.get_return().getResponse());
    }
    


        
        
}
