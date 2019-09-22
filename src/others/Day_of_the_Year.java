package others;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Yangxiao Wang on 8/10/2019
 */
public class Day_of_the_Year {
	
	public static void main(String[] args) {
		Day_of_the_Year cs = new Day_of_the_Year();
		System.out.println(cs.dayOfYear("2019-01-09"));
	}
	
	public int dayOfYear(String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		long diff = -1;
		try {
			Date date1 = simpleDateFormat.parse(date);
			Date date2 = simpleDateFormat.parse(date.substring(0, date.indexOf('-')) + "-01-01");
			diff = Math.round((date1.getTime() - date2.getTime()) / (double) 86400000) + 1;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int) diff;
	}
	
	
	class Solution {
		public int dayOfYear(String date) {
			Calendar cld = Calendar.getInstance();
			int[] time = Arrays.stream(date.split("-")).mapToInt(t -> Integer.parseInt(t)).toArray();
			cld.set(time[0], time[1] - 1, time[2]);
			return cld.get(Calendar.DAY_OF_YEAR);
		}
	}
	
	class Solution_2 {
		public int dayOfYear(String S) {
			String[] s = S.split("-");
			int year = Integer.parseInt(s[0]);
			int month = Integer.parseInt(s[1]);
			int date = Integer.parseInt(s[2]);
			boolean isLeap = checkYear(year);
			int noOfDays = 0;
			int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
			for (int i = 0; i < month - 1; i++) {
				if (isLeap && i == 1) {
					noOfDays += days[i] + 1;
					continue;
				}
				noOfDays += days[i];
			}
			return noOfDays + date;
		}
		
		boolean checkYear(int year) {
			if (year % 400 == 0)
				return true;
			if (year % 100 == 0)
				return false;
			if (year % 4 == 0)
				return true;
			return false;
		}
	}
	
	
}
