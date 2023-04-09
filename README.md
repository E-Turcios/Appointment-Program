# Appointment-Program

# Purpose of Application:

The aim of this software is to create a comprehensive graphical user interface (GUI) appointment scheduling application that includes a variety of features.

# Author Information
Author: Abdoulaye Boundy Djikine

Contact: adjikin@wgu.edu

Application Version: 1.0

Date: 04/05/23

# IDE Information:

IntelliJ IDEA 2022.3.2 (Community Edition)
Build #IC-223.8617.56, built on January 26, 2023
Runtime version: 17.0.5+1-b653.25 x86_64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
macOS 13.2.1
GC: G1 Young Generation, G1 Old Generation
Memory: 1024M
Cores: 8
Metal Rendering is ON
Non-Bundled Plugins:
com.intellij.properties.bundle.editor (223.7571.203)

Kotlin: 223-1.8.0-release-345-IJ8617.56

JavaFX SDK: JavaFX-SDK-19

# How to Run:
You must first install the necessary IDE and JavaFX applications to get going. 
Following their installation, open the project in IntelliJ and select the "run" option to start the program. 
Enter the login credentials that correspond to the data in the database's users table when the login screen displays.
The login screen can be shown in French if that language is selected in the local machine's language settings. 
The main console loads after you log in, giving you access to both the appointments and the customers table. 
The corresponding page will open when you click each button, allowing you to add, remove, or change clients and appointments. 
The program has radio options to filter appointments and error-checking throughout.
The "logout" button will return you to the login screen, while the "reports" button will send you to the reports page.

# MySQL Connector Driver: 
mysql-connector-java-8.0.32

# Additional report:
For the customized report, I decided to filter customers by their first level divisions.
The resultant table displays the first level division for each client on the left and the number of customers who fall into that division on the right.
The SQL query I used to create this report conducted an inner join between the First Level Divisions and Customers columns, matching the division ID to determine the number of customers each division had.
This report offers helpful insight into the areas with a high customer concentration.