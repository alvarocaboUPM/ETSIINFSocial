package es.upm.etsiinf.sos;

import java.rmi.RemoteException;

import es.upm.etsiinf.sos.model.xsd.PasswordPair;
import es.upm.etsiinf.sos.model.xsd.State;
import es.upm.etsiinf.sos.model.xsd.User;
import es.upm.etsiinf.sos.model.xsd.Username;

public class testing {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) throws RemoteException {

        ETSIINFSocialSkeleton skeleton = new ETSIINFSocialSkeleton();
        ETSIINFSocialSkeleton skeleton2 = new ETSIINFSocialSkeleton();

        /*
         * PRUEBAS login
         */

        Login prueba_login = new Login();
        User aux = new User();
        aux.setName("pabloso");
        aux.setPwd("pablitoclavounclabito2234");
        prueba_login.setArgs0(aux);
        System.out.println("\n");
        resPrint(skeleton.login(prueba_login).get_return().getResponse());
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");
        User adm = new User();
        Login prueba_login2 = new Login();
        adm.setName("admin");
        adm.setPwd("admin");
        prueba_login2.setArgs0(adm);
        skeleton2.login(prueba_login2);

        /*
         * PRUEBAS addUser
         */
        es.upm.etsiinf.sos.AddUser user1 = new AddUser();
        Username prueba = new Username();
        prueba.setUsername("Laura");
        user1.setArgs0(prueba);
        System.out.println("\n");
        resPrint(skeleton.addUser(user1).get_return().getResponse());
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

        resPrint(skeleton.changePassword(pwd).get_return().getResponse());
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");

        /*
         * PRUEBAS addFriend
         */

        // 1. Prueba admin añade a pabloso
        AddFriend friend = new AddFriend();
        Username user_friend = new Username();
        user_friend.setUsername("pabloso");
        friend.setArgs0(user_friend);

        skeleton2.addFriend(friend);

        // 2. Prueba pabloso añade a Laura
        AddFriend friend3 = new AddFriend();
        Username user_friend3 = new Username();
        user_friend3.setUsername("Laura");
        friend3.setArgs0(user_friend3);

        resPrint(skeleton.addFriend(friend3).get_return().getResponse());

        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");

        /*
         * PRUEBAS getMyFriends
         */

        GetMyFriends gt = new GetMyFriends();
        resPrint(skeleton.getMyFriends(gt).get_return()!=null);
        System.out.println("\n-------------------------------------------------------------");

        // /*
        // * PRUEBAS publishState
        // */
        PublishStateResponse res_state = new PublishStateResponse();
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
        resPrint(res_state.get_return().getResponse());
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");

        // /*
        // * PRUEBAS getMyStates
        // */
        GetMyStatesResponse res_get_states = new GetMyStatesResponse();
        GetMyStates get_states = new GetMyStates();
        res_get_states = skeleton.getMyStates(get_states);
        String[] estados = res_get_states.get_return().getStates();
        for (int i = 0; i < estados.length; i++) {
            System.out.println("Estado " + i + " = " + estados[i]);
        }

        // /*
        // * PRUEBAS getMyFriendStates
        // */
        GetMyFriendStatesResponse res_get_states_friends = new GetMyFriendStatesResponse();
        GetMyFriendStates get_states_friends = new GetMyFriendStates();
        Username username = new Username();
        username.setUsername("pabloso");
        get_states_friends.setArgs0(username);
        res_get_states_friends = skeleton2.getMyFriendStates(get_states_friends);
        String[] estados_friends = res_get_states_friends.get_return().getStates();
        for (int i = 0; i < estados_friends.length; i++) {
            System.out.println("Estado " + i + " = " + estados_friends[i]);
        }
        resPrint(res_get_states_friends.get_return().getResult());
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");

        
        // /*
        // * PRUEBAS removeFriend
        // */

        // 1. Prueba con usuario que no es amigo
        RemoveFriend removeF = new RemoveFriend();

        Username userRemoveF = new Username();
        userRemoveF.setUsername("Jaime");
        removeF.setArgs0(userRemoveF);
        resPrint(skeleton.removeFriend(removeF).get_return().getResponse());

        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");

        // 2. Prueba con usuario que es amigo
        RemoveFriend removeF2 = new RemoveFriend();
        Username userRemoveF2 = new Username();
        userRemoveF2.setUsername("Laura");
        removeF2.setArgs0(userRemoveF2);
        resPrint(skeleton.removeFriend(removeF2).get_return().getResponse());
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");

        /*
         * PRUEBAS removeUser
         */
        RemoveUser remove = new RemoveUser();
        remove.setArgs0(prueba);
        System.out.println("\n");
        resPrint(skeleton.removeUser(remove).get_return().getResponse());
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");


        /*
         * PRUEBAS logout
         */
        es.upm.etsiinf.sos.Logout logout = new Logout();
        System.out.println("\n");
        skeleton.logout(logout);
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------");

    }

    private static void resPrint(Boolean method){
        if (method) {
            System.out.print(ANSI_GREEN + "\nOK" + ANSI_RESET);
        } else {
            System.out.print(ANSI_RED + "\nERROR" + ANSI_RESET);
        }
    }

}