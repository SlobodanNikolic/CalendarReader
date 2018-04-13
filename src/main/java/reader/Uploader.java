package reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

public interface Uploader {
	
	public void UploadEvents(CalendarSettings settings);
	
	public void UploadEvents(ArrayList<CalendarEvent> events);
    
    public void UploadRecurringEvents(CalendarSettings settings);
    
	public void UploadRecurringEvents(ArrayList<CalendarEvent> events);
	
	public void UploadRecurringEvent(CalendarEvent calEvent, CalendarSettings settings, Calendar currentDay);
    
    public void UploadRecurringEvent(CalendarEvent calEvent);
    
    public void UploadNonRecurringEvents(CalendarSettings settings);
   
    public void UploadNonRecurringEvents(ArrayList<CalendarEvent> events);
    
    public void UploadNonRecurringEvent(CalendarEvent calEvent);
    
}
