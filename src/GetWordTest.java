import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;

class GetWordTest {

	@Test
	void testGetWord() 
	{
		Word test = new Word("Hello");
		String output = test.getWord();
		
		assertEquals("Hello", output);
	}

}
