import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CountryTest {

	@Test
	public void binarySearch() {

		List<Country> countries = new ArrayList<Country>();
		Country countryA = new Country("countryA", 15);
		Country countryB = new Country("countryB", 20);
		Country countryC = new Country("countryC", 6);
		countries.add(countryA);
		countries.add(countryB);
		countries.add(countryC);

		String name = getCountryName(countries);
		assertEquals(name, "countryA");

	}

	private String getCountryName(List<Country> countries) {

		Arrays.sort(countries.toArray());
		List<Country> cumulativeCountries = new ArrayList<Country>();
		int totalPopulation = 0;
		for (Country country : countries) {
			totalPopulation += country.getPopulation();
			cumulativeCountries.add(new Country(country.getName(),
					totalPopulation));
		}

		int randomIndex = StdRandom.uniform(totalPopulation);

		// findCountryWithThisPopulation,
		// A,5->B,20->C,45->D,90->E,140

		// 5 20 45 90 140 210
		return findCountryForIndex(randomIndex, cumulativeCountries);
	}

	private String findCountryForIndex(int randomIndex,
			List<Country> cumulativeCountries) {

		int lo = 0;
		int hi = cumulativeCountries.size() - 1;
		while (lo < hi) {
			int mid = (lo + hi) / 2;

			if (randomIndex < cumulativeCountries.get(mid).getPopulation())
				hi = mid - 1;

			else if (randomIndex > cumulativeCountries.get(mid).getPopulation())
				lo = mid + 1;
			
			else 
				break;

		}
		return cumulativeCountries.get(lo).getName();
	}
}
