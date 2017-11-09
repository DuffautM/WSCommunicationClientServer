package fr.DuffautM.WSCommunicationClientServer.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {

	private int port;
	private ServerSocket socket;
	private Thread acceptThread;
	private List<ClientFromServerSide> connectedUsers;
	
	public Server(int port)
	{
		this.port = port;
		
	}
	
	public void start() throws IOException
	{
		this.socket = new ServerSocket(this.port);
		this.acceptThread = new Thread(this);
		this.acceptThread.start();
		System.out.println("[Server] - Listening at port " + this.port);
		this.connectedUsers = new ArrayList<>();
		
	}

	@Override
	public void run() {
		
		while(true)
		{
			try 
			{
				Socket s = socket.accept();
				System.out.println("[Server] - Server available - Connection from " + s.getInetAddress());
				ClientFromServerSide c = new ClientFromServerSide(s);
				connectedUsers.add(c);
			} 
			catch (IOException e) 
			{
				System.out.println("[Server] - Server unavailable");
				e.printStackTrace();
			}
		}
		
	}
	
}
