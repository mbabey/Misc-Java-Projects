import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/** TimeSheet.
 * Represents a TimeSheet object.
 * Each TimeSheet has an employee number, an end of week, and a list of rows.
 * The end of the week must be a Friday.
 * Each row is a TimeSheetRow object.
 * 
 * @author Maxwell Babey
 * @version 2021, 1.0
 */
public class TimeSheet {

    /** Initialized to appease Checkstyle. */
    private final int five = 5;
    
    /** The number of an employee. */
    private String empNum;
    
    /** The end of the week. Must be a Friday. */
    private LocalDate endWeek;
    
    /** List of TimeSheetRow objects. */
    private List<TimeSheetRow> details = new ArrayList<TimeSheetRow>();

    /** Constructor. Makes a TimeSheet object.
     * 
     * @param emp - a String. The number of an employee.
     * @param date - a LocalDate. Must be a Friday. if not, it is adjusted to
     *                  a Friday. Represents the end of the week.
     */
    public TimeSheet(String emp, LocalDate date) {
        empNum = emp;
        //Checks if the day of the week in date is a Friday. 
        //If not, changes it to the next Friday.
        DayOfWeek isFriday = DayOfWeek.from(date);
        endWeek = (isFriday.getValue() == five) ? date 
                : date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
    }
   
    /** Zero-param constructor.
     *  Sets all instance variables to null.
     */
    public TimeSheet() {
        empNum = null;
        endWeek = null;
    }

    /** Getter.
    * @return the empNum
    */
    public String getEmpNum() {
        return empNum;
    }
   
    /** Setter.
     * @param empNum the empNum to set
     */
    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }

    /** Getter.
     * @return the endWeek
     */
    public LocalDate getEndWeek() {
        return endWeek;  
    }    
   
    /** Setter.
     * @param endWeek the endWeek to set
     * @throws IllegalArgumentException if param endWeek is not a Friday.
     */
    public void setEndWeek(LocalDate endWeek) throws IllegalArgumentException {
        //Checks if the day of the week in date is a Friday. If not, 
        //  throw an exception.
        DayOfWeek isFriday = DayOfWeek.from(endWeek);
        if (isFriday.getValue() == five) {
            this.endWeek = endWeek;
        } else {
            throw new IllegalArgumentException("End of the week "
                + "must be a Friday");
        }
    }
   
    /** Getter.
     * @return the details
     */
    public List<TimeSheetRow> getDetails() {
        return details;
    }   
    
    /** Adds a new TimeSheetRow to details list.
     * 
     * @param newRow - the TimeSheetRow to add to details
     */
    public void addRow(TimeSheetRow newRow) {
        details.add(newRow);
    }
    
    /** toString.
     * @return a String of data about the TimeSheet object.
     */
    public String toString() {
        return "Timesheet data:\nEmployee Number: " + empNum
                + "\nLast day of week: " + endWeek.getDayOfWeek()
                + "\nTimesheet:\n" + details;
    }
}
