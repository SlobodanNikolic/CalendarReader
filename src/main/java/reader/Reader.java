package reader;

import java.io.File;
import java.util.ArrayList;

public interface Reader {
	/** Interface method that should implement the reading of the {@link File} file that contains
	 * the events, using a {@link CalendarSettings} object that should be assigned 
	 * to settings parameter.
	 * @param file - the File that is read
	 * @param settings - Calendar Settings object, that is parsed from settings.txt 
     */
	public void ReadCalendar(File file, CalendarSettings settings);
	/** Interface method that should implement the reading of the {@link File} file that contains
	 * the events
	 * @param file File that is read
     */
	public void ReadCalendar(File file);
	/** Interface method that should implement getting the {@link CalendarSettings} object
     */
	public CalendarSettings GetSettings();
	/** Interface method that should implement getting the {@link ArrayList} object,
	 * containing {@link CalendarEvent} objects
     */
	public ArrayList<CalendarEvent> GetEvents();
}
