import java.io.IOException;
import javafx.application.Application; 
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The GUI for text analyzer
 * @author Richmond
 *
 */
public class TextAnalyzerGUI extends Application {
	
	/**
	 * Deploys JavaFX application
	 * @param args
	 */
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	/**
	 * Initiates primary stage of application
	 */
	@Override
	public void start(Stage primaryStage) throws IOException 
	{
		WordSearchServer server = new WordSearchServer();
		Stage serverStage = new Stage();
		server.start(serverStage);
		
		Parent root = FXMLLoader.load(getClass().getResource("panel.fxml"));
		primaryStage.setTitle("Word Occurrences");
		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(root, 600.0, 500.0));
		primaryStage.show();
		
		serverStage.setOnCloseRequest(new EventHandler<WindowEvent>()
		{
			public void handle(WindowEvent we) 
			{
				System.exit(0);
			}
		});
	}

	
	
}
