package Client;

import java.rmi.Naming;
import java.util.Scanner;

import Serveur.Chat;

public class Client {

	Chat stub;
	static String user;
	static PollThread threadClient;

	public Client(String userinput) throws Exception {

		stub = (Chat) Naming.lookup("//localhost/RmiServer");
		user = userinput;

		try {
			threadClient = new PollThread(stub);
			threadClient.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "deprecation", "resource" })
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		Client chatClient = new Client(user);

		System.out.println(chatClient.stub.messageBienvenue());
		System.out.println("Entrez votre nom d'utilisateur : ");
		user = sc.nextLine();

		chatClient.stub.Annonce("entrer");

		while (true) {
			System.out.println(">");
			String msg = sc.nextLine();
			if (msg.equals("fin")) {
				chatClient.stub.envoiMessage(user, chatClient.stub.Annonce("sortie du chat"));
				threadClient.stop();
				System.out.println("Vous avez envoyé tant de messages :" + chatClient.stub.NombreMsgEnvoye());
			} else {
				chatClient.stub.envoiMessage(user, msg);
			}

		}

	}
}