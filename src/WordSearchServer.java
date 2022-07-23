import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Server for word search queries
 * @author Richmond
 *
 */
public class WordSearchServer extends Application {
	@SuppressWarnings("resource")
	@Override
	public void start(Stage primaryStage) 
	{
		TextArea ta = new TextArea();
		Scene scene = new Scene(new ScrollPane(ta), 450, 200);
		primaryStage.setTitle("Word Search Server"); 
		primaryStage.setScene(scene); 
		primaryStage.show();
		primaryStage.setX(175);

		new Thread(() -> 
		{
			try {
				Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement("SELECT * FROM word");
				ResultSet dbList = statement.executeQuery();

				ServerSocket serverSocket = new ServerSocket(8000);
				Platform.runLater(() -> ta.appendText("Server started at " + new Date() + '\n'));
				Socket socket = serverSocket.accept();

				DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
				DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
				
				String result = null;
				Boolean foundFlag = false;

				while (true) 
				{
					String input = inputFromClient.readUTF();
					String word = input.toUpperCase();
					
					while (dbList.next()) 
					{	
						if (word.equals(dbList.getString("word"))) 
						{
							result = String.valueOf(dbList.getInt("count")) + " occurrences of { " + input + " }";
							foundFlag = true;
							break;
						}
					}

					if (!foundFlag)
					{
						result = "No matches found for { " + input + " }";
					}
					
					outputToClient.writeUTF(result);
					
					foundFlag = false;
					dbList = statement.executeQuery();

					Platform.runLater(() -> 
					{
						ta.appendText("Word received from client: " + input + '\n');
					});
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}).start();
	}

	/**
	 * The main method is only needed for the IDE with limited JavaFX support. Not
	 * needed for running from the command line.
	 */
	public static void main(String[] args) 
	{
		launch(args);
	}

	/**
	 * Establishes a connection to 'word_occurrences' database</br>
	 * @return Connection to database
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception 
	{
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/word_occurrences";
		String username = "root";
		String password = "mypass";

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);

		return conn;
	}
}
