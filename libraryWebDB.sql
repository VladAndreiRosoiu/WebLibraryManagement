CREATE SCHEMA libraryWebDB;
USE libraryWebDB;

CREATE TABLE users(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
username VARCHAR(255) NOT NULL UNIQUE,
user_password VARCHAR(255) NOT NULL,
user_role varchar(45),
email VARCHAR(255) NOT NULL UNIQUE,
registered_on DATE NOT NULL,
enabled BOOLEAN DEFAULT TRUE);

CREATE TABLE authors(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
additional_info VARCHAR(1000),
birth_date DATE NOT NULL,
can_be_displayed BOOLEAN DEFAULT TRUE,
CONSTRAINT author_cons UNIQUE (first_name, last_name, birth_date));

CREATE TABLE genre(
id INT PRIMARY KEY AUTO_INCREMENT,
genre_type VARCHAR (255) NOT NULL UNIQUE,
can_be_displayed BOOLEAN DEFAULT TRUE);

CREATE TABLE books(
id INT PRIMARY KEY AUTO_INCREMENT,
book_title VARCHAR(255) NOT NULL,
isbn BIGINT UNIQUE,
release_date DATE NOT NULL,
can_be_displayed BOOLEAN DEFAULT TRUE);

CREATE TABLE book_author(
id INT PRIMARY KEY AUTO_INCREMENT,
id_book INT NOT NULL,
id_author INT NOT NULL,
FOREIGN KEY (id_book) REFERENCES books(id),
FOREIGN KEY (id_author) REFERENCES authors(id),
CONSTRAINT book_author_constr UNIQUE (id_book, id_author));

CREATE TABLE book_genre(
id INT PRIMARY KEY AUTO_INCREMENT,
id_book INT NOT NULL,
id_genre INT NOT NULL,
CONSTRAINT book_genre_constr UNIQUE (id_book, id_genre),
FOREIGN KEY (id_book) REFERENCES books(id),
FOREIGN KEY (id_genre) REFERENCES genre(id));

CREATE TABLE book_stock(
id_book INT PRIMARY KEY,
stock INT,
FOREIGN KEY (id_book) REFERENCES books(id)
);

CREATE TABLE borrow_info(
id INT PRIMARY KEY AUTO_INCREMENT,
id_book INT NOT NULL,
borrowed_on DATE NOT NULL,
returned_on DATE,
FOREIGN KEY (id_book) REFERENCES books(id)
);

CREATE TABLE borrow_user(
id INT PRIMARY KEY AUTO_INCREMENT,
id_user INT NOT NULL,
id_borrow_info INT NOT NULL,
FOREIGN KEY (id_user) REFERENCES users(id),
FOREIGN KEY (id_borrow_info) references borrow_info (id)
);

INSERT INTO libraryWebDB.authors(first_name, last_name, additional_info, birth_date )
VALUES
('Naomi', 'Novik' , 'Naomi Novik is an American author of speculative fiction. Novik won both the Nebula Award for Best Novel and the Mythopoeic Fantasy Award in 2016 for her novel Uprooted.', '1973-04-30'),
('Roshani', 'Chokshi', 'Roshani Chokshi is an American children\' book author and a New York Times bestselling author.' , '1991-02-14'),
('Anthony', 'Doerr','Anthony Doerr is an American author of novels and short stories. He gained widespread recognition for his 2014 novel All the Light We Cannot See, which won the Pulitzer Prize for Fiction. ' ,'1973-10-27'),
('Markus', 'Zusak' , 'Markus Zusak is an Australian writer of German origin. He is best known for The Book Thief and The Messenger, two novels which became international bestsellers. He won the Margaret A. Edwards Award in 2014.', '1975-06-23'),
('Jane', 'Austen', 'Jane Austen was an English novelist known primarily for her six major novels, which interpret, critique and comment upon the British landed gentry at the end of the 18th century. Austen\'s plots often explore the dependence of women on marriage in the pursuit of favourable social standing and economic security.', '1775-12-16'),
('Anna', 'Quindlen', 'Anna Marie Quindlen is an American author, journalist, and opinion columnist. Her New York Times column, Public and Private, won the Pulitzer Prize for Commentary in 1992. She began her journalism career in 1974 as a reporter for the New York Post. Between 1977 and 1994 she held several posts at The New York Times.', '1953-07-8'),
('Delia', 'Owens', 'Delia Owens is an American author and zoologist. Her debut novel Where the Crawdads Sing topped The New York Times Fiction Best Sellers of 2019 for 25 non-consecutive weeks. The book has been on New York Times Bestsellers lists for more than a year. ', '1949-04-4'),
('Alex', 'Michaelides', 'Alex Michaelides is a bestselling British-Cypriot author and screenwriter. His debut novel, the psychological thriller The Silent Patient, is a New York Times and Sunday Times besteller.', '1977-09-04'),
('George', 'Orwell', 'Eric Arthur Blair, known by his pen name George Orwell, was an English novelist, essayist, journalist and critic. His work is characterised by lucid prose, biting social criticism, opposition to totalitarianism, and outspoken support of democratic socialism.', '1903-06-25'),
('Thomas', 'Pynchon', 'Thomas Ruggles Pynchon Jr. is an American novelist. A MacArthur Fellow, he is noted for his dense and complex novels. His fiction and non-fiction writings encompass a vast array of subject matter, genres and themes, including history, music, science, and mathematics. ', '1937-05-8'),
('Herman', 'Melville', 'Herman Melville was an American novelist, short story writer, and poet of the American Renaissance period. Among his best-known works are Moby-Dick, Typee, a romanticized account of his experiences in Polynesia, and Billy Budd, Sailor, a posthumously published novella.', '1819-08-01'),
('Andrew', 'Delbanco', 'Andrew H. Delbanco is the Alexander Hamilton Professor of American Studies at Columbia University. He is the author of several books, including College: What It Was, Is, and Should Be, which has been translated into Chinese, Korean, Turkish, Russian, and Hebrew. ', '1952-02-20'),
('Rockwell', 'Kent', 'Rockwell Kent was an American painter, printmaker, illustrator, writer, sailor, adventurer and voyager.', '1882-06-21');

INSERT INTO libraryWebDB.books(book_title, isbn, release_date)
VALUES
('A Deadly Education', 9780593128480, '2020-09-29'),
('The Silvered Serpents', 9781250144577, '2020-09-22'),
('All the Light We Cannot See', 9781476746586, '2014-05-01'),
('The Book Thief', 9780375831003, '2006-03-14'),
('Pride and Prejudice', 9780679783268, '2000-10-10'),
('Where the Crawdads Sing', 9780735219113, '2018-08-14'),
('The Silent Patient', 9781250301697, '2019-02-05'),
('Nineteen Eighty-Four', 9780452284234, '2003-05-06'),
('Moby-Dick or, the Whale', 9780142437247, '2003-02-21');

INSERT INTO libraryWebDB.book_stock (id_book, stock)
VALUES
(1,10),
(2,5),
(3,30),
(4,13),
(5,50),
(6,3),
(7,8),
(8,100),
(9,66);

INSERT INTO libraryWebDB.book_author (id_book, id_author)
VALUES
(1,1),
(2,2),
(3,3),
(4,4),
(4,5),
(5,6),
(6,7),
(7,7),
(7,8),
(8,9),
(8,10),
(9,11),
(9,12),
(9,13);

INSERT INTO libraryWebDB.genre (genre_type)
VALUES
('Science Fiction'),
('Magical Realism'),
('High Fantasy'),
('Fantasy Fiction'),
('Contemporary Fantasy'),
('Young Adult Fiction'),
('Novel'),
('Historical Fiction'),
('Historical Novel'),
('Romance Novel'),
('Satire'),
('Novel of Manners'),
('Regency Romance'),
('Domestic Fiction'),
('Regency Fiction'),
('Bildungsroman'),
('Mystery'),
('Thriller'),
('Suspense'),
('Psychological Fiction'),
('Social Science Fiction'),
('Political Fiction'),
('Epic'),
('Adventure Fiction'),
('Nautical Fiction');

INSERT INTO libraryWebDB.book_genre (id_book, id_genre)
VALUES
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(2,4),
(2,6),
(3,7),
(3,8),
(4,9),
(5,8),
(5,10),
(5,11),
(5,12),
(5,13),
(5,14),
(5,15),
(6,16),
(6,17),
(6,7),
(7,18),
(7,19),
(7,20),
(7,7),
(7,17),
(8,21),
(8,1),
(9,22),
(9,23),
(9,24),
(9,7);

INSERT INTO libraryWebDB.users(first_name, last_name, username, user_password, user_role, email, registered_on)
VALUES
('Vlad', 'Rosoiu', 'vladrosoiu', '$2a$10$i9iwWLDgWQ1KWC.Z6nSOwedJDjf2A8LWogsvI1EdcXI.nT6ohTLci', 'ROLE_USER', 'vrosoiu@mail.com', '2018-04-12'),
('Andrei', 'Andreescu', 'aandreescu', '$2a$10$3WaXkOZxAJeq.5.1PumyCe8XpXd/zeOurGYosBlnF15iZKwleICxi', 'ROLE_ADMIN', 'aandreescu@othermail.com', '2007-08-30'),
('Young', 'Mayer', 'youngmayer', '$2a$10$Smbm8FBRFuef5W9tE49O4u/0DboarKRsVYTE.W5V2F0jv7tBaVXoK', 'ROLE_USER','mayer@ymail.com', '2020-08-01'),
('Edmund', 'Kozey', 'edkozey', '$2a$10$Rg0scqDBmofJoByw4hSlNu85YPc6i8SjHh4CVP87k5UKkYf6DAw7q', 'ROLE_USER', 'ed@gmail.com', '2014-09-03');

INSERT INTO libraryWebDB.borrow_info(id_book, borrowed_on, returned_on) VALUES
(2,'2017-10-01','2017-10-14'),
(9,'2017-11-02','2017-11-30'),
(3,'2018-05-01','2018-05-30'),
(4,'2019-01-05','2019-01-28'),
(8,'2020-03-15','2020-04-01'),
(2,'2018-11-19','2018-11-25'),
(2,'2020-04-20','2020-05-05'),
(7,'2020-08-01','2020-08-15');

INSERT INTO libraryWebDB.borrow_info(id_book, borrowed_on) VALUES
(6,'2020-10-01'),
(4,'2020-10-10');

INSERT INTO libraryWebDB.borrow_user(id_user, id_borrow_info) VALUES
(1,1),
(1,2),
(3,3),
(4,4),
(4,5),
(1,6),
(1,7),
(3,8),
(1,9),
(3,10);

UPDATE libraryWebDB.book_stock
SET stock = (stock-1)
WHERE id_book=6;

UPDATE libraryWebDB.book_stock
SET stock = (stock-1)
WHERE id_book=4;