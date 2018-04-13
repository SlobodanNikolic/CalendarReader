package reader;

import java.util.Date;

public class CalendarEvent {

	/**Event start date */
	private Date startDate;
	/**Event end date*/
	private Date endDate;
	/**Event summary*/
	private String eventSummary;
	/**Event description*/
	private String eventDescription;
	/**Event location*/
	private String eventLocation;
	/**If true - remind user before the event takes place*/
	private Boolean remindBefore;
	/**Name of the day event is happening*/
	private String dayName;
	/**index of the day the event is happening. In standard settings,
	 * 0 for Sunday, 6 for Saturday*/
	private int dayWeekIndex;
	/**Event start time in Date format*/
	private Date startTime;
	/**Event end time in Date format*/
	private Date endTime;
	/**String representing the timezone, for example: America/Los_Angeles*/
	private String timeZone;
	
	/**
	 * Gets the {@link String} instance representing the event timezone.
	 * @return The {@link String} instance representing the event timezone
	 * For example: America/Los_Angeles.
	 */
	public String getTimeZone() {
		return timeZone;
	}

	/**
	 * Sets the {@link String} instance representing the event timezone.
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * Gets the {@link Date} instance representing the event date.
	 * @return The {@link Date} instance representing the event date
	 
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * Sets the {@link Date} instance representing the event date.	 
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the {@link Date} instance representing the event end time.
	 * @return The {@link Date} instance representing the event end time.
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * Sets the {@link Date} instance representing the event end time.
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * Gets the {@link Integer} value representing the index of the day in the week the event
	 * is happening.
	 * @return The {@link Integer} value representing the index of the day in the week the event
	 * is happening.
	 */
	public int getDayWeekIndex() {
		return dayWeekIndex;
	}

	/**
	 * Sets the {@link Integer} value representing the index of the day in the week the event
	 * is happening.
	 */
	public void setDayWeekIndex(int dayWeekIndex) {
		this.dayWeekIndex = dayWeekIndex;
	}

	/**
	 * Gets the {@link String} instance representing the name of the day event is happening.
	 * @return The {@link String} instance representing the name of the day event is happening.
	 */
	public String getDayName() {
		return dayName;
	}

	/**
	 * Sets the {@link String} instance representing the name of the day event is happening.
	 */
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}

	/**
	 * Creates the CalendarEvent object.
	 * @param startDate a {@link Date} referring to the start date of the event
	 * @param endDate a {@link Date} referring to the end date of the event
	 * @param eventSummary a {@link String} describing the event summary
	 * @param eventDescription a {@link String} describing the event description
	 * @param eventLocation a {@link String} describing the location of the event

	 */
	public CalendarEvent(Date startDate, Date endDate, String eventSummary, String eventDescription,
			String eventLocation) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.eventSummary = eventSummary;
		this.eventDescription = eventDescription;
		this.eventLocation = eventLocation;
		this.remindBefore = false;
	}
	
	/**
	 * An empty constructor, initializing all the fields to their respective
	 * initial values
	 */
	public CalendarEvent() {
		
	}

	/**
	 * Gets the {@link Date} instance representing the event start date.
	 * @return The {@link Date} instance representing the event start date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the {@link Date} instance representing the event start date.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the {@link Date} instance representing the event end date.
	 * @return The {@link Date} instance representing the event end date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the {@link Date} instance representing the event end date.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the {@link String} instance representing the event summary.
	 * @return The {@link String} instance representing the event summary
	 */
	public String getEventSummary() {
		return eventSummary;
	}

	/**
	 * Sets the {@link String} value representing the event summary.
	 */
	public void setEventSummary(String eventSummary) {
		this.eventSummary = eventSummary;
	}

	/**
	 * Gets the {@link String} instance representing the event description.
	 * @return The {@link String} instance representing the event description
	 */
	public String getEventDescription() {
		return eventDescription;
	}

	/**
	 * Sets the {@link String} value representing the event description.
	 */
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	/**
	 * Gets the {@link String} instance representing the event location.
	 * @return The {@link String} instance representing the event location
	 */
	public String getEventLocation() {
		return eventLocation;
	}

	/**
	 * Sets the {@link String} value representing the event location.
	 */
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	/**
	 * Gets the {@link Boolean} value that informs weather or not the user should be reminded
	 * before the event.
	 * @return The {@link Boolean} value that informs weather or not the user should be reminded
	 * before the event.
	 */
	public Boolean getRemindBefore() {
		return remindBefore;
	}

	/**
	 * Sets the {@link Boolean} value that informs weather or not the user should be reminded
	 * before the event.
	 */
	public void setRemindBefore(Boolean remindBefore) {
		this.remindBefore = remindBefore;
	}
	
	
}
