package accountManagement;

/**
 * @author Harry Tran, Simon Lei
 * @version  2013.11.25
 * This class contains the methods for file I/O
 * 
 */

import java.io.*;
import java.util.Date;
import java.util.Stack;

public class Database {
	
	public static void main(String[] arg) {
		writeHeadtoHead("Two", "One");
		displayMatchHistory("One","Two");
	}
	
	/**
	 * Prints the database file.
	 * @return if the operation was successful.
	 */
	public static boolean readFile() {

		String fileName = "database.csv";
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}

			bufferedReader.close();
			return true;
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
			return false;
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			return false;
		}
	}

	/**
	 *  See if username and password exists in database.
	 * @param username input username.
	 * @param password input password.
	 * @return true if the username and associated password exist in database.
	 */
	public static boolean verify(String username, String password) {

		String fileName = "database.csv";
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				String user = line.substring(0, line.indexOf(','));
				String s = line.substring(line.indexOf(',') + 1);
				String pass = s.substring(0, s.indexOf(','));

				if (user.equals(username) && pass.equals(password)) {
					bufferedReader.close();
					System.out.println("Verified!");
					return true;
				}
			}

			bufferedReader.close();
			System.out.println("Invalid!");
			return false;
		} catch (FileNotFoundException ex) {
			System.out.println("Error: Database cannot be found");
			return false;
		} catch (IOException ex) {
			System.out
					.println("Error: Account information cannot be initialized");
			return false;
		}
	}

	/**
	 * Add username and password to database
	 * @param username input username.
	 * @param password input password.
	 * @return if the operation was successful.
	 */
	public static int add(String username, String password) {

		if (password.length() < 8) {
			System.out
					.println("Invalid password: Password must be at least 8 characters long");
			return 4;
		}

		if (username.isEmpty()) {
			System.out.println("Enter a username");
			return 5;
		}

		for (int i = 0; i < username.length(); i++) {
			char c = username.charAt(i);
			if ((c < '0' || c > '9') && (c < 'A' || c > 'Z')
					&& (c < 'a' || c > 'z')) {
				System.out
						.println("Username must contain only ASCII alphanumeric characters.");
				return 6;
			}
		}

		boolean isNumber = false;
		boolean isUpAlpha = false;
		boolean isLowAlpha = false;
		boolean isNonAlpha = false;

		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (c >= '0' && c <= '9') {
				if (isNumber == false)
					isNumber = true;
			}
			if (c >= 'A' && c <= 'Z') {
				if (isUpAlpha == false)
					isUpAlpha = true;
			}
			if (c >= 'a' && c <= 'z') {
				if (isLowAlpha == false)
					isLowAlpha = true;
			}
			if ((c < '0' || c > '9') && (c < 'A' || c > 'Z')
					&& (c < 'a' || c > 'z')) {
				if (isNonAlpha == false)
					isNonAlpha = true;
			}
		}

		if (!isNumber || !isUpAlpha || !isLowAlpha || !isNonAlpha) {
			System.out
					.println("Invalid Password: Password must contain at least one number, one lowercase letter, one uppercase letter and one non-alphanumeric character.");
			return 1;
		}

		String fileName = "database.csv";
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				String user = line.substring(0, line.indexOf(','));

				if (user.equals(username)) {
					bufferedReader.close();
					System.out.println("Username already exists");
					return 2;
				}
			}

			bufferedReader.close();
			if (append(username, password)) {
				System.out.println("New account is created");
				return 3;
			} else {
				System.out.println("Error: Unable to create account");
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Error: Database cannot be found");
		} catch (IOException ex) {
			System.out
					.println("Error: Account information cannot be initialized");
		}
		return 0;
	}

	/**
	 *  Helper method to append username and password to database.
	 * @param username username to append.
	 * @param password password to append.
	 * @return if the operation was successful.
	 */
	public static boolean append(String username, String password) {

		String fileName = "database.csv";
		try {
			FileWriter fileWriter = new FileWriter(fileName, true);

			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.append(username + "," + password + ",0");

			bufferedWriter.newLine();

			bufferedWriter.close();
			return true;
		} catch (IOException ex) {
			return false;
		}
	}
	
	  /**
	   * Add a win to the input user.
	   * @param username input username.
	   */
	  public static void addWin(String username) {
	    
	    String fileName = "database.csv";    
	    String line = null;
	    boolean userExists = false;

	    try {
	      FileReader fileReader = new FileReader(fileName);
	      BufferedReader bufferedReader = new BufferedReader(fileReader);
	      
	      while((line = bufferedReader.readLine()) != null && !userExists) {
	        String user = line.substring(0,line.indexOf(','));
	        
	        if (user.equals(username)) {
	          userExists = true;
	        }
	      }      
	      bufferedReader.close();
	      
	      if (userExists){
	        FileReader fR = new FileReader(fileName);
	        BufferedReader bR = new BufferedReader(fR);
	        File copy = new File("copy.csv");
	        copy.createNewFile();
	        FileWriter fileWriter = new FileWriter("copy.csv");
	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	        int num = -1;
	        
	         while((line = bR.readLine()) != null) {
	           String user = line.substring(0,line.indexOf(','));
	           if (user.equals(username)){
	             String s = line.substring(0,line.lastIndexOf(',')+1);
	             num = Integer.parseInt(line.substring(line.lastIndexOf(',')+1)) + 1;
	             bufferedWriter.write(s+num);
	           }
	           else {
	             bufferedWriter.write(line);
	           }
	           bufferedWriter.newLine();
	         }
	         
	         bufferedWriter.close();
	         bR.close();
	         File old = new File(fileName);
	         old.delete();
	         copy.renameTo(old);
	         updateTop10(username,num);
	      }
	      else{
	      System.out.println("User does not exists in the Database");
	      }
	      return;
	    }
	    catch(FileNotFoundException ex) {
	      System.out.println("Error: Database cannot be found");
	      return;
	    }
	    catch(IOException ex) {
	      System.out.println("Error: Account information cannot be initialized");
	      return;
	    }
	  }
	  
	  /**
	   * Helper method to update the top 10 list.
	   * @param player the username of the player.
	   * @param score the global score of the player.
	   */
	  public static void updateTop10(String player,int score) {
	    
	    String fileName = "top10.csv";    
	    String line = null;
	    boolean playerExists = false;
	    File oldFile = new File(fileName);
	    File newFile = new File("copy.csv");

	    try {
	      FileReader fileReader = new FileReader(fileName);
	      BufferedReader bufferedReader = new BufferedReader(fileReader);
	      FileWriter fileWriter = new FileWriter("copy.csv");
	      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	      int count = 0;
	      
	      while((line = bufferedReader.readLine()) != null && count<10) {
	        String user = line.substring(0,line.indexOf(','));
	        int num = Integer.parseInt(line.substring(line.indexOf(',')+1));
	        
	        if (score>num&&!playerExists&&count<10) {
	          bufferedWriter.write(player+','+score);
	          bufferedWriter.newLine();
	          playerExists = true;
	          count++;
	        }
	        if (!player.equals(user)&&count<10) {
	          bufferedWriter.write(line);
	          bufferedWriter.newLine();
	          count++;
	        }
	      }
	      if (!playerExists&&count<10){
	        bufferedWriter.write(player+','+score);
	        bufferedWriter.newLine();
	      }
	      
	      bufferedReader.close();
	      bufferedWriter.close();
	      oldFile.delete();
	      newFile.renameTo(oldFile);
	      return;
	    }
	    catch(FileNotFoundException ex) {
	      System.out.println("Error: Database cannot be found");
	      return;
	    }
	    catch(IOException ex) {
	      System.out.println("Error: Account information cannot be initialized");
	      return;
	    }
	  }
	  
	  /**
	   * Display head-to-head stat between two input players
	   * @param player1 the username of player 1.
	   * @param player2 the username of player 2.
	   */
	  public static void displayHeadtoHead(String player1,String player2) {
	    
	    String fileName = "database.csv";    
	    String line = null;
	    String user1 = null;
	    String user2 = null;
	    int user1Num = -1;
	    int user2Num = -1;
	    boolean user1Exists = false;
	    boolean user2Exists = false;

	    try {
	      FileReader fileReader = new FileReader(fileName);
	      BufferedReader bufferedReader = new BufferedReader(fileReader);
	      
	      while((line = bufferedReader.readLine()) != null && !(user1Exists && user2Exists)) {
	        String user = line.substring(0,line.indexOf(','));
	        
	        if (user.equals(player1)) {
	          user1Exists = true;
	          user1 = user;
	          user1Num = Integer.parseInt(line.substring(line.lastIndexOf(',')+1));
	        }
	        else if (user.equals(player2)) {
	          user2Exists = true;
	          user2 = user;
	          user2Num = Integer.parseInt(line.substring(line.lastIndexOf(',')+1));
	        }
	      }
	      
	      bufferedReader.close();
	      if (!user1Exists){
	        System.out.println("Player 1, " + player1 + ", does not exist in the Database");
	      }
	      else if (!user2Exists){
	        System.out.println("Player 2, " + player2 + ", does not exist in the Database");
	      }
	      else {
	        System.out.println("Player 1: " + user1 + ' ' + user1Num);
	        System.out.println("Player 2: " + user2 + ' ' + user2Num);
	      }
	      return;
	    }
	    catch(FileNotFoundException ex) {
	      System.out.println("Error: Database cannot be found");
	      return;
	    }
	    catch(IOException ex) {
	      System.out.println("Error: Account information cannot be initialized");
	      return;
	    }
	  }
	  
	   /**
	    * Display the top 10 players
	    */
	  public static void displayTop10() {
	    
	    String fileName = "top10.csv";
	    String line = null;
	    int count = 0;

	    try {
	      FileReader fileReader = new FileReader(fileName);
	      BufferedReader bufferedReader = new BufferedReader(fileReader);
	      
	      while((line = bufferedReader.readLine()) != null) {
	        String user = line.substring(0,line.indexOf(','));
	        int num = Integer.parseInt(line.substring(line.indexOf(',')+1));
	        count++;
	        System.out.println(count + ". " + user + ' ' + num);
	      }      
	      
	      bufferedReader.close();
	      if (count!=10){
	        for (int i = count;i<10;i++){
	          System.out.println( (i+1) +  ""  + '.');
	        }
	      }
	      return;  
	    }
	    catch(FileNotFoundException ex) {
	      System.out.println("Unable to open file '" + fileName + "'");
	      return;
	    }
	    catch(IOException ex) {
	      System.out.println("Error reading file '" + fileName + "'");
	      return;
	    }
	  }
	  
	  /**
	   * Puts the top ten players into an array so that its easy to print to the GUI.
	   * @param topTenList an array containing the top ten players.
	   */
	  public static void extractTop10(String[] topTenList) {
		    
		    String fileName = "top10.csv";
		    String line = null;
		    int count = 0;

		    try {
		      FileReader fileReader = new FileReader(fileName);
		      BufferedReader bufferedReader = new BufferedReader(fileReader);
		      
		      while((line = bufferedReader.readLine()) != null) {
		        String user = line.substring(0,line.indexOf(','));
		        int num = Integer.parseInt(line.substring(line.indexOf(',')+1));
		        topTenList[count] = (count+1) + ".  " + user + " --- " + num;
		        count++;
		      }      
		      
		      bufferedReader.close();
		      if (count!=10){
		        for (int i = count;i<10;i++){
		        	topTenList[i] = (i+1) +  ""  + '.';
		        }
		      }
		      return;  
		    }
		    catch(FileNotFoundException ex) {
		      System.out.println("Unable to open file '" + fileName + "'");
		      return;
		    }
		    catch(IOException ex) {
		      System.out.println("Error reading file '" + fileName + "'");
		      return;
		    }
		  }
		 

	  /**
	   * Helper method to append username and password to database.
	   * @param winner the winner of the match.
	   * @param loser the loser of the match.
	   */
	  public static void writeHeadtoHead(String winner,String loser) {

	    String fileName = "matchRecord.csv";
	    try {
	      FileWriter fileWriter = new FileWriter(fileName,true);
	      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	      Date date = new Date();
	      bufferedWriter.append("Match ended on " + date.toString());      
	      bufferedWriter.newLine();
	      bufferedWriter.append("Winner: " + winner + " , " + "Loser: " + loser);      
	      bufferedWriter.newLine();
	      bufferedWriter.close();
	      return;
	    }
	    catch(IOException ex) {
	      System.out.println("Error: Unable to write in file");
	      return;
	    }
	  }
	  
	   /**
	    * Display match history between two specified players
	    * @param player1 the username of player 1.
	    * @param player2 the username of player 2.
	    */
	  public static void displayMatchHistory(String player1,String player2) {
	    
	    String fileName = "matchRecord.csv";
	    String line = null;
	    String record = "\0";

	    try {
	      FileReader fileReader = new FileReader(fileName);
	      BufferedReader bufferedReader = new BufferedReader(fileReader);
	      
	      while((line = bufferedReader.readLine()) != null) {
	        String date = line.substring(line.indexOf("on")+3);
	        line = bufferedReader.readLine();
	        String winner = line.substring(line.indexOf(':')+2,line.indexOf(',')-1);
	        String loser = line.substring(line.lastIndexOf(':')+2);
	        if ((player1.equals(winner)&&player2.equals(loser))||(player1.equals(loser)&&player2.equals(winner))) {
	          record = date + "      " + winner + "      " + loser + ',' + record;
	        }
	      }      
	      
	      bufferedReader.close();
	      while (record.indexOf(',') != -1){
	        String s = record.substring(0,record.indexOf(','));
	        System.out.println(s);
	        record = record.substring(record.indexOf(',')+1);
	      }
	      return;
	    }
	    catch(FileNotFoundException ex) {
	      System.out.println("Unable to open file '" + fileName + "'");
	      return;
	    }
	    catch(IOException ex) {
	      System.out.println("Error reading file '" + fileName + "'");
	      return;
	    }
	  }
	  
	  /**
	   * Puts the match history between two players into an array so that its easy to print to the GUI	  
	   * @param history contains a list of all the match records in the database.
	   * @param player1 the username of player 1.
	   * @param player2 the username of player 2.
	   * @return  a list of the most recent matches played between the two input players.
	   */
	  public static String extractMatchHistory(String[][] history, String player1,String player2) {
	     
	    String fileName = "matchRecord.csv";
	    String line = null;
	    Stack holder = new Stack();
	    int player1Wins = 0;
	    int player2Wins = 0;
	    int count = 0;

	    try {
	      FileReader fileReader = new FileReader(fileName);
	      BufferedReader bufferedReader = new BufferedReader(fileReader);
	      
	      while((line = bufferedReader.readLine()) != null) {
		        String date = line.substring(line.indexOf("on")+3);
		        line = bufferedReader.readLine();
		        String winner = line.substring(line.indexOf(':')+2,line.indexOf(',')-1);
		        String loser = line.substring(line.lastIndexOf(':')+2);
		        if ((player1.equals(winner)&&player2.equals(loser))||(player1.equals(loser)&&player2.equals(winner))) {	   
			          String[] entry = new String[3];
			          entry[0] = "  " + date;
			          entry[1] = "  " + winner;
			          entry[2] = "  " + loser;
			          holder.push(entry);
			          if(winner.equals(player1)) {
			        	  player1Wins++;
			          }
			          else {
			        	  player2Wins++;
			          }			          
		        }
	      }
	      
	      bufferedReader.close();
	      
	      while(!holder.isEmpty() && count < 20) {
	    	  history[count] = (String[]) holder.pop();
	    	  count++;
	      }

	      return player1Wins + "  -  " + player2Wins;
	    }
	    catch(FileNotFoundException ex) {
	      System.out.println("Unable to open file '" + fileName + "'");
	      return null;
	    }
	    catch(IOException ex) {
	      System.out.println("Error reading file '" + fileName + "'");
	      return null;
	    }
	  }  
}