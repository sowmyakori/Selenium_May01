import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

	public static void main(String[] args) throws ParseException {
		 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		    Calendar cal = Calendar.getInstance();		  
		    Date date = new Date();
		    cal.add(Calendar.DATE, 14);
		    String newDate = dateFormat.format(cal.getTime());
		    String day = new SimpleDateFormat("d").format(dateFormat.parse(newDate));
		    System.out.println(day);

	}

}
