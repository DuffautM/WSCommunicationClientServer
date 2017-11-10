package fr.DuffautM.WSCommunicationClientServer.Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import sun.awt.RequestFocusController;
import sun.awt.CausedFocusEvent.Cause;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.awt.ScrollPane;
import javax.swing.JComboBox;
import java.util.List;
import javax.swing.JList;
import javax.swing.SwingConstants;

public class ClientWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8047634998916119157L;
	private JPanel contentPane;
	private JPanel right_text_panel;
	private JPanel left_display_panel;
	private JPanel bottom_input_panel;
	private JScrollPane scrollPane;
	protected JTextArea messageBox;
	public JTextField input_Box;
	protected JTextField txtNickname;
	
	private List<IViewListener> listeners;

	/**
	 * Create the frame.
	 */
	public ClientWindow() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.listeners = new ArrayList<>();
		
		
		
		setTitle("CypherChat 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		left_display_panel = new JPanel();
		
		bottom_input_panel = new JPanel();
		
		right_text_panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(left_display_panel, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(right_text_panel, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE))
				.addComponent(bottom_input_panel, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(right_text_panel, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
						.addComponent(left_display_panel, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bottom_input_panel, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
		);
		
		txtNickname = new JTextField();
		txtNickname.setText("Anonymous");
		txtNickname.setHorizontalAlignment(SwingConstants.CENTER);
		txtNickname.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtNickname.setForeground(SystemColor.desktop);
		txtNickname.setColumns(10);
		txtNickname.setBackground(SystemColor.control);
		txtNickname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		txtNickname.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				notifyEvent("onNicknameChanged", txtNickname.getText());
				
			}
		});
		
		JComboBox comboBox = new JComboBox();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_left_display_panel = new GroupLayout(left_display_panel);
		gl_left_display_panel.setHorizontalGroup(
			gl_left_display_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_left_display_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_left_display_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
						.addComponent(txtNickname, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
						.addComponent(comboBox, 0, 124, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_left_display_panel.setVerticalGroup(
			gl_left_display_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_left_display_panel.createSequentialGroup()
					.addComponent(txtNickname, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
		);
		
		JList users_list = new JList();
		scrollPane_1.setViewportView(users_list);
		left_display_panel.setLayout(gl_left_display_panel);
		input_Box = new JTextField();
		input_Box.setColumns(10);
		
		input_Box.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String message = input_Box.getText();
				notifyEvent("onMessageSend", message);
				input_Box.setText("");
				
			}
		});
		
		JButton send_button = new JButton("Send");
		send_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String message = input_Box.getText();
				notifyEvent("onMessageSend", message);
				input_Box.setText("");
				
			}
		});
		GroupLayout gl_bottom_input_panel = new GroupLayout(bottom_input_panel);
		gl_bottom_input_panel.setHorizontalGroup(
			gl_bottom_input_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_bottom_input_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(input_Box, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(send_button)
					.addContainerGap())
		);
		gl_bottom_input_panel.setVerticalGroup(
			gl_bottom_input_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bottom_input_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_bottom_input_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(input_Box, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(send_button))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		bottom_input_panel.setLayout(gl_bottom_input_panel);
		right_text_panel.setLayout(new CardLayout(0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		right_text_panel.add(scrollPane, "name_97292847713645");
		
		messageBox = new JTextArea();
		messageBox.setEditable(false);
		scrollPane.setViewportView(messageBox);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void addListeners(IViewListener listener)
	{
		this.listeners.add(listener);
	}
	
	public void removeListenenrs(IViewListener listener)
	{
		this.listeners.remove(listener);
	}
	
	public void notifyEvent(String methodName, Object... args)
	{
		//Object... == Object[n]
		
		Method methodCall = null;
		
		for (Method method : IViewListener.class.getMethods())
		{
			if(methodName.equals(method.getName()))
			{
				methodCall = method;
				break;
			}
		}
		 if(methodCall == null)
		 {
			 throw new IllegalArgumentException("Event " + methodName + " doesn't exist");
		 }
		
		
		for(IViewListener listener : this.listeners)
		{
			try 
			{
				methodCall.invoke(listener, args);
			} 
			
			catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
}
