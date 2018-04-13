package reader;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class CalendarImportCSV implements Reader {

    /** An {@link ArrayList} of {@link String} objects that represent the lines of the
     *  file containing the event information
     */
	public ArrayList<String> eventStrings;
	/** An {@link ArrayList} of {@link CalendarEvent} objects that represent the
     * events extracted from the eventStrings object
     */
	public ArrayList<CalendarEvent> events;
	/** A number of first lines of the file that serve as titles for the
	 * Calendar events.
     */
	public String[] titles;
	
	/** An empty constructor
     */
	public CalendarImportCSV() {
		
	}
	
	/** Read Calendar function implements the Reader interface. 
	 * It parses the {@link File} containing the events, according to the formatting specified
	 * in the {@link CalendarSettings} settings object.
	 * @param file the {@link File} that contains the events
	 * @param settings the {@link CalendarSettings} object containing the formatting information
	 * of the file that contains the events
     */
	@Override
	public void ReadCalendar(File file, CalendarSettings settings) {
		// TODO Auto-generated method stub
		Scanner scanner = null;
		String calendarHeader = null;
		eventStrings = new ArrayList<String>();
		events = new ArrayList<CalendarEvent>();
		
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//If a callendar has a title header
        if(scanner.hasNextLine() && settings.getTitleLine()) {
        	for(int z=0; z<settings.getTitleLinesNumber(); z++) {
	        	calendarHeader = scanner.nextLine();
	        	titles = calendarHeader.split(settings.getWordDelimitter());
        	}
        }
        
        //If newline separates two records
        if(settings.getLineDelimitter().compareTo("new_line") == 0) {
	        //Adding every line of the file
	        while(scanner.hasNextLine()) {
	        	eventStrings.add(scanner.nextLine());
	        }
	        
	        scanner.close();
        }else {
        	scanner.useDelimiter(settings.getLineDelimitter());
        	while(scanner.hasNext()) {
        		eventStrings.add(scanner.next());
        	}
        }
        
        //Separating the events
        String[] eventSections;
        for(int i = 0; i<eventStrings.size(); i++) {
        	eventSections = eventStrings.get(i).split(settings.getWordDelimitter());
        	CalendarEvent e = new CalendarEvent();
        	Boolean filterPassed = false;
        	//First, we try to filter out the event
        	if(settings.getFilterIndex() != -1) {
        		String[] filters = settings.getFilters();
        		for(int j = 0; j < filters.length; j++) {
        			if(eventSections[settings.getFilterIndex()].contains(filters[j])) {
        				filterPassed = true;
        			}
        		}
        	} 
        	
        	if(!filterPassed)
        		continue;
        	
        	//Getting the name of the day
        	if(settings.getDayIndex() != -1) {
        		if(settings.getStringQuotes()) {
//        			System.out.println(eventSections[settings.getDayIndex()].replaceAll("\u00A0", ""));
        			e.setDayName(eventSections[settings.getDayIndex()].replaceAll("\u00A0", "").replaceAll("\"", "").trim());
        			
        		}
        		else {
        			e.setDayName(eventSections[settings.getDayIndex()].replaceAll("\u00A0", "").trim());
        		}
        		int index = -1;
        		//Setting the index of the day in a week
        		if((index = getDayWeekIndex(e.getDayName(), settings.getDaysShortExpressions())) != -1) {
        			e.setDayWeekIndex(index);
        		}

        	}
        	
        	//Getting the start date of the event
        	if(settings.getStartDateIndex()!=-1) {
            	String startDateString = null;
        		if(settings.getStringQuotes()) {
        			startDateString = eventSections[settings.getStartDateIndex()].replaceAll("\"", "").trim();        			
        		}
        		else {
        			startDateString = eventSections[settings.getStartDateIndex()].trim();        			
        		}
        		Date startDate=null;
        		SimpleDateFormat format = new SimpleDateFormat(settings.getDateFormat());
        		try {
        			startDate = format.parse(startDateString);

        		} catch (ParseException e1) {
        			// TODO Auto-generated catch block
        			e1.printStackTrace();
        		}
        		e.setStartDate(startDate);
        	}
        	
        	//Getting the end date of the event
        	if(settings.getEndDateIndex()!=-1) {
            	String endDateString = null;
        		if(settings.getStringQuotes()) {
        			endDateString = eventSections[settings.getEndDateIndex()].replaceAll("\"", "").trim();        			
        		}
        		else {
        			endDateString = eventSections[settings.getEndDateIndex()].trim();        			
        		}
        		Date endDate=null;
        		SimpleDateFormat format = new SimpleDateFormat(settings.getDateFormat());
        		try {
        			endDate = format.parse(endDateString);

        		} catch (ParseException e1) {
        			// TODO Auto-generated catch block
        			e1.printStackTrace();
        		}
        		e.setEndDate(endDate);
        	}
        	
        	
        	Date startTime=null;
    		Date endTime=null;
        	//Getting the start and the end time for the event
        	if(settings.getEventStartTimeIndex() != -1) {

        		//If start and end time indexes are the same,
        		//Then the start and end time are contained in the same field, sepparated by a string
        		if(settings.getEventEndTimeIndex()!=-1 && 
    				settings.getEventStartTimeIndex() == settings.getEventEndTimeIndex()) {
	        		String dateString = "";
	        		if(settings.getStringQuotes()) {
	        			dateString = eventSections[settings.getEventStartTimeIndex()].trim().replaceAll("\"", "");
	        		}
	        		else {
	        			dateString = eventSections[settings.getEventStartTimeIndex()].trim();
	        		}
	        		if(settings.getStartEndTimeSep().compareTo("false")!=0) {
		        		String[] timeArray = dateString.split(settings.getStartEndTimeSep());
		        		if(timeArray[1].length()<3) {
		        			timeArray[1] = timeArray[1].concat(":00");
		        		}
		        		startTime = new Date();
		        		endTime = new Date();
		
		        	    SimpleDateFormat format = new SimpleDateFormat(settings.getTimeFormat());
		        		try {
		        			startTime = format.parse(timeArray[0]);
		        			endTime = format.parse(timeArray[1]);
		
		        		} catch (ParseException e1) {
		        			// TODO Auto-generated catch block
		        			e1.printStackTrace();
		        		}
	        		}
	        		
	        		e.setStartTime(startTime);
	        		e.setEndTime(endTime);
        		}
        		else {
        			String startTimeString = null;
        			String endTimeString = null;
        			
	        		if(settings.getStringQuotes()) {
	        			startTimeString = eventSections[settings.getEventStartTimeIndex()].trim().replaceAll("\"", "");
	        			endTimeString = eventSections[settings.getEventEndTimeIndex()].trim().replaceAll("\"", "");
	        		}
	        		else {
	        			startTimeString = eventSections[settings.getEventStartTimeIndex()].trim();
	        			endTimeString = eventSections[settings.getEventEndTimeIndex()].trim();
	        		}
	        		startTime = new Date();
	        		endTime = new Date();
	
	        	    SimpleDateFormat format = new SimpleDateFormat(settings.getTimeFormat());
	        		try {
	        			startTime = format.parse(startTimeString);
	        			endTime = format.parse(endTimeString);
	
	        		} catch (ParseException e1) {
	        			// TODO Auto-generated catch block
	        			e1.printStackTrace();
	        		}
        		}
        	}
        	
        	if(settings.getLocationIndex() != -1) {
        		if(settings.getStringQuotes())
        			e.setEventLocation(eventSections[settings.getLocationIndex()].trim().replaceAll("\"", ""));
        		else
        			e.setEventLocation(eventSections[settings.getLocationIndex()].trim());
        	}
        	
        	if(settings.getDescriptionIndexes() != null) {
        		int[] descInd = settings.getDescriptionIndexes();
        		String desc = "";
        		for(int j=0; j<descInd.length; j++) {
        			desc += eventSections[descInd[j]] + ". ";
        		}
        		e.setEventDescription(desc);
        	}
        	
        	
        	if(settings.getSummaryIndexes() != null) {
        		int[] sumInd = settings.getSummaryIndexes();
        		String sum = "";
        		for(int j=0; j<sumInd.length; j++) {
        			sum += eventSections[sumInd[j]] + ". ";
        		}
        		e.setEventSummary(sum);
        	}
        	
        	events.add(e);
        	
        }
        
	}
	
	/** An utility method that returns the index of the day of the week
	 * @param dayName The name of the day as specified in the events file, 
	 * for example MON (for Monday)
	 * @param aliases A list of Strings, containing all of the aliases for the day names,
	 * for example: MON (for Monday), TUE (for Tuesday), WED, THU, FRI, SAT, SUN 
     */
	public int getDayWeekIndex(String dayName, String[] aliases) {
		
		for(int i = 0; i<aliases.length; i++) {
			if(dayName.compareTo(aliases[i])==0)
				return i+1;
		}
		return -1;
	}

	/** GetSettings function implements the {@link Reader} interface.
	 * It parses the settings.txt file, and gathers all of the information about
	 * the format of the file that contains the information
	 * about the events.
	 * @return settings A {@link CalendarSettings} object that contains the information about the formatting
	 * of the file containing the events.
     */
	@Override
	public CalendarSettings GetSettings() {
		// TODO Auto-generated method stub
		Scanner scanner = null;
		String line = "";
		CalendarSettings settings = null;
		
		try {
			scanner = new Scanner(new File("settings_file.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		line = scanner.nextLine();
		line = line.substring(15).trim();
		String fileExtension = line;
		
		line = scanner.nextLine();
		line = line.substring(16).trim();
		//Line delimiter for the file. Newline is usual, but can be anything
		//Separates two events of the input file
		String lineDelimitter = line;
		
		//Separates two parts of the same event
		line = scanner.nextLine();
		line = line.substring(16).trim();
		String wordDelimitter = line;
		
		line = scanner.nextLine();
		line = line.substring(11).trim();
		//Is the files first line the title line
		Boolean titleLine = Boolean.parseBoolean(line);
		
		//Do strings in the file have prefix and postfix quotes
		line = scanner.nextLine();
		line = line.substring(14).trim();
		Boolean stringQuotes = Boolean.parseBoolean(line);
		
		//Start date of the calendar
		line = scanner.nextLine();
		line = line.substring(20).trim();
		Date calendarStartDate = new Date();
	    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		try {
			if(line.compareTo("false")!=0)
				calendarStartDate = format.parse(line);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//End date of the calendar
		line = scanner.nextLine();
		line = line.substring(18).trim();
		Date calendarEndDate = new Date();
		try {
			if(line.compareTo("false")!=0)
				calendarEndDate = format.parse(line);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		line = scanner.nextLine();
		line = line.substring(18).trim();
		Date[] nonWorkingDates = null;
		if(line.compareTo("false")!=0) {
			String[] dateStrings = line.split(",");
			//List of non working dates
			nonWorkingDates = new Date[dateStrings.length];
			for(int i = 0; i < dateStrings.length; i++){
				try {
					nonWorkingDates[i] = format.parse(dateStrings[i]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//Checking if saturdays are work days
		line = scanner.nextLine();
		line = line.substring(18).trim();
		Boolean workingSaturdays = Boolean.parseBoolean(line);
		
		//Checking if sundays are work days
		line = scanner.nextLine();
		line = line.substring(16).trim();
		Boolean workingSundays = Boolean.parseBoolean(line);
		
		//if a part of the record is a day name, or a shortend day name,
		//index of that part of the event
		line = scanner.nextLine();
		line = line.substring(10).trim();
		int dayIndex = -1;
		if(line.compareTo("false")!=0)
			dayIndex = Integer.parseInt(line);
		
		//if a part of the event is a day name, and has a shortened representation
		line = scanner.nextLine();
		line = line.substring(23).trim();
		String[] daysShortExpressions = line.split(",");
		for(int i=0; i<daysShortExpressions.length; i++)
			daysShortExpressions[i]=daysShortExpressions[i].trim();
		
		//index of the start date of the event in the calendar
		line = scanner.nextLine();
		line = line.substring(17).trim();
		int startDateIndex = -1;
		if(line.compareTo("false")!=0)
			startDateIndex = Integer.parseInt(line);
		
		//index of the end date of the event in the calendar
		line = scanner.nextLine();
		line = line.substring(15).trim();
		int endDateIndex = -1;
		if(line.compareTo("false")!=0)
			endDateIndex = Integer.parseInt(line);
		
		//index of the start time of the event in the calendar
		line = scanner.nextLine();
		line = line.substring(23).trim();
		int eventStartTimeIndex = -1;
		if(line.compareTo("false")!=0)
			eventStartTimeIndex=Integer.parseInt(line);
		
		//index of the end time of the event in the calendar
		line = scanner.nextLine();
		line = line.substring(21).trim();
		int eventEndTimeIndex = -1;
		if(line.compareTo("false")!=0)
			eventEndTimeIndex = Integer.parseInt(line);
		
		//index of the end time of the event in the calendar
		line = scanner.nextLine();
		line = line.substring(31).trim();
		String startEndTimeSep = null;
		if(line.compareTo("false")!=0)
			startEndTimeSep = line;
		
		//index of the event duration in the calendar
		line = scanner.nextLine();
		line = line.substring(21).trim();
		int eventDurationIndex = -1;
		if(line.compareTo("false")!=0)
			eventDurationIndex = Integer.parseInt(line);
		
		//indexes of the event parts that the calendar should be filtered by
		//system automatically compares filters with the event parts at these indexes
		line = scanner.nextLine();
		line = line.substring(13).trim();
		int filterIndex = -1;
		if(line.compareTo("false")!=0)
			filterIndex = Integer.parseInt(line);
		
		//filter strings (values of the filters)
		line = scanner.nextLine();
		line = line.substring(8).trim();
		String[] filterStrings = line.split(",");
		String[] filters = new String[filterStrings.length];
		for(int i = 0; i<filterStrings.length; i++)
			filters[i] = filterStrings[i].trim();
		
		//Index of the location info in the event, or false
		line = scanner.nextLine();
		line = line.substring(15).trim();
		int locationIndex = -1;
		if(line.compareTo("false")!=0)
			locationIndex = Integer.parseInt(line);
		
		//Indexes of the description strings in the event
		line = scanner.nextLine();
		line = line.substring(20).trim();
		int[] descriptionIndexes = null;
		if(line.compareTo("false")!=0) {
			String[] descStrings = line.split(",");
			descriptionIndexes = new int[descStrings.length];
			for(int i = 0; i < descStrings.length; i++) {
				descStrings[i] = descStrings[i].trim();
				descriptionIndexes[i] = Integer.parseInt(descStrings[i]);
			}
		}
		
		//Indexes of the summary strings in the event
		line = scanner.nextLine();
		line = line.substring(16).trim();
		int[] summaryIndexes = null;
		if(line.compareTo("false")!=0) {
			String[] sumStrings = line.split(",");
			summaryIndexes = new int[sumStrings.length];
			for(int i = 0; i < sumStrings.length; i++) {
				sumStrings[i] = sumStrings[i].trim();
				summaryIndexes[i] = Integer.parseInt(sumStrings[i]);
			}
		}
				
		//Get the timezone for event times
		line = scanner.nextLine();
		line = line.substring(10).trim();
		String timeZone=null;
		if(line.compareTo("false")!=0) {
			timeZone = line;
		}
		
		//Get the time format for event times
		line = scanner.nextLine();
		line = line.substring(12).trim();
		String timeFormat=null;
		if(line.compareTo("false")!=0) {
			timeFormat = line;
		}
		
		//Get the date format for event times
		line = scanner.nextLine();
		line = line.substring(12).trim();
		String dateFormat=null;
		if(line.compareTo("false")!=0) {
			dateFormat = line;
		}
		
		
		//Get the reminder type for event times
		line = scanner.nextLine();
		line = line.substring(14).trim();
		String reminderType=null;
		if(line.compareTo("false")!=0) {
			reminderType = line;
		}
		
		//Get the number of title lines
		line = scanner.nextLine();
		line = line.substring(19).trim();
		int titleLinesNumber = -1;
		if(line.compareTo("false")!=0)
			titleLinesNumber = Integer.parseInt(line);
		
		System.out.println("Success");
        scanner.close();
        
        settings = new CalendarSettings(fileExtension, lineDelimitter, wordDelimitter, titleLine, 
        		stringQuotes, calendarStartDate, calendarEndDate, nonWorkingDates, workingSaturdays, 
        		workingSundays, dayIndex, daysShortExpressions, startDateIndex, endDateIndex, 
        		eventStartTimeIndex, eventEndTimeIndex, startEndTimeSep ,eventDurationIndex, filterIndex, filters,
        		locationIndex, descriptionIndexes, summaryIndexes, timeZone, timeFormat, dateFormat, reminderType,
        		titleLinesNumber);
        
        return settings;
	}
	
	/** Returns the ArrayList containing the events
	 * @return events An ArrayList of the events parsed from the file
     */
	@Override
	public ArrayList<CalendarEvent> GetEvents(){
		return events;
	}

	/** An implementation of the Reader interface, not used.
	 * @param file {@link File} that is parsed
     */
	@Override
	public void ReadCalendar(File file) {
		// TODO Auto-generated method stub
		
	}

}
