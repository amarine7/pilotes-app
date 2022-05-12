
--create an order for the user of id 1 on startup
INSERT INTO pilotes_order(delivery_address, number_of_pilotes, user_id, order_total, creation_date) VALUES('1234 Main Street', 5, 1, 6.65, NOW());
INSERT INTO pilotes_order(delivery_address, number_of_pilotes, user_id, order_total, creation_date) VALUES('544 Younge Street', 15, 2, 19.95, NOW());

-- create another user via import.sql
--INSERT INTO user(username, password, first_name, last_name, phone_number) VALUES('user', '1234', 'Zaphod', 'Beeblebrox', '12345678');
