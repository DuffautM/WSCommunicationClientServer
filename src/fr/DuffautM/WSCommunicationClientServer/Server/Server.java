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
	
	public void onClientDisconnection(ClientFromServerSide client)
	{
		System.out.println("[Server] - Client " + client.getSocket().getInetAddress() + " has been disconnected");
		//TODO
		//Remove client from list
		
		synchronized(this.connectedUsers)
		{
			this.connectedUsers.remove(client);
		}
	}
	
	public void onClientMessage(ClientFromServerSide client, String message)
	{
		System.out.println("[Server][" + socket.getInetAddress() +"] Received message : " + message);
		//Send message to all
		broadcastMessage(client, message);
	}
	
	public void broadcastMessage(ClientFromServerSide client, String message )
	{
		String data = "MSG;";
		data += client.getNickname();
		data += ";";
		data += (long)(System.currentTimeMillis()/1000);
		data += ";";
		data += client.getSocket().getInetAddress();
		data += ";";
		data += message;
		
		broadcast(data);
	}
	
	public void broadcast(String message)
	{
		
		ArrayList<ClientFromServerSide> tempCopy;
		
		synchronized (connectedUsers) 
		{
			tempCopy = new ArrayList<>(this.connectedUsers);			
		}
		
		for (ClientFromServerSide client : tempCopy)
		{
			client.write(message);
		}
	}
	
	

	@Override
	public void run() {
		
		while(true)
		{
			try 
			{
				Socket s = socket.accept();
				System.out.println("[Server] - Server available - Connection from " + s.getInetAddress());
				ClientFromServerSide c = new ClientFromServerSide(this, s);
				c.startPollingThread();
				
				synchronized (this.connectedUsers) 
				{
					this.connectedUsers.add(c);					
				}
			} 
			catch (IOException e) 
			{
				System.out.println("[Server] - Server unavailable");
				e.printStackTrace();
			}
		}
		
	}
	
}
