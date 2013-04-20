import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class BaseballEliminationTest {

	@Mock
	private In inFourTeams;
	@Mock
	private In inFiveTeams;

	@Before
	public void initMocks() {
		org.mockito.MockitoAnnotations.initMocks(this);
		mockFourTeamsInput();
		mockFiveTeamsInput();
	}


	@Test
	public void testBaseballEliminationIn() {

	}


	@Test
	public void testNumberOfTeams() {
		BaseballElimination baseballElimination = new BaseballElimination(
				inFourTeams);
		assertEquals(4, baseballElimination.numberOfTeams());
	}

	@Test
	public void testWins() {
		BaseballElimination baseballElimination = new BaseballElimination(
				inFourTeams);
		assertEquals(83, baseballElimination.wins("Atlanta"));
		assertEquals(80, baseballElimination.wins("Philadelphia"));
		assertEquals(78, baseballElimination.wins("New_York"));
		assertEquals(77, baseballElimination.wins("Montreal"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWinsException() {
		BaseballElimination baseballElimination = new BaseballElimination(
				inFourTeams);
		baseballElimination.wins("Japan");
	}

	@Test
	public void testLosses() {
		BaseballElimination baseballElimination = new BaseballElimination(
				inFourTeams);
		assertEquals(71, baseballElimination.losses("Atlanta"));
		assertEquals(79, baseballElimination.losses("Philadelphia"));
		assertEquals(78, baseballElimination.losses("New_York"));
		assertEquals(82, baseballElimination.losses("Montreal"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testLossesException() {
		BaseballElimination baseballElimination = new BaseballElimination(
				inFourTeams);
		baseballElimination.losses("Japan");
	}
	
	@Test
	public void testRemaining() {
		BaseballElimination baseballElimination = new BaseballElimination(
				inFourTeams);
		assertEquals(8, baseballElimination.remaining("Atlanta"));
		assertEquals(3, baseballElimination.remaining("Philadelphia"));
		assertEquals(6, baseballElimination.remaining("New_York"));
		assertEquals(3, baseballElimination.remaining("Montreal"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRemainingException() {
		BaseballElimination baseballElimination = new BaseballElimination(
				inFourTeams);
		baseballElimination.remaining("Japan");
	}
	
	@Test
	public void testAgainst() {
		BaseballElimination baseballElimination = new BaseballElimination(
				inFourTeams);
		assertEquals(1, baseballElimination.against("Atlanta", "Philadelphia"));
		assertEquals(6, baseballElimination.against("Atlanta", "New_York"));
		assertEquals(1, baseballElimination.against("Atlanta", "Montreal"));

		assertEquals(1, baseballElimination.against("Atlanta", "Philadelphia"));
		assertEquals(0, baseballElimination.against("Philadelphia", "New_York"));
		assertEquals(2, baseballElimination.against("Philadelphia", "Montreal"));
		assertEquals(0,
				baseballElimination.against("Philadelphia", "Philadelphia"));
	}


	@Test(expected=IllegalArgumentException.class)
	public void testAgainstException() {
		BaseballElimination baseballElimination = new BaseballElimination(
				inFourTeams);
		baseballElimination.against("Brazil","Japan");
	}
	
	@Test
	public void testleadingTeam() {
		BaseballElimination baseballElimination = new BaseballElimination(
				inFourTeams);
		//assertEquals("Atlanta", baseballElimination.getLeadingTeam());
	}

	@Test
	public void testIsEliminatedTrivially() {
		BaseballElimination baseballElimination = new BaseballElimination(
				inFourTeams);
		assertEquals(true, baseballElimination.isEliminated("Montreal"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIsEliminatedException() {
		BaseballElimination baseballElimination = new BaseballElimination(
				inFourTeams);
		baseballElimination.isEliminated("Mumbai");
	}
	
	@Test
	public void testIsEliminatedNonTrivially() {
		BaseballElimination baseballElimination = new BaseballElimination(
				inFiveTeams);
		assertEquals(true, baseballElimination.isEliminated("Detroit"));
		assertEquals(false, baseballElimination.isEliminated("New_York"));
	}
	

	@Test
	public void testCertificateOfElimination() {
		BaseballElimination baseballElimination = new BaseballElimination(
				inFiveTeams);
		
		Iterable<String> subSetRnull = baseballElimination.certificateOfElimination("New_York");
		assertEquals(null,subSetRnull);
		
		List<String> expected = new ArrayList<String>();
		expected.add("Baltimore");
		expected.add("Boston");
		expected.add("New_York");
		expected.add("Toronto");
		
		List<String> subsetR = (List<String>) baseballElimination.certificateOfElimination("Detroit");
		assertEquals(expected, subsetR);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCertificateOfEliminationException() {
		BaseballElimination baseballElimination = new BaseballElimination(
				inFourTeams);
		baseballElimination.certificateOfElimination("Mumbai");
	}

	private void mockFourTeamsInput() {

		when(inFourTeams.readInt()).thenReturn(4).thenReturn(83).thenReturn(71)
				.thenReturn(8).thenReturn(0).thenReturn(1).thenReturn(6)
				.thenReturn(1).thenReturn(80).thenReturn(79).thenReturn(3)
				.thenReturn(1).thenReturn(0).thenReturn(0).thenReturn(2)
				.thenReturn(78).thenReturn(78).thenReturn(6).thenReturn(6)
				.thenReturn(0).thenReturn(0).thenReturn(0).thenReturn(77)
				.thenReturn(82).thenReturn(3).thenReturn(1).thenReturn(2)
				.thenReturn(0).thenReturn(0);

		when(inFourTeams.readString()).thenReturn("Atlanta")
				.thenReturn("Philadelphia").thenReturn("New_York")
				.thenReturn("Montreal").thenReturn(null);

	}
	
	private void mockFiveTeamsInput() {

		when(inFiveTeams.readInt()).thenReturn(5)
		        .thenReturn(75).thenReturn(59).thenReturn(28)
				.thenReturn(0).thenReturn(3).thenReturn(8).thenReturn(7).thenReturn(3)
				.thenReturn(71).thenReturn(63).thenReturn(28)
				.thenReturn(3).thenReturn(0).thenReturn(2).thenReturn(7).thenReturn(4)
				.thenReturn(69).thenReturn(66).thenReturn(27)
				.thenReturn(8).thenReturn(2).thenReturn(0).thenReturn(0).thenReturn(0)
				.thenReturn(63).thenReturn(72).thenReturn(27)
				.thenReturn(7).thenReturn(7).thenReturn(0).thenReturn(0).thenReturn(0)
				.thenReturn(49).thenReturn(86).thenReturn(27)
				.thenReturn(3).thenReturn(4).thenReturn(0).thenReturn(0).thenReturn(0);

		when(inFiveTeams.readString()).thenReturn("New_York")
				.thenReturn("Baltimore").thenReturn("Boston")
				.thenReturn("Toronto").thenReturn("Detroit").thenReturn(null);

	}
	
}
