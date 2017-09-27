## Android app address book displaying and saving contact information for organisations and people.


### Run the App

[Download the APK file](https://github.com/skomer/ContactsBook/blob/master/app-debug.apk), and install it on your Android device. Then look for an app named 'ContactsBook'.


### Development

I began this task by considering what the models in the app would look like, and and what state might change as a user interacted with the app. I then began to build up a simple organisation contact entry form, and creating and connecting to a database to store the entered contact data. Next I implemented a listview to display existing saved organisations to the user.

I tried to practise test-driven development throughout, which certainly helped to keep my classes functioning as expected.


### The Challenge

I encountered two major problems during my work on this project. Firstly with my laptop, which periodically would freeze mouse and touchpad input for half an hour or so, which slowed me down a lot.

Secondly, using the SQLite database in Android was straightforward at first, before coming to a halt when trying to query one of my tables. The database reports 'no such table', and despite my best efforts I can't find an error in my code or another reason why the particular table shouldn't have been created as expected.

I believe the problem is related to the one described here:
[Stack Overflow | android-sqlite-no-such-table-exception](https://stackoverflow.com/questions/9916590/android-sqlite-no-such-table-exception)

Unable to solve this issue stalled progress on the app, meaning that I was unable to implement features relating to 'Person' contacts, as the 'people' database table is the one that is not found.

However, I had intended much of the implementation for the 'Person' contacts to be very similar to that for the 'Org' contacts. The main additional features that are lacking are the ability to delete a person or organisation contact, and the ability to see which people are members of which organisation.

I had intended to write the delete entries functionality into the 'DatabaseConnector' class, using the SQL command `DELETE FROM <table name> WHERE id = <provided id>;`

Retrieving a list of people who were members of an organisation would need the command

	SELECT * FROM people
	INNER JOIN org_people
	ON people.id = org_people.people_id
	WHERE org_people.org_id = <org_id>;


### Features
The contacts book can:
- add a new organisation contact
- display a list of existing organisation contacts
- persist an entered organisation contact, so that if the app is closed and reopened the entered contacts will display
