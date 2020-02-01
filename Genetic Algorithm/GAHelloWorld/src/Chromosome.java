
import java.util.Random;

/**
 * This class is used to define a chromosome for the genetic algorithm
 * simulation.
 *
 * This class is essentially nothing more than a container for the details of
 * the chromosome, namely the gene (the string that represents our target
 * string) and the fitness (how close the gene is to the target string).
 *
 * Note that this class is immutable. Calling <code>mate(Chromsome)</code> or
 * <code>mutate</code> will result in a new <code>Chromosome</code> instance
 * being created.
 *
 * @author John Svazic
 * @version 1.0
 */
public class Chromosome implements Comparable<Chromosome> {

    private final String gene;
    private final int fitness;

    /**
     * The target gene, converted to an array for convenience.
     */
    private static final char[] TARGET_GENE = "Hello World".toCharArray();

    /**
     * Convenience randomizer.
     */
    private static final Random rand = new Random(System.currentTimeMillis());

    /**
     * Default constructor.
     *
     */
    public Chromosome(String gene) {
        this.gene = gene;
        this.fitness = calculateFitness(gene);
    }

    public String getGene() {
        return gene;
    }

    public int getFitness() {
        return fitness;
    }

    private static int calculateFitness(String gene) {
        int fitness = 0;
        char[] arr = gene.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            fitness += Math.abs(((int) arr[i]) - ((int) TARGET_GENE[i]));
        }

        return fitness;
    }

    public Chromosome mutate() {
        char[] arr = gene.toCharArray();
        int idx = rand.nextInt(arr.length);
        int delta = (rand.nextInt() % 90) + 32;
        arr[idx] = (char) ((arr[idx] + delta) % 122);

        return new Chromosome(String.valueOf(arr));
    }

    public Chromosome[] mate(Chromosome mate) {
        // Convert the genes to arrays to make thing easier.
        char[] arr1 = gene.toCharArray();
        char[] arr2 = mate.gene.toCharArray();

        // Select a random pivot point for the mating
        int pivot = rand.nextInt(arr1.length);

        // Provide a container for the child gene data
        char[] child1 = new char[gene.length()];
        char[] child2 = new char[gene.length()];

        // Copy the data from each gene to the first child.
        System.arraycopy(arr1, 0, child1, 0, pivot);
        System.arraycopy(arr2, pivot, child1, pivot, (child1.length - pivot));

        // Repeat for the second child, but in reverse order.
        System.arraycopy(arr2, 0, child2, 0, pivot);
        System.arraycopy(arr1, pivot, child2, pivot, (child2.length - pivot));

        return new Chromosome[]{new Chromosome(String.valueOf(child1)),
            new Chromosome(String.valueOf(child2))};
    }

    public static Chromosome generateRandom() {
        char[] arr = new char[TARGET_GENE.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) (rand.nextInt(90) + 32);
        }
        return new Chromosome(String.valueOf(arr));
    }

    @Override
    public int compareTo(Chromosome c) {
        if (fitness < c.fitness) {
            return -1;
        } else if (fitness > c.fitness) {
            return 1;
        }

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Chromosome)) {
            return false;
        }

        Chromosome c = (Chromosome) o;
        return (gene.equals(c.gene) && fitness == c.fitness);
    }

    @Override
    public int hashCode() {
        return new StringBuilder().append(gene).append(fitness)
                .toString().hashCode();
    }
}
