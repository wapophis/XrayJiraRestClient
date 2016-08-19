package es.cuatrogatos.jira.xray.rest.client.core.internal.json.util;

import org.joda.time.DateTime;

import java.io.InputStream;
import java.text.*;
import java.util.*;

/**
 * Created by lucho on 18/08/16.
 */
public class XrayJiraDateFormatter {
    private final SimpleDateFormat standardDateTimeFormatter=new SimpleDateFormat("dd/MMM/yy hh:mm a");
    private final SimpleDateFormat recentDateTimeFormatter=new SimpleDateFormat("EE hh:mm a");
    private final SimpleDateFormat todayDateTimeFormatter=new SimpleDateFormat("hh:mm a");
    private final SimpleDateFormat weekInYearDateTimeFormatter=new SimpleDateFormat("w");
    private final SimpleDateFormat yearDateTimeFormatter=new SimpleDateFormat("yyyy");

    public Date parse(String inputDate) throws ParseException{
        Date out=null;
        try {
            out=standardDateTimeFormatter.parse(inputDate);
        } catch (ParseException e) {
            try {
                out=recentDateTimeFormatter.parse(inputDate);
                Integer currentWeek=Integer.parseInt(weekInYearDateTimeFormatter.format(new Date()));
                Integer currentYear=Integer.parseInt(yearDateTimeFormatter.format(new Date()));
                Calendar cal=Calendar.getInstance();
                cal.setTime(out);
                cal.set(Calendar.YEAR,currentYear);
                cal.set(Calendar.WEEK_OF_YEAR,currentWeek);
                out=cal.getTime();
               } catch (ParseException e1) {
                    out = todayDateTimeParse(inputDate);
            }
        }
        return out;
    }


    private Date todayDateTimeParse(String inputDate) throws ParseException {

        ResourceBundle labels = ResourceBundle.getBundle("XrayDateFormatter", Locale.getDefault());
        String todayValue=labels.getString("today");
        if(inputDate.contains(labels.getString("today"))){
            return todayDateTimeFormatter.parse(inputDate.replace(labels.getString("today"),""));
        }
        return null;
    }


}
