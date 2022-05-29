import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/** WordCounter. Counts the number of unique words in an input text file. For 
 * each unique word, counts the number of occurrences of that word.
 * 
 * @author Maxwell Babey
 * @version 2021, 1.0
 */
public class WordCounter {

    /** A list of all the words in the file being scanned. Each element
     * of the list has a corresponding frequency.
     */
    public static ArrayList<Word> listOfWords = new ArrayList<Word>();
    
    /** Parses a file. Adds new words, or increments the frequency of words that
     * already exist. Returns the size of the list of words.
     * @param fileName - the name of the file, a string
     * @throws FileNotFoundException
     * @return listOfWords.size() - the size of the list of words, an int
     */
    public int parseBook(String fileName) throws FileNotFoundException {
        
        //Create a new scanner containing the file to be parsed.
        Scanner fileScan = new Scanner(new File(fileName));
        fileScan.useDelimiter("[^A-Z'a-z]+");
        
        //Initialize switch. This will be tripped if a match is found.
        boolean match = false;    
        
        //Scan the file.
        while (fileScan.hasNext()) {
            Word wordOfGod = new Word(fileScan.next());
            
            //Add the first word manually. Avoid dead loop.
            if (listOfWords.size() == 0) {
                listOfWords.add(wordOfGod);
            } else {
                //Does wordOfGod match a word that is already in listOfWords?
                //Search the entire list for a match.
                for (int index = 0; index < listOfWords.size(); index++) {
                    //If there is a match, increment frequency and
                    //set match to true.
                    if ((wordOfGod.getWord()).equalsIgnoreCase(
                            (listOfWords.get(index)).getWord())) {
                        (listOfWords.get(index)).incrementFrequency();
                        match = true;
                    }
                }
                //If match is false, no match was found. Add the word.
                if (!match) {                         
                    listOfWords.add(wordOfGod);
                }
                //Reset match in the event it was flipped.
                match = false;
            }
        }
        fileScan.close();
        return listOfWords.size();
    }    
    
    /** Sorts listOfWords based on frequency. Adds the top listSize most frequent
     * words to a list. Returns the list as a formatted string.
     * @param listSize - the size of the top words list, an int
     * @return words - formatted list of top words, a string
     */
    public String printTopWords (int listSize) {
        // First, sort the words in listOfWords based on frequency.
        listOfWords.sort(null);
        
        // Initialize top word list.
        ArrayList<Word> topWords = new ArrayList<Word>();
        // Take the top (listSize) words and add them to the top word list.
        for (int index = 0; index < listSize; index++) {
            topWords.add(listOfWords.get(index));
        }
        //Create a list of words in string format.
        String words = "The top " + listSize + " words:";
        for (int index = 0; index < topWords.size(); index++) {
            words += "\nWord: " + (topWords.get(index)).getWord() 
                + "   Frequency: " + (topWords.get(index)).getFrequency();
        }
        return words;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        
        WordCounter theBible = new WordCounter();
        
        System.out.println("Unique words: " 
                + theBible.parseBook("input.txt"));
        
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter an amount of most frequent words to view: ");
        int listSize = userInput.nextInt();
        userInput.close();
        
        System.out.println(theBible.printTopWords(listSize));
    }

}
