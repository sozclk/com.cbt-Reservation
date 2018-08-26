Feature: Reserving room functionality

Scenario: Teacher reserves a room (Positive)
	Given a teacher is signed in with valid username and password
	When a teacher clicks on hunt button
	Then the teacher should be able to reserve a room

	
Scenario: Team leader is able to reserve room (Positive)
	Given a team leader is signed in  with valid username and password
	When a team leader clicks on hunt button
	Then the team leader should be able to reserve a room
	
	