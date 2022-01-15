SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;


CREATE TABLE public.dishes (
    id bigint NOT NULL,
    description character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    price double precision NOT NULL
);


ALTER TABLE public.dishes OWNER TO postgres;

CREATE SEQUENCE public.dishes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.dishes_id_seq OWNER TO postgres;

CREATE TABLE public.offices (
    id bigint NOT NULL,
    city character varying(255) NOT NULL,
    phone_number character varying(255) NOT NULL,
    post_code character varying(255) NOT NULL,
    street character varying(255) NOT NULL
);


ALTER TABLE public.offices OWNER TO postgres;

CREATE SEQUENCE public.offices_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.offices_id_seq OWNER TO postgres;


CREATE TABLE public.order_dish (
    id bigint NOT NULL,
    amount bigint,
    dish_id bigint,
    order_id bigint
);


ALTER TABLE public.order_dish OWNER TO postgres;

CREATE SEQUENCE public.order_dish_id_seq
    AS integer
    START WITH 31
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.order_dish_id_seq OWNER TO postgres;

CREATE TABLE public.discount_type(
    id bigint NOT NULL,
    name character varying(255)
);

ALTER TABLE public.discount_type OWNER TO postgres;

CREATE SEQUENCE public.discount_type_id_seq
    AS integer
    START WITH 6
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.discount_type_id_seq OWNER TO postgres;

CREATE TABLE public.orders (
    id bigint NOT NULL,
    address character varying(255),
    delivered boolean,
    discount_id bigint,
    client_id bigint,
    office_id bigint
);


ALTER TABLE public.orders OWNER TO postgres;

CREATE SEQUENCE public.orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_seq OWNER TO postgres;


CREATE TABLE public.people (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    phone_number character varying(255) NOT NULL,
    surname character varying(255) NOT NULL
);


ALTER TABLE public.people OWNER TO postgres;

CREATE SEQUENCE public.people_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.people_id_seq OWNER TO postgres;


CREATE TABLE public.roles (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.roles OWNER TO postgres;

CREATE SEQUENCE public.roles_id_seq
    AS integer
    START WITH 3
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_id_seq OWNER TO postgres;


CREATE TABLE public.users (
    id bigint NOT NULL,
    password character varying(255),
    username character varying(255),
    person_id bigint,
    role_id bigint
);


ALTER TABLE public.users OWNER TO postgres;

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

ALTER TABLE ONLY public.dishes
    ADD CONSTRAINT dishes_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.offices
    ADD CONSTRAINT offices_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.order_dish
    ADD CONSTRAINT order_dish_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.discount_type
    ADD CONSTRAINT discount_type_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.people
    ADD CONSTRAINT people_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.dishes ALTER COLUMN id SET DEFAULT nextval('public.dishes_id_seq'::regclass);

ALTER TABLE ONLY public.offices ALTER COLUMN id SET DEFAULT nextval('public.offices_id_seq'::regclass);

ALTER TABLE ONLY public.order_dish ALTER COLUMN id SET DEFAULT nextval('public.order_dish_id_seq'::regclass);

ALTER TABLE ONLY public.discount_type ALTER COLUMN id SET DEFAULT nextval('public.discount_type_id_seq'::regclass);

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);

ALTER TABLE ONLY public.people ALTER COLUMN id SET DEFAULT nextval('public.people_id_seq'::regclass);

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);

ALTER TABLE ONLY public.order_dish
    ADD CONSTRAINT fk1fevhe8ke4l3uebaotqn5ae77 FOREIGN KEY (order_id) REFERENCES public.orders(id) ON DELETE CASCADE;

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_discount_type_fkey FOREIGN KEY (discount_id) REFERENCES public.discount_type(id);


ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkojjigrbyd7qrcwrxvr7e9bdr2 FOREIGN KEY (client_id) REFERENCES public.users(id) ON DELETE CASCADE;

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkoypdvlnn1p9wj240bw078746b FOREIGN KEY (office_id) REFERENCES public.offices(id);

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fksv7wp99d6g5x8iisfpjf6sbpg FOREIGN KEY (person_id) REFERENCES public.people(id) ON DELETE CASCADE;

ALTER TABLE ONLY public.users
    ADD CONSTRAINT User_roleId_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id);


ALTER TABLE ONLY public.order_dish
    ADD CONSTRAINT fksxcogiw9xscinh77ixpor5apo FOREIGN KEY (dish_id) REFERENCES public.dishes(id);

INSERT INTO public.roles values (1, 'admin');
INSERT INTO public.roles values (2, 'client');

INSERT INTO public.discount_type values (0, 'None');
INSERT INTO public.discount_type values (1, 'Student');
INSERT INTO public.discount_type values (2, 'Veteran');
INSERT INTO public.discount_type values (3, 'Big family');
INSERT INTO public.discount_type values (4, 'Disabled');
