DROP TABLE Persons;

CREATE TABLE Persons
(
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(254) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    profilePic VARCHAR(254),
    vaccine BIT NOT NULL,
    password VARCHAR(200) NOT NULL,
    test DATE,
    vaccineCard VARCHAR(256),
    id VARCHAR(256)

);

INSERT INTO Persons (username, name, email, phone, profilePic, vaccine, password, test, vaccineCard, id)
 VALUES ('peytonhobson', 'Peyton Hobson', 'peyton.hobson1@gmail.com', '5035802322', 'Scenic Road', 1,
         'b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86', '2021-04-10',
         'https://ibb.co/dMxGyFG/thumbnail-IMG-4993.jpg', 'https://ibb.co/pjLXPXM');
INSERT INTO Persons (username, name, email, phone, profilePic, vaccine, password, test, vaccineCard, id)
VALUES ('smith', 'John Smith', 'john-smith@uiowa.edu', '1234123', '7f15b53c-acd6-430b-9585-9a39c3fce2b9-washington-chinook-scenic-byway.jpg', 1,
        'de279e78fb7d58283c4b7782568ac88b352db2dc7d73e29f8087aec491598467875edf19146d71335c559c27982b9cdc83f8b4125998a41fdd5a73ac4f4e3a51', NULL, 'https://www.cdc.gov/coronavirus/2019-ncov/images/vaccines/cdc-vaccine-card.png?_=81468', 'https://pbs.twimg.com/media/BosHgb6CYAAT4j_.jpg');



DROP TABLE Organizations;

CREATE TABLE Organizations
    (
        username VARCHAR(50) NOT NULL PRIMARY KEY,
        name VARCHAR(50) NOT NULL,
        email VARCHAR(254) NOT NULL,
        phone VARCHAR(15) NOT NULL,
        profilePic VARCHAR(254),
        password VARCHAR(200) NOT NULL,
        vaccine BIT NOT NULL,
        mask BIT NOT NULL,
        test BIT NOT NULL,
        organizationType VARCHAR(20) NOT NULL
);

INSERT INTO Organizations (username, name, email, phone, profilePic, password, vaccine,mask,
                           test, organizationType)
VALUES ('stateOfIowa', 'Iowa', 'iowa@iowa.com', '911', 'Lake at Sunset', 'a4802c46d2c1e5c5d176433f84eeb474bc155aa8c79e306f69a2f458856a06c1e5eef36705a9d42a091584f33c8be6262d3b36c4b8b96ba343efffe1e07333ab',
        0,0,0, 'Government');
INSERT INTO Organizations (username, name, email, phone, profilePic, password, vaccine,mask,
                           test, organizationType)
VALUES ('universityOfIowa', 'UniversityOfIowa', 'uiowa@uiowa.edu', '319-555-2323', 'Tree at Sunset', 'a4802c46d2c1e5c5d176433f84eeb474bc155aa8c79e306f69a2f458856a06c1e5eef36705a9d42a091584f33c8be6262d3b36c4b8b96ba343efffe1e07333ab',
        0,0,0, 'Government');
INSERT INTO Organizations (username, name, email, phone, profilePic, password, vaccine,mask,
                           test, organizationType)
VALUES ('mickieD', 'McDonalds', 'mcdonalds@mcdonalds.com', '1-800-777-777', 'Lake at Sunset', 'ba22d730b3fd4393e77ce956d2b3499b7d8215696648d31d158816747b3d8120c60afe68c9b202a2d4e604278f069f21221bbe560d06f4fa84158937679232cc',
        0,1,0, 'Retail');
INSERT INTO Organizations (username, name, email, phone, profilePic, password, vaccine,mask,
                           test, organizationType)
VALUES ('England', 'England', 'brits@england.gov', '2-123-123-1234', 'Lake at Sunrise', '949172108e98662b49209b48c8465d514080339573a1fee85a8f4cc4977b58ce25bdce8ee80c61ac90db715982363efb7e4c96a75c10867c2544f27abc9e081c',
        1,1,1, 'Government');
INSERT INTO Organizations (username, name, email, phone, profilePic, password, vaccine,mask,
                           test, organizationType)
VALUES ('UiowaHospital', 'University of Iowa Hospital', 'uiowahealthcare@uiowa.edu', '911', 'Scenic Road', 'd2205fd750b2f46090feb7fd2f5fb27961be4750578346d1a50a512fde3019243bdc03227375c80176768952d4d7e8cfb8a96f48a73a19c275e5f185a43da22e',
        0,1,0, 'HealthCare');

