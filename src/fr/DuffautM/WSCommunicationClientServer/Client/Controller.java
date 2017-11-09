package fr.DuffautM.WSCommunicationClientServer.Client;

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

		//view.messageBox.setText(view.input_Box.getText() + "\n" + message);
		
	}

	@Override
	public void onNicknameChanged(String nickname) {

		
		
	}

	@Override
	public void onMessageSend(String message) {

		
		
	}

	@Override
	public void onCypherMethodChanged(String cypherMethod) {

		
		
	}

}
