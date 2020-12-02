package Client;

import java.util.ArrayList;

import Serveur.Chat;

public class PollThread extends Thread {
	Chat in;
	ArrayList<String> msg;

	public PollThread(Chat reponse) throws Exception {
		in = reponse;
		msg = reponse.getMessage(0);
	}

	public void run() {
		try {
			ArrayList<String> DernierMessage = new ArrayList<String>(0);

			while (true) {
				DernierMessage = in.getMessage(msg.size());
				for (int i = 0; i < DernierMessage.size(); i++) {
					msg.add(DernierMessage.get(i));
					System.out.println(DernierMessage.get(i));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}