-- create table
create table Task2
(
	ID integer primary key autoincrement not null,
	Name text not null,
	Status character not null,
	Important character not null
);

--Drop table Task;

--INSERT INTO Task VALUES ('Wash Clothes','Half Done','Medium');
INSERT INTO Task2(Name, Status, Important) VALUES ('Wash Clothes','Half Done','Medium');
INSERT INTO Task2(Name, Status, Important) VALUES ('Wash Dishes','Done','Low');

--Edit/Update
UPDATE Task2 SET Name ='PSM 1 HAHA', Status='Not Done HAHA', Important='Low HAHA' WHERE ID = 13;

-- Select statement
SELECT ID, Name, Status, Important
FROM Task2;

