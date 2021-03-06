package task1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class WeekCalendarTask1 {

    public String date;

    private String[] header = new String[]{"日", "一", "二", "三", "四", "五", "六"};

    private String[] printWeekDates;

    public WeekCalendarTask1(String date) {
        this.date = date;
        this.printWeekDates = parseToWeekDates(date);
    }


    public String formatTextOutPut() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < header.length; i++) {
            sb.append(header[i]);
            if (i != printWeekDates.length - 1) {
                sb.append("\t");
            }
        }
        sb.append("\n");
        for (int i = 0; i < printWeekDates.length; i++) {
            sb.append(printWeekDates[i]);
            if (i != printWeekDates.length - 1) {
                sb.append("\t");
            }
        }
        return sb.toString();
    }

    public String[] parseToWeekDates(String inputDate) {
        String[] weekDates = new String[7];
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(inputDate));
            // 当前日期是该周中的第几天
            int currentDatIndex = cal.get(Calendar.DAY_OF_WEEK);
            // 将calendar跳转到上周的最后一天，后续遍历七次 add 1
            int firstDateOfWeekIndex = cal.getFirstDayOfWeek() - currentDatIndex;
            cal.add(Calendar.DATE, firstDateOfWeekIndex - 1);
            // 遍历七次 找到每个日期
            for (int i = 0; i < weekDates.length; i++) {
                cal.add(Calendar.DATE, 1);
                weekDates[i] = new SimpleDateFormat("d").format(cal.getTime());
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("illegal arg");
        }
        return weekDates;
    }

}
