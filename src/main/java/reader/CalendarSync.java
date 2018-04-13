package reader;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.util.DateTime;

import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarSync implements Uploader{
	
	private ArrayList<CalendarEvent> events;
	
    /** Creates the CalendarSync object
     * @param events An {@link ArrayList} of {@link CalendarEvent} objects
     *  
    */
	public CalendarSync(ArrayList<CalendarEvent> events) {
		this.events = events;
	}
	
	private static final String APPLICATION_NAME =
		        "Calendar Reader";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/calendarReader1");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/calendar-java-quickstart
     */
    private static final List<String> SCOPES =
        Arrays.asList(CalendarScopes.CALENDAR);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
            CalendarSync.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Calendar client service.
     * @return an authorized Calendar client service
     * @throws IOException
     */
    public static com.google.api.services.calendar.Calendar
        getCalendarService() throws IOException {
        Credential credential = authorize();
        return new com.google.api.services.calendar.Calendar.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Sync the users data and connects to the Google Calendar Service.
     * @throws IOException
     */
    public static void Sync() throws IOException {
        // Build a new authorized API client service.
        // Note: Do not confuse this class with the
        //   com.google.api.services.calendar.model.Calendar class.
    	try {
	        com.google.api.services.calendar.Calendar service =
	            getCalendarService();
	
	        // List the next 10 events from the primary calendar.
	        DateTime now = new DateTime(System.currentTimeMillis());
	        Events events = service.events().list("primary")
	            .setMaxResults(10)
	            .setTimeMin(now)
	            .setOrderBy("startTime")
	            .setSingleEvents(true)
	            .execute();
	        
	        List<Event> items = events.getItems();
	        if (items.size() == 0) {
	            System.out.println("No upcoming events found.");
	        } else {
	            System.out.println("Upcoming events");
	            for (Event event : items) {
	                DateTime start = event.getStart().getDateTime();
	                if (start == null) {
	                    start = event.getStart().getDate();
	                }
	                System.out.printf("%s (%s)\n", event.getSummary(), start);
	            }
	        }
    	}
    	catch(javax.net.ssl.SSLHandshakeException e) {
    		System.out.println("Can't connect to the google server. This may be"
    				+ " an internet connection problem");
    	}
        
    }
    
    /**
     * Uploads users events to the Google Calendar.
     * If the settings object startDateIndex field is properly initialized
     * in the settings.txt file, the events are non-recurring. That means that every
     * event has a start and an end date.
     * If not, the events have a day name information alongside them, so they will be
     * recurring, and repeat themselves every weekend on the same day. They do not
     * have explicit start and end dates.
     * @param settings A {@link CalendarSettings} object
     */
    public void UploadEvents(CalendarSettings settings) {
    	if(settings.getStartDateIndex()!=-1) {
    		System.out.println("Uploading non-recurring events");
    		UploadNonRecurringEvents(settings);
    	}else {
    		System.out.println("Uploading recurring events");
    		UploadRecurringEvents(settings);
    	}
    }
    
    /**
     * Uploads recurring events to the Google Calendar Service, from the start date
     * stated in the settings object, until the end date.
     * @param settings {@link CalendarSettings} object
     */
    public void UploadRecurringEvents(CalendarSettings settings) {
    	java.util.Calendar currentDay = Calendar.getInstance();
    	currentDay.setTime(settings.getCalendarStartDate());
    	
    	System.out.println("Recrurring events - start date: " + currentDay.getTime().toString());
    	System.out.println("End date: " + settings.getCalendarEndDate().toString());
    	
    	while(currentDay.getTime().compareTo(settings.getCalendarEndDate())<=0) {
    		System.out.println("Current day: " + currentDay.getTime().toString());
	    	if(settings.workingDate(currentDay.getTime())) {
	    		System.out.println("Working day");
	    		for(int i=0; i<events.size(); i++) {
		    		if(currentDay.get(Calendar.DAY_OF_WEEK) == events.get(i).getDayWeekIndex()) {
		    	    	System.out.println("Uploading event for " + events.get(i).getDayWeekIndex());

		    			UploadRecurringEvent(events.get(i), settings, currentDay);
		    		}
		    	}
	    	}
	    	currentDay.add(Calendar.DAY_OF_MONTH, 1);
    	}
    }
    
    /**
     * Uploads non recurring events to the Google Calendar Service
     * @param settings {@link CalendarSettings} object
     */
    public void UploadNonRecurringEvents(CalendarSettings settings) {
    	
    	for(int i=0; i<events.size(); i++) {
    		System.out.println(events.get(i).getEventSummary());
    	}
    }
    
    /**
     * Uploads one recurring event to the Google Calendar Service
     * @param calEvent {@link CalendarEvent} object - the event that is uploaded
     * @param settings {@link CalendarSettings} object - the settings object
     * @param currentDay {@link Calendar} object - the day on which the event is happening.
     * This is automatically calculated in the {@link UploadRecurringEvents} method.
     */
    public void UploadRecurringEvent(CalendarEvent calEvent, CalendarSettings settings, Calendar currentDay) {
        // Build a new authorized API client service.
        // Note: Do not confuse this class with the
        //   com.google.api.services.calendar.model.Calendar class.
        com.google.api.services.calendar.Calendar service = null;
		try {
			service = getCalendarService();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Could not connect to the Calendar service");
			e1.printStackTrace();
		}

        Event event = new Event()
        	    .setSummary(calEvent.getEventSummary())
        	    .setLocation(calEvent.getEventLocation())
        	    .setDescription(calEvent.getEventDescription());
        
        
        java.util.Calendar c = java.util.Calendar.getInstance();
        java.util.Calendar c2 = java.util.Calendar.getInstance();

    	
    	if(calEvent.getStartTime() != null) {
    		c.setTime(currentDay.getTime());
    		c2.setTime(calEvent.getStartTime());
    		c.set(java.util.Calendar.HOUR_OF_DAY, c2.get(java.util.Calendar.HOUR_OF_DAY));
    		c.set(java.util.Calendar.MINUTE, c2.get(java.util.Calendar.MINUTE));
    		c.set(java.util.Calendar.SECOND, c2.get(java.util.Calendar.SECOND));
    	}
    	else {
    		c.setTime(currentDay.getTime());
    		c.set(java.util.Calendar.HOUR_OF_DAY, 9);
    		c.set(java.util.Calendar.MINUTE, 0);
    		c.set(java.util.Calendar.SECOND, 0);
    	}
    	
    	
    	DateTime startDateTime = new DateTime(c.getTime());
    	
    	EventDateTime eventStartDateTime = new EventDateTime()
    	    .setDateTime(startDateTime)
    	    .setTimeZone(settings.getTimeZone());
    	event.setStart(eventStartDateTime);

    	if(calEvent.getEndTime() != null) {
    		c.setTime(currentDay.getTime());
    		c2.setTime(calEvent.getEndTime());
    		c.set(java.util.Calendar.HOUR_OF_DAY, c2.get(java.util.Calendar.HOUR_OF_DAY));
    		c.set(java.util.Calendar.MINUTE, c2.get(java.util.Calendar.MINUTE));
    		c.set(java.util.Calendar.SECOND, c2.get(java.util.Calendar.SECOND));
    	}
    	else {
    		c.setTime(currentDay.getTime());
    		c.set(java.util.Calendar.HOUR_OF_DAY, 22);
    		c.set(java.util.Calendar.MINUTE, 0);
    		c.set(java.util.Calendar.SECOND, 0);
    	}
    	
    	
    	DateTime endDateTime = new DateTime(c.getTime());
    	
    	EventDateTime eventEndDateTime = new EventDateTime()
    	    .setDateTime(endDateTime)
    	    .setTimeZone(settings.getTimeZone());
    	event.setEnd(eventEndDateTime);
    	
    	
    	String calendarId = "primary";
    	System.out.println(event.getSummary());
    	try {
			event = service.events().insert(calendarId, event).execute();
	    	System.out.println(event.getHtmlLink());
	    	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception with the event upload");
			e.printStackTrace();
		}
    }

    /**
     * This method is not used. If needed, the user should implement this interface
     * method
     */
	@Override
	public void UploadEvents(ArrayList<CalendarEvent> events) {
		// TODO Auto-generated method stub
		
	}

	/**
     * This method is not used. If needed, the user should implement this interface
     * method
     */
	@Override
	public void UploadRecurringEvents(ArrayList<CalendarEvent> events) {
		// TODO Auto-generated method stub
		
	}

	/**
     * This method is not used. If needed, the user should implement this interface
     * method
     */
	@Override
	public void UploadRecurringEvent(CalendarEvent calEvent) {
		// TODO Auto-generated method stub
		
	}

	/**
     * This method is not used. If needed, the user should implement this interface
     * method
     */
	@Override
	public void UploadNonRecurringEvents(ArrayList<CalendarEvent> events) {
		// TODO Auto-generated method stub
		
	}

	/**
     * This method is not used. If needed, the user should implement this interface
     * method
     */
	@Override
	public void UploadNonRecurringEvent(CalendarEvent calEvent) {
		// TODO Auto-generated method stub
		
	}

}
