package frc;

/**
 * For when you're too lazy to make a class to return multiple values.
 */
public class Tuples {
    private Tuples() {
    }

    /**
     * A 2-tuple. Also known as a twople.
     */
    public static class Two<A, B> {
        public final A first;
        public final B second;

        public Two(A first, B second) {
            this.first = first;
            this.second = second;
        }
    }

    /**
     * A 3-tuple. Also known as a threeple.
     */
    public static class Three<A, B, C> extends Two<A, B> {
        public final C third;

        public Three(A first, B second, C third) {
            super(first, second);
            this.third = third;
        }
    }
}