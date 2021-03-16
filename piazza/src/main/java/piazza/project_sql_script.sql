CREATE TABLE user (
	userName varchar(50),
	password varchar(50),
	type varchar(50),
	PRIMARY KEY (userName));

CREATE TABLE course (
	courseID varchar(50),
	term int,
	PRIMARY KEY (courseID) );

CREATE TABLE userInCourse (
	userName varchar(50),
	courseID varchar(50),
	FOREIGN KEY (userName) REFERENCES user(userName) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (courseID)  REFERENCES course(courseID) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE folder (
	folderID int NOT NULL AUTO_INCREMENT, 
	name varchar(50), 
	courseID varchar(50), 
	PRIMARY KEY (folderID), 
	FOREIGN KEY (courseID) REFERENCES course(courseID) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE subfolder (
	parentFolderID int, 
	subFolderID int, 
	FOREIGN KEY (parentFolderID) REFERENCES folder (folderID) ON UPDATE CASCADE ON DELETE CASCADE, 
	FOREIGN KEY (subFolderID) REFERENCES folder(folderID));

CREATE TABLE post (
	postID int NOT NULL AUTO_INCREMENT, 
	text varchar(500), 
	nrGoodComment int, 
	threadID int, 
	PRIMARY KEY (postID));

CREATE TABLE thread (
	threadID int NOT NULL AUTO_INCREMENT, 
	tag varchar(50), 
	color varchar(50), 
	folderID int, 
	postID int, 
	PRIMARY KEY (threadID), 
	FOREIGN KEY (folderID) REFERENCES folder(folderID) ON UPDATE CASCADE ON DELETE CASCADE, 
	FOREIGN KEY (postID) REFERENCES post(postID) ON UPDATE CASCADE ON DELETE CASCADE);

ALTER TABLE post ADD FOREIGN KEY (threadID) REFERENCES thread(threadID);

CREATE TABLE UserViewedPost (
	userName varchar(50), 
	postID int,
	date DATE,
	FOREIGN KEY (userName) REFERENCES user(userName) ON UPDATE CASCADE ON DELETE CASCADE, 
	FOREIGN KEY (postID) REFERENCES post(postID) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE UserLikedPost (
	userName varchar(50), 
	postID int, 
	FOREIGN KEY (userName) REFERENCES user(userName) ON UPDATE CASCADE ON DELETE CASCADE, 
	FOREIGN KEY (postID) REFERENCES post(postID) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE UserPost (
	userName varchar(50), 
	postID int,
	date DATE,
	FOREIGN KEY (userName) REFERENCES user(userName) ON UPDATE CASCADE ON DELETE CASCADE, 
	FOREIGN KEY (postID) REFERENCES post(postID) ON UPDATE CASCADE ON DELETE CASCADE);

