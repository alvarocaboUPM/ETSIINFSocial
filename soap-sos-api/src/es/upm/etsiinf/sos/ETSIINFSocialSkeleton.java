
/**
 * ETSIINFSocialSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package es.upm.etsiinf.sos;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import es.upm.etsiinf.sos.model.xsd.FriendList;
import es.upm.etsiinf.sos.model.xsd.Response;
import es.upm.etsiinf.sos.model.xsd.StatesList;
import es.upm.etsiinf.sos.model.xsd.User;
import es.upm.etsiinf.sos.model.xsd.Username;
import es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub;

/**
 * ETSIINFSocialSkeleton java skeleton for the axisService
 */
public class ETSIINFSocialSkeleton {

        private static Map<String, User> users = new HashMap<String, User>();
	
	private static List<User> connected = new ArrayList<User>();
	private static User root = new User();
	private User userID;
	private static boolean rootIsPresent = false;
    private boolean adminLoggedIn = false;
    static List<Username> usersTotal = new ArrayList<Username>();
    FriendList friendsList = new FriendList();
    StatesList estados = new StatesList();
    static Map<String,StatesList> mapaEstados = new HashMap<String,StatesList>();
    
    
    

    
    
        
    /**
     * Auto generated method signature
     * 
     * @param addUser
     * @return addUserResponse
     * @throws RemoteException
     */
    public es.upm.etsiinf.sos.AddUserResponse addUser (es.upm.etsiinf.sos.AddUser addUser) throws RemoteException {
        es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub stub = new UPMAuthenticationAuthorizationWSSkeletonStub();
        Username username = addUser.getArgs0();
        es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.AddUserResponse response = new es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.AddUserResponse();        
        es.upm.etsiinf.sos.model.xsd.AddUserResponse responseFinal = new es.upm.etsiinf.sos.model.xsd.AddUserResponse();
        AddUserResponse aux_final = new AddUserResponse();

        es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.AddUser user = new es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.AddUser();
        es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.UserBackEnd aux = new es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.UserBackEnd();
        aux.setName(username.getUsername());
        user.setUser(aux);
        
        if (this.userID == null) {
            System.out.println("Por favor, inicia sesión para añadir usuarios.");
            responseFinal.setResponse(false);
            aux_final.set_return(responseFinal); 
            return aux_final;
        }
        // Verificar si el usuario que llama a esta función es el administrador y que no esté ya registrado
        if (iAmRoot()) {    
            response = stub.addUser(user);
            if (response.get_return().getResult()) {
                responseFinal.setResponse(true);
                aux_final.set_return(responseFinal); 
                usersTotal.add(username);
                System.out.println("Ha añadido al usuario " + username.getUsername() + " con contraseña " + response.get_return().getPassword() + " exitosamente.");
                return aux_final;
            }
            else {
                System.out.println("ERROR: usuario con nombre " + username.getUsername() + " ya registrado.");
                responseFinal.setResponse(false);
                aux_final.set_return(responseFinal); 
                return aux_final;
            }
        }
        else {
            System.out.println("Solo el administrador del sistema puede añadir usuarios.");
            responseFinal.setResponse(false);
            aux_final.set_return(responseFinal); 
            return aux_final;
        }
    }

    private boolean iAmRoot() {
		// return same(this.userID, root);
		boolean result = false;
		if (rootIsPresent) {
			same(this.userID, root);
			result = true;
		}
		return result;
	}

    private boolean same (User user1, User user2) {
		return (user1.getName().equals(user2.getName()) &&
				user1.getPwd().equals(user2.getPwd()));
	}
        
    private void printMap () {
		int i = 0;
		System.out.println("========================= <HashMap> =========================");
		for (String name: users.keySet()) {
			String key = name.toString();
			String value = users.get(key).getName();
			String pwd = users.get(key).getPwd();
			System.out.println("Map entry [" + i + "] -> Username: " + key + "| Name: " + value + "| Password: " + pwd);
			i += 1;
		}
		System.out.println("========================= </HashMap> =========================\n");
	}




    /**
     * Auto generated method signature
     * 
     * @param login
     * @return loginResponse
     * @throws RemoteException
     */

    public es.upm.etsiinf.sos.LoginResponse login(
            es.upm.etsiinf.sos.Login login) throws RemoteException {
               
            es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub stub = new UPMAuthenticationAuthorizationWSSkeletonStub();
                
                User user_aux = login.getArgs0();
                String username = user_aux.getName();
                String password = user_aux.getPwd();
            
                Response response = new Response();
                LoginResponse responseFinal = new LoginResponse();
            
                if (username.equals("admin")) {
                    // El usuario admin no se gestiona a través del servicio UPMAuthenticationAuthorization
                    rootIsPresent = true;
                    this.userID = user_aux;
                    response.setResponse(false);
                    responseFinal.set_return(response);
                    System.out.println("¡Bienvenido admin, ha iniciado sesión con éxito!");
                    connected.add(user_aux);
                    return responseFinal;
                    
                }
                es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.LoginBackEnd login_aux = new es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.LoginBackEnd();
                login_aux.setName(username);
                login_aux.setPassword(password);
                es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.Login logito = new es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.Login();
                logito.setLogin(login_aux);
                
                boolean res = stub.login(logito).get_return().getResult();
                
                if (adminLoggedIn || res) {
                    // Si el usuario admin está autenticado o el nombre de usuario y contraseña son válidos
                    // según el servicio UPMAuthenticationAuthorization, la operación de login tiene éxito.
                    System.out.println("¡Bienvenido " + username + ", has iniciado sesión con éxito!");
                    this.userID = user_aux;
                    connected.add(user_aux);
                    response.setResponse(true);
                    responseFinal.set_return(response);
                    
                } else {
                    // Si se intenta hacer login con un usuario diferente al autenticado actualmente
                    // o las credenciales son inválidas, la operación de login falla.
                    response.setResponse(false);
                    responseFinal.set_return(response);
                    System.out.println("Credenciales incorrectas.");
                    
                }    
                return responseFinal; 
    }

    private void printListConnected() {
		int i = 0;
		System.out.println("========================= <List> =========================");
		for (User value: connected) {
			String name = value.getName();
			String pwd = value.getPwd();
			System.out.println("List entry [" + i + "] -> Name: " + name + "| Password: " + pwd);
			i += 1;
		}
		System.out.println("========================= </List> =========================\n");
	}

    private void printListFriends() {
		
        String [] amigos = friendsList.getFriends();
		System.out.println("========================= <List> =========================");
		for (int i = 0; i<amigos.length; i++) {
			System.out.println("List entry [" + i + "] -> Name: " + amigos[i]);
		}
		System.out.println("========================= </List> =========================\n");
	}

    private void printStates() {
		
        String [] states = estados.getStates();
		System.out.println("========================= <List> =========================");
		for (int i = 0; i<states.length; i++) {
			System.out.println("List entry [" + i + "] -> State description: " + states[i]);
		}
		System.out.println("========================= </List> =========================\n");
	}


    private boolean isConnected (User user) {
		boolean result = false;
		for (User user1: connected) {
			if (same(user1, user)) {
				result = true;
			}
		}
		return result;
	}

    private boolean isFriend (Username user) {
		boolean result = false;
        String [] amigos = friendsList.getFriends();
        if(amigos == null) {
            return false;
        }
		for (int i = 0; i < friendsList.getFriends().length; i++) {
            if (amigos[i].equals(user.getUsername()))
            result = true;
        }
		return result;
	}



    /**
     * Auto generated method signature
     * 
     * @param logout
     * @return
     */

     public void logout(
        es.upm.etsiinf.sos.Logout logout) {

        boolean res = false;
        for (User users: connected) {
            if (same(users, this.userID)) {
                connected.remove(users);
                this.userID = null;
                System.out.println("Has cerrado la sesión correctamente, hasta pronto "+ users.getName()+".");
                res = true;
                break;
            }
        }
        
        if (!res) System.out.println("No hay ninguna sesión iniciada.");
        }



    /**
     * Auto generated method signature
     * 
     * @param removeUser
     * @return removeUserResponse
     * @throws RemoteException
     */

    public es.upm.etsiinf.sos.RemoveUserResponse removeUser(
            es.upm.etsiinf.sos.RemoveUser removeUser) throws RemoteException {

        es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub stub = new UPMAuthenticationAuthorizationWSSkeletonStub();

        Username user_to_delete = removeUser.getArgs0();
        String name = user_to_delete.getUsername();

        RemoveUserResponse res = new RemoveUserResponse();
        Response aux = new Response();

        es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.ExistUserResponseE res_aux = new es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.ExistUserResponseE();
        es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.ExistUser exist = new es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.ExistUser();
        es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.Username user_delete = new es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.Username();
        user_delete.setName(name);
        exist.setUsername(user_delete);
        res_aux = stub.existUser(exist);
        boolean check = res_aux.get_return().getResult();

        //No se puede eliminar al admin
        if(name.equals("admin")) {
            aux.setResponse(false);
            res.set_return(aux);
            System.out.println("No se puede eliminar al admin del sistema.");
            return res;
        }

        if (this.userID == null) {
            System.out.println("Por favor, inicia sesión para eliminar usuarios.");
            aux.setResponse(false);
            res.set_return(aux); 
            return res;
        }
        
        //Solamente el propio usuario puede eliminarse a si mismo o el admin y ademas, debe existir el usuario a borrar
        if (iAmRoot() || this.userID.getName().equals(name) || check) {
            es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUserE remove = new es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUserE();
            es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUser to_remove = new es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUser();
            to_remove.setName(name);
            remove.setRemoveUser(to_remove);
            stub.removeUser(remove);
            System.out.println("Ha eliminado al usuario " + name + " con éxito.");
            aux.setResponse(true);
            res.set_return(aux);
            return res;
        }
        else {
            aux.setResponse(false);
            res.set_return(aux);
            System.out.println("Introduce un usuario válido para eliminar.");
            return res;
        }
    }

    /**
     * Auto generated method signature
     * 
     * @param changePassword
     * @return changePasswordResponse
     * @throws RemoteException
     */

     public es.upm.etsiinf.sos.ChangePasswordResponse changePassword(
        es.upm.etsiinf.sos.ChangePassword changePassword) throws RemoteException {
        
        es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub stub = new UPMAuthenticationAuthorizationWSSkeletonStub();
        
        ChangePasswordResponse res = new ChangePasswordResponse();
        Response result = new Response();

        if (this.userID == null) {
            System.out.println("Por favor, inicia sesión para cambiar tu contraseña.");
            result.setResponse(false);
            res.set_return(result); 
            return res;
        }

        // Si el admin intenta cambiar su contraseña, lo hace.
        if (iAmRoot()) {
            this.userID.setPwd(changePassword.getArgs0().getNewpwd());
            result.setResponse(true);
            res.set_return(result);
            System.out.println("El usuario " + this.userID.getName() +  " ha cambiado su contraseña con éxito.");
        }
        else {
            es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.ChangePassword change = new es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.ChangePassword();
            es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.ChangePasswordBackEnd to_change = new es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.ChangePasswordBackEnd();
            es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.ChangePasswordResponseE resp = new es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.ChangePasswordResponseE();
            to_change.setNewpwd(changePassword.getArgs0().getNewpwd());
            to_change.setOldpwd(changePassword.getArgs0().getOldpwd());
            to_change.setName(this.userID.getName());
            change.setChangePassword(to_change);
            
            if (isConnected(this.userID) && this.userID.getPwd().equals(changePassword.getArgs0().getOldpwd())) {
                resp = stub.changePassword(change);
                boolean resultado = resp.get_return().getResult();
                if (resultado) {
                    System.out.println("El usuario " + this.userID.getName() +  " ha cambiado su contraseña con éxito.");
                    result.setResponse(true);
                    res.set_return(result);
                }

            }
            else {
                System.out.println("El usuario " + this.userID.getName() +  " no ha cambiado su contraseña.");
                result.setResponse(false);
                res.set_return(result);
            }
        }
        return res;           
    }



    /**
     * Auto generated method signature
     * 
     * @param addFriend
     * @return addFriendResponse
     * @throws RemoteException
     */

     public es.upm.etsiinf.sos.AddFriendResponse addFriend(
        es.upm.etsiinf.sos.AddFriend addFriend) throws RemoteException {
            es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub stub = new UPMAuthenticationAuthorizationWSSkeletonStub();
        

            Username username = addFriend.getArgs0();


            AddFriendResponse res = new AddFriendResponse();
            Response aux = new Response();

            es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.ExistUserResponseE res_aux = new es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.ExistUserResponseE();
            es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.ExistUser exist = new es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.ExistUser();
            es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.Username user_delete = new es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.Username();
            user_delete.setName(username.getUsername());
            exist.setUsername(user_delete);
            res_aux = stub.existUser(exist);
            boolean check = res_aux.get_return().getResult();

            if (this.userID == null) {
                System.out.println("Por favor, inicia sesión para añadir amigos.");
                aux.setResponse(false);
                res.set_return(aux); 
                return res;
            }
            
            if (!isConnected(this.userID)) {
                System.out.println("El usuario " + this.userID.getName() + " no ha iniciado sesión correctamente.");
                aux.setResponse(false);
                res.set_return(aux);
                return res;
            }

            if (!check) {
                System.out.println("El usuario " + username.getUsername() + " no está registrado en la red.");
                aux.setResponse(false);
                res.set_return(aux);
                return res;
            }
            //Si no, simplemente lo añadimos como amigo comprobando que no esté repetido ya
            if (!esRepetido(username.getUsername(), friendsList)) {

                friendsList.addFriends(username.getUsername());
                String [] amigos = friendsList.getFriends();
                aux.setResponse(true);
                res.set_return(aux);
                System.out.println("El usuario " + userID.getName() + " ha añadido como amigo al usuario " + username.getUsername() + " con éxito.");
                printListFriends();
                return res;
            } else {
                System.out.println("El usuario " + this.userID.getName() + " ya tiene añadido como amigo al usuario " + username.getUsername() + ".");
                aux.setResponse(false);
                res.set_return(aux);
                return res;
            }
    
    }

    public boolean esRepetido (String amigo, FriendList lista) {
        if (lista.getFriends() != null) {
            String [] amigos = lista.getFriends();
            for (int i = 0; i < amigos.length; i++) {
                if (amigos[i].equals(amigo)) return true;
            }
        }
        return false;
    }

    /**
     * Auto generated method signature
     * 
     * @param removeFriend
     * @return removeFriendResponse
     * @throws RemoteException
     */

     public es.upm.etsiinf.sos.RemoveFriendResponse removeFriend(
        es.upm.etsiinf.sos.RemoveFriend removeFriend) throws RemoteException {
        
        es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub stub = new UPMAuthenticationAuthorizationWSSkeletonStub();
        
            
        Username username = removeFriend.getArgs0();
        

        RemoveFriendResponse res = new RemoveFriendResponse();
        Response response = new Response();

        es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.ExistUserResponseE res_aux = new es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.ExistUserResponseE();
        es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.ExistUser exist = new es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.ExistUser();
        es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.Username user_delete = new es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub.Username();
        user_delete.setName(username.getUsername());
        exist.setUsername(user_delete);
        res_aux = stub.existUser(exist);
        boolean check = res_aux.get_return().getResult();

        if (this.userID == null) {
            System.out.println("Por favor, inicia sesión para eliminar amigos.");
            response.setResponse(false);
            res.set_return(response); 
            return res;
        }

        if (!isConnected(this.userID)) {
            System.out.println("El usuario " + this.userID.getName() + " no ha iniciado sesión correctamente.");
            response.setResponse(false);
            res.set_return(response);
            return res;
        }

        if (!check) {
            System.out.println("El usuario " + username.getUsername() + " no está registrado en la red.");
            response.setResponse(false);
            res.set_return(response);
            return res;
        }

        User user = new User();
        user.setName(username.getUsername());

        if (!isFriend(username)) {
            System.out.println("El usuario " + userID.getName() + " no tiene añadido como amigo al usuario " + username.getUsername());
            response.setResponse(false);
            res.set_return(response);
            return res;
        }
        // Si no, lo eliminamos
        
        eliminar(username.getUsername());
        response.setResponse(true);
        res.set_return(response);
        System.out.println("Ha eliminado de su lista de amigos al usuario " + username.getUsername() + " con éxito.");
        printListFriends();
        return res;

    }

    public void eliminar (String amigo) {

        int index = -1;
        String [] amigos = friendsList.getFriends();

        for (int i = 0; i < amigos.length; i++) {
            if (amigos[i].equals(amigo)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            // Crea un nuevo array con una longitud menor
            String[] nuevoAmigos = new String[amigos.length - 1];

            // Copia los elementos al nuevo array, excluyendo el elemento a eliminar
            int j = 0;
            for (int i = 0; i < amigos.length; i++) {
                if (i != index) {
                    nuevoAmigos[j] = amigos[i];
                    j++;
                }
            }
            friendsList.setFriends(nuevoAmigos);
        } 
    }

    /**
     * Auto generated method signature
     * 
     * @param getMyFriends
     * @return getMyFriendsResponse
     */

     public es.upm.etsiinf.sos.GetMyFriendsResponse getMyFriends(
        es.upm.etsiinf.sos.GetMyFriends getMyFriends) {

            GetMyFriendsResponse res = new GetMyFriendsResponse();
            FriendList amigos = new FriendList();
            
            
            
            if (this.userID == null) {
                System.out.println("Por favor, inicia sesión para consultar tu lista de amigos.");
                amigos.setFriends(new String [0]);
                amigos.setResult(false);
                res.set_return(amigos);
                return res;
            }
            else {
                amigos.setFriends(friendsList.getFriends());
                amigos.setResult(true);
                res.set_return(amigos);
                System.out.println("Ha realizado la obtención de su lista de amigos con éxito.");
                printListFriends();
                return res;
            }
            
    }


    /**
     * Auto generated method signature
     * 
     * @param publishState
     * @return publishStateResponse
     */

     public es.upm.etsiinf.sos.PublishStateResponse publishState(
        es.upm.etsiinf.sos.PublishState publishState) {
          
            PublishStateResponse res = new PublishStateResponse();
            Response res_aux = new Response();

            if (this.userID == null) {
                res_aux.setResponse(false);
                res.set_return(res_aux);
                System.out.println("Debes iniciar sesión para publicar un estado.");
                return res;
            }
            else {
                String msg = publishState.getArgs0().getMessage();
                estados.addStates(msg);
                res_aux.setResponse(true);
                res.set_return(res_aux);
                mapaEstados.put(this.userID.getName(), estados);
                System.out.println("El usuario " + this.userID.getName() + " ha publicado el estado [" + msg + "] con éxito.");
                printStates();
                System.out.println("El mapa tiene: " + mapaEstados.get(this.userID.getName()).getStates().length);
                return res;
            }
    }

    /**
     * Auto generated method signature
     * 
     * @param getMyStates
     * @return getMyStatesResponse
     */

     public es.upm.etsiinf.sos.GetMyStatesResponse getMyStates(
        es.upm.etsiinf.sos.GetMyStates getMyStates) {

            GetMyStatesResponse res = new GetMyStatesResponse();
            StatesList res_aux = new StatesList();
            
            if (this.userID == null) {
                res_aux.setResult(false);
                res_aux.setStates(new String [0]);
                res.set_return(res_aux);
                System.out.println("Debes iniciar sesión para obtener la lista de estados publicados.");
                return res;
            }
            else {
                String [] mensajes = estados.getStates();
                int longitud = Math.min(mensajes.length, 10);
                String [] last = new String[longitud];
                for (int i = 0; i < longitud; i++) {
                    last[i] = mensajes[mensajes.length - 1 - i];
                }
                res_aux.setResult(true);
                res_aux.setStates(last);
                res.set_return(res_aux);
                System.out.println("Has obtenido los últimos 10 estados publicados con éxito.");
                return res;
            }
}
    
    
    /**
     * Auto generated method signature
     * 
     * @param getMyFriendStates
     * @return getMyFriendStatesResponse
     */

    public es.upm.etsiinf.sos.GetMyFriendStatesResponse getMyFriendStates(
            es.upm.etsiinf.sos.GetMyFriendStates getMyFriendStates) {
        
            GetMyFriendStatesResponse res = new GetMyFriendStatesResponse();
            StatesList states = new StatesList();

            Username user = getMyFriendStates.getArgs0();
            String nombre = user.getUsername();

            if (this.userID == null) {
                states.setResult(false);
                states.setStates(new String[0]);
                res.set_return(states);
                System.out.println("Debes iniciar sesión para obtener la lista de estados publicados de tu amigo.");
                return res;
            }
            else {
                if (!isFriend(user)) {
                    states.setResult(false);
                    states.setStates(new String[0]);
                    res.set_return(states);
                    System.out.println("El usuario " + user.getUsername() + " no es tu amigo.");
                    return res;
                }
                else {
                    //User aux = new User();
                    //aux.setName(user.getUsername());
                    
                    StatesList lista_estados = mapaEstados.get(nombre);
                    String [] mensajes = lista_estados.getStates();
                    int longitud = Math.min(mensajes.length, 10);
                    String [] last = new String[longitud];
                    for (int i = 0; i < longitud; i++) {
                        last[i] = mensajes[mensajes.length - 1 - i];
                    }
                    states.setResult(true);
                    states.setStates(last);
                    res.set_return(states);
                    System.out.println("Has obtenido los últimos 10 estados publicados de tu amigo [" + user.getUsername() + "] con éxito.");
                    return res;

                }
            }
    }

    

    

    

    

    

    

    

    

    

}
