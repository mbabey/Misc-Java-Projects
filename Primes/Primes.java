import java.util.ArrayList;
import java.util.Scanner;

/** Primes.
 * Finds prime numbers between 0 and N.
 * Contains methods to print all prime numbers between 0 and N,
 * count the prime numbers between 0 and N, and determine if a
 * certain number between 0 and N is prime.
 * 
 * @author Maxwell Babey
 * @version 2021, 1.0
 */
public class Primes {
    
    /** Boolean Arraylist. Primes will be true, non-primes will be false.
     */
    private ArrayList<Boolean> primes;
    
    /** The size of ArrayList primes, and the upper limit of the
     * numbers to be checked for prime status.
     */
    private int n;
    
    /** Constructor. Creates a Primes obj.
     * 
     * @param num - an int
     */
    public Primes(int num) {
        // num represents the upper limit of the numbers to
        // be checked for prime status.
        n = num + 1;
        primes = new ArrayList<Boolean>();
        for (int i = 0; i < n; i++) {
            primes.add(true);
        }
        calculatePrimes();
    }
    
    /** Determines the prime numbers between 0 and N.
     */
    private void calculatePrimes() {
        // Determines the numbers that are prime. Sets their value to true.
        primes.set(0, false);
        primes.set(1, false);
        int falseIndex;
        for (int index = 2; index < Math.sqrt(n); index++) {
            if (primes.get(index)) {
                // This loop eliminates all numbers that are a 
                //multple of a prime.
                for (int mult = 0; (index * index) + (mult * index) < n;
                        mult++) {
                    falseIndex =  ((index * index) + (mult * index));
                    primes.set(falseIndex, false);
                }
            }
        }
    }
    
    /** Prints a list of prime numbers between 0 and N.
     * 
     * @return listOfPrimes - a string
     */
    public String printPrimes() {
        ArrayList<Integer> arrayOfPrimes = new ArrayList<Integer>();
        for (int index = 0; index < n; index++) {
            if (primes.get(index)) {
                arrayOfPrimes.add(index);
            }
        }
        String listOfPrimes = "";
        for (int indexPrint = 0; indexPrint < arrayOfPrimes.size();
                indexPrint++) {
            listOfPrimes += arrayOfPrimes.get(indexPrint) + " ";
        }
        
        return listOfPrimes;
    }

    /** Counts the number of prime numbers between 0 and N.
     * 
     * @return count - an int
     */
    public int countPrimes() {
        int count = 0;
        for (int index = 0; index < n; index++) {
            if (primes.get(index)) {
                count++;
            }
        }
        return count;
    }
    
    /** Checks if a certain index is prime.
     * 
     * @param index - the number to check prime status on.
     * @return true - if the number is prime.
     *         false - if the number is not prime.
     */
    public boolean isPrime(int index) {
        return primes.get(index);
    }
    
    /** Drives the program.
     * 
     * @param args unused
     */
    public static void main(String[] args) {
       
        // Take an input from the user. Return an error if the input
        // is less than 2.
        Scanner inputScan = new Scanner(System.in);
        int userInput;
        do {
            System.out.print("Enter an upper bound: ");
            userInput = inputScan.nextInt();
            if (userInput < 2) {
                System.out.print("Please choose a number greater"
                        + " than 1. ");
            }
        } while (userInput < 2);
        
        //Create a Primes object.
        Primes prime1 = new Primes(userInput);
        
        //Print number of primes between 0 and userInput.
        System.out.println("The amount of prime numbers between 0 and "
                + userInput + ": " + prime1.countPrimes());
        
        //Ask the user to pick a number.
        int userPrimeSeek;
        do {
            System.out.print("Enter a number between 0 and "
                    + userInput + " to find out if it is prime: ");
            userPrimeSeek = inputScan.nextInt();
        } while (userPrimeSeek > userInput);
        inputScan.close();
        
        //Tell the user if the number is prime or not.
        if (prime1.isPrime(userPrimeSeek)) {
            System.out.println("It is prime!");
        } else {
            System.out.println("It is not prime.");
        }
        
        //Print a list of primes.
        System.out.println("The prime numbers are:");
        System.out.println(prime1.printPrimes());
            
    }
}
