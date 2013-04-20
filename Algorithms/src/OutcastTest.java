import java.util.BitSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class OutcastTest {

	@Mock
	private WordNet wordnet;

	@Before()
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testOutcast() {
		when(wordnet.distance("horse", "zebra")).thenReturn(1);
		when(wordnet.distance("horse", "bear")).thenReturn(1);
		when(wordnet.distance("horse", "horse")).thenReturn(0);
		when(wordnet.distance("horse", "table")).thenReturn(4);

		when(wordnet.distance("zebra", "zebra")).thenReturn(0);
		when(wordnet.distance("zebra", "bear")).thenReturn(1);
		when(wordnet.distance("zebra", "horse")).thenReturn(1);
		when(wordnet.distance("zebra", "table")).thenReturn(4);

		when(wordnet.distance("bear", "zebra")).thenReturn(1);
		when(wordnet.distance("bear", "bear")).thenReturn(0);
		when(wordnet.distance("bear", "horse")).thenReturn(1);
		when(wordnet.distance("bear", "table")).thenReturn(4);

		when(wordnet.distance("table", "zebra")).thenReturn(4);
		when(wordnet.distance("table", "bear")).thenReturn(4);
		when(wordnet.distance("table", "horse")).thenReturn(4);
		when(wordnet.distance("table", "table")).thenReturn(0);

		Outcast outcast = new Outcast(wordnet);
		String[] nouns = { "horse", "zebra", "bear", "table" };
		assertEquals("table", outcast.outcast(nouns));

	}

	@Test
	public void testOutcastTwoNouns() {
		when(wordnet.distance("Turing", "Turing")).thenReturn(0);
		when(wordnet.distance("Turing", "von_Neumann")).thenReturn(1);
		when(wordnet.distance("von_Neumann", "Turing")).thenReturn(1);
		when(wordnet.distance("von_Neumann", "von_Neumann")).thenReturn(0);

		Outcast outcast = new Outcast(wordnet);
		String[] nouns = { "Turing", "von_Neumann" };
		assertEquals("Turing", outcast.outcast(nouns));
	}

	@Test
	public void suffixArray() {

		String original = "apple";
		String[] suffixes = new String[original.length()];
		for (int i = 0; i < original.length(); i++) {
			suffixes[i] = original.substring(i, original.length());
			System.out.println(suffixes[i]);
		}

	}

	@Test
	public void sortMillionIntegers() {
		// 1. create 1 million size array of int/maybe bits count[i]
		// 2. read input array and put in count[a[i]]++
		// 3. read count array and print frequencies
		int a[] = { 3, 4, 5, 1, 23, 22, 21, 1, 1, 4, 2 };
		BitSet bitSet = new BitSet();
		for (int item : a) {
			bitSet.set(item);
		}
		System.out.println("Using bitmask");
		for (int i = 0; i < bitSet.length(); i++) {
			if (bitSet.get(i))
				System.out.println(i);
		}

		System.out.println("Using int array count");
		int count[] = new int[24];
		for (int item : a) {
			if (count[item] == 0) {
				count[item] = 1;
			} else {
				count[item]++;
			}
		}
		for (int i = 0; i < count.length; i++) {
			if (count[i] != 0) {
				for (int j = 0; j < count[i]; j++) {
					System.out.print(i + " ");
				}
			}
		}

	}

	@Test
	public void stockQuote() {
		String name = "http://finance.yahoo.com/q?s=";
		In in = new In(name + "goog");
		String text = in.readAll();
		int start = text.indexOf("Prev Close:", 0);
		int from = text.indexOf("td class=\"yfnc_tabledata1\">", start);
		String temp = new String("td class=\"yfnc_tabledata1\">");
		temp.length();
		int to = text.indexOf("</td>", from);
		String price = text.substring(from + temp.length(), to);
		StdOut.println(price);
	}

	@Test
	public void RabinKarp() {
		String key = "reap";
		int M = 4;
		
		long hash = hash(key,M);	
		String text = "appleonemoreapple";
		
		

	}

	private long hash(String key, int M) {
		final int R = 10;
		final int Q = 997;
		long h = 0;
		for (int j = 0; j < M; j++)
			h = (R * h + key.charAt(j)) % Q;
		return h;
	}
}
