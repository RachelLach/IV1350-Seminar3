package se.kth.iv1350.pos.adaptation;
import java.util.Random;

/**
 * A subclass of {@link Random} that modifies the behavior of the {@code nextInt()} method.
 * This class inherits from {@code Random} and overrides the {@code nextInt()} method
 * to always return even numbers by multiplying the original output by 2.
 */
public class InheritedRandom extends Random {

    /**
     * Returns a pseudorandom, uniformly distributed {@code int} value
     * that is always even.
     * @return a pseudorandom even integer value.
     */
    @Override
    public int nextInt() {
        int original = super.nextInt();
        return original * 2; // Make it always even
    }

}
