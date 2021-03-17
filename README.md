# TDT4145-Piazza-database-project
## Gruppe 17
### Thomas Borge Skøien, Lotte Eide og Hanne Opseth Rygg

This Java program uses SQL to solve the Piazza project given in TDT4145 Database og Datamodellering. The program solves the following 5 use cases:

1. A user logs into the system.
2. A user makes a post belonging to a spesific folder.
3. An instructor replies to a post belonging to a specific folder.
4. A user searches for posts with a specific keyword.
5. An instructor views statistics for users and how many post they have read and how many they have created.


**Steps after downloading both SQL and Java files:**
1. Create new scheme named *piazza*.
2. Run *project_sql_script.sql* to create tables in this scheme.
3. Run *testInput.sql* to fill the tables.
4. Run *Piazza.jar*.
5. Log in as instructor:
username: *t@s.com*, 
password: *thomas123*,
6. Or log in as student:
username: *geir@outlook.no*,
password: *pizzabolle*
7. Play with the use cases.

**Recompile with maven**
All the dependencies are added into the single jar file. The user should just need to run piazza-"version"-jar-with-dependencies.jar.
To recompile you have to install maven and then run mvn clean package from the piazza folder.