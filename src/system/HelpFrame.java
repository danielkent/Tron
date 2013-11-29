package system;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * 
 * @author Kent
 * 
 */
public class HelpFrame extends JFrame {

	JPanel panel = new ImagePanel("res\\image\\stats.png");
	JLabel info = new JLabel(
			"<html>OBJECTIVE:<br>The player has to take control of a light cycle and try to cause his/her opponents to crash by running into a light <br> trail, obstacle  or the wall.<br><br><br>CONTROLS:<br> UP: 鈫�or W <br> DOWN: 鈫�or S <br> RIGHT: 鈫�or D <br> LEFT: 鈫�or A <br><br><br>ABOUT:<br> This game was developed as a course project for ECSE 321 by: <br> Simon Lei  <br> Ruofan Wu <br>  Zi Yang Zhang <br> Harry Tran <br> Daniel Kent <br><br> We acknowledge the original version of the game. All Rights Reserved. May not be distributed without the  <br> permission of the authors. 2013 ");
	JButton back = new JButton("Back");

	public HelpFrame() {

		super("TRON: Help");
		setSize(700, 600);
		setLocation(630, 170);

		info.setBounds(50, 100, 400, 400);
		info.setForeground(Color.WHITE);

		panel.add(info);
		panel.add(back);

		getContentPane().add(panel);

		setVisible(true);

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				dispose();
			}
		});
	}

}