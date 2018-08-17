Feature: Reserving room functionality

@temp
Scenario: Teacher reserves a room (Positive)
	Given a teacher is signed in with valid username and password
	When a teacher clicks on hunt button
	Then the teacher should be able to reserve a room
	