package fr.DuffautM.WSCommunicationClientServer.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GestionDiscussion implements Runnable{

	Socket socket;
	
	Thread TSending;
	Thread TReception;
	
	public GestionDiscussion(Socket socket) 
	{
		this.socket = socket;	
		
	}

	@Override
	public void run() {
		try
		{
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
			
			TSending = new Thread(new Sending(out));
			TReception = new Thread(new Reception(in));			
			
		}
		catch (Exception e) 
		{

		}

		
	}
	
	
	
}
