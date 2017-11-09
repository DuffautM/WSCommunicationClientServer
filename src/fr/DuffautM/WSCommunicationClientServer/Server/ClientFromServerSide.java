package fr.DuffautM.WSCommunicationClientServer.Server;

import java.net.Socket;

public class ClientFromServerSide {
	
	private Socket socket;

	public ClientFromServerSide(Socket socket) 
	{
		this.socket = socket;
	}
	
	public Socket getSocket()
	{
		return this.socket;
	}
	


}
