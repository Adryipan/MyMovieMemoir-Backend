create table cinema (
    cinema_id bigint not null, 
    cinema_name varchar(255), 
    postcode integer not null, 
    primary key (cinema_id)
);

create table cinema_sequence (next_val bigint);
insert into cinema_sequence values (1);

create table credential (
    user_id bigint not null, 
    password varchar(255), 
    sign_up_date date, 
    username varchar(255), 
    primary key (user_id)
);

create table memoir (
    mem_id bigint not null, 
    comment varchar(512), 
    movie_name varchar(255), 
    rating float, 
    release_date date, 
    watch_datetime TIMESTAMP, 
    cinema_id bigint, 
    user_id bigint not null, 
    primary key (mem_id)
);

create table memoir_sequence (next_val bigint);
insert into memoir_sequence values (1);

create table person (
    user_id bigint not null, 
    dob DATE, f_name varchar(255), 
    gender varchar(255), 
    postcode integer, 
    state_code varchar(255), 
    street_address varchar(255), 
    surname varchar(255), 
    primary key (user_id)
);

create table person_sequence (next_val bigint);
insert into person_sequence values (1);

alter table credential add constraint username_unique unique (username);
alter table credential add constraint person_credential_FK foreign key (user_id) references person (user_id);
alter table memoir add constraint cinema_memoir_FK foreign key (cinema_id) references cinema (cinema_id);
alter table memoir add constraint person_memoir_FK foreign key (user_id) references person (user_id);

set sql_safe_updates = 0;
INSERT INTO PERSON (user_id, f_name, SURNAME, GENDER, DOB, STREET_ADDRESS, STATE_CODE, POSTCODE)
	VALUES (1, 'John', 'Smith', 'M', '1995-03-28', '16 Coane Street', 'VIC', '3166');
INSERT INTO PERSON (user_id, f_name, SURNAME, GENDER, DOB, STREET_ADDRESS, STATE_CODE, POSTCODE)
	VALUES (2, 'Mary', 'Hughes', 'F', '1993-04-02', '42 Colleague Walk', 'VIC', '3800');
INSERT INTO PERSON (user_id, f_name, SURNAME, GENDER, DOB, STREET_ADDRESS, STATE_CODE, POSTCODE)
	VALUES (3, 'Karina', 'Perry', 'F', '1975-09-29', '151 City Road', 'VIC', '3006');
UPDATE person_sequence set next_val = 4 where next_val = 1;

INSERT INTO CREDENTIAL (USERNAME, PASSWORD, SIGN_UP_DATE, USER_ID)
	VALUES ('john.smith@monash.edu.au', '827ccb0eea8a706c4c34a16891f84e7b', '2020-03-20', 1);
INSERT INTO CREDENTIAL (USERNAME, PASSWORD, SIGN_UP_DATE, USER_ID)
	VALUES ('maryH@hotmail.com', '01cfcd4f6b8770febfb40cb906715822', '2020-01-15', 2);
INSERT INTO CREDENTIAL (USERNAME, PASSWORD, SIGN_UP_DATE, USER_ID)
	VALUES ('kptheforce@gmail.com', '2617242e7090b13125606dc81766844b', '2020-03-28', 3);

INSERT INTO CINEMA (cinema_id, CINEMA_NAME, POSTCODE)
	VALUES (1, 'Waverley Cinema', '3149');
INSERT INTO CINEMA (cinema_id, CINEMA_NAME, POSTCODE)
	VALUES (2, 'Hoyts', '3148');
INSERT INTO CINEMA (cinema_id, CINEMA_NAME, POSTCODE)
	VALUES (3, 'Village Cinemas Century City', '3150');
INSERT INTO CINEMA (cinema_id, CINEMA_NAME, POSTCODE)
	VALUES (4, 'Hoyts', '3000');
INSERT INTO CINEMA (cinema_id, CINEMA_NAME, POSTCODE)
	VALUES (5, 'Hoyts Docklands', '3008');
UPDATE cinema_sequence set next_val = 6 where next_val = 1;

INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (1, 'Die Another Day', '2002-11-20',  '2018-03-28 14:00:00', '007 is so cool!', 5, 1, 1);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (2, 'Casino Royale', '2006-11-14', '2018-03-29 15:00:00',  'Not used to the new 007 style', 3, 1, 1);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (3, 'Quantum of Solace', '2008-10-29', '2018-03-30 20:00:00',  'Daniel is getting better', 4, 1, 1);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (4, 'Skyfall', '2012-10-23', '2020-02-12 22:00:00',  'OMG I can''t believe that M is dead. But finally know M and 007 more.', 4, 1, 1);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME,  COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (5, 'Spectre', '2015-10-26', '2020-02-13 20:00:00', 'Daniel is doing well. The new M is a bit weird. Lea is playing well.', 4.5, 1, 1);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (6, 'Tomorrow Never Dies', '1997-09-12', '2020-03-11 17:00:00',  'This is very classical.', 5, 1, 1);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (7, 'Iron Man', '2008-05-02', '2008-05-04 15:00:00', 'The iron man suit is so cool omg. JARVIS is the future!', 5, 1, 1);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (8, 'Iron Man 2', '2010-05-07', '2010-05-10 14:00:00',  'I cannot imagine if the suites come true. How many people can get helped.', 5, 1, 2);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (9, 'Iron Man 3', '2013-05-03', '2013-05-06 16:00:00',  'Yes there are so may suits and can resemble remotly!', 5, 1, 4);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (10, 'Captain America: The First Avenger', '2011-07-22', '2011-07-23 16:00:00',  'I quite like the background of WW2. It''s an interesting plot', 5, 1, 3);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (11, 'Crazy Rich Asian', '2018-08-07', '2020-02-19 22:00:00', 'Can totally relate the situation with my own experience.', 5, 1, 5);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (12, 'Avergers: Endgame', '2019-04-26', '2019-04-26 09:00:00', 'RIP Tony Stark. What a beautiful ending. I am IRON MAN', 5, 1, 2);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (13, 'Avergers: Endgame', '2019-04-26', '2019-05-01 14:00:00', 'This is the second time watching it. It feels so touching.', 5, 1, 2);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (14, 'Men In Black International', '2019-06-13', '2019-07-03 12:00:00',  'This product does not match the original series at all. There''s no reason to continue MIB if it''s going like this.', 1, 2, 3);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (15, 'IT', '2017-09-07', '2019-06-05 10:00:00',  'This is not scarry at all. The plot is pretty bad.', 0.5, 3, 5);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (16, 'Child''s play', '2019-06-20', '2019-12-19 12:00:00',  'This is more like creepy than scarry.', 0, 2, 5);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME, COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (17, 'Remake1', '2010-04-17', '2020-04-17 16:00:00',  'Test', 4, 1, 1);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME,  COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (18, 'Remake2', '2008-04-17', '2020-04-17 16:00:00', 'Test2', 1, 1, 1);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME,  COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (19, 'Remake2', '2018-04-17', '2020-04-17 16:00:00', 'Test3', 1, 1, 1);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME,  COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (20, 'Remake1', '2020-04-17', '2020-04-17 16:00:00', 'Test4', 5, 1, 1);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME,  COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (21, 'Highest1', '2020-04-17', '2020-04-17 16:00:00', 'Test5', 4, 1, 1);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME,  COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (22, 'Highest2', '2020-04-17','2020-04-17 16:00:00', 'Test6', 3.5, 1, 1);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME,  COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (23, 'Highest3', '2020-04-17', '2020-04-17 16:00:00', 'Test7', 3, 1, 1);
INSERT INTO MEMOIR (mem_id, MOVIE_NAME, RELEASE_DATE, WATCH_DATETIME,  COMMENT, RATING, USER_ID, CINEMA_ID)
	VALUES (24, 'Remake1', '2020-04-17', '2020-04-17 16:00:00', 'Test8', 2.5, 1, 1);
UPDATE memoir_sequence set next_val = 25 where next_val = 1;
set sql_safe_updates = 1;