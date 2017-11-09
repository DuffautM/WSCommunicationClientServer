package fr.DuffautM.WSCommunicationClientServer.Client;

public interface IViewListener {

	public void onNicknameChanged(String nickname);
	public void onMessageSend(String message);
	public void onCypherMethodChanged(String cypherMethod);
	
}
