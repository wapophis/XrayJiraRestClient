package es.cuatrogatos.jira.xray.rest.client.core.internal.json.util;

import es.cuatrogatos.jira.xray.rest.client.api.domain.TestRun;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by lucho on 19/08/16.
 */
public class XrayJiraDateFormatterTest {
    SimpleDateFormat sdf;
    Date expectedDate;
    Date obtainedDate;
    private  XrayJiraDateFormatter formatter;
    ResourceBundle XratDateTimeFormatterLabels = ResourceBundle.getBundle("XrayDateFormatter", Locale.getDefault());


    private String[] genericDatesFormatables={"22/Jun/15 11:25 AM","22/Jun/15 11:25 PM"};
    private String[] currentWeekDaysFormatables={"Lunes 1:19 PM","Lunes 1:19 AM",
            "Martes 1:19 PM","Martes 1:19 AM",
            "Miércoles 1:19 PM","Miércoles 1:19 AM",
            "Jueves 1:19 PM","Jueves 1:19 AM",
            "Viernes 1:19 PM","Viernes 1:19 AM",
            "Sábado 1:19 PM","Sábado 1:19 AM",
            "Domingo 1:19 PM","Domingo 1:19 AM",
    };
    private String[] currentDayTimesFormatables={"Hoy 7:29 PM","Hoy 7:29 AM"};


    @Before
    public void setUp() throws Exception {
        expectedDate=null;
        obtainedDate=null;
        formatter=new XrayJiraDateFormatter();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testParseLongDates() throws Exception {
        sdf=new SimpleDateFormat("dd/MMM/yy hh:mm a");

        for(int i=0;i<genericDatesFormatables.length;i++){
            obtainedDate=formatter.parse(genericDatesFormatables[i]);
            expectedDate=sdf.parse(genericDatesFormatables[i]);
            assertEquals(getDateYear(expectedDate),getDateYear(obtainedDate));
            assertEquals(getDateWeekOfYear(expectedDate),getDateWeekOfYear(obtainedDate));
            assertEquals(getDateDay(expectedDate),getDateDay(obtainedDate));
            assertEquals(getDateHour(expectedDate),getDateHour(obtainedDate));
            assertEquals(getDateMinute(expectedDate),getDateMinute(obtainedDate));
        }

    }

    @Test
    public void testParseRecentDates() throws Exception {

        sdf=new SimpleDateFormat("EE hh:mm a");

        for(int i=0;i<currentWeekDaysFormatables.length;i++) {
            expectedDate = sdf.parse(currentWeekDaysFormatables[i]);
            obtainedDate=formatter.parse(currentWeekDaysFormatables[i]);

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, getDateYear(new Date()));                //  CURRENT YEAR
            cal.set(Calendar.WEEK_OF_YEAR, getDateWeekOfYear(new Date()));  //  CURRENT WEEK
            cal.set(Calendar.DAY_OF_WEEK, getDayOfWeekNumber(expectedDate));//  DAY EXPECTED
            cal.set(Calendar.HOUR_OF_DAY,getDateHour(expectedDate));        //  HOUR EXPECTED
            cal.set(Calendar.MINUTE,getDateMinute(expectedDate));           //  MINUTE EXPECTED
            expectedDate = cal.getTime();

            assertEquals(getDateYear(expectedDate), getDateYear(obtainedDate));
            assertEquals(getDateWeekOfYear(expectedDate), getDateWeekOfYear(obtainedDate));
            assertEquals(getDateDay(expectedDate), getDateDay(obtainedDate));
            assertEquals(getDateHour(expectedDate), getDateHour(obtainedDate));
            assertEquals(getDateMinute(expectedDate), getDateMinute(obtainedDate));
        }

    }

    @Test
    public void testParseTodayDates() throws Exception {

        sdf=new SimpleDateFormat("hh:mm a");

        for(int i=0;i<currentDayTimesFormatables.length;i++) {
            expectedDate = sdf.parse(currentDayTimesFormatables[i].replace(XratDateTimeFormatterLabels.getString("today"), ""));
            obtainedDate=formatter.parse(currentDayTimesFormatables[i]);


            Calendar cal1 = Calendar.getInstance();
            cal1.set(Calendar.YEAR, getDateYear(new Date()));                   // CURRENT YEAR
            cal1.set(Calendar.WEEK_OF_YEAR, getDateWeekOfYear(new Date()));     // CURRENT WEEK
            cal1.set(Calendar.DAY_OF_WEEK, getDayOfWeekNumber(new Date()));     // CURRENT DAY
            cal1.set(Calendar.HOUR_OF_DAY,getDateHour(expectedDate));           // EXPECTED HOUR
            cal1.set(Calendar.MINUTE,getDateMinute(expectedDate));              // EXPECTED MINUTE
            expectedDate = cal1.getTime();

            assertEquals(getDateYear(expectedDate), getDateYear(obtainedDate));
            assertEquals(getDateWeekOfYear(expectedDate), getDateWeekOfYear(obtainedDate));
            assertEquals(getDateDay(expectedDate), getDateDay(obtainedDate));
            assertEquals(getDateHour(expectedDate), getDateHour(obtainedDate));
            assertEquals(getDateMinute(expectedDate), getDateMinute(obtainedDate));
        }

    }

    private Integer getDateWeekOfYear(Date myDate){
        SimpleDateFormat sdf=new SimpleDateFormat("w");
        return Integer.parseInt(sdf.format(myDate));
    }

    private Integer getDateDay(Date myDate){
        SimpleDateFormat sdf=new SimpleDateFormat("dd");
        return Integer.parseInt(sdf.format(myDate));
    }

    private Integer getDayOfWeekNumber(Date myDate){
        SimpleDateFormat sdf=new SimpleDateFormat("u");
        Integer dayNumber=Integer.parseInt(sdf.format(myDate));
        switch(dayNumber){
            case 1:
                return Calendar.MONDAY;
            case 2:
                return Calendar.TUESDAY;
            case 3:
                return Calendar.WEDNESDAY;
            case 4:
                return Calendar.THURSDAY;
            case 5:
                return Calendar.FRIDAY;
            case 6:
                return Calendar.SATURDAY;
            case 7:
                return Calendar.SUNDAY;
        }
        return -1;
    }

    private Integer getDateHour(Date myDate){
        SimpleDateFormat sdf=new SimpleDateFormat("HH");
        return Integer.parseInt(sdf.format(myDate));
    }

    private Integer getDateMinute(Date myDate){
        SimpleDateFormat sdf=new SimpleDateFormat("mm");
        return Integer.parseInt(sdf.format(myDate));
    }

    private Integer getDateYear(Date myDate){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
        return Integer.parseInt(sdf.format(myDate));
    }
}