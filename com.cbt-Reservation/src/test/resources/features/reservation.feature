Feature: Reserving room functionality


Scenario: Teacher reserves a room (Positive)
	Given a teacher is signed in with valid username and password
	When a teacher clicks on hunt button
	Then the teacher should be able to reserve a room

	
Scenario: Team leader is able to reserve room (Positive)
	Given a team leader is signed in  with valid username and password
	When a team leader clicks on hunt button
	Then the team leader should be able to reserve a room
	
Scenario: Student should not be able to reserve room (Negative)
	Given a student is signed in with valid username and password
	When a student clicks on hunt button
	Then the student should NOT be able to reserve a room
@temp
Scenario: Team leader should be able to see the reservation but students should not
	Given a team leader reserved a room
	Then team leader should be able to see the reservation
	And student from the same team should be able to see the reservation
	And student from different team should be able to see the reservation
	

Scenario: Team leader deletes a reservation and no user should be able to see it
	Given a team leader deletes the reservation
	Then the team leader should not be able to see the reservation
	And student from the same team should not be able to see the reservation
	And student from different team should not be able to see the reservation