package com.driveds.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.driveds.controllers.ControladorUsuario;
import com.driveds.exceptions.PasswordIncorrectException;
import com.driveds.exceptions.UserNotFoundException;
import com.driveds.service.ScanDirectory;

public class Index extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField txtLogin;
	
	private JPasswordField txtSenha;
	
	private JPanel panel;
	
	private JLabel lblLogin;
	
	private JLabel lblSenha;
	
	private JButton btnLogin;
	
	private static Index index;
	
	public Index() {
		
		this.setTitle("Dados de Login");
		this.setSize(350, 150);
		centerWindow(this);
		
		panel = new JPanel(null);
		
		initComponents();
		
		this.add(panel);
	}
	
	public static void centerWindow(JFrame frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	private void initComponents() {
		
		lblLogin = UiUtils.createJLabel(60, 24, 10, 10, "Login:");
		panel.add(lblLogin);
	
		lblSenha = UiUtils.createJLabel(60, 24, 10, 40, "Senha:");
		panel.add(lblSenha);
		
		txtLogin = UiUtils.createTxtField(250, 24, 50, 10);
		panel.add(txtLogin);
		
		txtSenha = UiUtils.createPasswordTxt(250, 24, 50, 40);
		panel.add(txtSenha);
		
		btnLogin = UiUtils.createJButton(100, 24, 125, 75, "Iniciar");
		panel.add(btnLogin);
		final Component c = this;
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String login = txtLogin.getText();
				String senha = txtSenha.getText();
				
				if (!ControladorUsuario.isDadosValido(login, senha)) {
					JOptionPane.showMessageDialog(c, "Todos os campo sáo obrigatórios");
					return;
				}
				
				try {
					ControladorUsuario.userIsValid(login, senha);
				} catch (HeadlessException | IOException | UserNotFoundException | PasswordIncorrectException e1) {
					JOptionPane.showMessageDialog(c, e1.getMessage());
					return;
				}
				
				initThread(login);
				index.setVisible(false);
			}
		});
	}
	
	private void initThread(String login) {
		Thread t = new Thread(new ScanDirectory(login));
		t.start();
	}
	
	public static void main(String[] args) {
		index = new Index();
		index.setVisible(true);
	}
	
	public static void visibleWindow() {
		index.setVisible(true);
	}

	public static void invisibleWindow() {
		index.setVisible(true);
	}
}
