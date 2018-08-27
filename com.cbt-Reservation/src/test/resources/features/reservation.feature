Feature: Reserving room functionality

Scenario: Teacher reserves a room (Positive)
	Given a teacher is signed in with valid username and password
	When a teacher clicks on hunt button
	Then the teacher should be able to reserve a room

	
Scenario: Team leader is able to reserve room (Positive)
	Given a team leader is signed in  with valid username and password
	When a team leader clicks on hunt button
	Then the team leader should be able to reserve a room
	
	@asel
Scenario Outline: visibility of study room reservations
    Given user on the main page
    When user should see the 6 study room names on the map
    And user clicks on any room from "<roomnames>"
    Then user should see the reservations for the current date
    
    Examples:
    |roomnames|
    |google| 
    |apple|
    |microsoft|
    |amazon|
    |tesla| 
    |facebook |
	