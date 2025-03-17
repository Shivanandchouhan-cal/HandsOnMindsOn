import java.util.*;

public class Prime {
    static List<Integer> primes = new ArrayList<>();
    static Set<Integer> primeSet = new HashSet<>();
    static List<Integer> bestSet = new ArrayList<>(); 

    public static void main(String[] args) {
        generatePrimes(10000);
        
        int result = findLowestPrimeSet();
        
        System.out.println("Lowest sum of five primes: " + result);
        System.out.println("Prime set: " + bestSet);
    }

    
    static void generatePrimes(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                primes.add(i);
                primeSet.add(i);
                for (int j = i * 2; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    
    static boolean isPrimePair(int a, int b) {
        return isPrime(concat(a, b)) && isPrime(concat(b, a));
    }

    
    static int concat(int a, int b) {
        return Integer.parseInt("" + a + b);
    }

    
    static boolean isPrime(int num) {
        if (num < 2) return false;
        if (primeSet.contains(num)) return true;  
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    
    static int findLowestPrimeSet() {
        return findPrimeSet(new ArrayList<>(), 0, Integer.MAX_VALUE);
    }

    
    static int findPrimeSet(List<Integer> currentSet, int index, int minSum) {
        if (currentSet.size() == 5) {
           
            int sum = 0;
            for (int num : currentSet) {
                sum += num;
            }

            
            if (sum < minSum) {
                minSum = sum;
                bestSet = new ArrayList<>(currentSet);  
            }

            return minSum;
        }

        for (int i = index; i < primes.size(); i++) {
            int prime = primes.get(i);
            if (isValid(currentSet, prime)) {
                currentSet.add(prime);
                minSum = findPrimeSet(currentSet, i + 1, minSum);  
                currentSet.remove(currentSet.size() - 1);  
            }
        }

        return minSum;
    }

    
    static boolean isValid(List<Integer> set, int newPrime) {
        for (int p : set) {
            if (!isPrimePair(p, newPrime)) {
                return false;
            }
        }
        return true;
    }
}
