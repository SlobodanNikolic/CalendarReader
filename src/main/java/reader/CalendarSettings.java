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
	
	/**
	 * Gets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 * @return titleLinesNumber The {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public int[] getDescriptionIndexes() {
		return descriptionIndexes;
	}

	/**
	 * Gets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 * @return titleLinesNumber The {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * Sets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	/**
	 * Sets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setDescriptionIndexes(int[] descriptionIndexes) {
		this.descriptionIndexes = descriptionIndexes;
	}

	/**
	 * Gets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 * @return titleLinesNumber The {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public int[] getSummaryIndexes() {
		return summaryIndexes;
	}

	/**
	 * Sets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setSummaryIndexes(int[] summaryIndexes) {
		this.summaryIndexes = summaryIndexes;
	}

	/**
	 * Gets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 * @return titleLinesNumber The {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public String getStartEndTimeSep() {
		return startEndTimeSep;
	}

	/**
	 * Sets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
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
	
	/**
	 * Gets the {@link String} value representing the number of the title lines
	 * in the settings file.
	 * @return fileExtension The {@link String} value representing the number of the title lines
	 * in the settings file.
	 */
	public String getFileExtension() {
		return fileExtension;
	}
	
	/**
	 * Sets the {@link String} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	/**
	 * Gets the {@link String} value representing the number of the title lines
	 * in the settings file.
	 * @return lineDelimitter The {@link String} value representing the number of the title lines
	 * in the settings file.
	 */
	public String getLineDelimitter() {
		return lineDelimitter;
	}
	
	/**
	 * Sets the {@link String} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setLineDelimitter(String lineDelimitter) {
		this.lineDelimitter = lineDelimitter;
	}
	
	/**
	 * Gets the {@link String} value representing the number of the title lines
	 * in the settings file.
	 * @return wordDelimitter The {@link String} value representing the number of the title lines
	 * in the settings file.
	 */
	public String getWordDelimitter() {
		return wordDelimitter;
	}
	
	/**
	 * Sets the {@link String} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setWordDelimitter(String wordDelimitter) {
		this.wordDelimitter = wordDelimitter;
	}
	
	/**
	 * Gets the {@link Boolean} value representing the number of the title lines
	 * in the settings file.
	 * @return titleLine The {@link Boolean} value representing the number of the title lines
	 * in the settings file.
	 */
	public Boolean getTitleLine() {
		return titleLine;
	}
	
	/**
	 * Sets the {@link Boolean} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setTitleLine(Boolean titleLine) {
		this.titleLine = titleLine;
	}
	
	/**
	 * Gets the {@link Boolean} value representing the number of the title lines
	 * in the settings file.
	 * @return stringQuotes The {@link Boolean} value representing the number of the title lines
	 * in the settings file.
	 */
	public Boolean getStringQuotes() {
		return stringQuotes;
	}
	
	/**
	 * Sets the {@link Boolean} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setStringQuotes(Boolean stringQuotes) {
		this.stringQuotes = stringQuotes;
	}
	
	/**
	 * Gets the {@link Date} value representing the number of the title lines
	 * in the settings file.
	 * @return calendarStartDate The {@link Date} value representing the number of the title lines
	 * in the settings file.
	 */
	public Date getCalendarStartDate() {
		return calendarStartDate;
	}
	
	/**
	 * Sets the {@link Date} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setCalendarStartDate(Date calendarStartDate) {
		this.calendarStartDate = calendarStartDate;
	}
	
	/**
	 * Gets the  value representing the number of the title lines
	 * in the settings file.
	 * @return nonWorkingDates The  value representing the number of the title lines
	 * in the settings file.
	 */
	public Date[] getNonWorkingDates() {
		return nonWorkingDates;
	}
	
	/**
	 * Sets the value representing the number of the title lines
	 * in the settings file.
	 */
	public void setNonWorkingDates(Date[] nonWorkingDates) {
		this.nonWorkingDates = nonWorkingDates;
	}
	
	/**
	 * Gets the {@link Boolean} value representing the number of the title lines
	 * in the settings file.
	 * @return workingSaturdays The {@link Boolean} value representing the number of the title lines
	 * in the settings file.
	 */
	public Boolean getWorkingSaturdays() {
		return workingSaturdays;
	}
	
	/**
	 * Sets the {@link Boolean} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setWorkingSaturdays(Boolean workingSaturdays) {
		this.workingSaturdays = workingSaturdays;
	}
	
	/**
	 * Gets the {@link Boolean} value representing the number of the title lines
	 * in the settings file.
	 * @return workingSundays The {@link Boolean} value representing the number of the title lines
	 * in the settings file.
	 */
	public Boolean getWorkingSundays() {
		return workingSundays;
	}
	
	/**
	 * Sets the {@link Boolean} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setWorkingSundays(Boolean workingSundays) {
		this.workingSundays = workingSundays;
	}
	
	/**
	 * Gets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 * @return dayIndex The {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public int getDayIndex() {
		return dayIndex;
	}
	
	/**
	 * Sets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setDayIndex(int dayIndex) {
		this.dayIndex = dayIndex;
	}
	
	/**
	 * Gets the value representing the number of the title lines
	 * in the settings file.
	 * @return daysShorExpressions The value representing the number of the title lines
	 * in the settings file.
	 */
	public String[] getDaysShortExpressions() {
		return daysShortExpressions;
	}
	
	/**
	 * Sets the value representing the number of the title lines
	 * in the settings file.
	 */
	public void setDaysShortExpressions(String[] daysShortExpressions) {
		this.daysShortExpressions = daysShortExpressions;
	}
	
	/**
	 * Gets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 * @return startDateIndex The {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public int getStartDateIndex() {
		return startDateIndex;
	}
	
	/**
	 * Sets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setStartDateIndex(int startDateIndex) {
		this.startDateIndex = startDateIndex;
	}
	
	/**
	 * Gets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 * @return endDateIndex The {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public int getEndDateIndex() {
		return endDateIndex;
	}
	
	/**
	 * Sets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setEndDateIndex(int endDateIndex) {
		this.endDateIndex = endDateIndex;
	}
	
	/**
	 * Gets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 * @return eventStartTimeIndex The {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public int getEventStartTimeIndex() {
		return eventStartTimeIndex;
	}
	
	/**
	 * Sets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setEventStartTimeIndex(int eventStartTimeIndex) {
		this.eventStartTimeIndex = eventStartTimeIndex;
	}
	
	/**
	 * Gets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 * @return eventEndTimeIndex The {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	
	public int getEventEndTimeIndex() {
		return eventEndTimeIndex;
	}
	
	/**
	 * Sets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setEventEndTimeIndex(int eventEndTimeIndex) {
		this.eventEndTimeIndex = eventEndTimeIndex;
	}
	
	/**
	 * Gets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 * @return eventDurationIndex The {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public int getEventDurationIndex() {
		return eventDurationIndex;
	}
	
	/**
	 * Sets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setEventDurationIndex(int eventDurationIndex) {
		this.eventDurationIndex = eventDurationIndex;
	}
	
	/**
	 * Gets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 * @return filterIndex The {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public int getFilterIndex() {
		return filterIndex;
	}
	
	/**
	 * Sets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setFilterIndex(int filterIndexes) {
		this.filterIndex = filterIndex;
	}
	
	/**
	 * Gets the {@link String} value representing the number of the title lines
	 * in the settings file.
	 * @return filters The {@link String} value representing the number of the title lines
	 * in the settings file.
	 */
	public String[] getFilters() {
		return filters;
	}
	
	/**
	 * Sets the {@link String} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setFilters(String[] filters) {
		this.filters = filters;
	}
	
	/**
	 * Gets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 * @return locationIndex The {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public int getLocationIndex() {
		return locationIndex;
	}
	
	/**
	 * Sets the {@link Integer} value representing the number of the title lines
	 * in the settings file.
	 */
	public void setLocationIndex(int locationIndex) {
		this.locationIndex = locationIndex;
	}
	
}
