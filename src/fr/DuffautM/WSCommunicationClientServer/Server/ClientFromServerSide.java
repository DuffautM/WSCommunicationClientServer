package fr.DuffautM.WSCommunicationClientServer.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientFromServerSide implements Runnable {
	
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private Server parent;
	private Thread thread;
	private String nickname = "Anonymous";

	public ClientFromServerSide(Server parent, Socket socket) throws IOException 
	{
		this.parent = parent;
		this.socket = socket;	
	    this.out = new PrintWriter(socket.getOutputStream(), true);
	    this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
	}
	
	public Socket getSocket()
	{
		return this.socket;
	}

	public void startPollingThread() {

		this.thread = new Thread(this);
		this.thread.start();
		
	}
	
	public boolean close()
	{
		try
		{
			this.thread.interrupt();
			this.in.close();
			this.out.close();
			this.socket.close();
			this.parent.onClientDisconnection(this);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public boolean write(String data)
	{
		this.out.println(data);
		
		try
		{
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	@Override
	public void run() {

		String read;
		
		while(true)
		{
			try 
			{
				read = this.in.readLine();
				if(read == null)
				{
					close();
					return;
				}
				
				parent.onClientMessage(this, read);
				
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
		}
		
		
	}

	public String getNickname() {
		// TODO Auto-generated method stub
		return this.nickname ;
	}
	
	


}
