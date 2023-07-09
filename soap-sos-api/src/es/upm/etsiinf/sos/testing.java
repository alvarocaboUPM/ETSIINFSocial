package es.upm.etsiinf.sos;

import java.rmi.RemoteException;

import es.upm.etsiinf.sos.model.xsd.PasswordPair;
import es.upm.etsiinf.sos.model.xsd.State;
import es.upm.etsiinf.sos.model.xsd.User;
import es.upm.etsiinf.sos.model.xsd.Username;

public class testing {

    public static void main(String [] args) throws RemoteException {

        ETSIINFSocialSkeleton skeleton = new ETSIINFSocialSkeleton();
        ETSIINFSocialSkeleton skeleton2= new ETSIINFSocialSkeleton();
        

        /*
         * PRUEBAS login
         */

        //Iniciamos sesion con user2
        Login prueba_login = new Login();
        User aux = new User();
        aux.setName("user2");
        aux.setPwd("user26867");
        prueba_login.setArgs0(aux);
        System.out.println("\n");
        System.out.println("LOGIN 1 = " +skeleton.login(prueba_login).get_return().getResponse());
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");

        //Iniciamos sesion con admin
        User user23 = new User();
        Login prueba_login2 = new Login();
        user23.setName("admin");
        user23.setPwd("admin");
        prueba_login2.setArgs0(user23);
        System.out.println("LOGIN 2 = " + skeleton2.login(prueba_login2).get_return().getResponse());
         

        //Añadimos a user2
        /*es.upm.etsiinf.sos.AddUser user1 = new AddUser();
        Username prueba = new Username();
        prueba.setUsername("user2");
        user1.setArgs0(prueba);
        System.out.println("\n");
        AddUserResponse resAddUser = skeleton2.addUser(user1);
        System.out.println("ADDUSER USER2 = " + resAddUser.get_return().getResponse());
        String pwd = resAddUser.get_return().getPwd();
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");*/

        //Añadimos a user1
        /*es.upm.etsiinf.sos.AddUser user2 = new AddUser();
        Username prueba2 = new Username();
        prueba2.setUsername("user1");
        user2.setArgs0(prueba2);
        System.out.println("\n");
        AddUserResponse resAddUser2 = skeleton.addUser(user2);
        System.out.println("ADDUSER USER2 = " + resAddUser2.get_return().getResponse());
        String pwd2 = resAddUser2.get_return().getPwd();
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");*/
        
        


        //admin eliminando a user2
        Username prueba24 = new Username();
        prueba24.setUsername("user2");
        RemoveUser remove = new RemoveUser();
        remove.setArgs0(prueba24);
        System.out.println("\n");
        RemoveUserResponse resRemove = skeleton2.removeUser(remove);
        System.out.println("RESULTADO DE ADMIN REMOVING USER2 = " + resRemove.get_return().getResponse());
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------"); 


        //Iniciamos sesion con user2 eliminado
        Login prueba_login3 = new Login();
        User aux2 = new User();
        aux2.setName("user2");
        aux2.setPwd("user26867");
        prueba_login3.setArgs0(aux2);
        System.out.println("\n");
        System.out.println("LOGIN USER2 REMOVED = " +skeleton.login(prueba_login3).get_return().getResponse());
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");

          

        /*
        * PRUEBAS logout
        */
        /*es.upm.etsiinf.sos.Logout logout = new Logout();
        System.out.println("\n");
        //skeleton.logout(logout);
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");
        */
        /*
         * PRUEBAS addUser
         */
       /*  es.upm.etsiinf.sos.AddUser user1 = new AddUser();
        Username prueba = new Username();
        prueba.setUsername("Laura");
        user1.setArgs0(prueba);
        System.out.println("\n");
        //skeleton.addUser(user1);
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");
        */
        /*
         * PRUEBAS removeUser
         */
       /*  RemoveUser remove = new RemoveUser();
        remove.setArgs0(prueba);
        System.out.println("\n");
        //skeleton.removeUser(remove);
        //aux2.setPwd("pruebita3230");
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");
        */
        
        /*
         * PRUEBAS changePassword
         */
        /*es.upm.etsiinf.sos.ChangePassword pwd = new es.upm.etsiinf.sos.ChangePassword();
        PasswordPair pwd_pair = new PasswordPair();
        pwd_pair.setNewpwd("pablitoclavounclabito2234");
        pwd_pair.setOldpwd("pablitoclavounclabito22");
        pwd.setArgs0(pwd_pair);

        //skeleton.changePassword(pwd);
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");
        
        */
        /*
         * PRUEBAS addFriend
         */
        /*AddFriend friend = new AddFriend();
        Username user_friend = new Username();
        user_friend.setUsername("pabloso");
        friend.setArgs0(user_friend);

        AddFriendResponse res_friend = new AddFriendResponse();
        res_friend = skeleton2.addFriend(friend);
        //System.out.println(res_friend.get_return().getResponse());

        AddFriend friend3 = new AddFriend();
        Username user_friend3 = new Username();
        user_friend3.setUsername("Laura");
        friend3.setArgs0(user_friend3);

        AddFriendResponse res_friend3 = new AddFriendResponse();
        //res_friend3 = skeleton.addFriend(friend3);

        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");
            */
        /*
         * PRUEBAS removeFriend
         */

        /*RemoveFriend removeF = new RemoveFriend();
        RemoveFriendResponse responseFriend = new RemoveFriendResponse();
        Username userRemoveF = new Username();
        userRemoveF.setUsername("Jaime");
        removeF.setArgs0(userRemoveF);
        //responseFriend = skeleton.removeFriend(removeF);
        //System.out.println(responseFriend.get_return().getResponse());
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");


        */
        /*
         * PRUEBAS getMyFriends
         */
        /*GetMyFriendsResponse rsp = new GetMyFriendsResponse();
        GetMyFriends gt = new GetMyFriends();
        //rsp = skeleton.getMyFriends(gt);
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");
        */
        /*
         * PRUEBAS publishState
         */
       /*  PublishStateResponse res_state = new PublishStateResponse();
        PublishState state = new PublishState();
        State aux_state = new State();
        aux_state.setMessage("Estado 1");
        state.setArgs0(aux_state);
        res_state = skeleton.publishState(state);
        aux_state.setMessage("Estado 2");
        state.setArgs0(aux_state);
        res_state = skeleton.publishState(state);
        aux_state.setMessage("Estado 3");
        state.setArgs0(aux_state);
        res_state = skeleton.publishState(state);
        aux_state.setMessage("Estado 4");
        state.setArgs0(aux_state);
        res_state = skeleton.publishState(state);
        aux_state.setMessage("Estado 5");
        state.setArgs0(aux_state);
        res_state = skeleton.publishState(state);
        aux_state.setMessage("Estado 6");
        state.setArgs0(aux_state);
        res_state = skeleton.publishState(state);
        aux_state.setMessage("Estado 7");
        state.setArgs0(aux_state);
        res_state = skeleton.publishState(state);
        aux_state.setMessage("Estado 8");
        state.setArgs0(aux_state);
        res_state = skeleton.publishState(state);
        aux_state.setMessage("Estado 9");
        state.setArgs0(aux_state);
        res_state = skeleton.publishState(state);
        aux_state.setMessage("Estado 10");
        state.setArgs0(aux_state);
        res_state = skeleton.publishState(state);
        aux_state.setMessage("Estado 11");
        state.setArgs0(aux_state);
        res_state = skeleton.publishState(state);
        aux_state.setMessage("Estado 12");
        state.setArgs0(aux_state);
        res_state = skeleton.publishState(state);
        System.out.println(res_state.get_return().getResponse());
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");
*/
        /*
         * PRUEBAS getMyStates
         */
        /*GetMyStatesResponse res_get_states = new GetMyStatesResponse();
        GetMyStates get_states = new GetMyStates();*/
        /*res_get_states = skeleton.getMyStates(get_states);
        String [] estados = res_get_states.get_return().getStates();
        for (int i = 0; i < estados.length; i++) {
            System.out.println("Estado " + i + " = " + estados[i]);
        }*/

        /*
         * PRUEBAS getMyFriendStates
         */
        /*GetMyFriendStatesResponse res_get_states_friends = new GetMyFriendStatesResponse();
        GetMyFriendStates get_states_friends = new GetMyFriendStates();
        Username username = new Username();
        username.setUsername("pabloso");
        get_states_friends.setArgs0(username);
        res_get_states_friends = skeleton2.getMyFriendStates(get_states_friends);
        String [] estados_friends = res_get_states_friends.get_return().getStates();
        for (int i = 0; i < estados_friends.length; i++) {
            System.out.println("Estado " + i + " = " + estados_friends[i]);
        }
        System.out.println(res_get_states_friends.get_return().getResult());
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");
        */
    }
    


        
        
}