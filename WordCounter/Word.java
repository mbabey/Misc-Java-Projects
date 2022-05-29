public class Word implements Comparable<Word> {
    
    /** The word to be scanned.
     */
    private final String word;
    
    /** Integer value that increments by one every time a certain word
     * is scanned.
     */
    private int frequency;
    
    /** Constructor. Creates a Word object.
     * 
     * @param inputWord - a string
     */
    public Word(String inputWord) {
        word = inputWord;
        frequency = 1;
    }

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }
    
    /**
     * @return the frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /** Increments the frequency by 1.
     */
    public void incrementFrequency() {
        frequency++;
    }

    /** Compare the frequency of this Word to other Word
     * If this Word has the higher frequency, return -1.
     * If this Word has the lower frequency, return 1.
     * If this Word and other Word have the same frequency, return 0.
     * 
     * @return an int.
     */
        @Override
    public int compareTo(Word other) {
        int identity = 0;
        if (frequency > other.getFrequency()) {
            identity = -1;
        } else if (frequency < other.getFrequency()) {
            identity = 1;
        }
        return identity;
    }
    
    /** 
     * @return word (a string) and frequency (an int)
     *          concatenated and formatted.
     */
    public String toString() {
        return "Word: " + word + "\nFrequency: " + frequency;
    }
    
}
