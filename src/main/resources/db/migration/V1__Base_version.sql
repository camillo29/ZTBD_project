--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3
-- Dumped by pg_dump version 13.3

-- Started on 2021-11-27 11:02:47

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

--
-- TOC entry 201 (class 1259 OID 26108)
-- Name: dishes; Type: TABLE; Schema: public; Owner: postgres
--

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
--
-- TOC entry 203 (class 1259 OID 26124)
-- Name: order_dish; Type: TABLE; Schema: public; Owner: postgres
--

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

--
-- TOC entry 204 (class 1259 OID 26129)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id bigint NOT NULL,
    address character varying(255),
    delivered boolean,
    total_price double precision,
    client_id bigint,
    employee_id bigint,
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
--
-- TOC entry 205 (class 1259 OID 26134)
-- Name: people; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.people (
    id bigint NOT NULL,
    e_mail character varying(255) NOT NULL,
    fired boolean,
    name character varying(255) NOT NULL,
    phone_number character varying(255) NOT NULL,
    surname character varying(255) NOT NULL,
    office_id bigint
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
--
-- TOC entry 206 (class 1259 OID 26142)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

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
--
-- TOC entry 207 (class 1259 OID 26147)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    password character varying(255),
    username character varying(255),
    person_id bigint
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
--
-- TOC entry 208 (class 1259 OID 26155)
-- Name: users_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_roles (
    user_id bigint NOT NULL,
    roles_id bigint NOT NULL
);


ALTER TABLE public.users_roles OWNER TO postgres;

--CREATE SEQUENCE public.users_roles_id_seq
--    AS integer
--    START WITH 1
--    INCREMENT BY 1
--    NO MINVALUE
--    NO MAXVALUE
--    CACHE 1;


--ALTER TABLE public.users_roles_id_seq OWNER TO postgres;

--
-- TOC entry 2883 (class 2606 OID 26115)
-- Name: dishes dishes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dishes
    ADD CONSTRAINT dishes_pkey PRIMARY KEY (id);


--
-- TOC entry 2885 (class 2606 OID 26123)
-- Name: offices offices_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offices
    ADD CONSTRAINT offices_pkey PRIMARY KEY (id);


--
-- TOC entry 2887 (class 2606 OID 26128)
-- Name: order_dish order_dish_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_dish
    ADD CONSTRAINT order_dish_pkey PRIMARY KEY (id);


--
-- TOC entry 2889 (class 2606 OID 26133)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- TOC entry 2891 (class 2606 OID 26141)
-- Name: people people_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.people
    ADD CONSTRAINT people_pkey PRIMARY KEY (id);


--
-- TOC entry 2893 (class 2606 OID 26146)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 2895 (class 2606 OID 26154)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2897 (class 2606 OID 26159)
-- Name: users_roles users_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, roles_id);

ALTER TABLE ONLY public.dishes ALTER COLUMN id SET DEFAULT nextval('public.dishes_id_seq'::regclass);

ALTER TABLE ONLY public.offices ALTER COLUMN id SET DEFAULT nextval('public.offices_id_seq'::regclass);

ALTER TABLE ONLY public.order_dish ALTER COLUMN id SET DEFAULT nextval('public.order_dish_id_seq'::regclass);

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);

ALTER TABLE ONLY public.people ALTER COLUMN id SET DEFAULT nextval('public.people_id_seq'::regclass);

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);

--ALTER TABLE ONLY public.users_roles ALTER COLUMN id SET DEFAULT nextval('public.users_roles_id_seq'::regclass);



--
-- TOC entry 2899 (class 2606 OID 26165)
-- Name: order_dish fk1fevhe8ke4l3uebaotqn5ae77; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_dish
    ADD CONSTRAINT fk1fevhe8ke4l3uebaotqn5ae77 FOREIGN KEY (order_id) REFERENCES public.orders(id);


--
-- TOC entry 2906 (class 2606 OID 26200)
-- Name: users_roles fk2o0jvgh89lemvvo17cbqvdxaa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fk2o0jvgh89lemvvo17cbqvdxaa FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 2905 (class 2606 OID 26195)
-- Name: users_roles fka62j07k5mhgifpp955h37ponj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fka62j07k5mhgifpp955h37ponj FOREIGN KEY (roles_id) REFERENCES public.roles(id);


--
-- TOC entry 2901 (class 2606 OID 26175)
-- Name: orders fkgd67qo7p9pvyabrt03jamvni5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkgd67qo7p9pvyabrt03jamvni5 FOREIGN KEY (employee_id) REFERENCES public.users(id);


--
-- TOC entry 2900 (class 2606 OID 26170)
-- Name: orders fkojjigrbyd7qrcwrxvr7e9bdr2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkojjigrbyd7qrcwrxvr7e9bdr2 FOREIGN KEY (client_id) REFERENCES public.users(id);


--
-- TOC entry 2902 (class 2606 OID 26180)
-- Name: orders fkoypdvlnn1p9wj240bw078746b; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkoypdvlnn1p9wj240bw078746b FOREIGN KEY (office_id) REFERENCES public.offices(id);


--
-- TOC entry 2903 (class 2606 OID 26185)
-- Name: people fkp7vat0x7v0dsjnme75grid8p6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.people
    ADD CONSTRAINT fkp7vat0x7v0dsjnme75grid8p6 FOREIGN KEY (office_id) REFERENCES public.offices(id);


--
-- TOC entry 2904 (class 2606 OID 26190)
-- Name: users fksv7wp99d6g5x8iisfpjf6sbpg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fksv7wp99d6g5x8iisfpjf6sbpg FOREIGN KEY (person_id) REFERENCES public.people(id);


--
-- TOC entry 2898 (class 2606 OID 26160)
-- Name: order_dish fksxcogiw9xscinh77ixpor5apo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_dish
    ADD CONSTRAINT fksxcogiw9xscinh77ixpor5apo FOREIGN KEY (dish_id) REFERENCES public.dishes(id);


-- Completed on 2021-11-27 11:02:47

--
-- PostgreSQL database dump complete
--

INSERT INTO public.roles values (1, 'admin');
INSERT INTO public.roles values (2, 'client');
