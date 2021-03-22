# TDT4145-Piazza-database-project
## Gruppe 17
### Thomas Borge Skøien, Lotte Eide og Hanne Opseth Rygg

This Java program uses SQL to solve the Piazza project given in TDT4145 Database og Datamodellering. The program solves the following 5 use cases:

1. A user logs into the system.
2. A user makes a post belonging to a spesific folder.
3. An instructor replies to a post belonging to a specific folder.
4. A user searches for posts with a specific keyword.
5. An instructor views statistics for users and how many posts they have read and how many they have created.


#### Steps after cloning the project
1. Create new scheme named *piazza*.
2. Run *project_sql_script.sql* to create tables in this scheme.
3. Run *testInput.sql* to fill the tables.
4. Either add a user "Thomas" with the password "vbdatdat123" to your database or go to file *src\main\java\piazza\DBConn.java* and change *DB_USERNAME* and *DB_PASSWORD* to your local user account in MySQL. If you're doing this you have to recompile and make your own jar file using the method below.
5. Run *piazza-"version".jar* from the Executables folder. Alternatively the one with dependencies from the target folder within piazza if you compile it yourself.
6. Log in as instructor:
username: 't@s.com', 
password: *thomas123*,
7. Or log in as student:
username: *geir@outlook.no*,
password: *pizzabolle*
7. Play with the use cases, if you get an error, try to make sure your inputs are correct.

#### Recompile with maven
All the dependencies are added into the single jar file. The user should just need to run piazza-"version"-jar-with-dependencies.jar.
To recompile you have to install maven and then run `mvn clean package` from the piazza folder. 