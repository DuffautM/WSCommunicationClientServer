package fr.DuffautM.WSCommunicationClientServer.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Controller implements IModelListener, IViewListener{

	private Model model;
	private ClientWindow view;
	
	Socket sock;
	PrintWriter out;
	BufferedReader in;
	Thread TGestionConnection;
	
	String rcvd = null;
	
	public Controller(Model model, ClientWindow view) {

		this.model = model;
		this.view = view;
		
		model.addListeners(this);
		view.addListeners(this);
		
	}
	
	public void openConnection()
	{
		try 
		{
			//Open connection
			sock = new Socket("127.0.0.1", 500);
			
			Thread TGestionConnection = new Thread(new GestionDiscussion(sock));
			TGestionConnection.start();
			

		} 
		catch (IOException e) 
		{

		}
		
	}
	
	public void closeConnection()
	{
		try 
		{
			sock.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
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

		openConnection();
		
		try 
		{
			rcvd = in.readLine();
			view.messageBox.append(rcvd);
		} 
		catch (IOException e) 
		{

			e.printStackTrace();
		}
			
		
		
		closeConnection();

	}

	@Override
	public void onNicknameChanged(String nickname) {

		System.out.println("kek");
		
	}

	@Override
	public void onMessageSend(String message) {
		
		openConnection();
		
		
		
		
		

		try {
			//Open connection
			Socket sock = new Socket("127.0.0.1", 500);
			//Write message
			PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
			//out.println(message);
			//Receive message
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

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
