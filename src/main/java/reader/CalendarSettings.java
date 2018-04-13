package reader;

import java.util.Date;

public class CalendarSettings {
	
	//Extension of the calendar input file
	private String fileExtension;
	//Line delimiter for the file. Newline is usual, but can be anything
	//Separates two events of the input file
	private String lineDelimitter;
	//Separates two parts of the same event
	private String wordDelimitter;
	//Is the files first line the title line
	private Boolean titleLine;
	//Do strings in the file have prefix and postfix quotes
	private Boolean stringQuotes;
	//Start date of the calendar
	private Date calendarStartDate;
	//End date of the calendar
	private Date calendarEndDate;
	//List of non working dates
	private Date[] nonWorkingDates;
	//time zone format string of the events
	private String timeZone;
	//time format string of the events
	private String timeFormat;
	//date format string of the events
	private String dateFormat;
	//reminder type of the events
	private String reminderType;
	//number of lines before records, that are not important for the calendar
	private int titleLinesNumber;
	

	/**
	 * Gets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 * @return titleLinesNumber The {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public int getTitleLinesNumber() {
		return titleLinesNumber;
	}

	
	/**
	 * Sets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setTitleLinesNumber(int titleLinesNumber) {
		this.titleLinesNumber = titleLinesNumber;
	}
	
	/**
	 * Gets the {@link Integer} value representing the time zone
	 * in the settings file.
	 * @return titleLinesNumber The {@link Integer} value representing the time zone
	 * in the settings file.
	 */
	public String getTimeZone() {
		return timeZone;
	}

	/**
	 * Sets the {@link Integer} value representing the time zone
	 * in the settings file.
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * Gets the {@link Integer} value representing the time format
	 * @return titleLinesNumber The {@link Integer} value representing the time format
	 */
	public String getTimeFormat() {
		return timeFormat;
	}

	/**
	 * Sets the {@link Integer} value representing the time format
	 * in the settings file.
	 */
	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	/**
	 * Gets the {@link Integer} value representing the date format
	 * in the settings file.
	 * @return titleLinesNumber The {@link Integer} value representing the date format
	 * in the settings file.
	 */
	public String getDateFormat() {
		return dateFormat;
	}

	/**
	 * Sets the {@link Integer} value representing the date format
	 * in the settings file.
	 */
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	/**
	 * Gets the {@link String} value representing the reminder type
	 * in the settings file.
	 * @return titleLinesNumber The {@link Integer} value representing the reminder type
	 */
	public String getReminderType() {
		return reminderType;
	}

	/**
	 * Sets the {@link Integer} value representing the reminder type
	 * in the settings file.
	 */
	public void setReminderType(String reminderType) {
		this.reminderType = reminderType;
	}

	/**
	 * Returns weather or not the {@link Date} is a working day
	 */
	public Boolean workingDate(Date d) {
		if(nonWorkingDates == null)
			return true;
		for(int i = 0; i < nonWorkingDates.length; i++) {
			if(d.compareTo(nonWorkingDates[i])==0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Gets the {@link Date} value representing the end date of the calendar
	 * in the settings file.
	 * @return titleLinesNumber The {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public Date getCalendarEndDate() {
		return calendarEndDate;
	}

	/**
	 * Sets the {@link Integer} value representing the end date of the calendar
	 * in the settings file.
	 */
	public void setCalendarEndDate(Date calendarEndDate) {
		this.calendarEndDate = calendarEndDate;
	}
	private Boolean workingSaturdays;
	private Boolean workingSundays;
	//if a part of the record is a day name, or a shortend day name,
	//index of that part of the event
	private int dayIndex;
	//if a part of the event is a day name, and has a shortend representation
	private String[] daysShortExpressions;
	//index of the start date of the event in the calendar
	private int startDateIndex;
	//index of the end date of the event in the calendar
	private int endDateIndex;
	//index of the start time of the event in the calendar
	private int eventStartTimeIndex;
	//index of the end date of the event in the calendar
	private int eventEndTimeIndex;
	//Start time and end time separator
	private String startEndTimeSep;
	//indexes of the event parts that go into the event description
	private int[] descriptionIndexes;
	//indexes of the event parts that go into the event description
	private int[] summaryIndexes;
	private int counter;
	
//	/**
//	 * Gets the {@link Integer} array representing the indexes of the description strings
//	 * in the events file
//	 * @return descriptionIndexes 
//	 */
	public int[] getDescriptionIndexes() {
		return descriptionIndexes;
	}

//	/**
//	 * Gets the {@link Integer} value representing the number of the title lines
//	 * in the settings file.
//	 * @return titleLinesNumber The {@link Integer} value representing the number of the title lines
//	 * in the settings file.
//	 */
	public int getCounter() {
		return counter;
	}

//	/**
//	 * Sets the {@link Integer} value representing the number of the title lines
//	 * in the settings file.
//	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

//	/**
//	 * Sets the {@link Integer} value representing the number of the title lines
//	 * in the settings file.
//	 */
	public void setDescriptionIndexes(int[] descriptionIndexes) {
		this.descriptionIndexes = descriptionIndexes;
	}

//	/**
//	 * Gets the {@link Integer} value representing the number of the title lines
//	 * in the settings file.
//	 * @return titleLinesNumber The {@link Integer} value representing the number of the title lines
//	 * in the settings file.
//	 */
	public int[] getSummaryIndexes() {
		return summaryIndexes;
	}

//	/**
//	 * Sets the {@link Integer} value representing the number of the title lines
//	 * in the settings file.
//	 */
	public void setSummaryIndexes(int[] summaryIndexes) {
		this.summaryIndexes = summaryIndexes;
	}

//	/**
//	 * Gets the {@link Integer} value representing the number of the title lines
//	 * in the settings file.
//	 * @return titleLinesNumber The {@link Integer} value representing the number of the title lines
//	 * in the settings file.
//	 */
	public String getStartEndTimeSep() {
		return startEndTimeSep;
	}
//
//	/**
//	 * Sets the {@link Integer} value representing the number of the title lines
//	 * in the settings file.
//	 */
	public void setStartEndTimeSep(String startEndTimeSep) {
		this.startEndTimeSep = startEndTimeSep;
	}
	//index of the event duration in the calendar
	private int eventDurationIndex;
	//indexes of the event parts that the calendar should be filtered by
	//system automatically compares filters with the event parts at these indexes
	private int filterIndex;
	//filter strings (values of the filters)
	private String[] filters;
	private int locationIndex;
	
	/**
	 * Creates a {@link CalendarSettings} object, with the date that is parsed from the
	 * settings.txt file
	 
	 */
	public CalendarSettings(String fileExtension, String lineDelimitter, String wordDelimitter, Boolean titleLine,
			Boolean stringQuotes, Date calendarStartDate, Date calendarEndDate, Date[] nonWorkingDates, Boolean workingSaturdays,
			Boolean workingSundays, int dayIndex, String[] daysShortExpressions, int startDateIndex, int endDateIndex,
			int eventStartTimeIndex, int eventEndTimeIndex, String startEndTimeSep, int eventDurationIndex, int filterIndex,
			String[] filters, int locationIndex, int[] descriptionIndexes, int[] summaryIndexes, String timeZone, String timeFormat,
			String dateFormat, String reminderType, int titleLinesNumber) {
		
		this.fileExtension = fileExtension;
		this.lineDelimitter = lineDelimitter;
		this.wordDelimitter = wordDelimitter;
		this.titleLine = titleLine;
		this.stringQuotes = stringQuotes;
		this.calendarStartDate = calendarStartDate;
		this.calendarEndDate = calendarEndDate;
		this.nonWorkingDates = nonWorkingDates;
		this.workingSaturdays = workingSaturdays;
		this.workingSundays = workingSundays;
		this.dayIndex = dayIndex;
		this.daysShortExpressions = daysShortExpressions;
		this.startDateIndex = startDateIndex;
		this.endDateIndex = endDateIndex;
		this.eventStartTimeIndex = eventStartTimeIndex;
		this.eventEndTimeIndex = eventEndTimeIndex;
		this.startEndTimeSep = startEndTimeSep;
		this.eventDurationIndex = eventDurationIndex;
		this.filterIndex = filterIndex;
		this.filters = filters;
		this.locationIndex = locationIndex;
		this.descriptionIndexes = descriptionIndexes;
		this.summaryIndexes = summaryIndexes;
		this.timeZone = timeZone;
		this.timeFormat = timeFormat;
		this.dateFormat = dateFormat;
		this.reminderType = reminderType;
		this.titleLinesNumber = titleLinesNumber;
	}
	
	public CalendarSettings() {
		
	}
	
	
	public String getFileExtension() {
		return fileExtension;
	}
	
	
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	
	public String getLineDelimitter() {
		return lineDelimitter;
	}
	
	
	public void setLineDelimitter(String lineDelimitter) {
		this.lineDelimitter = lineDelimitter;
	}
	
	public String getWordDelimitter() {
		return wordDelimitter;
	}
	
	
	public void setWordDelimitter(String wordDelimitter) {
		this.wordDelimitter = wordDelimitter;
	}
	
	
	public Boolean getTitleLine() {
		return titleLine;
	}
	
	
	public void setTitleLine(Boolean titleLine) {
		this.titleLine = titleLine;
	}
	
	
	public Boolean getStringQuotes() {
		return stringQuotes;
	}
	
	
	public void setStringQuotes(Boolean stringQuotes) {
		this.stringQuotes = stringQuotes;
	}
	
	
	public Date getCalendarStartDate() {
		return calendarStartDate;
	}
	
	
	public void setCalendarStartDate(Date calendarStartDate) {
		this.calendarStartDate = calendarStartDate;
	}
	
	
	public Date[] getNonWorkingDates() {
		return nonWorkingDates;
	}
	
	
	public void setNonWorkingDates(Date[] nonWorkingDates) {
		this.nonWorkingDates = nonWorkingDates;
	}

	public Boolean getWorkingSaturdays() {
		return workingSaturdays;
	}
	

	public void setWorkingSaturdays(Boolean workingSaturdays) {
		this.workingSaturdays = workingSaturdays;
	}
	

	public Boolean getWorkingSundays() {
		return workingSundays;
	}
	
	public void setWorkingSundays(Boolean workingSundays) {
		this.workingSundays = workingSundays;
	}
	

	public int getDayIndex() {
		return dayIndex;
	}
	
	
	public void setDayIndex(int dayIndex) {
		this.dayIndex = dayIndex;
	}
	
	
	public String[] getDaysShortExpressions() {
		return daysShortExpressions;
	}
	
	
	public void setDaysShortExpressions(String[] daysShortExpressions) {
		this.daysShortExpressions = daysShortExpressions;
	}
	
	
	public int getStartDateIndex() {
		return startDateIndex;
	}
	
	
	public void setStartDateIndex(int startDateIndex) {
		this.startDateIndex = startDateIndex;
	}
	
	
	public int getEndDateIndex() {
		return endDateIndex;
	}
	
	
	public void setEndDateIndex(int endDateIndex) {
		this.endDateIndex = endDateIndex;
	}
	
	
	public int getEventStartTimeIndex() {
		return eventStartTimeIndex;
	}
	
	
	public void setEventStartTimeIndex(int eventStartTimeIndex) {
		this.eventStartTimeIndex = eventStartTimeIndex;
	}
	
	
	
	public int getEventEndTimeIndex() {
		return eventEndTimeIndex;
	}
	
	
	public void setEventEndTimeIndex(int eventEndTimeIndex) {
		this.eventEndTimeIndex = eventEndTimeIndex;
	}
	
	
	public int getEventDurationIndex() {
		return eventDurationIndex;
	}
	
	
	public void setEventDurationIndex(int eventDurationIndex) {
		this.eventDurationIndex = eventDurationIndex;
	}
	

	public int getFilterIndex() {
		return filterIndex;
	}
	
	
	public void setFilterIndex(int filterIndexes) {
		this.filterIndex = filterIndex;
	}
	
	
	public String[] getFilters() {
		return filters;
	}
	
	
	public void setFilters(String[] filters) {
		this.filters = filters;
	}
	
	
	public int getLocationIndex() {
		return locationIndex;
	}
	
	
	public void setLocationIndex(int locationIndex) {
		this.locationIndex = locationIndex;
	}
	
}
