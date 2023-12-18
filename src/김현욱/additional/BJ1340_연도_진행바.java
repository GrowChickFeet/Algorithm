package 김현욱.additional;

import java.io.*;

public class BJ1340_연도_진행바 {
    public static int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static int getDays(int year, int month) {
        if (month == 2 && isLeapYear(year)) {
            return 29;
        } else {
            return months[month - 1];
        }
    }

    public static int getMonth(String month) {
        switch (month) {
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
            default:
                return -1;
        }
    }

    public static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine().replace(",", "");
        String[] split = input.split(" ");

        String monthStr = split[0];
        int month = getMonth(monthStr);
        int day = Integer.parseInt(split[1]);
        int year = Integer.parseInt(split[2]);
        int hour = Integer.parseInt(split[3].split(":")[0]);
        int min = Integer.parseInt(split[3].split(":")[1]);
        long total = (isLeapYear(year) ? 366 : 365) * 24 * 60;
        long time = 0;
        for (int i = 1; i < month; i++) {
            time += 24 * 60 * getDays(year, i);
        }
        time += 24 * 60 * (day - 1) + hour * 60 + min;
        bw.write(Double.toString((double) (time) * 100 / total));
        br.close();
        bw.close();
    }
}
