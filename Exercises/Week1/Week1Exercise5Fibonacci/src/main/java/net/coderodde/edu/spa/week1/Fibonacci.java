package net.coderodde.edu.spa.week1;

public class Fibonacci {

    public static final class BruteForce {
        
        public static long fibonacci(int n) {
            switch (n) {
                case 0:
                    return 0L;
                    
                case 1:
                    return 1L;
                    
                default:
                    return fibonacci(n - 1) + fibonacci(n - 2);
            }
        }
    }
    
    public static final class DynamicProgramming {
        
        public static long fibonacci(int n) {
            switch (n) {
                case 0:
                    return 0L;
                    
                case 1:
                case 2:
                    return 1L;
            }
            
            long[] memory = new long[n + 1];
            
            memory[1] = 1L;
            memory[2] = 1L;
            
            return fibonacci(n, memory);
        }
        
        private static long fibonacci(int n, long[] memory) {
            if (memory[n] != 0) {
                return memory[n];
            }
            
            return memory[n] = fibonacci(n - 1, memory) + memory[n - 2];
        }
    }
    
    public static void main(String[] args) {
        int N = 43;
        
        long startTime = System.currentTimeMillis();
        long result = BruteForce.fibonacci(N);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Bruteforce fib(" + N + ") = " + result + " in " +
                           (endTime - startTime) + " milliseconds.");
        
        startTime = System.currentTimeMillis();
        result = DynamicProgramming.fibonacci(N);
        endTime = System.currentTimeMillis();
        
        System.out.println("Dynamic programming fib(" + N + ") = " + result + 
                           " in " + (endTime - startTime) + " milliseconds.");
    }
}
