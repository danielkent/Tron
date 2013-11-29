
package accountManagement;

/** 
 * @author Simon Lei, Daniel Dominic
 * @version  2013.11.25
 * Class which contains the sign up window.
 */
import javax.swing.*;

import accountManagement.Database;

import java.awt.*;
import java.awt.event.*;

import system.ImagePanel;

public class SignUpPage extends JFrame {
	JPanel panel = new ImagePanel("res\\image\\signup.png");
	JLabel nameLabel = new JLabel("Name");
	public JTextField nameInput = new JTextField(15);
	JLabel passLabel = new JLabel("Create password");
	public JPasswordField passInput = new JPasswordField(15);
	public JButton signUp = new JButton("Sign Up");
	Database database = new Database();
	public boolean testing = false;

	/** Class constructor
	 * 
	 */
	public SignUpPage() {
		super("Tron Sign Up");
		setSize(300, 300);
		setLocationRelativeTo(null);
		panel.setLayout(null);

		nameLabel.setBounds(50, 70, 150, 20);
		nameLabel.setForeground(Color.WHITE);
		nameInput.setBounds(120, 70, 150, 20);
		passLabel.setBounds(10, 100, 150, 20);
		passLabel.setForeground(Color.WHITE);
		passInput.setBounds(120, 100, 150, 20);
		signUp.setBounds(150, 150, 80, 20);

		panel.add(nameLabel);
		panel.add(nameInput);
		panel.add(passLabel);
		panel.add(passInput);
		panel.add(signUp);

		getContentPane().add(panel);
		setVisible(true);
		action();
	}

	/** 
	 * Action listeners and events for the sign up button.
	 */
	public void action() {
		signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String puname = nameInput.getText();
				String ppaswd = passInput.getText();
				int message = database.add(puname, ppaswd);

				if (message == 1) {
					if(!testing) {
						JOptionPane.showMessageDialog(null,"Invalid password: password must contain at least one number, one lowercase letter, one uppercase letter and one non-alphanumeric character.");
					}
					nameInput.setText("");
					passInput.setText("");
					nameInput.requestFocus();
				}

				else if (message == 2) {
					if(!testing) {
						JOptionPane.showMessageDialog(null,"Username already exists!");
					}
					nameInput.setText("");
					passInput.setText("");
					nameInput.requestFocus();
				}

				else if (message == 3) {
					if(!testing) {
						JOptionPane.showMessageDialog(null,"Account has been created!");
					}
					dispose();
				}

				else if (message == 4) {
					if(!testing) {
						JOptionPane.showMessageDialog(null,"Invalid password: password must be at least 8 characters long.");
					}
					nameInput.setText("");
					passInput.setText("");
					nameInput.requestFocus();
				}

				else if (message == 5) {
					if(!testing) {
						JOptionPane.showMessageDialog(null,"You must enter a username.");
					}
					nameInput.setText("");
					passInput.setText("");
					nameInput.requestFocus();
				}

				else if (message == 6) {
					if(!testing) {
						JOptionPane.showMessageDialog(null,"Username must contain only alphanumeric characters.");
					}
					nameInput.setText("");
					passInput.setText("");
					nameInput.requestFocus();
				}
			}
		});
	}
}