package com.driveds.ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UiUtils {

	public static JTextField createTxtField(int width, int height, int x, int y) {
		JTextField txt = new JTextField();
		txt.setBounds(x, y, width, height);
		return txt;
	}
	
	public static JPasswordField createPasswordTxt(int width, int height, int x, int y) {
		JPasswordField txt = new JPasswordField();
		txt.setBounds(x, y, width, height);
		return txt;
	}
	
	public static JLabel createJLabel(int width, int height, int x, int y, String label) {
		JLabel lbl = new JLabel(label);
		lbl.setBounds(x, y, width, height);
		return lbl;
	}
	
	public static JButton createJButton(int width, int height, int x, int y, String label) {
		JButton btn = new JButton(label);
		btn.setBounds(x, y, width, height);
		return btn;
	}
}
