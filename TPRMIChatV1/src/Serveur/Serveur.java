package Serveur;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Serveur extends UnicastRemoteObject implements Chat {

	private static final long serialVersionUID = 1L;
	static ArrayList<String> messages = new ArrayList<String>(0);
	static int NbrClients = 0;

	public Serveur() throws RemoteException {
		super();
	}

	public static void main(String args[]) {

		try {
			LocateRegistry.createRegistry(1099);
			Serveur chatServeur = new Serveur();
			Naming.rebind("//localhost/RmiServer", chatServeur);
			System.out.println("Serveur pret!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String messageBienvenue() throws RemoteException {
		return "Bienvenue à la version 1 de notre Chat!";
	}

	public ArrayList<String> getMessage(int PositionDansLaListe) throws RemoteException {

		ArrayList<String> Listmsg = new ArrayList<String>(0);
		for (int i = PositionDansLaListe; i < messages.size(); i++) {
			Listmsg.add(messages.get(i));
		}

		return Listmsg;
	}

	public void envoiMessage(String user, String msg) throws RemoteException {
		messages.add(user + " : " + msg);
	}

	public int NombreMsgEnvoye() throws Exception {
		int count = 0;
		for (String msg : messages) {
			count++;
		}
		return count;
	}

	public String Annonce(String status) throws Exception {

		if (status.equals("entrer")) {
			NbrClients++;
			return "\nUn client vient d'arriver dans le chat" + "\nLe nombre d'utilisateur actuel est " + NbrClients;
		} else {
			NbrClients--;
			return "\nUn client vient de quitter le chat" + "\nLe nombre d'utilisateur actuel est " + NbrClients;
		}
	}

}