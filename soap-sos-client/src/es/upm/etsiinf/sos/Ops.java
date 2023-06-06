package es.upm.etsiinf.sos;

import es.upm.etsiinf.sos.ws.ETSIINFSocialStub;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.Login;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.LoginResponse;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.AddFriend;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.AddFriendResponse;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.AddUser;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.AddUserResponseE;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.ChangePassword;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.ChangePasswordResponse;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.FriendList;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.GetMyFriendStates;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.GetMyFriendStatesResponse;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.GetMyFriends;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.GetMyFriendsResponse;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.GetMyStates;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.GetMyStatesResponse;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.Logout;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.PasswordPair;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.PublishState;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.RemoveFriend;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.RemoveFriendResponse;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.RemoveUser;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.RemoveUserResponse;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.State;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.StatesList;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.User;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.Username;
import es.upm.etsiinf.sos.ws.ETSIINFSocialStub.PublishStateResponse;
import java.rmi.RemoteException;

public class Ops {

	public ETSIINFSocialStub StubClient;
	private FriendList amigos;
	private StatesList estados;
	String usuario;
	String contraseña;

	public Ops(ETSIINFSocialStub StubClientIn) {
		StubClient = StubClientIn;
		amigos = new FriendList();
		estados = new StatesList();
		usuario = "admin";
		contraseña = "admin";
	}

	public void login(String Username, String UPass) {
		Login login = new Login();
		User user = new User();
		user.setName(Username);
		user.setPwd(UPass);
		usuario = Username;
		contraseña = UPass;
		login.setArgs0(user);
		LoginResponse logResponse;
		try {
			logResponse = StubClient.login(login);

			System.out.println((logResponse.get_return().getResponse()
			? "\033[32m[OK]: \033[0m El usuario " + user.getName() + " ha iniciado sesion correctamente"
			: "\033[31m[ERROR]: \033[0m El usuario " + user.getName()
			+ " NO ha iniciado sesion correctamente\n"));

		} catch (RemoteException e) {
			System.out.print("\n[\033[31m[ERROR]: \033[0m]: ");
			System.out.println("Error al iniciar sesion -> CONNECTION ERROR: \033[0m \n");
			e.printStackTrace();

		}
	}

	// El usuario deberá cerrar sesión ( _logout_ ) cuando no acceda a la red
	public void logout() {
		Logout logout = new Logout();
		try {
			StubClient.logout(logout);
			System.out.print("\n\033[32m[OK]: \033[0m ");
			System.out.println("Se ha cerrado sesion satisfactoriamente");

		} catch (RemoteException e) {
			System.out.print("\n\033[31m[ERROR]: \033[0m ");
			System.out.println("Se ha producido un error al intentar cerrar sesion -> CONNECTION ERRROR! ");

			e.printStackTrace();

		}
	}

	// los usuarios deberán darse de alta en la red social ( _addUser_ ) e iniciar
	// sesión ( _login_ )
	public void addUser(String Username) {
		AddUser UsTest = new AddUser();
		Username param = new Username();
		param.setUsername(Username);
		usuario = Username;
		UsTest.setArgs0(param);
		AddUserResponseE UserResponse;
		try {
			UserResponse = StubClient.addUser(UsTest);

			System.out.println((UserResponse.get_return().getResponse()
					? "\033[32m[OK]: \033[0m El usuario" + Username + " se ha dado de alta"
					: "\033[31m[ERROR]: \033[0m ha habido un error dando de alta al usuario \n"));

		} catch (RemoteException e) {
			System.out.print("\n\033[31m[ERROR]: \033[0m ");
			System.out.println("Error al añadir el usuario -> CONNECTION ERRROR! ");
			e.printStackTrace();

		}
	}

	// se podrá dar de baja en la red ( _removeUser_ )
	public void removeUser(String Username) {
		login(Username, Username);
		RemoveUser remove = new RemoveUser();
		Username param = new Username();
		param.setUsername(Username);
		remove.setArgs0(param);
		RemoveUserResponse remRes;
		try {
			remRes = StubClient.removeUser(remove);

			System.out.println((remRes.get_return().getResponse()
					? "\033[32m[OK]: \033[0m El usuario " + Username + " ha sido dado de baja correctamente"
					: "\033[31m[ERROR]: \033[0m El usuario " + Username + " NO ha sido dado de baja correctamente\n"));

		} catch (RemoteException e) {
			System.out.print("\n\033[31m[ERROR]: \033[0m ");
			System.out.println(
					"Se ha producido un error al intentar dar de baja al usuario -> CONNECTION ERROR: \033[0m ");
			e.printStackTrace();

		}
	}

	// changePassword (PasswordPair password)**
	public void changePassword(String Oldpass, String Newpass) {
		ChangePassword changePassword = new ChangePassword();
		PasswordPair param = new PasswordPair();
		param.setOldpwd(Oldpass);
		param.setNewpwd(Newpass);
		changePassword.setArgs0(param);
		ChangePasswordResponse chgpasres;
		try {
			chgpasres = StubClient.changePassword(changePassword);

			System.out.println(chgpasres.get_return().getResponse()
					? "\033[32m[OK]: \033[0m Se ha cambiado la contraseña satisfactoriamente"
					: "\033[31m[ERROR]: \033[0mNo se ha podido cambiar la contraseña  debido a un error");

		} catch (RemoteException e) {
			System.out.print("\n\033[31m[ERROR]: \033[0m ");

			System.out
					.println("Se ha producido un error al intentar cambiar la contraseña -> CONNECTION ERROR: \033[0m");
			e.printStackTrace();

		}

	}

	public void addFriend(String friend) {
		AddFriend addFriend = new AddFriend();
		Username param = new Username();
		param.setUsername(friend);
		addFriend.setArgs0(param);
		AddFriendResponse addfrdres;
		try {
			addfrdres = StubClient.addFriend(addFriend);
			amigos.addFriends(friend);

			System.out.println((addfrdres.get_return().getResponse()
					? "\033[32m[OK]: \033[0m El usuario " + friend + " se ha añadido correctamente a la lista de amigos"
					: "\033[31m[ERROR]: \033[0m El usuario " + friend
							+ " NO se ha añadido correctamente a la lista de amigos\n"));

		} catch (RemoteException e) {
			System.out.print("\n\033[31m[ERROR]: \033[0m ");

			System.out.println(
					"Se ha producido un error al intentar añadir un amigo nuevo -> CONNECTION ERROR: \033[0m ");
			e.printStackTrace();

		}

	}

	public void removeFriend(String friend) {
		RemoveFriend remove_friend = new RemoveFriend();
		Username Username = new Username();
		Username.setUsername(friend);
		remove_friend.setArgs0(Username);
		RemoveFriendResponse remfrdres;
		try {
			remfrdres = StubClient.removeFriend(remove_friend);

			System.out.println(
					(remfrdres.get_return().getResponse()
							? "\033[32m[OK]: \033[0m El usuario " + friend
									+ " se ha eliminado correctamente de tu lista de amigos"
							: "\033[31m[ERROR]: \033[0m El usuario " + friend
									+ " no ha podido ser eliminado de tu lista de amigos debido a un error"));
		} catch (RemoteException e) {
			System.out.print("\n\033[31m[ERROR]: \033[0m ");
			System.out.println("Se ha producido un error al intentar eliminar un amigo -> CONNECTION ERROR: \033[0m ");
			e.printStackTrace();
		}
	}

	public void getMyFriends() {
		GetMyFriends getfrds = new GetMyFriends();
		try {
			System.out.print("\n\033[34m[DEBUG]: \033[0m ");
			System.out.println("Lista de amigos: \n ");
			GetMyFriendsResponse r = StubClient.getMyFriends(getfrds);
			FriendList list = r.get_return();
			for (String f : list.getFriends()) {
				System.out.println(f + "\n");
			}
		} catch (NullPointerException e1) {
			System.out.print("\n\033[31m[ERROR]: \033[0m ");
			System.out.println("No existe la lista de amigos");
		} catch (RemoteException e) {
			System.out.print("\n\033[31m[ERROR]: \033[0m ");
			System.out.println(
					"Se ha producido un error al intentar obtener la lista de amigos -> CONNECTION ERROR: \033[0m");
			e.printStackTrace();
		}
	}

	public void publishState(String estado) {
		PublishStateResponse pustres = new PublishStateResponse();
		PublishState Pstate;
		State stdo;
		System.out.print("\n\033[34m[DEBUG]: \033[0m ");
		System.out.println("Añadiendo estado al sistema...");
		try {
			Pstate = new PublishState();
			stdo = new State();
			stdo.setMessage(estado);
			pustres = StubClient.publishState(Pstate);
			estados.addStates(estado);
			System.out.println(pustres.get_return().getResponse() ? "Estado añadido correctamente"
					: "Ha habido un error al añadir el estado");
		} catch (RemoteException e) {
			System.out.print("\n\033[31m[ERROR]: \033[0m ");
			System.out.println("Se ha producido un error al intentar añadir un estado -> CONNECTION ERROR: \033[0m ");
			e.printStackTrace();
		}
	}

	public void getMyStates() {
		GetMyStatesResponse gtstatsres = new GetMyStatesResponse();
		GetMyStates gtstats = new GetMyStates();
		try {
			gtstatsres = StubClient.getMyStates(gtstats);
			String[] estados = gtstatsres.get_return().getStates();
			if (estados != null) {
				System.out.println("\n\033[32m[OK]: \033[0m ");
				for (int i = 0; i < estados.length; i++) {
					System.out.println("Estado " + i + " = " + estados[i]);
				}
			} else {
				System.out.println("No hay estados que mostrar");
			}
		} catch (RemoteException e) {
			System.out.print("\n\033[31m[ERROR]: \033[0m ");
			e.printStackTrace();
		}
	}

	public void getMyFriendStates(String user) {
		GetMyFriendStatesResponse resgtfrdsst;
		GetMyFriendStates gtfrdsst;
		Username Username;
		try {
			resgtfrdsst = new GetMyFriendStatesResponse();
			gtfrdsst = new GetMyFriendStates();
			Username = new Username();
			Username.setUsername(user);
			gtfrdsst.setArgs0(Username);
			resgtfrdsst = StubClient.getMyFriendStates(gtfrdsst);
			String[] estados_friends = resgtfrdsst.get_return().getStates();
			int i = 0;
			if (estados_friends == null)
				System.out.println("\n\033[31m[ERROR]: \033[0m No hay estados disponibles");
			System.out.println("\n\033[32m[OK]: \033[0m ");
			for (String estado : estados_friends) {
				System.out.println("Estado " + i + " = " + estado);
				i++;
			}

		} catch (RemoteException e) {
			System.out.print("\n\033[31m[ERROR]: \033[0m ");
			e.printStackTrace();
		}
	}

}