import java.util.Arrays;

public class NimSquare {
    static final int LIMIT = 100000;
    static int[] grundy = new int[LIMIT + 1];

   
    static void computeGrundyNumbers() {
        Arrays.fill(grundy, -1);
        grundy[0] = 0; // Base case

        for (int n = 1; n <= LIMIT; n++) {
            boolean[] mex = new boolean[100];
            for (int i = 1; i * i <= n; i++) {
                mex[grundy[n - i * i]] = true;
            }
            
            for (int g = 0; g < mex.length; g++) {
                if (!mex[g]) {
                    grundy[n] = g;
                    break;
                }
            }
        }
    }

   
    static long countLosingPositions() {
        int maxGrundy = 0;
        
       
        for (int i = 0; i <= LIMIT; i++) {
            maxGrundy = Math.max(maxGrundy, grundy[i]);
        }

        int[] freq = new int[maxGrundy + 1];

        
        for (int i = 0; i <= LIMIT; i++) {
            freq[grundy[i]]++;
        }

        long count = 0;

        
        for (int g1 = 0; g1 <= maxGrundy; g1++) {
            for (int g2 = g1; g2 <= maxGrundy; g2++) {
                int g3 = g1 ^ g2; // Solve for g3
                if (g3 < g2 || g3 > maxGrundy) continue;

               
                if (g1 == g2 && g2 == g3) {
                    count += (long) freq[g1] * (freq[g1] - 1) * (freq[g1] - 2) / 6;
                } else if (g1 == g2) {
                    count += (long) freq[g1] * (freq[g1] - 1) / 2 * freq[g3];
                } else if (g2 == g3) {
                    count += (long) freq[g2] * (freq[g2] - 1) / 2 * freq[g1];
                } else {
                    count += (long) freq[g1] * freq[g2] * freq[g3];
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
       

        computeGrundyNumbers();
        long losingPositions = countLosingPositions();

       
        System.out.println("Number of losing positions: " + losingPositions);
       
    }
}
