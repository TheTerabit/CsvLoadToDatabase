# CsvLoadToDatabase

## Java / Apache Camel

This application creates a file integration context that processes .csv files and loads records to particular table in given database.

# Precondition:

Configuration.json file contains:
- Server ip and path to a directory where you will put the .csv files to process
- Datebase ip and port

You also have to input username and password to the server and database

# How to use it:
1. Run the program
2. Paste the .csv file into the directory specified in the configuration file
3. The .csv file is processed and moved to another directory
4. All the records should be inserted into the specified database (the table is chosen depending on the name of .csv file)

# How to add your own entities:
1. Add a model class with needed annotations
2. Add an if statment to method in processor.java class 
