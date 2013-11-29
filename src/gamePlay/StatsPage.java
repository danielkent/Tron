package gamePlay;

/** 
 * @author Simon Lei
 * @version  2013.11.25
 * Class which contains the match statistics page.
 */

import javax.swing.*;

import accountManagement.Account;
import accountManagement.Database;
import system.ImagePanel;

import java.awt.*;
import java.awt.event.*;

public class StatsPage extends JFrame {
	JPanel panel = new ImagePanel("res\\image\\stats.png");
	JLabel topTenHeader = new JLabel("Top 10");
	JButton close = new JButton("Close");
	Database database = new Database();

	/** Class constructor
	 * 
	 */
	public StatsPage(Account playerOne, Account playerTwo) {
		super("Statistics");
		setSize(1200, 800);
		setLocationRelativeTo(null);
		panel.setLayout(null);
		
		String[] topTenList = new String[10];
		database.extractTop10(topTenList);
		
		topTenHeader.setBounds(100, 70, 150, 20);
		topTenHeader.setFont(new Font("Tahoma", Font.BOLD, 20));
		topTenHeader.setForeground(Color.WHITE);
		
		JLabel firstPlayer = new JLabel(topTenList[0]);
		JLabel secondPlayer = new JLabel(topTenList[1]);
		JLabel thirdPlayer = new JLabel(topTenList[2]);
		JLabel fourthPlayer = new JLabel(topTenList[3]);
		JLabel fifthPlayer = new JLabel(topTenList[4]);
		JLabel sixthPlayer = new JLabel(topTenList[5]);
		JLabel seventhPlayer = new JLabel(topTenList[6]);
		JLabel eighthPlayer = new JLabel(topTenList[7]);
		JLabel ninthPlayer = new JLabel(topTenList[8]);
		JLabel tenthPlayer = new JLabel(topTenList[9]);
		
		firstPlayer.setBounds(100, 120, 150, 20);
		firstPlayer.setFont(new Font("Tahoma", Font.BOLD, 16));
		firstPlayer.setForeground(Color.WHITE);
		
		secondPlayer.setBounds(100, 150, 150, 20);
		secondPlayer.setFont(new Font("Tahoma", Font.BOLD, 16));
		secondPlayer.setForeground(Color.WHITE);

		thirdPlayer.setBounds(100, 180, 150, 20);
		thirdPlayer.setFont(new Font("Tahoma", Font.BOLD, 16));
		thirdPlayer.setForeground(Color.WHITE);

		fourthPlayer.setBounds(100, 210, 150, 20);
		fourthPlayer.setFont(new Font("Tahoma", Font.BOLD, 16));
		fourthPlayer.setForeground(Color.WHITE);

		fifthPlayer.setBounds(100, 240, 150, 20);
		fifthPlayer.setFont(new Font("Tahoma", Font.BOLD, 16));
		fifthPlayer.setForeground(Color.WHITE);

		sixthPlayer.setBounds(100, 270, 150, 20);
		sixthPlayer.setFont(new Font("Tahoma", Font.BOLD, 16));
		sixthPlayer.setForeground(Color.WHITE);

		seventhPlayer.setBounds(100, 300, 150, 20);
		seventhPlayer.setFont(new Font("Tahoma", Font.BOLD, 16));
		seventhPlayer.setForeground(Color.WHITE);

		eighthPlayer.setBounds(100, 330, 150, 20);
		eighthPlayer.setFont(new Font("Tahoma", Font.BOLD, 16));
		eighthPlayer.setForeground(Color.WHITE);

		ninthPlayer.setBounds(100, 360, 150, 20);
		ninthPlayer.setFont(new Font("Tahoma", Font.BOLD, 16));
		ninthPlayer.setForeground(Color.WHITE);

		tenthPlayer.setBounds(100, 390, 150, 20);
		tenthPlayer.setFont(new Font("Tahoma", Font.BOLD, 16));
		tenthPlayer.setForeground(Color.WHITE);
		
		close.setBounds(1050, 700, 100, 20);
		
		panel.add(topTenHeader);
		panel.add(firstPlayer);
		panel.add(secondPlayer);
		panel.add(thirdPlayer);
		panel.add(fourthPlayer);
		panel.add(fifthPlayer);
		panel.add(sixthPlayer);
		panel.add(seventhPlayer);
		panel.add(eighthPlayer);
		panel.add(ninthPlayer);
		panel.add(tenthPlayer);
		panel.add(close);

		
		if(playerOne.getName() != null && playerTwo.getName() != null ) {
			JLabel matchHistoryHeader = new JLabel("Match History");
			JLabel playerOneHeader = new JLabel(playerOne.getName(),SwingConstants.CENTER);
			JLabel playerTwoHeader = new JLabel(playerTwo.getName(),SwingConstants.CENTER);
			JLabel dateHeader = new JLabel("Date");
			JLabel winnerHeader = new JLabel("Winner");
			JLabel loserHeader = new JLabel("Loser");						

			String[] columns = {"Date", "Winner", "Loser"};
			
			String[][] matchHistory = new String[20][3];
			String score = database.extractMatchHistory(matchHistory, playerOne.getName(), playerTwo.getName());
			JLabel scoreLabel = new JLabel(score);
			
			JTable history = new JTable(matchHistory, columns) {
				public boolean isCellEditable(int data, int column) {
					return false;
				}
			};
			
			history.setForeground(Color.WHITE);
			history.setBackground(Color.BLACK);
			history.setOpaque(false);
			history.setBounds(400,190,700,600);
			history.setRowHeight(20);
			
			matchHistoryHeader.setBounds(670, 70, 150, 20);
			matchHistoryHeader.setFont(new Font("Tahoma", Font.BOLD, 20));
			matchHistoryHeader.setForeground(Color.WHITE);		
			
			playerOneHeader.setBounds(420, 100, 300, 20);
			playerOneHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
			playerOneHeader.setForeground(Color.YELLOW);		
			
			playerTwoHeader.setBounds(770, 100, 300, 20);
			playerTwoHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
			playerTwoHeader.setForeground(Color.CYAN);
			
			scoreLabel.setBounds(720, 100, 200, 20);
			scoreLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
			scoreLabel.setForeground(Color.WHITE);	
			
			dateHeader.setBounds(410, 160, 200, 20);
			dateHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
			dateHeader.setForeground(Color.WHITE);	
			
			winnerHeader.setBounds(640, 160, 200, 20);
			winnerHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
			winnerHeader.setForeground(Color.WHITE);	
			
			loserHeader.setBounds(870, 160, 200, 20);
			loserHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
			loserHeader.setForeground(Color.WHITE);	
			
			panel.add(history);
			panel.add(matchHistoryHeader);
			panel.add(playerOneHeader);
			panel.add(playerTwoHeader);
			panel.add(scoreLabel);
			panel.add(dateHeader);
			panel.add(winnerHeader);
			panel.add(loserHeader);
			
			
		}

		getContentPane().add(panel);
		setVisible(true);
		
		action();
	}
	
	public void action() {
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				dispose();
			}
		});
	}
}