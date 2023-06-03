package es.upm.etsiinf.sos;
import es.upm.etsiinf.sos.ETSIINFSocialStub.*;
import es.upm.etsiinf.sos.ETSIINFSocialStub.AddUser;
import es.upm.etsiinf.sos.ETSIINFSocialStub.PasswordPair;
import es.upm.etsiinf.sos.ETSIINFSocialStub.Login;
import es.upm.etsiinf.sos.ETSIINFSocialStub.User;
import es.upm.etsiinf.sos.ETSIINFSocialStub.GetMyFriends;
import java.rmi.RemoteException;
import org.apache.axis2.AxisFault;
public class Ops {
   
	//los usuarios deberán darse de alta en la red social ( _addUser_ ) e iniciar sesión ( _login_ )
	static boolean addUser(ETSIINFSocialStub StubClient, String Username) {
		AddUser UsTest = new AddUser();
		Username param = new Username();
		param.setUsername(Username);
		UsTest.setArgs0(param);
		AddUserResponseE UserResponse;
		try {
		
			UserResponse = StubClient.addUser(UsTest);
			//String pwd = UserResponse.get_return().getPwd();
			//Password.put(Username, pwd);
			return UserResponse.get_return().getResponse();
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	static boolean login(ETSIINFSocialStub client, String userName, String userPwd) {
		Login login1 = new Login();
		User param = new User();
		param.setName(userName);
		param.setPwd(userPwd);
		login1.setArgs0(param);
		try {
			LoginResponse r = client.login(login1);
			// System.out.println("SALIDA LOGIN ADMIN EN CLIENTE: " +
			// r.get_return().getResponse());
			return r.get_return().getResponse();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	
	// El usuario deberá cerrar sesión ( _logout_ ) cuando no acceda a la red
	static void logout(ETSIINFSocialStub StubClient) {
		Logout logout = new Logout();
		try {
			StubClient.logout(logout);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	
	//se podrá dar de baja en la red  ( _removeUser_ )
	static boolean removeUser(ETSIINFSocialStub StubClient, String Username) {
		RemoveUser remove = new RemoveUser();
		Username param = new Username();
		param.setUsername(Username);
		remove.setArgs0(param);
		RemoveUserResponse r12;
		try {
			r12 = StubClient.removeUser(remove);
			return r12.get_return().getResponse();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	//changePassword (PasswordPair password)**
	static boolean changePassword(ETSIINFSocialStub StubClient, String oldPwd, String newPwd) {
		ChangePassword changePassword = new ChangePassword();
		PasswordPair param = new PasswordPair();
		param.setOldpwd(oldPwd);
		param.setNewpwd(newPwd);
		changePassword.setArgs0(param);
		ChangePasswordResponse r;
		try {
			r = StubClient.changePassword(changePassword);
			return r.get_return().getResponse();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	static boolean addFriend(ETSIINFSocialStub StubClient, String friend) {
		AddFriend addFriend = new AddFriend();
		Username param = new Username();
		param.setUsername(friend);
		addFriend.setArgs0(param);
		AddFriendResponse r;
		try {
			r = StubClient.addFriend(addFriend);
			return r.get_return().getResponse();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	static boolean removeFriend(ETSIINFSocialStub StubClient, String friend) {

		RemoveFriend remove_friend = new RemoveFriend();
		Username Username = new Username();
		Username.setUsername(friend);
		remove_friend.setArgs0(Username);
		RemoveFriendResponse r;
		try {
			r = StubClient.removeFriend(remove_friend);
			return r.get_return().getResponse();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}