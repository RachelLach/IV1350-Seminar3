package se.kth.iv1350.pos.adaptation;
import java.util.Random;

/**
 * A class that adapts {@link Random} using composition.
 * It wraps a Random object and modifies its behavior.
 */
public class ComposedRandom {
    private Random random;

    /**
     * Creates a new instance of ComposedRandom.
     * Initializes the internal Random object.
     */
    public ComposedRandom() {
        this.random = new Random();
    }

    /**
     * Returns a random integer, modified to always be even.
     * It gets a random integer from the internal Random object
     * and multiplies it by 2.
     * @return an even random integer.
     */
    public int nextInt() {
        int original = random.nextInt();
        return original * 2;
    }
}
