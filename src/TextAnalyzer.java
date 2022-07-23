import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Text analyzer class that analyzes word occurences in a file
 * @author Richmond
 *
 */
public class TextAnalyzer {
	
	/**
	 * Retrieves all words from database in descending order</br>
	 * Used in {@link Controller#handleAnalyzeClick()}
	 * @return A list containing preformatted strings of Word member variables and its respective rank order
	 * @throws Exception 
	 */
	public List<String> getList() throws Exception 
	{
		List<String> resultList = new ArrayList<String>();
		List<Word> wordList = new ArrayList<Word>();
		Connection conn = getConnection();
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM word");
		ResultSet dbList = statement.executeQuery();
		
		int i = 0;
		while(dbList.next())
		{
			wordList.add(new Word(dbList.getString("word"), dbList.getInt("count")));
			Collections.sort(wordList, Word.descendingCount);
			resultList.add(i+1 + ".\t " + " < " + wordList.get(i).getCount() + " >  " +  wordList.get(i).getWord());
			++i;
		}
		
		return resultList;
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
