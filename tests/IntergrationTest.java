package Tests;
import static org.junit.Assert.*;

import java.awt.Window;
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import system.GameMenu;
import accountManagement.Account;
import accountManagement.Database;
import accountManagement.SignUpPage;

/**
 * @author Simon
 * @version 2013.11.25
 * Unit testing integration of code.
 */

public class IntergrationTest {
	
	@Test
	public void TestGameStart() {

		Account playerOne = new Account();
		Account playerTwo = new Account();		
		playerOne.setName("test");
		playerTwo.setName("testtwo");
		GameMenu menu = new GameMenu(playerOne,playerTwo);
		menu.testing = true;
			
		menu.start.doClick();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(menu.isVisible());
	}
	
	@Test
	public void TestGameNotStart() {

		Account playerOne = new Account();
		Account playerTwo = new Account();		
		playerOne.setName("OnlyPlayerOneLoggedIn");
		GameMenu menu = new GameMenu(playerOne,playerTwo);
		menu.testing = true;
	
		menu.start.doClick();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(menu.isVisible());
		
	}
	
	@Test
	public void TestInputCreateAccount() {
		
		File f = null;

		try {
			f = new File("database.csv");
			f.delete();
			f.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}

		int valid = Database.add("test", "Testing_123");

		assertEquals(3, valid, 0);

		SignUpPage page = new SignUpPage();
		page.testing = true;
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		page.nameInput = new JTextField("NewAccount");
		page.passInput = new JPasswordField("Testing_123");
		
			
		page.signUp.doClick();
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(page.isVisible());
	}
	
	@Test
	public void TestInputAccountAlreadyExists() {
		
		File f = null;

		try {
			f = new File("database.csv");
			f.delete();
			f.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}

		int valid = Database.add("test", "Testing_123");

		assertEquals(3, valid, 0);

		SignUpPage page = new SignUpPage();		
		page.testing = true;
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		page.nameInput = new JTextField("test");
		page.passInput = new JPasswordField("Testing_123");
		
			
		page.signUp.doClick();
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(page.isVisible());
	}
	
	@Test
	public void TestInputLogin() {
		
		File f = null;

		try {
			f = new File("database.csv");
			f.delete();
			f.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}

		int valid = Database.add("test", "Testing_123");

		assertEquals(3, valid, 0);
		
		Account playerOne = new Account();
		Account playerTwo = new Account();

		GameMenu menu = new GameMenu(playerOne, playerTwo);		
		menu.testing = true;
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		menu.pOneNameInput = new JTextField("test");
		menu.pOnePassInput = new JPasswordField("Testing_123");
		menu.pOneLogIn.doClick();
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(menu.pOneLoggedIn.isVisible());
	}

	@Test
	public void TestInputLoginFailed() {
		
		File f = null;

		try {
			f = new File("database.csv");
			f.delete();
			f.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}

		int valid = Database.add("test", "Testing_123");

		assertEquals(3, valid, 0);
		
		Account playerOne = new Account();
		Account playerTwo = new Account();

		GameMenu menu = new GameMenu(playerOne, playerTwo);
		menu.testing = true;
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		menu.pOneNameInput = new JTextField("UserNotExist");
		menu.pOnePassInput = new JPasswordField("Testing_123");
		menu.pOneLogIn.doClick();
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertTrue(menu.pOneLogIn.isVisible());
	}
	

	@Test
	public void TestOpenSignUp() {
		Account playerOne = new Account();
		Account playerTwo = new Account();

		GameMenu menu = new GameMenu(playerOne, playerTwo);		
		menu.testing = true;
		menu.signup.doClick();		
		assertNotNull(menu.signUpPage);
	}
	
	
	@Test
	public void TestOpenStats() {
		Account playerOne = new Account();
		Account playerTwo = new Account();

		GameMenu menu = new GameMenu(playerOne, playerTwo);		
		menu.stats.doClick();		
		assertNotNull(menu.stats);		
	}
	
	
	@Test
	public void TestOpenHelp() {
		Account playerOne = new Account();
		Account playerTwo = new Account();

		GameMenu menu = new GameMenu(playerOne, playerTwo);		
		menu.help.doClick();		
		assertNotNull(menu.help);		
		
	}
			
}
