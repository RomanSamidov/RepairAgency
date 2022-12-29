--DROP database IF EXISTS testdb;
--CREATE database testdb;
--USE testdb;


drop table if exists repair_orders;
drop table if exists users;
drop table if exists roles;
drop table if exists orders_statuses;



CREATE TABLE roles (
	id SERIAL PRIMARY KEY,
	name VARCHAR(30) UNIQUE
);

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	login VARCHAR(30) NOT NULL UNIQUE,
	password VARCHAR(63) NOT NULL,
	email VARCHAR(30) NOT NULL,
	allow_letters BOOL DEFAULT(true),
	confirmed BOOL DEFAULT(false),
	role_id INT NOT NULL REFERENCES roles(id) on delete cascade,
	account INT DEFAULT(0)
);

CREATE TABLE orders_statuses (
	id SERIAL PRIMARY KEY,
	name VARCHAR(30) UNIQUE
);

CREATE TABLE repair_orders (
	id SERIAL PRIMARY KEY,
	user_id INT NOT NULL REFERENCES users(id) on delete cascade,
	craftsman_id INT REFERENCES users(id) on delete CASCADE,
	creation_date TIMESTAMPTZ NOT NULL,
	text VARCHAR(255),
	price INT,
	finish_date TIMESTAMPTZ,
	status_id INT NOT NULL REFERENCES orders_statuses(id) on delete cascade,
	feedback_date TIMESTAMPTZ,
	feedback_text VARCHAR(255),
	feedback_mark INT
);

INSERT INTO roles VALUES (DEFAULT, 'Admin');
INSERT INTO roles VALUES (DEFAULT, 'Manager');
INSERT INTO roles VALUES (DEFAULT, 'Craftsman');
INSERT INTO roles VALUES (DEFAULT, 'Client');

INSERT INTO orders_statuses VALUES (DEFAULT, 'Created');
INSERT INTO orders_statuses VALUES (DEFAULT, 'Pending payment');
INSERT INTO orders_statuses VALUES (DEFAULT, 'Paid');
INSERT INTO orders_statuses VALUES (DEFAULT, 'Canceled');
INSERT INTO orders_statuses VALUES (DEFAULT, 'In progress');
INSERT INTO orders_statuses VALUES (DEFAULT, 'Completed');

INSERT INTO users VALUES (DEFAULT, 'admin1', 'F294D0FCC947C6C133A107AF2109105291D6B249FAAC794527E35CB578CEC4', 'exam1@gmail.com', true, false, 1, DEFAULT);
INSERT INTO users VALUES (DEFAULT, 'manager1', 'F294D0FCC947C6C133A107AF2109105291D6B249FAAC794527E35CB578CEC4', 'exam2@gmail.com', true, false, 2, DEFAULT);
INSERT INTO users VALUES (DEFAULT, 'manager2', 'F294D0FCC947C6C133A107AF2109105291D6B249FAAC794527E35CB578CEC4', 'exam3@gmail.com', true, false, 2, DEFAULT);
INSERT INTO users VALUES (DEFAULT, 'craft1', 'F294D0FCC947C6C133A107AF2109105291D6B249FAAC794527E35CB578CEC4', 'exam4@gmail.com', true, false, 3, DEFAULT);
INSERT INTO users VALUES (DEFAULT, 'craft2', 'F294D0FCC947C6C133A107AF2109105291D6B249FAAC794527E35CB578CEC4', 'exam5@gmail.com', true, false, 3, DEFAULT);
INSERT INTO users VALUES (DEFAULT, 'user1', 'F294D0FCC947C6C133A107AF2109105291D6B249FAAC794527E35CB578CEC4', 'exam6@gmail.com', true, false, 4, DEFAULT);
INSERT INTO users VALUES (DEFAULT, 'user2', 'F294D0FCC947C6C133A107AF2109105291D6B249FAAC794527E35CB578CEC4', 'exam7@gmail.com', true, false, 4, DEFAULT);

INSERT INTO repair_orders VALUES (DEFAULT, 6, 5, '2022-12-27 23:25:03.000 +0200', 'My01', 350, NULL, 1, NULL, NULL, NULL);
INSERT INTO repair_orders VALUES (DEFAULT, 7, 4, '2022-12-27 23:25:03.000 +0200', 'My02', 320, NULL, 1, NULL, NULL, NULL);

SELECT * FROM users;
SELECT * FROM repair_orders;
SELECT * FROM orders_statuses;
SELECT * FROM roles;

SELECT * FROM users ORDER BY id OFFSET 1 ROWS FETCH NEXT 2 ROWS ONLY;