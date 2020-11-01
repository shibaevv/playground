package au.com.totemsoft.playground.utils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.TimeZone;

public class DateCalculator {

    private static final String PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
    private static final DateFormat FORMAT = new SimpleDateFormat(PATTERN);
    static {
        // to eliminate TZ daylight saving offsets
        FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
    private static final long MILLISECONDS_PER_DAY = 24 * 60 * 60 * 1000L;
    private static final long DAYS_PER_YEAR = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30 + 31;
    private static final long DAYS_PER_LEAPYEAR = 31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30 + 31;

    public static LocalDate toLocalDate(String date) {
        return FORMATTER.parse(date, LocalDate::from);
    }

    public static Date toDate(String date) throws ParseException {
        return FORMAT.parse(date);
    }

    public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    /**
     * OPTION 1 - Using Java 8 java.time.temporal.ChronoUnit class and java.time.LocalDate
     * The first and the last day are considered partial days and never counted
     * @param startDate
     * @param endDate
     * @return
     */
    public long fullDays(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("endDate before startDate");
        }
        if (endDate.isEqual(startDate)) {
            return 0;
        }
        LocalDate startDateInclusive = startDate.plusDays(1);
        LocalDate endDateExclusive = endDate;
        long days = ChronoUnit.DAYS.between(startDateInclusive, endDateExclusive);
        return days;
    }

    /**
     * OPTION 2 - Using Java java.util.Date class
     * The first and the last day are considered partial days and never counted
     * @param startDate
     * @param endDate
     * @return
     */
    public long fullDays(Date startDate, Date endDate) {
        if (endDate.before(startDate)) {
            throw new IllegalArgumentException("endDate before startDate");
        }
        if (endDate.compareTo(startDate) == 0) {
            return 0;
        }
        Date startDateInclusive = addDays(startDate, 1);
        Date endDateInclusive = endDate;
        long diff = endDateInclusive.getTime() - startDateInclusive.getTime();
        //long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        long days = diff / MILLISECONDS_PER_DAY;
        return days;
    }

    /**
     * OPTION 3 - From the dev manager - write the code from scratch and not use libraries?
     * The first and the last day are considered partial days and never counted
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException 
     */
    public long fullDays(String startDate, String endDate) throws ParseException {
        if (endDate.compareTo(startDate) < 0) {
            throw new IllegalArgumentException("endDate before startDate");
        }
        if (endDate.compareTo(startDate) == 0) {
            return 0;
        }
        // get year/day (can just parse date string)
        Calendar c = Calendar.getInstance();
        // start year/day
        c.setTime(toDate(startDate));
        final int startYear = c.get(Calendar.YEAR);
        final int startDay = c.get(Calendar.DAY_OF_YEAR);
        // end year/day
        c.setTime(toDate(endDate));
        final int endYear = c.get(Calendar.YEAR);
        final int endDay = c.get(Calendar.DAY_OF_YEAR);
        //
        long days = 0;
        for (int y = startYear; y <= endYear; y++) {
            // check simple boundary conditions
            if (startYear == endYear) {
                days += (endDay - startDay);
                break;
            }
            final boolean leapYear = new GregorianCalendar().isLeapYear(y);
            if (startYear < y && y < endYear) {
                days += (leapYear ? DAYS_PER_LEAPYEAR : DAYS_PER_YEAR);
                continue;
            }
            //
            if (y == startYear) {
                days += ((leapYear ? DAYS_PER_LEAPYEAR : DAYS_PER_YEAR) - startDay);
                continue;
            }
            //
            if (y == endYear) {
                days += endDay;
                continue;
            }
        }
        return days - 1;
    }

    private static void print(Scanner scanner) throws ParseException {
        final DateCalculator dc = new DateCalculator();
        System.out.print("Enter start date [" + PATTERN + "]: ");
        String startDate = scanner.next();
        System.out.print("Enter end date [" + PATTERN + "]: ");
        String endDate = scanner.next();
        //
        //long fullDays = dc.fullDays(toLocalDate(startDate), toLocalDate(endDate));
        long fullDays = dc.fullDays(toDate(startDate), toDate(endDate));
        System.out.println(String.format("%s - %s: %d days", startDate, endDate, fullDays));
        //
    }

    public static void main(String args[] ) throws Exception {
        System.out.println(">>> Date Calculator <<<");
        try (Scanner scanner = new Scanner(System.in);) {
            while (true) {
                print(scanner);
                //
                System.out.print("Continue (Y/N)?: ");
                String result = scanner.next().toUpperCase();
                if (!result.equals("Y")) {
                    break;
                }
                System.out.println();
            }
        }
        //
        System.exit(0);
    }

}
