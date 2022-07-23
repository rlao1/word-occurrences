import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * JavaFX controller class
 * @author Richmond
 *
 */
public class Controller {
	
	@FXML ListView<String> resultView;
	@FXML TextField searchField;
	private DataOutputStream toServer = null;
	private DataInputStream fromServer = null;
	private Boolean connection = false;
	
	/**
	 * Reacts to mouse click input<br>
	 * Uses the {@link TextAnalyzer#getList()} method and FXML ListView to display text analyzer results
	 * @throws Exception
	 */
	public void handleAnalyzeClick() throws Exception 
	{
		resultView.getItems().clear();
		TextAnalyzer obj = new TextAnalyzer();
		resultView.getItems().addAll(obj.getList());
	}

	/**
	 * Reacts to mouse click input</br>
	 * Clears the designated FXML ListView
	 * @throws IOException
	 */
	public void clearResultView() throws IOException 
	{
		resultView.getItems().clear();
	}
	
	/**
	 * Reacts to mouse click input</br>
	 * Queries the count data for a user-given word through {@link WordSearchServer} via {@link Controller#connect()}
	 * @throws Exception
	 */
	public void searchForWord() throws Exception
	{	
		resultView.getItems().clear();
		
		if(!connection) connect();
		
		String word = searchField.getText();
		
		toServer.writeUTF(word);
        toServer.flush();
        
        String result = fromServer.readUTF();
        resultView.getItems().addAll(result);

	}
	
	/**
	 * Establishes a client-server connection
	 * @throws Exception
	 */
	public void connect() throws Exception
	{
		@SuppressWarnings("resource")
		Socket socket = new Socket("localhost", 8000);
		fromServer = new DataInputStream(socket.getInputStream());
		toServer = new DataOutputStream(socket.getOutputStream());
		connection = true;
	}
	
}
