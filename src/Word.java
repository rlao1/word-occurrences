import java.util.Comparator;

/**
 * Word object class
 * @author Richmond
 *
 */
public class Word {
	private String word;
	private int count;
	
	public static Comparator<Word> descendingCount = new Comparator<Word>() {
		public int compare(Word a, Word b) 
		{
			int aCount = a.getCount();
			int bCount = b.getCount();
			return bCount - aCount;
		}
	};
	
	/**
	 * Constructor with word field as parameter
	 * @param w word
	 */
	public Word(String w) 
	{
		word = w;
		count = 1;
	}
	
	/**
	 * Constructor with both fields as parameters
	 * @param w word
	 * @param c number of occurrences for word
	 */
	public Word(String w, int c) 
	{
		word = w;
		count = c;
	}
	
	/**
	 * Increments the count field by one
	 */
	public void incrementCount() 
	{
		count++;
	}
	
	/**
	 * Gets the count field
	 * @return count
	 */
	public int getCount()
	{
		return count;
	}
	
	/**
	 * Gets the word field
	 * @return word
	 */
	public String getWord()
	{
		return word;
	}
	
	/**
	 * Displays both fields in console
	 */
	public void printStat() 
	{
		System.out.printf("%s, %d\n", word, count);
	}
	
}
