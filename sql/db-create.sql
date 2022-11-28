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
	login VARCHAR(30) UNIQUE,
	password VARCHAR(63),
	role_id INT NOT NULL REFERENCES roles(id) on delete cascade,
	account INT DEFAULT(0)
);

CREATE TABLE orders_statuses (
	id SERIAL PRIMARY KEY,
	name VARCHAR(30) UNIQUE
);

CREATE TABLE repair_orders (
	id SERIAL PRIMARY KEY,
	user_id INT REFERENCES users(id) on delete cascade,
	craftsman_id INT REFERENCES users(id) on delete CASCADE,
	text VARCHAR(255),
	price INT,
	status_id INT REFERENCES orders_statuses(id) on delete cascade,
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

INSERT INTO users VALUES (DEFAULT, 'admin1', 'admin1', 1, DEFAULT);
INSERT INTO users VALUES (DEFAULT, 'manager1', 'manager1', 2, DEFAULT);
INSERT INTO users VALUES (DEFAULT, 'manager2', 'manager2', 2, DEFAULT);
INSERT INTO users VALUES (DEFAULT, 'craft1', 'craft1', 3, DEFAULT);
INSERT INTO users VALUES (DEFAULT, 'craft2', 'craft2', 3, DEFAULT);
INSERT INTO users VALUES (DEFAULT, 'user1', 'user1', 4, DEFAULT);
INSERT INTO users VALUES (DEFAULT, 'user2', 'user2', 4, DEFAULT);

INSERT INTO repair_orders VALUES (DEFAULT, 6, 5, 'My01', 350, 1, NULL, NULL);
INSERT INTO repair_orders VALUES (DEFAULT, 7, 4, 'My02', 320, 1, NULL, NULL);

SELECT * FROM users;
SELECT * FROM repair_orders;
SELECT * FROM orders_statuses;
SELECT * FROM roles;
