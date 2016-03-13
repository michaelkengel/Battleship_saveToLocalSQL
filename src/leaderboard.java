import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.*;


public class leaderboard {

	// DATABASE CONNECTION INFORMATION
	static String location = "jdbc:mysql://localhost:3306/sys";
	static String user_name = "root";
	static String password = "mypassword123";
	static String table_name = "highscore";

	public static String getHighest() { // Will count the total number of
										// students listed
		String don = "";
		try {
			// Create a connection to the server
			Connection myConn = DriverManager.getConnection(location, user_name, password);
			// Create a statement
			Statement myState = myConn.createStatement();
			// Execute SQL Quary
			ResultSet top3 = myState.executeQuery("select min(score) from (" + table_name + ")");
			top3.next();
			int count = top3.getInt(1);

			ResultSet topname = myState.executeQuery("select (name) from (" + table_name + ") where score = " + count);
			String out = null;
			if (topname.next()) {
				out = topname.getString(1);
			}
			System.out.println("The current highest score is " + count + " held by " + out + ".");
			don = out.substring(0, 1).toUpperCase() + out.substring(1) + " holds the highscore of " + count + " moves.";
		} catch (Exception exc) {
			exc.printStackTrace();
		} // Will display valuable information
		return don;
	}

	public static void updateHighest(int shots, String name) {
		try {
			// Create a connection to the server
			Connection myConn = DriverManager.getConnection(location, user_name, password);

			// Create a statement
			Statement myState = myConn.createStatement();

			// Execute SQL Quary
			ResultSet top3 = myState.executeQuery("select max(score) from (" + table_name + ")");
			top3.next();
			int count = top3.getInt(1);

			System.out.println("Adding your score to the leaderboard....");
			myState.executeUpdate("INSERT INTO highscore (name,score) VALUES ('" + name + "','" + shots + "')");
			System.out.println("Complete.");

		} catch (Exception exc) {
			exc.printStackTrace();
		} // Will display valuable information

	}

	public static void showBoard() {
		try {
			// Create a connection to the server
			Connection myConn = DriverManager.getConnection(location, user_name, password);

			// Create a statement
			Statement myState = myConn.createStatement();

			// Execute SQL Quary
			String sql = "SELECT name, score FROM highscore ORDER BY score ASC"; // Change
																					// to
																					// DESC
																					// for
																					// descending
			ResultSet rs = myState.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");
				int score = rs.getInt("score");
				// Display values
				System.out.print(name.substring(0, 1).toUpperCase() + name.substring(1) + " - ");
				System.out.print("Score: " + score + "\n");
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		} // Will display valuable information
	}

	public void infoBox() {
		JOptionPane.showMessageDialog(null, "Welcome to Battle Ship\n" + getHighest(), "" + "Highscores",
				JOptionPane.OK_OPTION);
	}

	private static void remove() {
		try {
			// Create a connection to the server
			Connection myConn = DriverManager.getConnection(location, user_name, password);

			// Create a statement
			Statement myState = myConn.createStatement();

			// Collect info
			System.out.println("What is the name you would like to remove?");
			Scanner keyboard = new Scanner(System.in);
			String stuName = keyboard.nextLine().toLowerCase();

			// WIP
			// Execute SQL Quary
			String out = "DELETE FROM " + table_name + " WHERE name = '" + stuName + "'";
			myState.executeUpdate(out);
			System.out.println("Removed " + stuName);
		}

		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private static void set() {
		try {
			// Create a connection to the server
			Connection myConn = DriverManager.getConnection(location, user_name, password);

			// Create a statement
			Statement myState = myConn.createStatement();

			// Collect info
			System.out.println("What is the name you want?");
			Scanner keyboard = new Scanner(System.in);
			String stuName = keyboard.nextLine().toLowerCase();

			// Execute SQL Quary
			myState.execute("UPDATE " + table_name + " SET ");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private static void get() { // Will count the total number of students
								// listed
		try {
			// Create a connection to the server
			Connection myConn = DriverManager.getConnection(location, user_name, password);

			// Create a statement
			Statement myState = myConn.createStatement();

			// Execute SQL Quary
			ResultSet rs = myState.executeQuery("select count(*) from " + table_name);
			rs.next();
			int count = rs.getInt(1);
			System.out.println(count);
		} catch (Exception exc) {
			exc.printStackTrace();
		} // Will display valuable information
	}

}
