--DROP database IF EXISTS testdb;
--CREATE database testdb;
--USE testdb;

drop table if exists managers;
drop table if exists repair_orders;
drop table if exists craftsmans;
drop table if exists users;
drop table if exists orders_statuses;



CREATE table managers (
	id SERIAL PRIMARY KEY,
	login VARCHAR(30) UNIQUE,
	password VARCHAR(63)
);

CREATE TABLE craftsmans (
	id SERIAL PRIMARY KEY,
	login VARCHAR(30) UNIQUE,
	password VARCHAR(63)
);

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	login VARCHAR(30) UNIQUE,
	password VARCHAR(63),
	account INT DEFAULT(0)
);

CREATE TABLE orders_statuses (
	id SERIAL PRIMARY KEY,
	name VARCHAR(30) UNIQUE
);


CREATE TABLE repair_orders (
	id SERIAL PRIMARY KEY,
	user_id INT REFERENCES users(id) on delete cascade,
	craftsman_id INT REFERENCES craftsmans(id) on delete CASCADE,
	text VARCHAR(255),
	price INT,
	status_id INT REFERENCES orders_statuses(id) on delete cascade,
	feedback_text VARCHAR(255),
	feedback_mark INT
);

INSERT INTO orders_statuses VALUES (DEFAULT, 'Created');
INSERT INTO orders_statuses VALUES (DEFAULT, 'Pending payment');
INSERT INTO orders_statuses VALUES (DEFAULT, 'Paid');
INSERT INTO orders_statuses VALUES (DEFAULT, 'Canceled');
INSERT INTO orders_statuses VALUES (DEFAULT, 'In progress');
INSERT INTO orders_statuses VALUES (DEFAULT, 'Completed');

INSERT INTO managers VALUES (DEFAULT, 'admin1', 'admin1');
INSERT INTO managers VALUES (DEFAULT, 'admin2', 'admin2');

INSERT INTO craftsmans VALUES (DEFAULT, 'craft1', 'craft1');
INSERT INTO craftsmans VALUES (DEFAULT, 'craft2', 'craft2');

INSERT INTO users VALUES (DEFAULT, 'user1', 'user1', DEFAULT);
INSERT INTO users VALUES (DEFAULT, 'user2', 'user2', DEFAULT);

INSERT INTO repair_orders VALUES (DEFAULT, 1, 2, 'My01', 350, 1, NULL, NULL);
INSERT INTO repair_orders VALUES (DEFAULT, 2, 1, 'My02', 320, 1, NULL, NULL);

SELECT * FROM managers;
SELECT * FROM craftsmans;
SELECT * FROM users;
SELECT * FROM repair_orders;
SELECT * FROM orders_statuses;


