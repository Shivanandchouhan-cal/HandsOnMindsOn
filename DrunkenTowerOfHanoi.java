import java.math.BigInteger;
 
public class DrunkenTowerOfHanoi {
    private static final int MODULO = 1_000_000_000;
 
    public static long expectedMoves(int n, int k, int a, int b, int c) {
        long hanoiMoves = (1L << n) - 1;
        long drunkWalk = drunkardExpectedSteps(k, b, a) + drunkardExpectedSteps(k, a, c) + drunkardExpectedSteps(k, c, b);
        return (hanoiMoves * drunkWalk) % MODULO;
    }
 
    private static long drunkardExpectedSteps(int k, int start, int target) {
        return 2L * start * (k - target);
    }
 
    public static void main(String[] args) {
        BigInteger sum = BigInteger.ZERO;
        for (int n = 1; n <= 10000; n++) {
            int k = 10 * n, a = 3 * n, b = 6 * n, c = 9 * n;
            sum = sum.add(BigInteger.valueOf(expectedMoves(n, k, a, b, c))).mod(BigInteger.valueOf(MODULO));
        }
        System.out.println(sum);
    }
}
