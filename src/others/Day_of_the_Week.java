package others;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Yangxiao on 9/7/2019.
 */
public class Day_of_the_Week {
	
	public String dayOfTheWeek(int day, int month, int year) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat weekFormat = new SimpleDateFormat("EEEE");
		try {
			Date date = simpleDateFormat.parse(year + "-" + month + "-" + day);
			return weekFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "error";
	}
	
}
