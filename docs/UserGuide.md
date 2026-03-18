---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# LockedIn User Guide

LockedIn is a **desktop app for managing job applications, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, LockedIn can get your application tracking tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. Download the latest `.jar` file from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for LockedIn.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:a

   * `list` : Lists all applications.

   * `add n/Google r/Software Engineer Intern d/2025-02-14 u/https://careers.google.com s/Applied` : Adds an application to LockedIn.

   * `delete 3` : Deletes the 3rd application shown in the current list.

   * `clear` : Deletes all applications.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/COMPANY`, `COMPANY` is a parameter which can be used as `add n/Google`.

* Items in square brackets are optional.<br>
  e.g `n/COMPANY [u/URL]` can be used as `n/Google u/https://careers.google.com` or as `n/Google`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/COMPANY r/ROLE`, `r/ROLE n/COMPANY` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding an application: `add`

Adds an application to LockedIn.

Format: `add n/COMPANY r/ROLE d/APPLICATION_DATE [u/URL] [s/STATUS]`

* `COMPANY` and `ROLE` should contain only alphanumeric characters and spaces, and should not be blank.
* `APPLICATION_DATE` should be a valid date in the format `yyyy-MM-dd`.
* `URL`, if provided, must start with `http://` or `https://`.

<box type="tip" seamless>

**Tip:** If `s/STATUS` is omitted, the application status defaults to `Applied`.
</box>

Examples:
* `add n/Google r/Software Engineer Intern d/2025-02-14`
* `add n/Meta r/AI Research Intern d/2025-03-01 u/https://www.example.com s/Interview`

### Listing all applications : `list`

Shows a list of all applications in LockedIn.

Format: `list`

### Editing an application : `edit`

Edits an existing application in LockedIn.

Format: `edit INDEX [n/COMPANY] [r/ROLE] [d/APPLICATION_DATE] [u/URL] [s/STATUS]`

* Edits the application at the specified `INDEX`. The index refers to the index number shown in the displayed application list. The index **must be a positive integer** 1, 2, 3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* `COMPANY` and `ROLE` should contain only alphanumeric characters and spaces, and should not be blank.
* `APPLICATION_DATE` should be a valid date in the format `yyyy-MM-dd`.
* `URL`, if provided, must start with `http://` or `https://`.

Examples:
* `edit 1 r/Software Engineer d/2025-03-10` Edits the role and application date of the 1st application.
* `edit 2 n/OpenAI s/Offered` Edits the company and status of the 2nd application.

### Incrementing application status : `next`

Increments the status of an application to the next stage in the workflow.

Format: `next INDEX`

* Increments the status of the application at the specified `INDEX`.
* The index refers to the index number shown in the displayed application list.
* The index **must be a positive integer** 1, 2, 3, ...
* Applications progress through the following status sequence: Applied -> OA -> Interview -> Offered -> Rejected -> Withdrawn -> Applied (cycles back).

Examples:
* `list` followed by `next 1` increments the 1st application's status to the next stage.
* `next 3` increments the 3rd application's status.

### Copying application URL : `copy`

Copies the URL of an application to the system clipboard.

Format: `copy INDEX`

* Copies the URL of the application at the specified `INDEX`.
* The index refers to the index number shown in the displayed application list.
* The index **must be a positive integer** 1, 2, 3, ...
* If the application does not have a URL, an error message will be shown.

Examples:
* `list` followed by `copy 1` copies the 1st application's URL to the clipboard.
* `find Google` followed by `copy 1` copies the 1st application's URL in the results of the `find` command.

### Locating applications by company: `find`

Finds applications whose company names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* At least one keyword must be provided.
* The search is case-insensitive. e.g `google` will match `Google`
* The order of the keywords does not matter. e.g. `Google Meta` will match `Meta Google`
* Only the company name is searched.
* Only full words will be matched e.g. `Goog` will not match `Google`
* Applications matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Google Meta` will return applications whose company names contain `Google` or `Meta`

Examples:
* `find Google` returns applications whose company name contains `Google`
* `find Meta Apple` returns applications whose company name contains `Meta` or `Apple`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting an application : `delete`

Deletes the specified application from LockedIn.

Format: `delete INDEX`

* Deletes the application at the specified `INDEX`.
* The index refers to the index number shown in the displayed application list.
* The index **must be a positive integer** 1, 2, 3, ...

Examples:
* `list` followed by `delete 2` deletes the 2nd application in LockedIn.
* `find Google` followed by `delete 1` deletes the 1st application in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from LockedIn.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

LockedIn data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

LockedIn data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, LockedIn will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause LockedIn to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous LockedIn home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add n/COMPANY r/ROLE d/APPLICATION_DATE [u/URL] [s/STATUS]` <br> e.g., `add n/Google r/Software Engineer Intern d/2025-02-14 u/https://careers.google.com s/Applied`
**Clear**  | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Next**   | `next INDEX`<br> e.g., `next 3`
**Copy**   | `copy INDEX`<br> e.g., `copy 3`
**Edit**   | `edit INDEX [n/COMPANY] [r/ROLE] [d/APPLICATION_DATE] [u/URL] [s/STATUS]`<br> e.g.,`edit 2 n/OpenAI s/Offered`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find Google Meta`
**List**   | `list`
**Help**   | `help`
