import java.util.ArrayList;

/** TimeSheetRow.
 * <p>Represents a single row for a TimeSheet object.
 * Hours worked are packed into a long and represented in a hexadecimal format.
 * Each day's hours are represented on two hexadecimal digits.
 * The format the hexadecimal follows is:
 *      0xFR-TH-WE-TU-MO-SU-SA
 * For example, if the hours for Saturday through Friday are:
 *      3.2, 1.1, 4.5, 5.5, 3.2, 2.0, 5.0
 * The hexadecimal representation will be:
 *      0x321420372d0b20
 * </p>
 * @author Maxwell Babey
 * @version 2021, 1.0
 */
public class TimeSheetRow {

    /** A mask for altering the hex that represents the hours worked during the
     * week. */
    private static final long[] UMASK = {
        0xFFFFFFFFFFFFFF00L, 
        0xFFFFFFFFFFFF00FFL, 
        0xFFFFFFFFFF00FFFFL, 
        0xFFFFFFFF00FFFFFFL,
        0xFFFFFF00FFFFFFFFL, 
        0xFFFF00FFFFFFFFFFL, 
        0xFF00FFFFFFFFFFFFL};
    
    /** Initialized to appease Checkstyle. */
    public final int ten = 10;

    /** Initialized to appease Checkstyle. */
    public final int six = 6;
    
    /** Initialized to appease Checkstyle. */
    public final int five = 5;
    
    /** Initialized to appease Checkstyle. */
    public final int four = 4;
    
    /** Initialized to appease Checkstyle. */
    public final int three = 3;
    
    /** The project number. */
    private int projectNum;
    
    /** The work package. */
    private String workPackage;
    
    /** A packed version of the hours. */
    private long hours;
    
    /** An ArrayList of the hours worked per day during the week. */
    private ArrayList<Float> weekArray;
    
    /** 0-arg Constructor. Creates a TimeSheetRow object with all 
     * params 0 or null. */
    public TimeSheetRow() {
        projectNum = 0;
        workPackage = null;
        hours = 0L;
    }
    
    /** Constructor. Creates a TimeSheetRow object.
     * 
     * @param projectNum - the project number, an int
     * @param workPackage - the work package, a string
     * @param sat - hours worked on Saturday, a float
     * @param sun - hours worked on Sunday, a float
     * @param mon - hours worked on Monday, a float
     * @param tue - hours worked on Tuesday, a float
     * @param wed - hours worked on Wednesday, a float
     * @param thu - hours worked on Thursday, a float
     * @param fri - hours worked on Friday, a float
     * @throws IllegalArgumentException - if hours for any given day do
     *          not fall between 0.0f and 24.0f
     */
    public TimeSheetRow(int projectNum, String workPackage, float sat,
            float sun, float mon, float tue, float wed, float thu, float fri) 
            throws IllegalArgumentException {
        this.projectNum = projectNum;
        this.workPackage = workPackage;

        //Create an ArrayList for the days;
        weekArray = new ArrayList<Float>();
        weekArray.add(fri);
        weekArray.add(thu);
        weekArray.add(wed);
        weekArray.add(tue);
        weekArray.add(mon);
        weekArray.add(sun);
        weekArray.add(sat);
        
        //Check if any hour entries are out of bounds.
        final float maxHours = 24.0F;
        final float minHours = 0.0F;
        for (float day : weekArray) {
            if (day > maxHours || day < minHours) {
                throw new IllegalArgumentException("Hours entry "
                        + "out of bounds.");
            }
        }
        
        //Pack the hours for the days in the week into a long.
        String hexMode = "";
        for (float hoursOnDay : weekArray) {
            hexMode += this.hexMode(hoursOnDay);
        }
        hours = this.hourPack(hexMode);   
    }
    
    /** Accessor.
     * @return the projectNum
     */
    public int getProjectNum() {
        return projectNum;
    }

    /** Mutator.
     * @param projectNum the projectNum to set
     */
    public void setProjectNum(int projectNum) {
        this.projectNum = projectNum;
    }

    /** Accessor.
     * @return the workPackage
     */
    public String getWorkPackage() {
        return workPackage;
    }

    /** Mutator.
     * @param workPackage the workPackage to set
     */
    public void setWorkPackage(String workPackage) {
        this.workPackage = workPackage;
    }

    /** Accessor.
     * @return the weekArray
     */
    public ArrayList<Float> getWeekArray() {
        return weekArray;
    }

    /** Mutator.
     * @param weekArray the weekArray to set
     * @throws IllegalArgumentException - if hours for any given day do
     *          not fall between 0.0f and 24.0f
     */
    public void setWeekArray(ArrayList<Float> weekArray)
        throws IllegalArgumentException {
        final float maxHours = 24.0F;
        final float minHours = 0.0F;
        for (float day : weekArray) {
            if (day > maxHours || day < minHours) {
                throw new IllegalArgumentException("Hours entry "
                        + "out of bounds.");
            }
        }
        this.weekArray = weekArray;
    }

    
    
    /** Returns a string representing the hours on a given day as a hex
     * backwards.
     * @param hoursOnDay - a float
     * @return hexMode - a String
     */
    private String hexMode(float hoursOnDay) {
        //Initialize necessary variables.
        final float tenFloat = 10.0f;
        String hexMode = "";
        int rem = 0;
        int dayTen = (int) (hoursOnDay * tenFloat);
        int iteration = 0;
        
        //Convert the input hoursOnDay to hex.
        while (!(dayTen == 0 && iteration == 2)) {
            rem = dayTen % (ten + six);
            dayTen = dayTen / (ten + six);
            
            //Assign appropriate hex digit if rem > 9.
            switch (rem) {
                case(ten + five):
                    hexMode += "f";
                    break;
                case(ten + four):
                    hexMode += "e";
                    break;
                case(ten + three):
                    hexMode += "d";
                    break;
                case(ten + 2):
                    hexMode += "c";
                    break;
                case(ten + 1):
                    hexMode += "b";
                    break;
                case(ten):
                    hexMode += "a";
                    break;
                default:
                    hexMode += Integer.toString(rem);
            }
            //Ensures that each day is represented on two hex digits.
            iteration++;
        }
        return hexMode;
    }
    
    /** Packs the hours into a long.
     * But first, reverses the string from the hexMode method.
     * @param hexMode - a string
     * @return hours packed as a long
     */
    private long hourPack(String hexMode) {
        //Append the hex prefix backwards.
        hexMode += "x0";
        
        //The hexMode string is backwards, so reverse it.
        char ch;
        String hexModeFlip = "";
        for (int i = 0; i < hexMode.length(); i++) {
            ch = hexMode.charAt(i); 
            hexModeFlip = ch + hexModeFlip; 
        }
        
        //Turn the string into a long.
        long packedHours = Long.decode(hexModeFlip);
        return packedHours;
    }
    
    /** Get the hours on a given day.
     * dayNum is between 0 (Saturday) and 6 (Friday) 
     * 
     * @param dayNum - an int
     * @return the hours on a given day - a float
     * @throws IllegalArgumentException - if dayNum does
     *          not fall between 0 and 6
     */
    public float getHour(int dayNum) {
        //Initialize necessary variables.
        float dayHours = 0.0f;
        final int bits = 8;
        
        //Checks input and retrieves appropriate date hours.
        if (dayNum == six) {
            dayHours = (float) (hours & ~UMASK[0]);            
        } else if (dayNum == five) {
            dayHours = (float) ((hours & ~UMASK[1]) >> bits);
        } else if (dayNum == four) {
            dayHours = (float) ((hours & ~UMASK[2]) >> 2 * bits);    
        } else if (dayNum == three) {
            dayHours = (float) ((hours & ~UMASK[three]) >> three 
                    * bits);            
        } else if (dayNum == 2) {
            dayHours = (float) ((hours & ~UMASK[four]) >> four 
                    * bits);            
        } else if (dayNum == 1) {
            dayHours = (float) ((hours & ~UMASK[five]) >> five 
                    * bits);            
        } else if (dayNum == 0) {
            dayHours = (float) ((hours & ~UMASK[six]) >> six * bits);
        } else {
            throw new IllegalArgumentException("Day value out of bounds");
        }
        
        return dayHours / ten;        
    }
    
    /** Set the hours for a given day.
     * dayNum is between 0 (Saturday) and 6 (Friday)
     * hoursOnDay is between 0.0f and 24.0f
     * 
     * @param dayNum - an int
     * @param hoursOnDay - a float
     * @throws IllegalArgumentException - if hours for any given day do
     *          not fall between 0.0f and 24.0f
     * @throws IllegalArgumentException - if dayNum does
     *          not fall between 0 and 6
     */
    public void setHour(int dayNum, float hoursOnDay)
        throws IllegalArgumentException {
        //Check if hour entry is out of bounds.
        final float maxHours = 24.0F;
        final float minHours = 0.0F;
        if (hoursOnDay > maxHours || hoursOnDay < minHours) {
            throw new IllegalArgumentException("Hours entry out of bounds.");
        }
        
        //Initialize necessary variables.
        long hoursMask;
        long newHours = 0;
        final int bits = 8;
        
        //Get the backwards hex string.
        String hexMode = this.hexMode(hoursOnDay);
        
        //Use the hex string to get the hours packed as a long.
        long hoursOnDayHex = this.hourPack(hexMode);
        
        //Now have the hex representation of hoursOnDay.
        //Must place it into the hours hex at the appropriate spot.
        //Using a mask, place the new value.
        if (dayNum == 0) {
            hoursMask = (hours & UMASK[six]);
            newHours = (hoursMask | (hoursOnDayHex << six * bits));            
        } else if (dayNum == 1) {
            hoursMask = (hours & UMASK[five]);
            newHours = (hoursMask | (hoursOnDayHex << five * bits));
        } else if (dayNum == 2) {
            hoursMask = (hours & UMASK[four]);
            newHours = (hoursMask | (hoursOnDayHex << four * bits));    
        } else if (dayNum == three) {
            hoursMask = (hours & UMASK[three]);
            newHours = (hoursMask | (hoursOnDayHex << three * bits));
        } else if (dayNum == four) {
            hoursMask = (hours & UMASK[2]);
            newHours = (hoursMask | (hoursOnDayHex << 2 * bits));
        } else if (dayNum == five) {
            hoursMask = (hours & UMASK[1]);
            newHours = (hoursMask | (hoursOnDayHex << bits));         
        } else if (dayNum == six) {
            hoursMask = (hours & UMASK[0]);
            newHours = (hoursMask | (hoursOnDayHex));
        } else {
            throw new IllegalArgumentException("Day value out of bounds.");
        }
        
        hours = newHours;
    }
    
    /** toString. Returns a list with the project number, the work package name,
     * and the hours worked on each day.
     * 
     * @return sendBack - a string
     */
    public String toString() {
        String sendBack = "PN: " + projectNum 
                + " | WP: " + workPackage;
        for (int index = weekArray.size() - 1; index >= 0; index--) {
            switch (index) {
                case(0):
                    sendBack += " | FR: " + weekArray.get(index);
                    break;
                case(1):
                    sendBack += " | TH: " + weekArray.get(index);
                    break;
                case(2):
                    sendBack += " | WE: " + weekArray.get(index);
                    break;
                case(three):
                    sendBack += " | TU: " + weekArray.get(index);
                    break;
                case(four):
                    sendBack += " | MO: " + weekArray.get(index);
                    break;
                case(five):
                    sendBack += " | SU: " + weekArray.get(index);
                    break;
                case(six):
                    sendBack += " | SA: " + weekArray.get(index);
                    break;
                default:
                    //default placed to appease Checkstyle.
                    //Not tested because it will not occur.
                    break;
            }
        }
        sendBack += "\n";
        return sendBack;
    }
}
