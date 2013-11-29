package gamePlay;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

import accountManagement.Account;
import accountManagement.Database;
import accountManagement.Player;


/**
 * @author ZiYang, Simon Lei
 * @version  2013.11.25
 * Class which contains the gameplay GUI.
 */

@SuppressWarnings("serial")
public class GamePlayUI extends JPanel implements ActionListener, KeyListener {
        
		JFrame frame = new JFrame("Tron");
        public Timer timer = new Timer(8, this);        
        int xPlayer1Coord, yPlayer1Coord, xDirPlayer1Speed = 1, yDirPlayer1Speed = 0;
        int WIDTH = 3, HEIGHT = 3;
        int xPlayer2Coord, yPlayer2Coord, xDirPlayer2Speed = -1, yDirPlayer2Speed = 0;
        int prevXDirPlayer1Speed, prevYDirPlayer1Speed, prevXDirPlayer2Speed, prevYDirPlayer2Speed;
        int gameStatus;
        boolean paused = false;
        int action = 0;
        int initialPaint = 0;
        public GamePlay game;
        private Account playerOne;
        private Account playerTwo;
        
        public GamePlayUI(GridMap selectedMap, Account playerOne, Account playerTwo){
        	game = new GamePlay(selectedMap,playerOne,playerTwo);
            this.playerOne = playerOne;
            this.playerTwo = playerTwo;
            this.gameStatus = 0;
            this.xPlayer1Coord = 5;
            this.yPlayer1Coord = game.gameplayMap.yDimension - 10;
            this.xPlayer2Coord = game.gameplayMap.xDimension - 10;
            this.yPlayer2Coord = 5;
            
            JPanel panel1 = new JPanel();
            JPanel panel2 = new JPanel();
            ImageIcon image1 = new ImageIcon("res\\image\\pic4.jpg");
            ImageIcon image2 = new ImageIcon("res\\image\\pic5.jpg");

            Image imageTemp1 = image1.getImage(); // transform it 
            Image newimg1 = imageTemp1.getScaledInstance((int)(game.gameplayMap.yDimension*0.488), game.gameplayMap.yDimension, Image.SCALE_AREA_AVERAGING); // scale it the smooth way 
            image1 = new ImageIcon(newimg1); // transform it back

            Image imageTemp2 = image2.getImage(); // transform it 
            Image newimg2 = imageTemp2.getScaledInstance((int)(game.gameplayMap.yDimension*0.488), game.gameplayMap.yDimension, Image.SCALE_AREA_AVERAGING); // scale it the smooth way 
            image2 = new ImageIcon(newimg2); // transform it back

            //label used to hold image
            JLabel back1 = new JLabel ();
            back1.setBounds(0,0,100,100);	
            back1.setIcon(image1);

            JLabel back2 = new JLabel ();
            back2.setBounds(0,0,100,100);
            back2.setIcon(image2);
            panel1.add(back1);
            panel2.add(back2);
            addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false); 
            this.setSize(game.gameplayMap.xDimension+20, game.gameplayMap.yDimension+50);
            Border b = BorderFactory.createLineBorder(Color.BLACK);
            this.setBorder(b);
            this.setBackground(Color.BLACK);
            frame.add(panel1,BorderLayout.LINE_START);
			frame.add(this,BorderLayout.CENTER);
            frame.add(panel2,BorderLayout.LINE_END);
			frame.setSize((int)(game.gameplayMap.xDimension+game.gameplayMap.yDimension*2*0.488)+30,(game.gameplayMap.yDimension+40)+10);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
            timer.start();

        }
        
        public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(initialPaint == 0) {
                	g.setColor(Color.BLUE);    // set the drawing color   
                	for(int i = 0; i < game.gameplayMap.xDimension ;i=i+10){
                		g.drawLine(i, 0, i, game.gameplayMap.yDimension);
                	}
                	for(int i = 0; i < game.gameplayMap.yDimension;i=i+10){
                		g.drawLine(0, i, game.gameplayMap.xDimension, i);
                	}
                	g.setColor(Color.WHITE); 
                	for(int i = 0; i < game.gameplayMap.yDimension; i++) {
                		for(int j = 0; j < game.gameplayMap.xDimension; j++) {
                			if(game.gameplayMap.coordinate[i][j] == 1) {
                                g.fillRect(j, i, 1, 1);
                			}
                		}
                	}
                	initialPaint++;
                }
        
                g.setColor(Color.YELLOW);
                g.fillRect(xPlayer1Coord,yPlayer1Coord,WIDTH,HEIGHT);
                
                g.setColor(Color.CYAN);
                g.fillRect(xPlayer2Coord, yPlayer2Coord, WIDTH, HEIGHT);
                
        }
        
        public void actionPerformed(ActionEvent e) {
            xPlayer1Coord = xPlayer1Coord + xDirPlayer1Speed;
            yPlayer1Coord = yPlayer1Coord + yDirPlayer1Speed;

            xPlayer2Coord = xPlayer2Coord + xDirPlayer2Speed;
            yPlayer2Coord = yPlayer2Coord + yDirPlayer2Speed;
                    
            if(game.player1Wins(xPlayer2Coord,yPlayer2Coord)) {
            	timer.stop();
            	playerOne.addWin();
            	if(playerOne.getScore() == 2) {
            		Database.addWin(playerOne.getName());
            		Database.writeHeadtoHead(playerOne.getName(), playerTwo.getName());
            	}
                WinnerMessage roundOver = new WinnerMessage(playerOne.getName(),playerOne,playerTwo);
                frame.dispose();
            }
              
            if(game.player2Wins(xPlayer1Coord,yPlayer1Coord)) {
            	timer.stop();
            	playerTwo.addWin();
            	if(playerTwo.getScore() == 2) {
            		Database.addWin(playerTwo.getName());
            		Database.writeHeadtoHead(playerTwo.getName(), playerOne.getName());
            	}
                WinnerMessage roundOver = new WinnerMessage(playerTwo.getName(),playerOne,playerTwo);
                frame.dispose();
            }
              
            game.gameplayMap.occupyBlock(xPlayer1Coord, yPlayer1Coord);
            game.gameplayMap.occupyBlock(xPlayer2Coord, yPlayer2Coord);
                    
            if(action == 0) {
                  repaint(xPlayer1Coord,yPlayer1Coord,WIDTH,HEIGHT);
                  action ++;
            }
            else {
                  repaint(xPlayer2Coord,yPlayer2Coord,WIDTH,HEIGHT);
                  action --;
            }
                        
        }

        @Override
        public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                
                if(key == KeyEvent.VK_P && !paused) {
                        paused = true;
                        timer.stop();
                }
                
                else if(key == KeyEvent.VK_U && paused) {
                        paused = false;
                        timer.restart();
                }
                
                else if(key == KeyEvent.VK_LEFT && !paused && game.player2.orientation != Player.RIGHT) {
                        xDirPlayer2Speed = -1;
                        yDirPlayer2Speed = 0;
                        game.player2.setOrientation(Player.LEFT);
                }
                
                else if(key == KeyEvent.VK_RIGHT && !paused&& game.player2.orientation != Player.LEFT) {
                        xDirPlayer2Speed = 1;
                        yDirPlayer2Speed = 0;
                        game.player2.setOrientation(Player.RIGHT);
                }
                
                else if(key == KeyEvent.VK_UP && !paused && game.player2.orientation != Player.DOWN) {
                        xDirPlayer2Speed = 0;
                        yDirPlayer2Speed = -1;
                        game.player2.setOrientation(Player.UP);
                }
                
                else if(key == KeyEvent.VK_DOWN && !paused && game.player2.orientation != Player.UP) {
                        xDirPlayer2Speed = 0;
                        yDirPlayer2Speed = 1;
                        game.player2.setOrientation(Player.DOWN);
                }
                
                else if(key == KeyEvent.VK_A && !paused && game.player1.orientation != Player.RIGHT) {
                        xDirPlayer1Speed = -1;
                        yDirPlayer1Speed = 0;
                        game.player1.setOrientation(Player.LEFT);
                }
                
                else if(key == KeyEvent.VK_D && !paused && game.player1.orientation != Player.LEFT) {
                        xDirPlayer1Speed = 1;
                        yDirPlayer1Speed = 0;
                        game.player1.setOrientation(Player.RIGHT);
                }
                
                else if(key == KeyEvent.VK_W && !paused && game.player1.orientation != Player.DOWN) {
                        xDirPlayer1Speed = 0;
                        yDirPlayer1Speed = -1;
                        game.player1.setOrientation(Player.UP);
                }
                
                else if(key == KeyEvent.VK_S && !paused && game.player1.orientation != Player.UP) {
                        xDirPlayer1Speed = 0;
                        yDirPlayer1Speed = 1;
                        game.player1.setOrientation(Player.DOWN);
                }
        
        }
        public int getGameStatus(){
                return gameStatus;
        }
        
        @Override        
        public void keyTyped(KeyEvent e){}
                
        @Override
        public void keyReleased(KeyEvent e){}
        
        
}