/**
 * Driver class for the "Hello, world!" genetic algorithm simulation.
 * 
 * This class is strictly used for an entry point into the simulation itself,
 * it has no other functionality.
 * 
 * @author John Svazic
 * @version 1.0
 */
public class Main {

	/**
	 * The main method used for execution of the application.
	 * 
	 * @param args Command-line arguments (ignored).
	 */
	public static void main(String[] args) {
		
		// The size of the simulation population
		final int populationSize = 2048;
		
		// The maximum number of generations for the simulation.
		final int maxGenerations = 16384;
		
		// The probability of crossover for any member of the population,
		// where 0.0 <= crossoverRatio <= 1.0
		final float crossoverRatio = 0.8f;
		
		// The portion of the population that will be retained without change
		// between evolutions, where 0.0 <= elitismRatio < 1.0
		final float elitismRatio = 0.1f;
		
		// The probability of mutation for any member of the population,
		// where 0.0 <= mutationRatio <= 1.0
		final float mutationRatio = 0.03f;
	
		// Get the current run time.  Not very accurate, but useful for 
		// some simple reporting.
		long startTime = System.currentTimeMillis();
		
		// Create the initial population
		Population pop = new Population(populationSize, crossoverRatio, 
				elitismRatio, mutationRatio);

		// Start evolving the population, stopping when the maximum number of
		// generations is reached, or when we find a solution.
		int i = 0;
		Chromosome best = pop.getPopulation()[0];
		
		while ((i++ <= maxGenerations) && (best.getFitness() != 0)) {
			System.out.println("Generation " + i + ": " + best.getGene());
			pop.evolve();
			best = pop.getPopulation()[0];
		}
		
		// Get the end time for the simulation.
		long endTime = System.currentTimeMillis();
		
		// Print out some information to the console.
		System.out.println("Generation " + i + ": " + best.getGene());
		System.out.println("Total execution time: " + (endTime - startTime) + 
				"ms");
	}
}