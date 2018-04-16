package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTool {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

    /**
     * 获取当前日期,格式 "YYYYMMDD"
     *
     * @return
     */
    public static String getToday() {
        Calendar calendar = Calendar.getInstance();
        StringBuffer today = new StringBuffer();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH) + 1;
        int d = calendar.get(Calendar.DAY_OF_MONTH);

        today.append(y);
        if (m < 10) {
            today.append("0");
        }
        today.append(m);
        if (d < 10) {
            today.append("0");
        }
        today.append(d);

        return today.toString();
    }

    /**
     * 获取输入日期的前 n天 (n为负整数)或后n天(n为正整数)。 getBeforeOrNextDay("20120720",1) 返回
     * 20120721 getBeforeOrNextDay("20120720",-1) 返回 20120719
     *
     * @param day
     * @param n
     * @return
     */
    public static String getBeforeOrNextDay(String day, int n) {
        if (day == null || "".equals(day) || day.length() != 8) {
            throw new RuntimeException("由于缺少必要的参数，获取输入日期的前 n天 或后n天出错：day=" + day + ";n=" + n);
        }
        try {
            String sYear = day.substring(0, 4);
            int year = Integer.parseInt(sYear);
            String sMonth = day.substring(4, 6);
            int month = Integer.parseInt(sMonth);
            String sDay = day.substring(6, 8);
            int dday = Integer.parseInt(sDay);
            Calendar cal = Calendar.getInstance();
            cal.set(year, month - 1, dday);
            cal.add(Calendar.DATE, n);
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            return df.format(cal.getTime());
        } catch (Exception e) {
            throw new RuntimeException("进行日期运算时输入得参数不符合系统规格." + e);
        }
    }
}
