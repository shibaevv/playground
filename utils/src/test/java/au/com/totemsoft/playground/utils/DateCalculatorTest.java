package au.com.totemsoft.playground.utils;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

import org.junit.Test;

import au.com.totemsoft.playground.utils.DateCalculator;

public class DateCalculatorTest {

    private DateCalculator dc = new DateCalculator();

    public static LocalDate toLocalDate(String date) {
        return DateCalculator.toLocalDate(date);
    }

    public static Date toDate(String date) throws ParseException {
        return DateCalculator.toDate(date);
    }

    @Test
    public void fullDays_LocalDate() {
        //assertEquals(   0L, dc.fullDays(toLocalDate("1972-11-07"), toLocalDate("1972-11-06")));
        assertEquals(     0L, dc.fullDays(toLocalDate("1972-11-07"), toLocalDate("1972-11-07")));
        assertEquals(     0L, dc.fullDays(toLocalDate("1972-11-07"), toLocalDate("1972-11-08")));
        assertEquals(     1L, dc.fullDays(toLocalDate("1972-11-07"), toLocalDate("1972-11-09")));
        assertEquals(     1L, dc.fullDays(toLocalDate("2000-01-01"), toLocalDate("2000-01-03")));
        assertEquals(    19L, dc.fullDays(toLocalDate("1983-06-02"), toLocalDate("1983-06-22")));
        assertEquals(   173L, dc.fullDays(toLocalDate("1984-07-04"), toLocalDate("1984-12-25")));
        assertEquals(   455L, dc.fullDays(toLocalDate("2019-01-01"), toLocalDate("2020-04-01"))); // leap
        assertEquals(  5324L, dc.fullDays(toLocalDate("1969-01-03"), toLocalDate("1983-08-03")));
        assertEquals(401400L, dc.fullDays(toLocalDate("1901-01-01"), toLocalDate("2999-12-31")));
    }

    @Test
    public void fullDays_Date() throws ParseException {
        //assertEquals(   0L, dc.fullDays(toDate("1972-11-07"), toDate("1972-11-06")));
        assertEquals(     0L, dc.fullDays(toDate("1972-11-07"), toDate("1972-11-07")));
        assertEquals(     0L, dc.fullDays(toDate("1972-11-07"), toDate("1972-11-08")));
        assertEquals(     1L, dc.fullDays(toDate("1972-11-07"), toDate("1972-11-09")));
        assertEquals(     1L, dc.fullDays(toDate("2000-01-01"), toDate("2000-01-03")));
        assertEquals(    19L, dc.fullDays(toDate("1983-06-02"), toDate("1983-06-22")));
        assertEquals(   173L, dc.fullDays(toDate("1984-07-04"), toDate("1984-12-25")));
        assertEquals(   455L, dc.fullDays(toDate("2019-01-01"), toDate("2020-04-01"))); // leap
        assertEquals(  5324L, dc.fullDays(toDate("1969-01-03"), toDate("1983-08-03")));
        assertEquals(401400L, dc.fullDays(toDate("1901-01-01"), toDate("2999-12-31")));
    }

    @Test
    public void fullDays_String() throws ParseException {
        //assertEquals(   0L, dc.fullDays("1972-11-07", "1972-11-06"));
        assertEquals(     0L, dc.fullDays("1972-11-07", "1972-11-07"));
        assertEquals(     0L, dc.fullDays("1972-11-07", "1972-11-08"));
        assertEquals(     1L, dc.fullDays("1972-11-07", "1972-11-09"));
        assertEquals(     1L, dc.fullDays("2000-01-01", "2000-01-03"));
        assertEquals(    19L, dc.fullDays("1983-06-02", "1983-06-22"));
        assertEquals(   173L, dc.fullDays("1984-07-04", "1984-12-25"));
        assertEquals(   455L, dc.fullDays("2019-01-01", "2020-04-01")); // leap
        assertEquals(  5324L, dc.fullDays("1969-01-03", "1983-08-03"));
        assertEquals(401400L, dc.fullDays("1901-01-01", "2999-12-31"));
    }

}
