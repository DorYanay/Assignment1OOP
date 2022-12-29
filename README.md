# Java Object Oriented Programming Task No.1
### Author and co-author:
[Dor Yanay](https://github.com/DorYanay)
[Yevgeny Ivanov](https://github.com/yevgenyivanov)

## Description
This project implements the Observer Pattern by using a modified version of StringBuilder (called 'UndoableStringBuilder').
##### Note: the projects was written on openjdk-19.0.1

## Files
#### UndoableStringBuilder.java
This class consists of functions that allow to 'undo' the actions performed by a regular StringBuilder. This class is for keeping track of the user actions and undoing them, if needed.

#### GroupAdmin.java
This class is for controlling, registering, unregistering members as well as inserting and deleting data from the string that all Members are registered, the class contains the following helper functions:
getCurrentINFO() - to get the current information in a string format of all user input that wasn't undone
notifyMembers() - update all Members to the latest information
getSize() - get total number of registered members
contains() - return boolean value of whether member is registered.

#### ConcreteMember.java
ConcreteMember is a type of Member that follows after the Updates of the UndoableStringBuilder.

#### Test Files
the folder "test" contains the tests for the .java files 
-Tests.java consists of testing the code's capabilities for registering, unregistering, group creating and member notification.  

#### Interfaces
Member.java Sender.java

## How to run the Project
We used Intellij 2202.3 , run the pom.xml as a project.


