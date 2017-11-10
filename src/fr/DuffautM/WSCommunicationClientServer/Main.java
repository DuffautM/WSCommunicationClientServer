package fr.DuffautM.WSCommunicationClientServer;

import java.awt.EventQueue;
import java.io.IOException;

import fr.DuffautM.WSCommunicationClientServer.Client.ClientWindow;
import fr.DuffautM.WSCommunicationClientServer.Client.Controller;
import fr.DuffautM.WSCommunicationClientServer.Client.Model;
import fr.DuffautM.WSCommunicationClientServer.Server.Server;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Server server = new Server(500);
		server.start(); 
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() 
			{

				ClientWindow view = new ClientWindow();
				Model model = new Model();
				Controller controller = new Controller(model, view);
				view.setVisible(true);
				view.input_Box.requestFocusInWindow();

			}
		});
	}
}

/*
 * TODO
 * Server to client display
 * Logger
 * Cypher
 * */
 