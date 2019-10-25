# CsvLoadToDatabase

## Java / Apache Camel

This application processes .csv files pasted in a particular directory (on your PC or a server with given IP address) and loads records to particular table in given database.

# Precondition:

Fill in the resources/configuration.json file with:
- source IP - IP of the server where the .csv files will appear or "127.0.0.1" if its the same machine where you run the program,
- source path - path to the directory where the .csv files will apprear
- source username - username to log onto the server/pc in order to use ftp to read the files
- source password - password matching the given username 
- destination IP - the IP of a machine where you want to copy the processed files
- destination path - path to the directory on that machine
- destination username
- destination password
- transfer frequency in seconds - how frequent you want the files to process and load to the database

In the future usernames and passwords will be given to the program with a standard input in order not to store them in the configuration file. You also will be able to input IP address, port, username and password to the database.

# How to use it:
1. Run the program
2. Paste the .csv files into the directory specified in the configuration file
3. The .csv files are processed and moved to another directory
4. All the records should be inserted into the specified database (the tables are chosen depending on the name of .csv file)
5. Go to 2. and repeat :)

# How to add your own entities:
1. Add a model class with needed annotations (@CsvBindByPosition(position=0) or  @CsvBindByName(column = "ID")).
2. This class must extend class Records and implement method getAllAtributes().
3. Method getAllAtributes() must return part of the SQL query with the values only, needed to insert records e.g. "('1','this is description', 'Active State', '123456789')". You can see other entities to check what they return.
4. Add this else if statment to method pickAccurateModel() in fileTransfer/ModelCreator.java class:

   else if (fileName.contains("regexp to recognize the file")) {
   
            modelClass = YourEntity.class;
            
            tableName = "Name of the database table";
            
   }
   
   
