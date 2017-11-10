package fr.DuffautM.WSCommunicationClientServer.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Controller implements IModelListener, IViewListener{

	private Model model;
	private ClientWindow view;
	
	public Controller(Model model, ClientWindow view) {

		this.model = model;
		this.view = view;
		
		model.addListeners(this);
		view.addListeners(this);
		
	}

	@Override
	public void onServerStateChanged(boolean status) {

		
		
	}

	@Override
	public void onUserConnected(String nickname, String ip, boolean newConnection) {

		
		
	}

	@Override
	public void onUserDisconnected(String nickname, String ip) {

		
		
	}

	@Override
	public void onMessageReceived(String nickname, String ip, String message) {

		//view.messageBox.setText(message);
		
	}

	@Override
	public void onNicknameChanged(String nickname) {

		System.out.println("kek");
		
	}

	@Override
	public void onMessageSend(String message) {

		try {
			//Open connection
			Socket sock = new Socket("127.0.0.1", 500);
			//Write message
			PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
			//out.println(message);
			//Receive message
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			out.println("NCK;Anon");
			out.println("MSG;" + message);
			String rcvd = in.readLine();
			//out.println("[Client] - " + rcvd);
			out.close();
			sock.close();
		} 
		catch (IOException e) 
		{
			System.out.println("[Client] - Impossible to connect");
			e.printStackTrace();
		}
		
	}

	@Override
	public void onCypherMethodChanged(String cypherMethod) {

		
		
	}

}
