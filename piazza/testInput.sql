insert into course values ("TDAT3023", 2), ("FI1104", 2), ("KULT3301",1), ("TDT4156", 2), ("TDT6725", 1), ("TDT4140");

insert into folder (name, courseID) values ("Assignments", "TDAT3023"), ("Lectures", "TDAT3023"), ("Exam", "TDAT3023"), ("Exam2016", "TDAT3023"), ("Assignments", "FI1104"), ("Lectures", "FI1104"), 
("Exam", "FI1104"), ("Assignments", "KLUT3301"), ("Lectures", "KLUT3301"), ("Exam", "KLUT3301"), ("Assignments", "TDT4156"), ("Lectures", "TDT4156"), ("Exam", "TDT4156"),
("Assignments", "TDT6725"), ("Lectures", "TDT6725"), ("Exam", "TDT6725"), ("Assignments", "TDT4140"), ("Lectures", "TDT4140"), ("Exam", "TDT4140");

insert into subfolder values (3, 4); 

insert into user values ("t@s.com", "thomas123", "instructor"), ("hanne@gmail.com", "passord123", "student"), ("lotte@hotmail.no", "lotte123", "student"),
("geir@outlook.no", "pizzabolle", "student"), ("sofie@ntnu.no", "badering", "instructor"), ("lise@stud.no", "hei", "student");

insert into post (text, nrGoodComment) values ("Korleis løyse oppgåve 1?", 5), ("Forelesing 12 på fredag", 12), ("Den er grei", 2),
("Når starter den?", 0), ("Kor mange poeng kan ein få på eksamen?", 2), ("Sjå side 12 i boka", 1), ("Korleis løyse oppgåve 2?", 7);

insert into thread(tag, color, folderID, postID) values ("question", "instructor-reply", 1, 1), ("announcement", "student-reply", 2, 2), ("question", "no-reply", 6, 5),
("question", "no-reply", 4, 7);

update post set threadID = 1 where postID = 1;
update post set threadID = 2 where postID = 2;
update post set threadID = 2 where postID = 3;
update post set threadID = 2 where postID = 4;
update post set threadID = 3 where postID = 5;
update post set threadID = 1 where postID = 6;
update post set threadID = 4 where postID = 7;

insert into userInCourse values ("t@s.com", "TDAT3023"), ("hanne@gmail.com", "TDAT3023"), ("lotte@hotmail.no", "TDAT3023"), ("geit@outlook", "TDAT3023"),
("sofie@ntnu.no","FI1104"), ("lise@stud.no", "TDAT3023")

insert into UserPost values ("t@s.com", 6, "2021-02-09"), ("hanne@gmail.com", 1, "2021-01-03"), ("lotte@hotmail.no", 3, "2021-02-03"), ("t@s.com", 2, "2021-02-03",
("geit@outlook", 4, "2021-04-02"), ("sofie@ntnu.no", 5, "2021-02-02"), ("lise@stud.no", 6, "2021-01-02");

insert into UserLikesPost values ("hanne@gmail.com", 6), ("hanne@gmail.com", 2), ("hanne@gmail.com", 3);

insert into UserViewedPost values ("t@s.com", 1, "2021-01-03"), ("t@s.com", 3, "2021-01-03"), ("t@s.com", 6, "2021-03-01");





