--DROP database IF EXISTS testdb;
--CREATE database testdb;
--USE testdb;


drop table if exists repair_orders;
drop table if exists users;
drop table if exists roles;
drop table if exists locales;
drop table if exists orders_statuses;



CREATE TABLE roles (
	id SERIAL8 PRIMARY KEY,
	name VARCHAR(30) UNIQUE
);

CREATE TABLE locales (
	id SERIAL8 PRIMARY KEY,
	name VARCHAR(30) UNIQUE
);

CREATE TABLE users (
	id SERIAL8 PRIMARY KEY,
	login VARCHAR(30) NOT NULL UNIQUE,
	password VARCHAR(63) NOT NULL,
	email VARCHAR(30) NOT NULL,
	allow_letters BOOL DEFAULT(true),
	confirmed BOOL DEFAULT(false),
	role_id BIGINT NOT NULL REFERENCES roles(id),
	account INT DEFAULT(0),
	locale_id BIGINT NOT NULL REFERENCES locales(id)
);

CREATE TABLE orders_statuses (
	id SERIAL8 PRIMARY KEY,
	name VARCHAR(30) UNIQUE
);

CREATE TABLE repair_orders (
	id SERIAL8 PRIMARY KEY,
	user_id BIGINT NOT NULL REFERENCES users(id) on delete cascade,
	craftsman_id BIGINT NULL REFERENCES users(id) on delete CASCADE,
	creation_date TIMESTAMPTZ NOT NULL,
	text VARCHAR(255),
	price INT NULL,
	finish_date TIMESTAMPTZ,
	status_id BIGINT NOT NULL REFERENCES orders_statuses(id),
	feedback_date TIMESTAMPTZ,
	feedback_text VARCHAR(255),
	feedback_mark INT NULL
);

INSERT INTO roles VALUES (DEFAULT, 'Admin');
INSERT INTO roles VALUES (DEFAULT, 'Manager');
INSERT INTO roles VALUES (DEFAULT, 'Craftsman');
INSERT INTO roles VALUES (DEFAULT, 'Client');

INSERT INTO locales VALUES (DEFAULT, 'en_US');
INSERT INTO locales VALUES (DEFAULT, 'uk_UA');

INSERT INTO orders_statuses VALUES (DEFAULT, 'Created');
INSERT INTO orders_statuses VALUES (DEFAULT, 'Pending payment');
INSERT INTO orders_statuses VALUES (DEFAULT, 'Paid');
INSERT INTO orders_statuses VALUES (DEFAULT, 'Canceled');
INSERT INTO orders_statuses VALUES (DEFAULT, 'In progress');
INSERT INTO orders_statuses VALUES (DEFAULT, 'Completed');

INSERT INTO users VALUES (DEFAULT, 'admin1', 'F294D0FCC947C6C133A107AF2109105291D6B249FAAC794527E35CB578CEC4', 'roman.samidov@gmail.com', true, false, 1, DEFAULT, 1);
INSERT INTO users VALUES (DEFAULT, 'manager1', 'F294D0FCC947C6C133A107AF2109105291D6B249FAAC794527E35CB578CEC4', 'roman.samidov@gmail.com', true, false, 2, DEFAULT, 1);
INSERT INTO users VALUES (DEFAULT, 'manager2', 'F294D0FCC947C6C133A107AF2109105291D6B249FAAC794527E35CB578CEC4', 'roman.samidov@gmail.com', true, false, 2, DEFAULT, 1);
INSERT INTO users VALUES (DEFAULT, 'craft1', 'F294D0FCC947C6C133A107AF2109105291D6B249FAAC794527E35CB578CEC4', 'roman.samidov@gmail.com', true, false, 3, DEFAULT, 1);
INSERT INTO users VALUES (DEFAULT, 'craft2', 'F294D0FCC947C6C133A107AF2109105291D6B249FAAC794527E35CB578CEC4', 'roman.samidov@gmail.com', true, false, 3, DEFAULT, 1);
INSERT INTO users VALUES (DEFAULT, 'user1', 'F294D0FCC947C6C133A107AF2109105291D6B249FAAC794527E35CB578CEC4', 'roman.samidov@gmail.com', true, false, 4, DEFAULT, 1);
INSERT INTO users VALUES (DEFAULT, 'user2', 'F294D0FCC947C6C133A107AF2109105291D6B249FAAC794527E35CB578CEC4', 'roman.samidov@gmail.com', true, false, 4, DEFAULT, 1) RETURNING id AS result;

UPDATE users SET account = account + 1 WHERE id = 7;

INSERT INTO repair_orders VALUES (DEFAULT, 6, 5, '2022-12-27 23:25:03.000 +0200', 'My01', 350, NULL, 1, NULL, NULL, NULL);
INSERT INTO repair_orders VALUES (DEFAULT, 7, 4, '2022-12-27 23:25:03.000 +0200', 'My02', 320, NULL, 1, NULL, NULL, NULL);
INSERT INTO repair_orders VALUES (DEFAULT, 7, NULL, '2022-12-27 23:25:03.000 +0200', 'My03', NULL, NULL, 1, NULL, NULL, NULL);

SELECT * FROM users;
SELECT * FROM repair_orders;
SELECT * FROM orders_statuses;
SELECT * FROM roles;
SELECT * FROM locales;

SELECT * FROM users ORDER BY id OFFSET 1 ROWS FETCH NEXT 2 ROWS ONLY;