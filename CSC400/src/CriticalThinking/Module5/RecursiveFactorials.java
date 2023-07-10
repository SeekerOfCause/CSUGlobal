package CriticalThinking.Module5;

public class RecursiveFactorials {
    // Straight recursive calculation by submitting n-1 on each recursive call
    public static int straightFactorail(int n) {
        if (n == 0) {
            return 1;
        } else {
        	System.out.println(n + " * " + (n-1));
            return n * straightFactorail(n - 1);
        }
    }

    // Factorial by multiplying a 'factorial' accumulator value by n on each recursion, then submitting n-1 into the next iteration
    public static int factorailWithAccumulator(int n, int factorial) {
        if (n == 0) {
            return factorial;
        } else {
        	System.out.println(n + " * " + factorial);
            return factorailWithAccumulator(n - 1, n * factorial);
        }
    }

    public static void main(String[] args) {
        int n1 = 5;

        // Straight recursion
        int factorial1 = straightFactorail(n1);
        System.out.println("Factorial of 5 using straight recursion: " + factorial1);

        // Recursion with an accumulator
        int factorial2 = factorailWithAccumulator(n1, 1);
        System.out.println("Factorial of 5 using an accumulator: " + factorial2);
        
        int n2 = 7;
        
        int factorial3 = straightFactorail(n2);
        System.out.println("\nFactorial of 7 using straight recursion: " + factorial3);
        
        int factorial4 = factorailWithAccumulator(n2, 1);
        System.out.println("Factorial of 7 using an accumulator: " + factorial4);
        
    }
}
