# pilotes-app

This Spring-based application allows us to manage the orders of the "pilotes" through some
API. Pilotes of the great Miquel Montoro are a Majorcan recipe that consisting of a meatball stew.
The following operations are implemented:
- **Create a pilotes order**, choosing between 5, 10 or 15 pilotes.
- **Update a pilotes order during the 5 minutes following the creation of the order**. After that time it will not be possible to modify any data of
the order because Miquel will be occupied cooking the pilotes.
- **Search orders by customer data**. Partial searches (e.g., all orders of customers whose name contains an “a” in their name) are allowed.

Keep in mind that:
- all types of data used are validated. For example, you must use a valid email format when placing the order.
- the order creation & update are secured: **only authenticated customers are allowed to order**.
- the order search operation is secured in that **only authenticated and authorized users are allowed to use the search**.
- there is a /login endpoint for authentication, and **the issued JWT is valid for 5 minutes**.

For now only two users are created and it is not possible to add more:
- username "alemari", authority "CAN_SEARCH";
- username "someone", authority "CAN_NOT_SEARCH";

Technical aspects:
- The project uses Lombok and Mapstruct
- The API follows REST standard
- The  project use MySQL database, but it's easily configurable to use an in-memory database such as H2 or similar too.
- Tests are included

Technical TODOs:
- add global exception handling and check if the swagger error "Unable to find a model that matches..." persists.
- add at least one operation public and not secured.
- for admnistrative users, grant infinite JWT validity.
- solve the enum problem for the number of allowed pilotes.
- add Sonarlint.
- add treating exceptions and logging the event in filters.
- separate user creation (through a User dto and via POST req) from user registration (User entity).
- enforce password validation at API user creation level.
- add Docker Compose for local development.
- test coverage 80% or above.
- add separate configuration for deployment on AWS (connection to RDS instead of a local MySQL, etc.)

The data model contains the following data:
- Basic Client info:
o first and last name
o username and password
o telephone number

- Basic Order info:
Order number (server side generated)
o Delivery address
o Number of pilotes
o Order total (server side generated, 1.33 € as price for a single pilotes)
