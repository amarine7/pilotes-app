-- create_records.sql

INSERT INTO user
            (id,
             username,
             password,
             first_name,
             last_name,
             phone_number)
VALUES (1,
        'user',
        '1234',
        'Zaphod',
        'Beeblebrox',
        '12345678'),
        (2,
        'user 2',
        '5678',
        'Mary',
        'Stewart',
        '123478');

INSERT INTO pilotes_order
            (id,
             delivery_address,
             number_of_pilotes,
             user_id)
VALUES (1,
        '1234 Main Street',
        5,
        1),
        (2,
        '544 Younge Street',
        15,
        2);
