package reader;

import java.io.File;
import java.util.ArrayList;

public interface Reader {
	
	public void ReadCalendar(File file, CalendarSettings settings);
	public void ReadCalendar(File file);
	public CalendarSettings GetSettings();
	public ArrayList<CalendarEvent> GetEvents();
}
