package Serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Chat extends Remote {

	public String messageBienvenue() throws RemoteException;

	public ArrayList<String> getMessage(int PositionDansLaListe) throws RemoteException;

	public void envoiMessage(String user, String msg) throws RemoteException;

	public int NombreMsgEnvoye() throws Exception;

	public String Annonce(String status) throws Exception;

}