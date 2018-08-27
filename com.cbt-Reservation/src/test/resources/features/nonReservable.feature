Feature: Room reservation
 @nr
Scenario Outline: Non-resrvable rooms

Given user is on the main page
When user clicks on "<rooms>"
Then he should not be able to reserve that rooms and see the schedule

Examples:
|rooms|
|kitchen|
|wellness room|
|classroom|
|lobby|
|loading...|
