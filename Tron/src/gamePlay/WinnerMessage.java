package gamePlay;

/** 
 * @author Simon Lei, Zi Yang Zhang
 * @version  2013.11.25
 * Class which contains the window displayed in transition between game rounds.
 */

import javax.swing.*;

import accountManagement.Account;
import java.awt.*;
import java.awt.event.*;

import system.ImagePanel;
import system.GameMenu;


public class WinnerMessage extends JFrame {
	Account playerOne;
	Account playerTwo;
	
	String[] maps = { "Easy", "Medium", "Hard" };
	
	JLabel choseMap = new JLabel("Map:");
	JComboBox mapsList = new JComboBox(maps);
	JPanel panel = new ImagePanel("res\\image\\signup.png");
	JLabel winnerLabel;
	JLabel scoreLabel;
	JButton changeFrame;
	int pullFrame;

	/** Class constructor
	 * 
	 */
	public WinnerMessage(String winner, Account playerOne, Account playerTwo) {
		super("GameOver");
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		setSize(360, 360);
		setLocationRelativeTo(null);
		panel.setLayout(null);
		winnerLabel = new JLabel(winner + " wins!",SwingConstants.CENTER);
		scoreLabel = new JLabel(playerOne.getScore() + " : " + playerTwo.getScore(),SwingConstants.CENTER);

		winnerLabel.setBounds(10, 50, 350, 20);
		winnerLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		winnerLabel.setForeground(Color.WHITE);
		scoreLabel.setBounds(10, 80, 350, 20);
		scoreLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		scoreLabel.setForeground(Color.WHITE);
		
		mapsList.setBounds(150, 200, 100, 20);
		choseMap.setFont(new Font("Tahoma", Font.BOLD, 13));
		choseMap.setForeground(Color.WHITE);
		choseMap.setBounds(110, 200, 200, 20);
		
		if(playerOne.getScore() == 2 || playerTwo.getScore() == 2 ) {
			changeFrame = new JButton("Match Over, Return to Menu");
			pullFrame = 1;
		}
		else {
			changeFrame = new JButton("Next Round");	
			pullFrame = 2;
		}
		changeFrame.setBounds(80, 250, 200, 20);

		panel.add(winnerLabel);
		panel.add(scoreLabel);
		panel.add(changeFrame);
		panel.add(mapsList);
		panel.add(choseMap);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		getContentPane().add(panel);
		setVisible(true);
		action();
	}

	public void action() {
		changeFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(pullFrame == 1) {
					playerOne.resetScore();
					playerTwo.resetScore();
					GameMenu menu = new GameMenu(playerOne,playerTwo);
					dispose();
				}
				else {
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
	}
}