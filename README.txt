******************************

		CALENDAR READER

******************************

						by Shlijani

STEP 1.

To be able to use the Calendar Reader software component properly,
you need to go to https://developers.google.com/calendar/quickstart/java,
which is a QuickStart guide for Java.
and follow the steps to get the needed .json file.

IF YOU DONT WANT TO DO THIS(But it is highly recomended)
A sample, usable .json file is included in the project,
and located under the resources folder.


STEP 2.

See the example events file.
These are a few lines from it.

The first line of the file is the title line, and it does not contain any events.
It will be skipped by the Reader.
All of the subfields are separated by a comma, and are in quotations.
Two event records are sepparated by a newline.
Index of the 'event start/end time' subfield is 5.

********************************************************************************************

"Predmet","Tip","Nastavnik","Grupe","Dan","Termin","Učionica"
"Objektno orijentisano programiranje","Laboratorijske vezbe","Stojanovic Vukoman","101","  PET  ","12:15-14","U7"
"Objektno orijentisano programiranje","Vezbe","Tomic Milan","101","  PET  ","16:15-18","U7"

********************************************************************************************


STEP 2.

Configure your settings.txt file

The settings.txt file is a textual document which specifies the structure
of the file containing the events.
With proper usage and modification of the settings.txt file, you can load any
type of file and use it to get your event data.

Example (This is how a settings.txt file looks like):



file_extension:	csv //This is the file extension. Needed so the loader could read the file.
					//Could be anything else: txt, docx etc.

line_delimitter: new_line 	//This is the macro for the new line. It specifies what is the
							//delimiter that separates two events in the file

word_delimitter: ","		//Specifies what is the delimiter that separates two parts of
							//the event, like 'title', 'date' etc.

title_line: true			//Is the first line of the file the line that contains titles of 							//the event subsection, and should be skipped ('title', 'date'...)

string_quotes: true			//Are strings and event subsections in quotations

calendar_start_date: 06.04.2018	//Date from which the events should be imported to Google 									//Calendar

calendar_end_date: 20.04.2018	//Date when importing should stop

non_working_dates: false	//If any dates are non-working, they should be set here
							//Example: 20.03.2018, 26.05.2018 etc.

working_saturdays: false	//Are saturdays working

working_sundays: false		//Are sundays working

day_index: 4				//If events are recurring, and repeating weekly, this is the 								//index of the event subfield, where the name of the day can be 							//found (The place where it says MON or TUE or WED, etc.)

days_short_expressions: NED, PON, UTO, SRE, ČET, PET, SUB //The shortened expressions for 																//the days of the events

start_date_index: false		//If events are non recurring, the index of the event subfield 								//where start date can be found

end_date_index: false		//If events are non recurring, the index of the event subfield 								//where end date can be found

event_start_time_index: 5	//The index of the event subfield where start time can be found

event_end_time_index: 5		//The index of the event subfield where end time can be found

event_start_end_time_separator: - //If start and end time are on the same field, the 											//dellimiter pattern that sepparates them

event_duration_index: false	//Index of the duration subfield, if any

filter_index: 3				//Index of the event subfield that should be a filter

filters: 202, 308			//Values of the before-mentioned filter. Only events with values 							//'filters', that are at the 'filter_index' of the event subfield
							//will be uploaded

location_index: 6			//Index of the location string in the event subfields

description_indexes: 0		//Indexes of the subfields that go into the event description,
							//separated by commas. 0, 1, 4 for example

summary_indexes: 0, 1, 2	//Indexes of the subfields that go into the event summary,
							//separated by commas. 0, 1, 4 for example

time_zone: America/Los_Angeles //Standard java.util.Date time zone format string

time_format: HH:MM			//Formats of the time used in the events file (HH:MM:ss)

date_format: dd.MM.yyyy		//Date format used in the events file

reminder_type: false		//Google Calendar API reminder type

title_lines_number: 1		//Number of the title lines in the events file. These are the 								//first few lines, representing some metadata usualy, and 									//contain no events.



STEP 4.

See the example files contained in the CalendarReader project.

CalendarEvent.java is the class that encapsulates one event.

Reader.java is the interface that should be implemented in order to read the settings.txt file and the file containing the events.

Uploader.java is the interface that should be implemented in order to upload events to the Google Calendars service.

ViewController.java is the class that opens the FileChooser in which you select the file containing the events data.

CalendarSync.java is the class which implements Uploader interface.

CalendarSettings.java is the class that encapsulates settings into a settings object.

CalendarImportCSV reads the settings.txt file and creates a CalendarSettings objects. It also reads the csv or any other file and loads the events.

Main.java implements all of the above. It should be implemented in that order.




