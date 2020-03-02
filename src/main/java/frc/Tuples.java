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

    /**
     * A 4-tuple. Also known as a fourple.
     */
    public static class Four<A, B, C, D> extends Three<A, B, C> {
        public final D fourth;

        public Four(A first, B second, C third, D fourth) {
            super(first, second, third);
            this.fourth = fourth;
        }
    }

    /**
     * A 5-tuple. Also known as a fiveple.
     */
    public static class Five<A, B, C, D, E> extends Four<A, B, C, D> {
        public final E fifth;

        public Five(A first, B second, C third, D fourth, E fifth) {
            super(first, second, third, fourth);
            this.fifth = fifth;
        }
    }

    /**
     * A 6-tuple. Also known as a sixple.
     */
    public static class Six<A, B, C, D, E, F> extends Five<A, B, C, D, E> {
        public final F sixth;

        public Six(A first, B second, C third, D fourth, E fifth, F sixth) {
            super(first, second, third, fourth, fifth);
            this.sixth = sixth;
        }
    }

    /**
     * A 7-tuple. Also known as a sevenple.
     */
    public static class Seven<A, B, C, D, E, F, G> extends Six<A, B, C, D, E, F> {
        public final G seventh;

        public Seven(A first, B second, C third, D fourth, E fifth, F sixth, G seventh) {
            super(first, second, third, fourth, fifth, sixth);
            this.seventh = seventh;
        }
    }

    /**
     * An 8-tuple. Also known as an eightple.
     */
    public static class Eight<A, B, C, D, E, F, G, H> extends Seven<A, B, C, D, E, F, G> {
        public final H eighth;

        public Eight(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth) {
            super(first, second, third, fourth, fifth, sixth, seventh);
            this.eighth = eighth;
        }
    }

    /**
     * A 9-tuple. Also known as a nineple.
     */
    public static class Nine<A, B, C, D, E, F, G, H, I> extends Eight<A, B, C, D, E, F, G, H> {
        public final I ninth;

        public Nine(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth, I ninth) {
            super(first, second, third, fourth, fifth, sixth, seventh, eighth);
            this.ninth = ninth;
        }
    }

    /**
     * A 10-tuple. Also known as a tenple.
     */
    public static class Ten<A, B, C, D, E, F, G, H, I, J> extends Nine<A, B, C, D, E, F, G, H, I> {
        public final J tenth;

        public Ten(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth, I ninth, J tenth) {
            super(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth);
            this.tenth = tenth;
        }
    }

    /**
     * An 11-tuple. Also known as an elevenple.
     */
    public static class Eleven<A, B, C, D, E, F, G, H, I, J, K> extends Ten<A, B, C, D, E, F, G, H, I, J> {
        public final K eleventh;

        public Eleven(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth, I ninth, J tenth,
                K eleventh) {
            super(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth);
            this.eleventh = eleventh;
        }
    }

    /**
     * A 12-tuple. Also known as a twelveple.
     */
    public static class Twelve<A, B, C, D, E, F, G, H, I, J, K, L> extends Eleven<A, B, C, D, E, F, G, H, I, J, K> {
        public final L twelfth;

        public Twelve(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth, I ninth, J tenth,
                K eleventh, L twelfth) {
            super(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh);
            this.twelfth = twelfth;
        }
    }

    /**
     * A 13-tuple. Also known as a thirteenple.
     */
    public static class Thirteen<A, B, C, D, E, F, G, H, I, J, K, L, M>
            extends Twelve<A, B, C, D, E, F, G, H, I, J, K, L> {
        public final M thirteenth;

        public Thirteen(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth, I ninth, J tenth,
                K eleventh, L twelfth, M thirteenth) {
            super(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh, twelfth);
            this.thirteenth = thirteenth;
        }
    }

    /**
     * A 14-tuple. Also known as a fourteenple.
     */
    public static class Fourteen<A, B, C, D, E, F, G, H, I, J, K, L, M, N>
            extends Thirteen<A, B, C, D, E, F, G, H, I, J, K, L, M> {
        public final N fourteenth;

        public Fourteen(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth, I ninth, J tenth,
                K eleventh, L twelfth, M thirteenth, N fourteenth) {
            super(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh, twelfth,
                    thirteenth);
            this.fourteenth = fourteenth;
        }
    }

    /**
     * A 15-tuple. Also known as a fifteenple.
     */
    public static class Fifteen<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O>
            extends Fourteen<A, B, C, D, E, F, G, H, I, J, K, L, M, N> {
        public final O fifteenth;

        public Fifteen(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth, I ninth, J tenth,
                K eleventh, L twelfth, M thirteenth, N fourteenth, O fifteenth) {
            super(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh, twelfth,
                    thirteenth, fourteenth);
            this.fifteenth = fifteenth;
        }
    }

    /**
     * A 16-tuple. Also known as a sixteenple.
     */
    public static class Sixteen<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P>
            extends Fifteen<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O> {
        public final P sixteenth;

        public Sixteen(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth, I ninth, J tenth,
                K eleventh, L twelfth, M thirteenth, N fourteenth, O fifteenth, P sixteenth) {
            super(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh, twelfth,
                    thirteenth, fourteenth, fifteenth);
            this.sixteenth = sixteenth;
        }
    }

    /**
     * A 17-tuple. Also known as a seventeenple.
     */
    public static class Seventeen<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q>
            extends Sixteen<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P> {
        public final Q seventeenth;

        public Seventeen(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth, I ninth, J tenth,
                K eleventh, L twelfth, M thirteenth, N fourteenth, O fifteenth, P sixteenth, Q seventeenth) {
            super(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh, twelfth,
                    thirteenth, fourteenth, fifteenth, sixteenth);
            this.seventeenth = seventeenth;
        }
    }

    /**
     * An 18-tuple. Also known as an eighteenple.
     */
    public static class Eighteen<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R>
            extends Seventeen<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q> {
        public final R eighteenth;

        public Eighteen(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth, I ninth, J tenth,
                K eleventh, L twelfth, M thirteenth, N fourteenth, O fifteenth, P sixteenth, Q seventeenth,
                R eighteenth) {
            super(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh, twelfth,
                    thirteenth, fourteenth, fifteenth, sixteenth, seventeenth);
            this.eighteenth = eighteenth;
        }
    }

    /**
     * A 19-tuple. Also known as a nineteenple.
     */
    public static class Nineteen<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S>
            extends Eighteen<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R> {
        public final S nineteenth;

        public Nineteen(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth, I ninth, J tenth,
                K eleventh, L twelfth, M thirteenth, N fourteenth, O fifteenth, P sixteenth, Q seventeenth,
                R eighteenth, S nineteenth) {
            super(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh, twelfth,
                    thirteenth, fourteenth, fifteenth, sixteenth, seventeenth, eighteenth);
            this.nineteenth = nineteenth;
        }
    }

    /**
     * A 20-tuple. Also known as a twentyple.
     */
    public static class Twenty<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T>
            extends Nineteen<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S> {
        public final T twentieth;

        public Twenty(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth, I ninth, J tenth,
                K eleventh, L twelfth, M thirteenth, N fourteenth, O fifteenth, P sixteenth, Q seventeenth,
                R eighteenth, S nineteenth, T twentieth) {
            super(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh, twelfth,
                    thirteenth, fourteenth, fifteenth, sixteenth, seventeenth, eighteenth, nineteenth);
            this.twentieth = twentieth;
        }
    }

    /**
     * A 21-tuple. Also known as a twenty-oneple.
     */
    public static class TwentyOne<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U>
            extends Twenty<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T> {
        public final U twentyFirst;

        public TwentyOne(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth, I ninth, J tenth,
                K eleventh, L twelfth, M thirteenth, N fourteenth, O fifteenth, P sixteenth, Q seventeenth,
                R eighteenth, S nineteenth, T twentieth, U twentyFirst) {
            super(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh, twelfth,
                    thirteenth, fourteenth, fifteenth, sixteenth, seventeenth, eighteenth, nineteenth, twentieth);
            this.twentyFirst = twentyFirst;
        }
    }

    /**
     * A 22-tuple. Also known as a twenty-twople.
     */
    public static class TwentyTwo<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V>
            extends TwentyOne<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U> {
        public final V twentySecond;

        public TwentyTwo(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth, I ninth, J tenth,
                K eleventh, L twelfth, M thirteenth, N fourteenth, O fifteenth, P sixteenth, Q seventeenth,
                R eighteenth, S nineteenth, T twentieth, U twentyFirst, V twentySecond) {
            super(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh, twelfth,
                    thirteenth, fourteenth, fifteenth, sixteenth, seventeenth, eighteenth, nineteenth, twentieth,
                    twentyFirst);
            this.twentySecond = twentySecond;
        }
    }

    /**
     * A 23-tuple. Also known as a twenty-threeple.
     */
    public static class TwentyThree<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W>
            extends TwentyTwo<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V> {
        public final W twentyThird;

        public TwentyThree(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth, I ninth,
                J tenth, K eleventh, L twelfth, M thirteenth, N fourteenth, O fifteenth, P sixteenth, Q seventeenth,
                R eighteenth, S nineteenth, T twentieth, U twentyFirst, V twentySecond, W twentyThird) {
            super(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh, twelfth,
                    thirteenth, fourteenth, fifteenth, sixteenth, seventeenth, eighteenth, nineteenth, twentieth,
                    twentyFirst, twentySecond);
            this.twentyThird = twentyThird;
        }
    }

    /**
     * A 24-tuple. Also known as a twenty-fourple.
     */
    public static class TwentyFour<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X>
            extends TwentyThree<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W> {
        public final X twentyFourth;

        public TwentyFour(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth, I ninth, J tenth,
                K eleventh, L twelfth, M thirteenth, N fourteenth, O fifteenth, P sixteenth, Q seventeenth,
                R eighteenth, S nineteenth, T twentieth, U twentyFirst, V twentySecond, W twentyThird, X twentyFourth) {
            super(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh, twelfth,
                    thirteenth, fourteenth, fifteenth, sixteenth, seventeenth, eighteenth, nineteenth, twentieth,
                    twentyFirst, twentySecond, twentyThird);
            this.twentyFourth = twentyFourth;
        }
    }

    /**
     * A 25-tuple. Also known as a twenty-fiveple.
     */
    public static class TwentyFive<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y>
            extends TwentyFour<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X> {
        public final Y twentyFifth;

        public TwentyFive(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth, I ninth, J tenth,
                K eleventh, L twelfth, M thirteenth, N fourteenth, O fifteenth, P sixteenth, Q seventeenth,
                R eighteenth, S nineteenth, T twentieth, U twentyFirst, V twentySecond, W twentyThird, X twentyFourth,
                Y twentyFifth) {
            super(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh, twelfth,
                    thirteenth, fourteenth, fifteenth, sixteenth, seventeenth, eighteenth, nineteenth, twentieth,
                    twentyFirst, twentySecond, twentyThird, twentyFourth);
            this.twentyFifth = twentyFifth;
        }
    }

    /**
     * A 26-tuple. Also known as a twenty-sixple.
     */
    public static class TwentySix<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z>
            extends TwentyFive<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y> {
        public final Z twentySixth;

        public TwentySix(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth, I ninth, J tenth,
                K eleventh, L twelfth, M thirteenth, N fourteenth, O fifteenth, P sixteenth, Q seventeenth,
                R eighteenth, S nineteenth, T twentieth, U twentyFirst, V twentySecond, W twentyThird, X twentyFourth,
                Y twentyFifth, Z twentySixth) {
            super(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh, twelfth,
                    thirteenth, fourteenth, fifteenth, sixteenth, seventeenth, eighteenth, nineteenth, twentieth,
                    twentyFirst, twentySecond, twentyThird, twentyFourth, twentyFifth);
            this.twentySixth = twentySixth;
        }
    }

    // i guess i'll let running out of alphabet stop me... for now
}