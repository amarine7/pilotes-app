-- DROP TABLE wfm_cs.workforce;

CREATE TABLE user (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	username varchar(50) NULL,
	password varchar(100) NULL,
	authority varchar(50) NULL,
	first_name varchar(50) NULL,
	last_name varchar(50) NULL,
	phone_number varchar(50) NULL,
	CONSTRAINT user_pk PRIMARY KEY (id),
	CONSTRAINT user_un UNIQUE (id),
	CONSTRAINT user_un2 UNIQUE (username)
);

CREATE TABLE pilotes_order (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	delivery_address varchar(100) NULL,
	number_of_pilotes int4 NULL,
	order_total float4 NULL,
	creation_date timestamp NULL,
	user_id int8 NULL,
	CONSTRAINT order_pk PRIMARY KEY (id)
);

ALTER TABLE pilotes_order ADD CONSTRAINT pilotes_order_fk FOREIGN KEY (user_id) REFERENCES user(id);

--
--
---- wfm_cs.workforce foreign keys
--

--ALTER TABLE wfm_cs.workforce ADD CONSTRAINT workforce_fk_1 FOREIGN KEY (mmc_id) REFERENCES wfm_cs.mmc(id);