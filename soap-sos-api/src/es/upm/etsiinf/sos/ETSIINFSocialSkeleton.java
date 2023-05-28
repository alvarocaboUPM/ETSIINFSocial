
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

import org.apache.axis2.AxisFault;

import es.upm.etsiinf.sos.model.xsd.Response;
import es.upm.etsiinf.sos.model.xsd.User;
import es.upm.etsiinf.sos.model.xsd.Username;
import es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonSkeleton;
import es.upm.fi.sos.t3.backend.xsd.LoginBackEnd;
import es.upm.fi.sos.t3.backend.xsd.UserBackEnd;
import es.upm.fi2.UPMAuthenticationAuthorizationWSSkeletonStub;

/**
 * ETSIINFSocialSkeleton java skeleton for the axisService
 */
public class ETSIINFSocialSkeleton {

        private static Map<String, User> users = new HashMap<String, User>();
	private static boolean instance = false;
	private static List<User> connected = new ArrayList<User>();
	private static User root = new User();
	private static String usernameRoot; 
	private User userID;
	private static boolean rootIsPresent = false;
    private boolean adminLoggedIn = false;
    
    

    
    
        
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
        // Verificar si el usuario que llama a esta función es el administrador y que no esté ya registrado
        if (iAmRoot()) {    
            response = stub.addUser(user);
            if (response.get_return().getResult()) {
                responseFinal.setResponse(true);
                aux_final.set_return(responseFinal); 
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

    private void printList() {
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

    private boolean isConnected (User user) {
		boolean result = false;
		for (User user1: connected) {
			if (same(user1, user)) {
				result = true;
			}
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
     * @param getMyFriendStates
     * @return getMyFriendStatesResponse
     */

    public es.upm.etsiinf.sos.GetMyFriendStatesResponse getMyFriendStates(
            es.upm.etsiinf.sos.GetMyFriendStates getMyFriendStates) {
        // TODO : fill this with the necessary business logic
        throw new java.lang.UnsupportedOperationException(
                "Please implement " + this.getClass().getName() + "#getMyFriendStates");
    }

    

    /**
     * Auto generated method signature
     * 
     * @param getMyStates
     * @return getMyStatesResponse
     */

    public es.upm.etsiinf.sos.GetMyStatesResponse getMyStates(
            es.upm.etsiinf.sos.GetMyStates getMyStates) {
        // TODO : fill this with the necessary business logic
        throw new java.lang.UnsupportedOperationException(
                "Please implement " + this.getClass().getName() + "#getMyStates");
    }

    /**
     * Auto generated method signature
     * 
     * @param getMyFriends
     * @return getMyFriendsResponse
     */

    public es.upm.etsiinf.sos.GetMyFriendsResponse getMyFriends(
            es.upm.etsiinf.sos.GetMyFriends getMyFriends) {
        // TODO : fill this with the necessary business logic
        throw new java.lang.UnsupportedOperationException(
                "Please implement " + this.getClass().getName() + "#getMyFriends");
    }

    /**
     * Auto generated method signature
     * 
     * @param addFriend
     * @return addFriendResponse
     */

    public es.upm.etsiinf.sos.AddFriendResponse addFriend(
            es.upm.etsiinf.sos.AddFriend addFriend) {
        // TODO : fill this with the necessary business logic
        throw new java.lang.UnsupportedOperationException(
                "Please implement " + this.getClass().getName() + "#addFriend");
    }

    

    /**
     * Auto generated method signature
     * 
     * @param publishState
     * @return publishStateResponse
     */

    public es.upm.etsiinf.sos.PublishStateResponse publishState(
            es.upm.etsiinf.sos.PublishState publishState) {
        // TODO : fill this with the necessary business logic
        throw new java.lang.UnsupportedOperationException(
                "Please implement " + this.getClass().getName() + "#publishState");
    }

    /**
     * Auto generated method signature
     * 
     * @param removeFriend
     * @return removeFriendResponse
     */

    public es.upm.etsiinf.sos.RemoveFriendResponse removeFriend(
            es.upm.etsiinf.sos.RemoveFriend removeFriend) {
        // TODO : fill this with the necessary business logic
        throw new java.lang.UnsupportedOperationException(
                "Please implement " + this.getClass().getName() + "#removeFriend");
    }

    

    

}
