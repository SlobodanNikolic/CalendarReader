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
	
	public int getTitleLinesNumber() {
		return titleLinesNumber;
	}

	public void setTitleLinesNumber(int titleLinesNumber) {
		this.titleLinesNumber = titleLinesNumber;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getReminderType() {
		return reminderType;
	}

	public void setReminderType(String reminderType) {
		this.reminderType = reminderType;
	}

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
	
	public Date getCalendarEndDate() {
		return calendarEndDate;
	}

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
	
	public int[] getDescriptionIndexes() {
		return descriptionIndexes;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void setDescriptionIndexes(int[] descriptionIndexes) {
		this.descriptionIndexes = descriptionIndexes;
	}

	public int[] getSummaryIndexes() {
		return summaryIndexes;
	}

	public void setSummaryIndexes(int[] summaryIndexes) {
		this.summaryIndexes = summaryIndexes;
	}

	public String getStartEndTimeSep() {
		return startEndTimeSep;
	}

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
