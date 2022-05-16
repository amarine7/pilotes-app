# pilotes-app

This Spring based application allows us to manage the orders of the "pilotes" through some
API. Pilotes of the great Miquel Montoro are a Majorcan recipe that consisting of a meatball stew.
The following operations are implemented:
- **Create a pilotes order**, choosing between 5, 10 or 15 pilotes.
- **Update a pilotes order during the 5 minutes following the creation of the order**. After that time it will not be possible to modify any data of
the order because Miquel will be occupied cooking the pilotes.
- **Search orders by customer data**. Partial searches (e.g., all orders of customers whose name contains an “a” in their name) are allowed.

Keep in mind that:
- all types of data used are validated. For example, you must use a valid email format when placing the order.
- the search operation is secured in that **only authorized users are allowed to use the search**. All other operations are public and not secured.

Technical aspects:
- The  project uses Lombok
- The API follows REST standard
- The  project use MySQL database, but it's easily configurable to use an in-memory database such as H2 or similar too.
- Tests are included

Technical TODOs:
- add Docker Compose for local development
- Test coverage 80% or above
- API documentation (Swagger)

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
