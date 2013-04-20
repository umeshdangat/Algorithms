public class Country implements Comparable<Country> {

	private String name;
	private int population;

	public Country(String name, int population) {
		this.name = name;
		this.population = population;
	}

	public String getName() {
		return name;
	}

	public int getPopulation() {
		return population;
	}

	@Override
	public int compareTo(Country that) {

		return this.getPopulation() - that.getPopulation();

	}

}
