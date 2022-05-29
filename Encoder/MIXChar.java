/** MIXChar.
 * Represents a MIXChar object.
 * <p> The MIXChars are a set of 56 characters. In this class, they are 
 * represented in the array THE_MIX. A MIXChar can be constructed with either 
 * a character, or an integer representing the location of the MIXChar in the
 * array THE_MIX. </p>
 * @author Maxwell Babey
 * @version 2021, 1.0
 */
public class MIXChar {

    /** The code representing uppercase delta. */
    private static final char DELTA = '\u0394';

    /** The code representing uppercase sigma. */
    private static final char SIGMA = '\u03a3';
    
    /** The code representing uppercase pi. */
    private static final char PI = '\u03a0';
    
    /** An array containing the MIXChars. */
    private static final char[] THE_MIX = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 
        'G', 'H', 'I', DELTA, 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
        SIGMA, PI, 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2',
        '3', '4', '5', '6', '7', '8', '9', '.', ',', '(', ')', '+', '-',
        '*', '/', '=', '$', '<', '>', '@', ';', ':', '\''};
    
    /** The numeric value represented by this MIXChar. */
    private int mixVal;
    
    /** Constructor. Creates a MIXChar object if possible.
     * 
     * @param c - a character
     * @throws IllegalArgumentException if c cannot be converted to a MIXChar
     */
    public MIXChar(char c) throws IllegalArgumentException {
        if (!isMIXChar(c)) {
            throw new IllegalArgumentException("Character entry is not valid.");
        }
        for (int i = 0; i < THE_MIX.length; i++) {
            if (c == THE_MIX[i]) {
                mixVal = i;
            }
        }
    }
    
    /** Constructor.
     * Takes an index and gets the character at that index of THE_MIX.
     * @param index - an int
     * @throws IllegalArgumentsException if index is not between 0 and 55.
     */
    public MIXChar(int index) throws IllegalArgumentException {
        if (index > THE_MIX.length - 1 || index < 0) {
            throw new IllegalArgumentException("Index out of bounds.");
        }
        mixVal = index;
    }
    
    /** Determines if the parameter character is a MIXChar.
     * 
     * @param c - a char
     * @return true if c is a MIXChar, false otherwise
     */
    public static boolean isMIXChar(char c) {
        for (char check : THE_MIX) {
            if (c == check) {
                return true;
            }
        }
        return false;
    }
    
    /** Converts the MIXChar object into the corresponding Java char.
     * 
     * @return the Java char corresponding to this MIXChar
     */
    public char toChar() {
        return THE_MIX[mixVal];
    }
    
    /** toString.
     * Converts the array of MIXChars into an array.
     * 
     * @param inputArray - an array of MIXChars
     * @return a string with characters corresponding to the
     *      characters in the input array.
     */
    public static String toString(MIXChar[] inputArray) {
        String mixString = "";
        for (MIXChar mixC : inputArray) {
            mixString += mixC.toChar();
        }
        return mixString;
    }
    
    /** toMIXChar.
     * Converts a string into an array of MIXChars.
     * 
     * @param s - a String
     * @return an array of MIXChar characters corresponding to the chars in s
     */
    public static MIXChar[] toMIXChar(String s) {
        MIXChar plug;
        MIXChar[] mixArray = new MIXChar[s.length()];
        for (int i = 0; i < s.length(); i++) {
            plug = new MIXChar(s.charAt(i));
            mixArray[i] = plug;
        }
        return mixArray;
    }
    
    /** Ordinal. 
     * 
     * @return the numerical value of this MIXChar.
     */
    public int ordinal() {
        return mixVal;
    }
    
    /** toString.
     * @return a string containing this MIXChar as a Java character.
     */
    public String toString() {
        String mixxie = "";
        mixxie += THE_MIX[mixVal];
        return mixxie;
    }
    
    
}
