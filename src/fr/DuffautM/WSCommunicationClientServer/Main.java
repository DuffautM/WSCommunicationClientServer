package fr.DuffautM.WSCommunicationClientServer;

import java.awt.EventQueue;

import fr.DuffautM.WSCommunicationClientServer.Client.ClientWindow;
import fr.DuffautM.WSCommunicationClientServer.Client.Controller;
import fr.DuffautM.WSCommunicationClientServer.Client.Model;

public class Main {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() 
			{

				ClientWindow view = new ClientWindow();
				Model model = new Model();
				Controller controller = new Controller(model, view);
				view.setVisible(true);

			}
		});
	}
}
