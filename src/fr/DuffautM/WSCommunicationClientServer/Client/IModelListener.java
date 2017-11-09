package fr.DuffautM.WSCommunicationClientServer.Client;

public interface IModelListener {

	public void onServerStateChanged(boolean status);
	public void onUserConnected(String nickname, String ip, boolean newConnection);
	public void onUserDisconnected(String nickname, String ip);
	public void onMessageReceived(String nickname, String ip, String message);
	
}
