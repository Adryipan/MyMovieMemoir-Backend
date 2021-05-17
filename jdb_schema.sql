drop table MEMOIR;
drop table CINEMA;
drop table CREDENTIALS;
drop table PERSON;

create table PERSON(
user_id int primary key generated always as identity,
first_name varchar(50) not null,
surname varchar(50) not null,
gender char(1) not null,
DoB date not null,
street_number varchar(4),
street_name varchar(50),
state_code varchar(3),
postcode varchar(4)
);

create table CREDENTIALS(
username varchar(50) primary key,
password varchar(50) not null,
sign_up_date date not null,
user_id int not null
);

create table MEMOIR(
mem_id int primary key generated always as identity,
movie_name varchar(50) not null,
release_date date not null,
watch_time time not null,
watch_date date not null,
comment varchar(300),
rating numeric(2,1),
user_id int,
cinema_id int
);

create table CINEMA(
cinema_id int primary key generated always as identity,
cinema_name varchar(50) not null,
postcode varchar(4) not null
);

alter table CREDENTIALS
add foreign key (user_id) references PERSON(user_id);

alter table MEMOIR
add foreign key (user_id) references PERSON(user_id);

alter table MEMOIR
add foreign key (cinema_id) references CINEMA(cinema_id);

INSERT INTO ADRIAN.PERSON (FIRST_NAME, SURNAME, GENDER, DOB, STREET_NUMBER, STREET_NAME, STATE_CODE, POSTCODE)
	VALUES ('John', 'Smith', 'M', '1995-03-28', '16', 'Coane Street', 'VIC', '3166');
INSERT INTO ADRIAN.PERSON (FIRST_NAME, SURNAME, GENDER, DOB, STREET_NUMBER, STREET_NAME, STATE_CODE, POSTCODE)
	VALUES ('Mary', 'Hughes', 'F', '1993-04-02', '42', 'Colleague Walk', 'VIC', '3800');
INSERT INTO ADRIAN.PERSON (FIRST_NAME, SURNAME, GENDER, DOB, STREET_NUMBER, STREET_NAME, STATE_CODE, POSTCODE)
	VALUES ('Karina', 'Perry', 'F', '1975-09-29', '151', 'City Road', 'VIC', '3006');

INSERT INTO ADRIAN.CREDENTIALS (USERNAME, PASSWORD, SIGN_UP_DATE, USER_ID)
	VALUES ('john.smith@monash.edu.au', '827ccb0eea8a706c4c34a16891f84e7b', '2020-03-20', 1);
INSERT INTO ADRIAN.CREDENTIALS (USERNAME, PASSWORD, SIGN_UP_DATE, USER_ID)
	VALUES ('maryH@hotmail.com', '01cfcd4f6b8770febfb40cb906715822', '2020-01-15', 2);
INSERT INTO ADRIAN.CREDENTIALS (USERNAME, PASSWORD, SIGN_UP_DATE, USER_ID)
	VALUES ('kptheforce@gmail.com', '2617242e7090b13125606dc81766844b', '2020-03-28', 3);

INSERT INTO ADRIAN.CINEMA (CINEMA_NAME, POSTCODE)
	VALUES ('Waverley Cinema', '3149');
INSERT INTO ADRIAN.CINEMA (CINEMA_NAME, POSTCODE)
	VALUES ('Hoyts', '3148');
INSERT INTO ADRIAN.CINEMA (CINEMA_NAME, POSTCODE)
	VALUES ('Village Cinemas Century City', '3150');
INSERT INTO ADRIAN.CINEMA (CINEMA_NAME, POSTCODE)
	VALUES ('Hoyts', '3000');
INSERT INTO ADRIAN.CINEMA (CINEMA_NAME, POSTCODE)
	VALUES ('Hoyts Docklands', '3008');

INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Die Another Day', '2002-11-20', '14:00:00', '2018-03-28', '007 is so cool!', 5, 1, 1);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Casino Royale', '2006-11-14', '15:00:00', '2018-03-29', 'Not used to the new 007 style', 3, 1, 1);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Quantum of Solace', '2008-10-29', '20:00:00', '2018-03-30', 'Daniel is getting better', 4, 1, 1);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Skyfall', '2012-10-23', '22:00:00', '2020-02-12', 'OMG I can''t believe that M is dead. But finally know M and 007 more.', 4, 1, 1);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Spectre', '2015-10-26', '20:00:00', '2020-02-13', 'Daniel is doing well. The new M is a bit weird. Lea is playing well.', 4.5, 1, 1);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Tomorrow Never Dies', '1997-09-12', '17:00:00', '2020-03-11', 'This is very classical.', 5, 1, 1);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Iron Man', '2008-05-02', '15:00:00', '2008-05-04', 'The iron man suit is so cool omg. JARVIS is the future!', 5, 1, 1);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Iron Man 2', '2010-05-07', '14:00:00', '2010-05-10', 'I cannot imagine if the suites come true. How many people can get helped.', 5, 1, 2);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Iron Man 3', '2013-05-03', '16:00:00', '2013-05-06', 'Yes there are so may suits and can resemble remotly!', 5, 1, 4);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Captain America: The First Avenger', '2011-07-22', '16:00:00', '2011-07-23', 'I quite like the background of WW2. It''s an interesting plot', 5, 1, 3);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Crazy Rich Asian', '2018-08-07', '22:00:00', '2020-02-19', 'Can totally relate the situation with my own experience.', 5, 1, 5);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Avergers: Endgame', '2019-04-26', '09:00:00', '2019-04-26', 'RIP Tony Stark. What a beautiful ending. I am IRON MAN', 5, 1, 2);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Avergers: Endgame', '2019-04-26', '14:00:00', '2019-05-01', 'This is the second time watching it. It feels so touching.', 5, 1, 2);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Men In Black International', '2019-06-13', '12:00:00', '2019-07-03', 'This product does not match the original series at all. There''s no reason to continue MIB if it''s going like this.', 1, 2, 3);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('IT', '2017-09-07', '10:00:00', '2019-06-05', 'This is not scarry at all. The plot is pretty bad.', 0.5, 3, 5);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Child''s play', '2019-06-20', '12:00:00', '2019-12-19', 'This is more like creepy than scarry.', 0, 2, 5);
	INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Remake1', '2010-04-17', '16:00:00', '2020-04-17', 'asdfsadf', 4, 1, 1);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Remake2', '2008-04-17', '16:00:00', '2020-04-17', 'sadfsadf', 1, 1, 1);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Remake2', '2018-04-17', '16:00:00', '2020-04-17', 'asdfsdf', 1, 1, 1);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Remake1', '2020-04-17', '16:00:00', '2020-04-17', 'asdfasdfa', 5, 1, 1);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Highest1', '2020-04-17', '16:00:00', '2020-04-17', 'asdfasdf', 4, 1, 1);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Highest2', '2020-04-17', '16:00:00', '2020-04-17', 'adfasdf', 3.5, 1, 1);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Highest3', '2020-04-17', '16:00:00', '2020-04-17', 'sagasdg', 3, 1, 1);
INSERT INTO ADRIAN.MEMOIR (MOVIE_NAME, RELEASE_DATE, WATCH_TIME, WATCH_DATE, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES ('Remake1', '2020-04-17', '16:00:00', '2020-04-17', 'asdasdasd', 2.5, 1, 1);
