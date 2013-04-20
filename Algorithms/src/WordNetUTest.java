import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class WordNetUTest {

	@Mock
	private In synsetsStream;
	@Mock
	private In hypernymsStream;

	@Before()
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testIsNoun() {

		when(synsetsStream.readLine())
				.thenReturn(
						"0,Actifed,trade name for a drug containing an antihistamine and a decongestant")
				.thenReturn(
						"1,antihistamine,a medicine used to treat allergies")
				.thenReturn(
						"2,nasal_decongestant,a decongestant that provides temporary relief of nasal")
				.thenReturn("3,cold cough, virus").thenReturn(null);

		when(hypernymsStream.readLine()).thenReturn("0,1,2").thenReturn("1,3")
				.thenReturn("2,3").thenReturn(null);

		WordNet wordNet = new WordNet(synsetsStream, hypernymsStream);
		assertEquals(true, wordNet.isNoun("Actifed"));
	}

	@Test
	public void testNouns() {

		when(synsetsStream.readLine())
				.thenReturn(
						"0,Actifed,trade name for a drug containing an antihistamine and a decongestant")
				.thenReturn(
						"1,antihistamine,a medicine used to treat allergies")
				.thenReturn(
						"2,nasal_decongestant,a decongestant that provides temporary relief of nasal")
				.thenReturn("3,cold cough, virus").thenReturn(null);

		when(hypernymsStream.readLine()).thenReturn("0,1,2").thenReturn("1,3")
				.thenReturn("2,3").thenReturn(null);

		WordNet wordNet = new WordNet(synsetsStream, hypernymsStream);

		Collection<String> actual = (Collection<String>) wordNet.nouns();

		assertEquals(true, actual.contains("Actifed"));
		assertEquals(true, actual.contains("antihistamine"));
		assertEquals(true, actual.contains("nasal_decongestant"));
		assertEquals(5, actual.size());
	}

	@Test
	public void testDistanceWhenNoPATH() {

		when(synsetsStream.readLine())
				.thenReturn(
						"0,Actifed,trade name for a drug containing an antihistamine and a decongestant")
				.thenReturn(
						"1,antihistamine,a medicine used to treat allergies")
				.thenReturn(
						"2,nasal_decongestant,a decongestant that provides temporary relief of nasal")
				.thenReturn("3,cold cough, virus")
				.thenReturn("4,crocin, tablet").thenReturn(null);

		when(hypernymsStream.readLine()).thenReturn("0,1,2").thenReturn("1,3")
				.thenReturn("2,3").thenReturn("4,2").thenReturn(null);

		WordNet wordNet = new WordNet(synsetsStream, hypernymsStream);

		assertEquals(2, wordNet.distance("crocin", "Actifed"));
	}

	@Test
	public void testDistanceWithSomePath() {

		when(synsetsStream.readLine())
				.thenReturn("0,run running, activity is running")
				.thenReturn("1,locomotion travel, travelling")
				.thenReturn("2,motion movement range, moving")
				.thenReturn("3,demotion,demoted")
				.thenReturn("4,variation, variety")
				.thenReturn("5,change, change is gauranteed").thenReturn(null);

		when(hypernymsStream.readLine()).thenReturn("0,1").thenReturn("1,2")
				.thenReturn("3,5").thenReturn("4,5").thenReturn("2,5")
				.thenReturn(null);

		WordNet wordNet = new WordNet(synsetsStream, hypernymsStream);
		assertEquals(4, wordNet.distance("running", "demotion"));
		assertEquals(1, wordNet.distance("range", "travel"));
		assertEquals(0, wordNet.distance("run", "running"));
		assertEquals(0, wordNet.distance("run", "running"));
	}

	@Test
	public void testSAPWithSomePath() {
		when(synsetsStream.readLine())
				.thenReturn("0,run running, activity is running")
				.thenReturn("1,locomotion travel, travelling")
				.thenReturn("2,motion movement range, moving")
				.thenReturn("3,demotion,demoted")
				.thenReturn("4,variation, variety")
				.thenReturn("5,change, change is gauranteed")
				.thenReturn("6,action, act of acting")
				.thenReturn("7,event, happening")
				.thenReturn("8,group_action, something done in a group")
				.thenReturn(null);

		when(hypernymsStream.readLine()).thenReturn("0,1").thenReturn("1,2")
				.thenReturn("3,5").thenReturn("4,5").thenReturn("2,5")
				.thenReturn("5,6").thenReturn("6,7").thenReturn("8,7,6")
				.thenReturn(null);

		WordNet wordNet = new WordNet(synsetsStream, hypernymsStream);
		assertEquals("change", wordNet.sap("running", "demotion"));
		assertEquals("run running", wordNet.sap("running", "run"));
		assertEquals("change", wordNet.sap("running", "demotion"));
		assertEquals("action", wordNet.sap("running", "group_action"));
		assertEquals(5, wordNet.distance("running", "group_action"));

	}

	@Test
	public void testSAPWithDisconnectedGraph() {
		when(synsetsStream.readLine())
				.thenReturn("0,run running, activity is running")
				.thenReturn("1,locomotion travel, travelling")
				.thenReturn("2,motion movement range, moving")
				.thenReturn("3,demotion,demoted")
				.thenReturn("4,variation, variety")
				.thenReturn("5,change, change is gauranteed")
				.thenReturn("6,action, act of acting")
				.thenReturn("7,event, happening")
				.thenReturn("8,group_action, something done in a group")
				.thenReturn(null);

		when(hypernymsStream.readLine()).thenReturn("0,1").thenReturn("1,2")
				.thenReturn("3,5").thenReturn("4,5").thenReturn("2,5")
				.thenReturn("5,6").thenReturn("6,7").thenReturn("8,7,6")
				.thenReturn(null);

		WordNet wordNet = new WordNet(synsetsStream, hypernymsStream);
		assertEquals("change", wordNet.sap("running", "demotion"));
		assertEquals("run running", wordNet.sap("running", "run"));
		assertEquals("change", wordNet.sap("running", "demotion"));
		assertEquals("action", wordNet.sap("running", "group_action"));
		assertEquals(5, wordNet.distance("running", "group_action"));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testDigraphGraphWithCycleAndRoot() {
		when(synsetsStream.readLine())
				.thenReturn("0,run running, activity is running")
				.thenReturn("1,locomotion travel, travelling")
				.thenReturn("2,motion movement range, moving")
				.thenReturn("3,demotion,demoted")
				.thenReturn("4,variation, variety").thenReturn(null);

		when(hypernymsStream.readLine()).thenReturn("1,0").thenReturn("1,2")
				.thenReturn("2,3").thenReturn("3,1").thenReturn("4,3")
				.thenReturn(null);

		new WordNet(synsetsStream, hypernymsStream);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDigraphGraphWithCycleAndNoRoot() {
		when(synsetsStream.readLine())
				.thenReturn("0,run running, activity is running")
				.thenReturn("1,locomotion travel, travelling")
				.thenReturn("2,motion movement range, moving")
				.thenReturn("3,demotion,demoted")
				.thenReturn("4,variation, variety").thenReturn(null);

		when(hypernymsStream.readLine()).thenReturn("0,1").thenReturn("1,2")
				.thenReturn("2,3").thenReturn("3,1").thenReturn("4,3")
				.thenReturn(null);

		new WordNet(synsetsStream, hypernymsStream);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDigraphGraphWithNoCycleAndNoRoot() {
		when(synsetsStream.readLine())
				.thenReturn("0,run running, activity is running")
				.thenReturn("1,locomotion travel, travelling")
				.thenReturn("2,motion movement range, moving")
				.thenReturn("3,demotion,demoted")
				.thenReturn("4,variation, variety").thenReturn(null);

		when(hypernymsStream.readLine()).thenReturn("0,1").thenReturn("1,2")
				.thenReturn("3,2").thenReturn("3,4").thenReturn(null);

		new WordNet(synsetsStream, hypernymsStream);
	}

	@Test
	public void testDigraphGraphWithNoCycleAndRoot() {
		when(synsetsStream.readLine())
				.thenReturn("0,run running, activity is running")
				.thenReturn("1,locomotion travel, travelling")
				.thenReturn("2,motion movement range, moving")
				.thenReturn("3,demotion,demoted")
				.thenReturn("4,variation, variety").thenReturn(null);

		when(hypernymsStream.readLine()).thenReturn("0,1").thenReturn("1,2")
				.thenReturn("2,3").thenReturn("4,3").thenReturn(null);

		WordNet wordNet = new WordNet(synsetsStream, hypernymsStream);
		assertEquals("demotion", wordNet.sap("run", "variation"));
	}

	@Test
	public void testDistanceForTwoNounsWithSameDistance() {
		when(synsetsStream.readLine())
				.thenReturn(
						"0,Turing Alan_Turing Alan_Mathison_Turing,English mathematician who conceived of the Turing machine and broke German codes during World War II (1912-1954")
				.thenReturn(
						"1,von_Neumann Neumann John_von_Neumann,United States mathematician who contributed to the development of atom bombs and of stored-program digital computers (1903-1957)")
				.thenReturn("2,mathematician,a person skilled in mathematics")
				.thenReturn(null);
		when(hypernymsStream.readLine()).thenReturn("0,2").thenReturn("1,2")
				.thenReturn(null);

		WordNet wordNet = new WordNet(synsetsStream, hypernymsStream);
		assertEquals(2, wordNet.distance("Turing", "von_Neumann"));
		assertEquals(2, wordNet.distance("von_Neumann", "Turing"));
	}
}
