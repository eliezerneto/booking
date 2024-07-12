-- public.sequence_id_accommodation definition

DROP SEQUENCE IF EXISTS public.sequence_id_accommodation;

CREATE SEQUENCE public.sequence_id_accommodation
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1
NO CYCLE;

-- public.sequence_id_accommodation_rating definition

DROP SEQUENCE IF EXISTS public.sequence_id_accommodation_rating;

CREATE SEQUENCE public.sequence_id_accommodation_rating
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1
NO CYCLE;

-- public.sequence_id_address definition

DROP SEQUENCE IF EXISTS public.sequence_id_address;

CREATE SEQUENCE public.sequence_id_address
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1
NO CYCLE;

-- public.sequence_id_amenity definition

DROP SEQUENCE IF EXISTS public.sequence_id_amenity;

CREATE SEQUENCE public.sequence_id_amenity
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1
NO CYCLE;

-- public.sequence_id_booking definition

DROP SEQUENCE IF EXISTS public.sequence_id_booking;

CREATE SEQUENCE public.sequence_id_booking
INCREMENT BY 50
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1
NO CYCLE;

-- public.sequence_id_city definition

DROP SEQUENCE IF EXISTS public.sequence_id_city;

CREATE SEQUENCE public.sequence_id_city
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1
NO CYCLE;

-- public.sequence_id_country definition

DROP SEQUENCE IF EXISTS public.sequence_id_country;

CREATE SEQUENCE public.sequence_id_country
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1
NO CYCLE;

-- public.sequence_id_guest definition

DROP SEQUENCE IF EXISTS public.sequence_id_guest;

CREATE SEQUENCE public.sequence_id_guest
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1
NO CYCLE;

-- public.sequence_id_room definition

DROP SEQUENCE IF EXISTS public.sequence_id_room;

CREATE SEQUENCE public.sequence_id_room
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1
NO CYCLE;

-- public.sequence_id_room_price definition

DROP SEQUENCE IF EXISTS public.sequence_id_room_price;

CREATE SEQUENCE public.sequence_id_room_price
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1
NO CYCLE;

-- public.guest definition

-- Drop table

-- DROP TABLE public.guest;

CREATE TABLE public.guest (
	id_guest int8 NOT NULL,
	email varchar(255) NOT NULL,
	"name" varchar(255) NOT NULL,
	phone varchar(255) NOT NULL,
	CONSTRAINT guest_pkey PRIMARY KEY (id_guest)
);

-- public.country definition

-- Drop table

-- DROP TABLE public.country;

CREATE TABLE public.country (
	id_country int8 NOT NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT country_pkey PRIMARY KEY (id_country)
);

-- public.city definition

-- Drop table

-- DROP TABLE public.city;

CREATE TABLE public.city (
	id_city int8 NOT NULL,
	"name" varchar(255) NOT NULL,
	country_id int8 NULL,
	CONSTRAINT city_pkey PRIMARY KEY (id_city)
);


-- public.city foreign keys

ALTER TABLE public.city ADD CONSTRAINT fkrpd7j1p7yxr784adkx4pyepba FOREIGN KEY (country_id) REFERENCES public.country(id_country);



-- public.amenity definition

-- Drop table

-- DROP TABLE public.amenity;

CREATE TABLE public.amenity (
	id_amenity int8 NOT NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT amenity_pkey PRIMARY KEY (id_amenity)
);

-- public.address definition

-- Drop table

-- DROP TABLE public.address;

CREATE TABLE public.address (
	id_address int8 NOT NULL,
	latitude float8 NOT NULL,
	longitude float8 NOT NULL,
	"number" varchar(255) NOT NULL,
	street varchar(255) NOT NULL,
	zipcode varchar(255) NOT NULL,
	city_id int8 NULL,
	CONSTRAINT address_pkey PRIMARY KEY (id_address)
);


-- public.address foreign keys

ALTER TABLE public.address ADD CONSTRAINT fkpo044ng5x4gynb291cv24vtea FOREIGN KEY (city_id) REFERENCES public.city(id_city);

-- public.accommodation_rating definition

-- Drop table

-- DROP TABLE public.accommodation_rating;

CREATE TABLE public.accommodation_rating (
	id_accommodation_rating int8 NOT NULL,
	average_rating float8 NOT NULL,
	number_of_ratings int4 NOT NULL,
	sum_rating int4 NOT NULL,
	id_accommodation int8 NULL,
	CONSTRAINT accommodation_rating_pkey PRIMARY KEY (id_accommodation_rating),
	CONSTRAINT uk_en6rafmmt070h4nto83o1o85h UNIQUE (id_accommodation)
);




-- public.accommodation definition

-- Drop table

-- DROP TABLE public.accommodation;

CREATE TABLE public.accommodation (
	id_accommodation int8 NOT NULL,
	description varchar(255) NOT NULL,
	"name" varchar(255) NOT NULL,
	"type" varchar(255) NOT NULL,
	id_accommodation_rating int8 NULL,
	id_address int8 NULL,
	CONSTRAINT accommodation_pkey PRIMARY KEY (id_accommodation),
	CONSTRAINT accommodation_type_check CHECK (((type)::text = ANY ((ARRAY['HOTEL'::character varying, 'POUSADA'::character varying, 'HOSTEL'::character varying, 'FLAT'::character varying, 'RESORT'::character varying])::text[]))),
	CONSTRAINT uk_h7g0qknfm98nw9w8hh80odh5f UNIQUE (id_accommodation_rating),
	CONSTRAINT uk_rk2cgfeq2059bwb5b0t89pvrf UNIQUE (id_address)
);

-- public.accommodation_rating foreign keys

ALTER TABLE public.accommodation_rating ADD CONSTRAINT fk7xfvl7ftc8tic085h86ciqktx FOREIGN KEY (id_accommodation) REFERENCES public.accommodation(id_accommodation);


-- public.accommodation foreign keys

ALTER TABLE public.accommodation ADD CONSTRAINT fk2q1gwvhe5d64wm982ht5kilao FOREIGN KEY (id_address) REFERENCES public.address(id_address);

-- public.accommodation_amenity definition

-- Drop table

-- DROP TABLE public.accommodation_amenity;

CREATE TABLE public.accommodation_amenity (
	id_accommodation int8 NOT NULL,
	id_amenity int8 NOT NULL
);


-- public.accommodation_amenity foreign keys

ALTER TABLE public.accommodation_amenity ADD CONSTRAINT fkgvyhimqh7nlk61fxks5bj9kem FOREIGN KEY (id_accommodation) REFERENCES public.accommodation(id_accommodation);
ALTER TABLE public.accommodation_amenity ADD CONSTRAINT fkidf1h1bxv7l64cru0tf5h927t FOREIGN KEY (id_amenity) REFERENCES public.amenity(id_amenity);



-- public.room definition

-- Drop table

-- DROP TABLE public.room;

CREATE TABLE public.room (
	id_room int8 NOT NULL,
	regular_base_price float8 NOT NULL,
	"type" varchar(255) NOT NULL,
	max_guest_quantity int4 NOT NULL,
	id_accommodation int8 NULL,
	CONSTRAINT room_pkey PRIMARY KEY (id_room),
	CONSTRAINT room_type_check CHECK (((type)::text = ANY ((ARRAY['INDIVIDUAL'::character varying, 'DUPLO'::character varying, 'TRIPLO'::character varying, 'COMPARTILHADO'::character varying])::text[])))
);


-- public.room foreign keys

ALTER TABLE public.room ADD CONSTRAINT fkr038nu7pb40d4mooy0c5fahd2 FOREIGN KEY (id_accommodation) REFERENCES public.accommodation(id_accommodation);


-- public.booking definition

-- Drop table

-- DROP TABLE public.booking;

CREATE TABLE public.booking (
	id_booking int8 NOT NULL,
	checkin_date date NOT NULL,
	checkout_date date NOT NULL,
	status varchar(255) NOT NULL,
	total_price float8 NOT NULL,
	number_of_guests int4 NOT NULL,
	id_guest int8 NOT NULL,
	id_room int8 NOT NULL,
	CONSTRAINT booking_pkey PRIMARY KEY (id_booking),
	CONSTRAINT booking_status_check CHECK (((status)::text = ANY ((ARRAY['CREATED'::character varying, 'WAITING_PAYMENT'::character varying, 'PAYED'::character varying, 'RESCHEDULED'::character varying, 'CANCELED'::character varying, 'FINISHED'::character varying])::text[])))
);


-- public.booking foreign keys

ALTER TABLE public.booking ADD CONSTRAINT fkhy7s6kfcis166vt34nd4oh1wy FOREIGN KEY (id_room) REFERENCES public.room(id_room);
ALTER TABLE public.booking ADD CONSTRAINT fkl2dipr9m0h71koiddi8fmhtob FOREIGN KEY (id_guest) REFERENCES public.guest(id_guest);





-- public.room_price definition

-- Drop table

-- DROP TABLE public.room_price;

CREATE TABLE public.room_price (
	id_room_price int8 NOT NULL,
	end_date timestamp(6) NOT NULL,
	percentual_change float8 NOT NULL,
	start_date timestamp(6) NOT NULL,
	"type" varchar(255) NOT NULL,
	id_room int8 NULL,
	CONSTRAINT room_price_pkey PRIMARY KEY (id_room_price),
	CONSTRAINT room_price_type_check CHECK (((type)::text = ANY ((ARRAY['ALTA_ESTACAO'::character varying, 'BAIXA_ESTACAO'::character varying, 'PROMOCAO'::character varying])::text[])))
);


-- public.room_price foreign keys

ALTER TABLE public.room_price ADD CONSTRAINT fk3jy4vf4hgtjyxk87d92m48cij FOREIGN KEY (id_room) REFERENCES public.room(id_room);