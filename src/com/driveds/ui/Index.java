package com.driveds.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.driveds.controllers.ControllerDirectory;
import com.driveds.service.ScanDirectory;

public class Index extends JFrame {

	private JTextField txtLogin;
	
	private JPasswordField txtSenha;
	
	private JTextField txtEmail;
	
	private JPanel panel;
	
	private JLabel lblLogin;
	
	private JLabel lblSenha;
	
	private JLabel lblEmail;
	
	private JButton btnLogin;
	
	public Index() {
		
		this.setTitle("Dados de Login");
		this.setSize(400, 400);
		
		panel = new JPanel(null);
		
		initComponents();
		
		
		this.add(panel);
	}
	
	private void initComponents() {
		
		lblLogin = UiUtils.createJLabel(60, 24, 10, 10, "Login:");
		panel.add(lblLogin);
	
		lblSenha = UiUtils.createJLabel(60, 24, 10, 40, "Senha:");
		panel.add(lblSenha);
		
		lblEmail = UiUtils.createJLabel(60, 24, 10, 70, "Email:");
		panel.add(lblEmail);
	
		txtLogin = UiUtils.createTxtField(250, 24, 50, 10);
		panel.add(txtLogin);
		
		txtSenha = UiUtils.createPasswordTxt(250, 24, 50, 40);
		panel.add(txtSenha);
		
		txtEmail = UiUtils.createTxtField(250, 24, 50, 70);
		panel.add(txtEmail);
		
		btnLogin = UiUtils.createJButton(70, 24, 50, 100, "Iniciar");
		panel.add(btnLogin);
		
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String login = txtLogin.getText();
				String senha = txtSenha.getText();
				String email = txtEmail.getText();
				
				System.out.println(login);
				System.out.println(senha);
				System.out.println(email);
				
				initThread();
			}
		});
	}
	
	private void initThread() {
		
		Thread t = new Thread(new ScanDirectory());
		t.start();
	}
	
	public static void main(String[] args) {
		
		new Index().setVisible(true);
	}
}
