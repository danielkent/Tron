package system;

/** 
 * @author Simon Lei, Daniel Dominic
 * @version  2013.11.25
 * Class which contains the game menu. This is where users log in, start games, view the help menu and view statistics
 */

import gamePlay.GamePlayUI;
import gamePlay.GridMap;
import gamePlay.StatsPage;

import javax.swing.*;

import accountManagement.Account;
import accountManagement.Database;
import accountManagement.SignUpPage;
import system.HelpFrame;
import system.ImagePanel;

import java.awt.*;
import java.awt.event.*;

public class GameMenu extends JFrame {

	String[] maps = { "Easy", "Medium", "Hard" };
	public boolean testing = false;

	Account playerOne;
	Account playerTwo;
	Database database = new Database();
	public StatsPage statsPage;
	public HelpFrame helpFrame;
	public SignUpPage signUpPage;

	JPanel panel = new ImagePanel("res\\image\\tron.png");

	JLabel choseMap = new JLabel("Please select a map:");
	JComboBox mapsList = new JComboBox(maps);

	public JButton start = new JButton("Start");
	public JButton signup = new JButton("Sign Up");
	public JButton stats = new JButton("Statistics");
	public JButton help = new JButton("Help");

	JLabel pOneHeader = new JLabel("  Player 1");
	JLabel pOneNameLabel = new JLabel("  Name");
	public JTextField pOneNameInput = new JTextField(15);
	JLabel pOnePassLabel = new JLabel("  Password");
	public JPasswordField pOnePassInput = new JPasswordField(15);
	public JButton pOneLogIn = new JButton("Login");
	public JButton pOneLogOut = new JButton("Logout");
	public JLabel pOneLoggedIn;

	JLabel pTwoHeader = new JLabel("  Player 2");
	JLabel pTwoNameLabel = new JLabel("  Name");
	JTextField pTwoNameInput = new JTextField(15);
	JLabel pTwoPassLabel = new JLabel("  Password");
	JPasswordField pTwoPassInput = new JPasswordField(15);
	JButton pTwoLogIn = new JButton("Login");
	JButton pTwoLogOut = new JButton("Logout");
	JLabel pTwoLoggedIn;
	
	/** 
	 * Class constructor. Sets the properties of the panel components and adds the panel to the frame.
	 * @param playerOne imports object of Account
	 * @param playerTwo imports object of Account
	 */
	
	public GameMenu(Account playerOne, Account playerTwo) {
		super("Tron: MENU");
		setSize(1200, 800);
		setLocationRelativeTo(null);
		panel.setLayout(null);

		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		
		// The following are the properties of the buttons (minus login and logout)
		mapsList.setBounds(930, 100, 100, 20);
		choseMap.setFont(new Font("Tahoma", Font.BOLD, 13));
		choseMap.setForeground(Color.WHITE);
		choseMap.setBounds(915, 80, 200, 20);
		start.setBounds(930, 450, 100, 20);
		signup.setBounds(930, 490, 100, 20);
		stats.setBounds(930, 530, 100, 20);
		help.setBounds(930,570,100,20);

		// The following are the properties of the player 1 login space.
	
		pOneHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
		pOneHeader.setForeground(Color.YELLOW);
		pOneHeader.setBounds(80, 180, 100, 20);
		pOneNameLabel.setForeground(Color.YELLOW);
		pOneNameLabel.setBounds(80, 220, 50, 20);
		pOneNameInput.setBounds(160, 220, 150, 27);
		pOneNameInput.setFont(new Font("Tahoma", Font.BOLD, 14));
		pOnePassLabel.setForeground(Color.YELLOW);
		pOnePassLabel.setBounds(80, 255, 70, 20);
		pOnePassInput.setBounds(160, 255, 150, 27);
		pOneLogIn.setBounds(230, 290, 80, 20);
		pOneLogOut.setBounds(230, 290, 80, 20);

		// The following are the properties of the player 2 login space.
		pTwoHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
		pTwoHeader.setForeground(Color.CYAN);
		pTwoHeader.setBounds(80, 400, 100, 20);
		pTwoNameLabel.setForeground(Color.CYAN);
		pTwoNameLabel.setBounds(80, 440, 50, 20);
		pTwoNameInput.setBounds(160, 440, 150, 27);
		pTwoNameInput.setFont(new Font("Tahoma", Font.BOLD, 14));
		pTwoPassLabel.setForeground(Color.CYAN);
		pTwoPassLabel.setBounds(80, 475, 70, 20);
		pTwoPassInput.setBounds(160, 475, 150, 27);
		pTwoLogIn.setBounds(230, 510, 80, 20);
		pTwoLogOut.setBounds(230, 510, 80, 20);

		panel.add(mapsList);
		panel.add(choseMap);
		panel.add(start);
		panel.add(signup);
		panel.add(stats);
		panel.add(help);

		panel.add(pOneHeader);
		panel.add(pOneNameLabel);
		panel.add(pOneLogIn);
		panel.add(pOneLogOut);
		panel.add(pOneNameInput);
		panel.add(pOnePassLabel);
		panel.add(pOnePassInput);

		panel.add(pTwoHeader);
		panel.add(pTwoNameLabel);
		panel.add(pTwoLogIn);
		panel.add(pTwoLogOut);
		panel.add(pTwoNameInput);
		panel.add(pTwoPassLabel);
		panel.add(pTwoPassInput);
		
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		// Condition to check if players are already logged in.
		if(playerOne.getName()!=null && playerTwo.getName()!=null ) {
			pOneHeader.setVisible(false);
			pOneNameLabel.setVisible(false);
			pOneNameInput.setVisible(false);
			pOnePassLabel.setVisible(false);
			pOnePassInput.setVisible(false);	
			pTwoHeader.setVisible(false);
			pTwoNameLabel.setVisible(false);
			pTwoNameInput.setVisible(false);
			pTwoPassLabel.setVisible(false);
			pTwoPassInput.setVisible(false);	
			pOneLogIn.setVisible(false);
			pTwoLogIn.setVisible(false);	
			
			pOneLoggedIn = new JLabel("Player 1: " + playerOne.getName());
			pOneLoggedIn.setFont(new Font("Tahoma", Font.BOLD, 15));
			pOneLoggedIn.setForeground(Color.YELLOW);
			pOneLoggedIn.setBounds(80, 180, 200, 20);	
			pTwoLoggedIn = new JLabel("Player 2: " + playerTwo.getName());
			pTwoLoggedIn.setFont(new Font("Tahoma", Font.BOLD, 15));
			pTwoLoggedIn.setForeground(Color.CYAN);
			pTwoLoggedIn.setBounds(80, 400, 200, 20);
			panel.add(pOneLoggedIn);
			panel.add(pTwoLoggedIn);
		}
		else {
			pOneLogOut.setVisible(false);
			pTwoLogOut.setVisible(false);
		}
		actionLogin();
		actionButtons();
	}
	
	/** 
	 * Action listeners and events for the login and logout buttons.
	 */
	public void actionLogin() {
		pOneLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String puname = pOneNameInput.getText();
				String ppaswd = pOnePassInput.getText();
				if (database.verify(puname, ppaswd)) {
					// checks to see if input user is already logged in
					if (puname.equals(playerTwo.getName())) {
						if(!testing) {
							JOptionPane.showMessageDialog(null,"That user is already logged in");
						}
					} else {
						playerOne.setName(puname);
						pOneHeader.setVisible(false);
						pOneNameLabel.setVisible(false);
						pOneNameInput.setVisible(false);
						pOnePassLabel.setVisible(false);
						pOnePassInput.setVisible(false);
						pOneLogIn.setVisible(false);
						pOneLogOut.setVisible(true);
						pOneLoggedIn = new JLabel("Player 1: " + playerOne.getName());
						pOneLoggedIn.setFont(new Font("Tahoma", Font.BOLD, 15));
						pOneLoggedIn.setForeground(Color.YELLOW);
						pOneLoggedIn.setBounds(80, 180, 200, 20);	
						panel.add(pOneLoggedIn);
						pOneLoggedIn.setVisible(true);

					}
				} else {
					if(!testing) {
						JOptionPane.showMessageDialog(null,"Wrong Password / Username");
					}
					pOneNameInput.setText("");
					pOnePassInput.setText("");
					pOneNameInput.requestFocus();
				}

			}
		});

		pTwoLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String puname = pTwoNameInput.getText();
				String ppaswd = pTwoPassInput.getText();
				if (database.verify(puname, ppaswd)) {
					// checks to see if input user is already logged in
					if (puname.equals(playerOne.getName())) {
						if(!testing) {
							JOptionPane.showMessageDialog(null,"That user is already logged in");
						}
					} else {
						playerTwo.setName(puname);
						pTwoHeader.setVisible(false);
						pTwoNameLabel.setVisible(false);
						pTwoNameInput.setVisible(false);
						pTwoPassLabel.setVisible(false);
						pTwoPassInput.setVisible(false);
						pTwoLogIn.setVisible(false);
						pTwoLogOut.setVisible(true);
						pTwoLoggedIn = new JLabel("Player 2: " + playerTwo.getName());
						pTwoLoggedIn.setFont(new Font("Tahoma", Font.BOLD, 15));
						pTwoLoggedIn.setForeground(Color.CYAN);
						pTwoLoggedIn.setBounds(80, 400, 200, 20);
						panel.add(pTwoLoggedIn);
						pTwoLoggedIn.setVisible(true);

					}
				} else {
					if(!testing) {
						JOptionPane.showMessageDialog(null,"Wrong Password / Username");
					}
					pTwoNameInput.setText("");
					pTwoPassInput.setText("");
					pTwoNameInput.requestFocus();
				}

			}
		});

		pOneLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				playerOne.setName(null);
				pOneLogIn.setVisible(true);
				pOneLogOut.setVisible(false);
				pOneHeader.setVisible(true);
				pOneNameLabel.setVisible(true);
				pOneNameInput.setVisible(true);
				pOnePassLabel.setVisible(true);
				pOnePassInput.setVisible(true);	
				pOneLoggedIn.setVisible(false);

			}
		});

		pTwoLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				playerTwo.setName(null);
				pTwoLogIn.setVisible(true);
				pTwoLogOut.setVisible(false);
				pTwoHeader.setVisible(true);
				pTwoNameLabel.setVisible(true);
				pTwoNameInput.setVisible(true);
				pTwoPassLabel.setVisible(true);
				pTwoPassInput.setVisible(true);	
				pTwoLoggedIn.setVisible(false);
	
			}
		});
	}

	/**
	 * Action listeners and events for all buttons except those in actionLogin.
	 */
	public void actionButtons() {
		signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				signUpPage = new SignUpPage();
			}
		});

		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (playerOne.getName() == null || playerTwo.getName() == null) {
					if(!testing) {
						JOptionPane.showMessageDialog(null,"Both players need to be logged in to play.");
					}
				} else {
					String select = (String) mapsList.getSelectedItem();
					GridMap map = null;
					if (select.equals("Easy")) {

						map = new GridMap(750, 500);
						

					}
					if (select.equals("Medium")) {

						int[] xObstacle = { 150, 250, 500, 600 };
						int[] yObstacle = { 200, 300, 200, 300 };
						map = new GridMap(750, 500, xObstacle,
								yObstacle);

					}
					if (select.equals("Hard")) {
		
						int[] xObstacle = { 50, 250, 300, 450, 500, 700 };
						int[] yObstacle = { 50, 250, 200, 300, 250, 450 };
						map = new GridMap(750, 500, xObstacle,
								yObstacle);

					}

					GamePlayUI game = new GamePlayUI(map,playerOne,playerTwo);

					dispose();
				}
			}
		});

		stats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				statsPage = new StatsPage(playerOne, playerTwo);
			}
		});
		
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				helpFrame = new HelpFrame();
			}
		});

	}
}