import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.jupiter.api.Test;
import java.util.*;

class DescendingCountTest {

	@Test
	void testListOrder() 
	{
		List<Word> list = new ArrayList<Word>();
		list.add(new Word("Three", 3));
		list.add(new Word("Seven", 7));
		list.add(new Word("Five", 5));
		
		List<Word> ordered = new ArrayList<Word>();
		ordered.add(list.get(1));
		ordered.add(list.get(2));
		ordered.add(list.get(0));
		
		Collections.sort(list, Word.descendingCount);

		assertThat(list, is(ordered));
	}

}
