/** Message.
 * Represents a message encoded into MIXChar as a long. Includes methods 
 * for decoding.
 * <p>Message can be constructed with either a string or an array of MIXChars. 
 * When constructed with a string, the string is converted to an array of 
 * MIXChars before being encoded. The MIXChars are encoded as longs and 
 * stored in an array of longs. The encoding process is as follows:
 * <ol>
 * <li>Determine the number of characters in the MIXChar array.
 * <li>Determine the length of the instance long[] used to store the encoded
 * MIXChars.
 * <li>For each set of 11 MIXChars, convert the ordinal values of each MIXChar 
 * into base 56 and add them to a long. Repeat this for every set of 11 
 * MIXChars.
 * <li>If there are fewer than 11 MIXChars, repeat the above process only the 
 * necessary amount of times.
 * <li> Add each long, as it is created, to the instance long[].
 * </ol>
 * The decoding process is as follows:
 * <ol>
 * <li>Because they are stored in base 56, the MIXChars can be retrieved out of 
 * the longs in which they are stored by converting those longs back into 
 * base 10. 
 * <li>This gets the ordinal value of each encoded MIXChar.
 * <li>The ordinal value
 * can be used to retrieve the corresponding MIXChar, which is then added to a
 * string. This string is the original input, decoded.
 * </ol>
 * </p>
 * 
 * @author Maxwell Babey
 * @version 2021, 1.0
 */
public class Message {

    /** The maximum number of MIXChars that can be packed into one long. */
    public static final int MAX_PACK = 11;
    
    /** The base to convert into. */
    public static final int BASE = 56;
    
    /** The array of MIXChars encoded into longs. */
    private long[] encodeArray;
    
    /** Counts the total number of MIXChars in an array of MIXChars. */
    private int count;
    
    /** Constructor. 
     * Accepts an array of MIXChars and passes them to helper method encode.
     * @param m - an array of MIXChars.
     */
    public Message(MIXChar[] m) {
        this.encode(m);
    }
    
    /** Constructor.
     * Accepts a string, converts it to na array of MIXChars and passes them
     * to helper method encode.
     * @param s - the string to convert to an array.
     */
    public Message(String s) {
        MIXChar[] m = MIXChar.toMIXChar(s);
        this.encode(m);
    }
    
    /** This helper method packs MIXChars in array m into longs. Each long
     * stores 11 MIXChars. Those longs are then stored in an 
     * array of longs mixArray.
     * 
     * @param m - an array of MIXChars.
     */
    private void encode(MIXChar[] m) {
        //Counts the number of MIXChars in MIXChar[] m.
        count = m.length;
        int localCount = count;
        
        /* If the remainder of count / 11 = 0, then mixArrayLength = count / 11.
         * Otherwise, mixArrayLength = count / 11 + 1. In this case, the 
         * +1 element of mixArray[] will have less than 11 MIXChars.*/ 
        int encodeArrayLength = (count % MAX_PACK == 0) ? count / MAX_PACK 
                : (count / MAX_PACK) + 1;
        encodeArray = new long[encodeArrayLength];
        
        /* Convert the ordinal values of each set of 11 MIXChars
         * in m into base 56, add them to a long, then add that long to
         * encodeArray. */
        int encodeArrayIndex = 0;
        long ordinalAtIndex = 0L;
        long ordinalInBase = 0L;
        for (int iteration = 0; iteration < encodeArrayLength; iteration++) {
            if (localCount > MAX_PACK) {
                for (int index = 0; index < MAX_PACK; index++) {
                    // Get the MIXChar ordinal at a particular index of m.
                    ordinalAtIndex = (long) m[index + (MAX_PACK 
                            * (iteration))].ordinal();
                
                    // Convert the ordinal to base 56 and add to a long.
                    ordinalInBase += ordinalAtIndex 
                            * (long) Math.pow(BASE, (index % MAX_PACK));
                }
                // Add the long to encodeArray at the correct index.
                encodeArray[encodeArrayIndex] = ordinalInBase;
            
                // Increment the index in which to place ordinalInBase 
                // and reset ordinalInBase for the next loop.    
                encodeArrayIndex++;
                ordinalInBase = 0L;
                localCount -= MAX_PACK;
            } else {
                for (int index = 0; index < localCount; index++) {
                    // Get the MIXChar ordinal at a particular index of m.
                    ordinalAtIndex = (long) m[index + (MAX_PACK 
                            * (iteration))].ordinal();
                
                    // Convert the ordinal to base 56 and add to a long.
                    ordinalInBase += ordinalAtIndex 
                            * (long) Math.pow(BASE, (index % MAX_PACK));
                }
                // Add the long to mixArray at the correct index.
                encodeArray[encodeArrayIndex] = ordinalInBase;
            }
        }
    }
    
    /** toString.
     * Decodes the longs in mixArray back into MIXChars, then places those 
     * MIXChars into a string. Returns the string.
     * 
     * @return decoded array of longs as a string
     */
    public String toString() {
        //Initialize necessary variables.
        int localCount = count;
        long toDecode = 0L;
        long ordinal = 0L;
        String decoded = "";
        MIXChar mixxie;
        
        //Decoding loop.
        for (int iteration = 0; iteration < encodeArray.length; iteration++) {
            
            //Get the long to decode.
            toDecode = encodeArray[iteration];
            
            /* Divide the long at encodeArray index by 56 MAX_PACK times,
             * take the remainder separately each time,
             * use the remainder to get the corresponding MIXChar,
             * add the MIXChar to a string. */
            if (localCount > MAX_PACK) {
                for (int j = 0; j < MAX_PACK; j++) {
                    ordinal = Long.remainderUnsigned(toDecode, BASE);
                    mixxie = new MIXChar((int) ordinal);
                    decoded += mixxie.toChar();
                    toDecode = Long.divideUnsigned(toDecode, BASE);
                }
                localCount -= MAX_PACK;
                
            /* Divide the long at encodeArray index by 56 localCount times,
             * take the remainder separately each time,
             * use the remainder to get the corresponding MIXChar,
             * add the MIXChar to a string. */
            } else {
                for (int j = 0; j < localCount; j++) {
                    ordinal = Long.remainderUnsigned(toDecode, BASE);
                    mixxie = new MIXChar((int) ordinal);
                    decoded += mixxie.toChar();
                    toDecode = Long.divideUnsigned(toDecode, BASE);
                }
            }
        }
        return decoded;
    }
    
    /** toLongs.
     * Prints the unsigned longs in encodeArray at each index as 
     * a series of strings, separated by spaces.
     * @return a string of the unsigned longs in encodeArray
     */
    public String toLongs() {
        String longString = "";
        for (long toPrint : encodeArray) {
            longString += Long.toUnsignedString(toPrint) + " ";
        }
        return longString;
    }

    public static void main(String[] args) {
      Message msg = new Message("THIS PROGRAM ENCODES A MESSAGE USING MIXCHARS INTO AN ARRAY OF LONGS. IT CAN DECODE THE MESSAGE TOO.");
      System.out.println(msg.toLongs());
      System.out.println(msg.toString());
    }
}
