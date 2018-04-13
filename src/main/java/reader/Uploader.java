package reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

public interface Uploader {
	
	/** Interface method that should implementUploading of multiple events to the Calendar service
     @param settings {@link CalendarSettings} object that is parsed from the settings.txt file
     */
	public void UploadEvents(CalendarSettings settings);
	
	/** Interface method that should implement Uploading of multiple events to the Calendar service
    @param events An ArrayList of {@link CalendarEvent} objects
    */
	public void UploadEvents(ArrayList<CalendarEvent> events);
    
	/** Interface method that should implement Uploading of multiple recurring events to the Calendar service
     @param settings {@link CalendarSettings} object that is parsed from the settings.txt file
    */
    public void UploadRecurringEvents(CalendarSettings settings);
    
    /** Interface method that should implement Uploading of multiple recurring events to the Calendar service
    @param events An ArrayList of {@link CalendarEvent} objects
    */
	public void UploadRecurringEvents(ArrayList<CalendarEvent> events);
	
	 /** Interface method that should implementUploading of a single recurring event to the Calendar service
    @param calEvent {@link CalendarEvent object} that represents a single event
    @param settings {@link CalendarSettings} object that is parsed from the settings.txt file
    */
	public void UploadRecurringEvent(CalendarEvent calEvent, CalendarSettings settings, Calendar currentDay);
    
	 /** Interface method that should implementUploading of a single recurring event to the Calendar service
    @param calEvent {@link CalendarEvent object} that represents a single event
    */
    public void UploadRecurringEvent(CalendarEvent calEvent);
    
    /** Interface method that should implementUploading of multiple recurring events to the Calendar service
    @param settings {@link CalendarSettings} object that is parsed from the settings.txt file
    */
    public void UploadNonRecurringEvents(CalendarSettings settings);
   
    /** Interface method that should implement Uploading of multiple non recurring events to the Calendar service
    @param events An ArrayList of {@link CalendarEvent} objects
    */
    public void UploadNonRecurringEvents(ArrayList<CalendarEvent> events);
    
    /** Interface method that should implementUploading of a single non recurring event to the Calendar service
    @param calEvent {@link CalendarEvent object} that represents a single event
    */
    public void UploadNonRecurringEvent(CalendarEvent calEvent);
    
}
