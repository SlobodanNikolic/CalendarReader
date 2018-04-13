package reader;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalendarImportCSV calendarImport = new CalendarImportCSV();
		CalendarSettings settings = calendarImport.GetSettings();
		File file = ViewController.getInstance().OpenFileChooser();
		if(file!=null)
			calendarImport.ReadCalendar(file, settings);
		else {
			return;
		}
		

		CalendarSync uploader = new CalendarSync(calendarImport.GetEvents());
		uploader.UploadEvents(settings);
		
	}

}
