--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3
-- Dumped by pg_dump version 13.3

-- Started on 2021-11-19 13:49:23

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
-- TOC entry 202 (class 1259 OID 17081)
-- Name: CarBrands; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."CarBrands" (
    id int NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public."CarBrands" OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 17079)
-- Name: CarBrands_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."CarBrands_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."CarBrands_id_seq" OWNER TO postgres;

--
-- TOC entry 3078 (class 0 OID 0)
-- Dependencies: 201
-- Name: CarBrands_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."CarBrands_id_seq" OWNED BY public."CarBrands".id;


--
-- TOC entry 218 (class 1259 OID 17612)
-- Name: Cars; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Cars" (
    id int NOT NULL,
    model character varying(255) NOT NULL,
    "engineCapacity" integer NOT NULL,
    "kilometersTraversed" integer NOT NULL,
    price integer NOT NULL,
    "carBrandId" integer NOT NULL,
    "fuelTypeId" integer NOT NULL,
    "gearBoxTypeId" integer NOT NULL,
    "officeId" integer NOT NULL,
    "personId" integer
);


ALTER TABLE public."Cars" OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 17610)
-- Name: Cars_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Cars_id_seq"
    AS integer
    START WITH 2
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Cars_id_seq" OWNER TO postgres;

--
-- TOC entry 3079 (class 0 OID 0)
-- Dependencies: 217
-- Name: Cars_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Cars_id_seq" OWNED BY public."Cars".id;


--
-- TOC entry 204 (class 1259 OID 17089)
-- Name: FuelTypes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."FuelTypes" (
    id int NOT NULL,
    name character varying(255)
);


ALTER TABLE public."FuelTypes" OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 17087)
-- Name: FuelTypes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."FuelTypes_id_seq"
    AS integer
    START WITH 4
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."FuelTypes_id_seq" OWNER TO postgres;

--
-- TOC entry 3080 (class 0 OID 0)
-- Dependencies: 203
-- Name: FuelTypes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."FuelTypes_id_seq" OWNED BY public."FuelTypes".id;


--
-- TOC entry 206 (class 1259 OID 17097)
-- Name: GearBoxTypes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."GearBoxTypes" (
    id int NOT NULL,
    name character varying(255)
);


ALTER TABLE public."GearBoxTypes" OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 17095)
-- Name: GearBoxTypes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."GearBoxTypes_id_seq"
    AS integer
    START WITH 4
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."GearBoxTypes_id_seq" OWNER TO postgres;

--
-- TOC entry 3081 (class 0 OID 0)
-- Dependencies: 205
-- Name: GearBoxTypes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."GearBoxTypes_id_seq" OWNED BY public."GearBoxTypes".id;


--
-- TOC entry 208 (class 1259 OID 17105)
-- Name: Offices; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Offices" (
    id int NOT NULL,
    city character varying(255) NOT NULL,
    street character varying(255) NOT NULL,
    "postCode" character varying(255),
    "phoneNumber" character varying(255) NOT NULL
);


ALTER TABLE public."Offices" OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 17103)
-- Name: Offices_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Offices_id_seq"
    AS integer
    START WITH 4
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Offices_id_seq" OWNER TO postgres;

--
-- TOC entry 3082 (class 0 OID 0)
-- Dependencies: 207
-- Name: Offices_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Offices_id_seq" OWNED BY public."Offices".id;


--
-- TOC entry 214 (class 1259 OID 17135)
-- Name: People; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."People" (
    id int NOT NULL,
    name character varying(255),
    surname character varying(255) NOT NULL,
    "eMail" character varying(255) NOT NULL,
    "phoneNumber" numeric,
    "userId" integer NOT NULL
);


ALTER TABLE public."People" OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 17133)
-- Name: People_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."People_id_seq"
    AS integer
    START WITH 3
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."People_id_seq" OWNER TO postgres;

--
-- TOC entry 3083 (class 0 OID 0)
-- Dependencies: 213
-- Name: People_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."People_id_seq" OWNED BY public."People".id;


--
-- TOC entry 210 (class 1259 OID 17116)
-- Name: Roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Roles" (
    id int NOT NULL,
    name character varying(255)
);


ALTER TABLE public."Roles" OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 17114)
-- Name: Roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Roles_id_seq"
    AS integer
    START WITH 3
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Roles_id_seq" OWNER TO postgres;

--
-- TOC entry 3084 (class 0 OID 0)
-- Dependencies: 209
-- Name: Roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Roles_id_seq" OWNED BY public."Roles".id;


--
-- TOC entry 216 (class 1259 OID 17203)
-- Name: UserRoles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."UserRoles" (
    id int NOT NULL,
    "userId" integer,
    "roleId" integer
);


ALTER TABLE public."UserRoles" OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 17201)
-- Name: UserRoles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."UserRoles_id_seq"
    AS integer
    START WITH 3
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."UserRoles_id_seq" OWNER TO postgres;

--
-- TOC entry 3085 (class 0 OID 0)
-- Dependencies: 215
-- Name: UserRoles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."UserRoles_id_seq" OWNED BY public."UserRoles".id;


--
-- TOC entry 212 (class 1259 OID 17124)
-- Name: Users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Users" (
    id int NOT NULL,
    "eMail" character varying(255) NOT NULL,
    password character varying(255) NOT NULL
);


ALTER TABLE public."Users" OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 17122)
-- Name: Users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Users_id_seq"
    AS integer
    START WITH 3
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Users_id_seq" OWNER TO postgres;

--
-- TOC entry 3086 (class 0 OID 0)
-- Dependencies: 211
-- Name: Users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Users_id_seq" OWNED BY public."Users".id;


--
-- TOC entry 2906 (class 2604 OID 17084)
-- Name: CarBrands id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."CarBrands" ALTER COLUMN id SET DEFAULT nextval('public."CarBrands_id_seq"'::regclass);


--
-- TOC entry 2914 (class 2604 OID 17615)
-- Name: Cars id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Cars" ALTER COLUMN id SET DEFAULT nextval('public."Cars_id_seq"'::regclass);


--
-- TOC entry 2907 (class 2604 OID 17092)
-- Name: FuelTypes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."FuelTypes" ALTER COLUMN id SET DEFAULT nextval('public."FuelTypes_id_seq"'::regclass);


--
-- TOC entry 2908 (class 2604 OID 17100)
-- Name: GearBoxTypes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."GearBoxTypes" ALTER COLUMN id SET DEFAULT nextval('public."GearBoxTypes_id_seq"'::regclass);


--
-- TOC entry 2909 (class 2604 OID 17108)
-- Name: Offices id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Offices" ALTER COLUMN id SET DEFAULT nextval('public."Offices_id_seq"'::regclass);


--
-- TOC entry 2912 (class 2604 OID 17138)
-- Name: People id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."People" ALTER COLUMN id SET DEFAULT nextval('public."People_id_seq"'::regclass);


--
-- TOC entry 2910 (class 2604 OID 17119)
-- Name: Roles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Roles" ALTER COLUMN id SET DEFAULT nextval('public."Roles_id_seq"'::regclass);


--
-- TOC entry 2913 (class 2604 OID 17206)
-- Name: UserRoles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."UserRoles" ALTER COLUMN id SET DEFAULT nextval('public."UserRoles_id_seq"'::regclass);


--
-- TOC entry 2911 (class 2604 OID 17127)
-- Name: Users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Users" ALTER COLUMN id SET DEFAULT nextval('public."Users_id_seq"'::regclass);


--
-- TOC entry 2918 (class 2606 OID 17086)
-- Name: CarBrands CarBrands_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."CarBrands"
    ADD CONSTRAINT "CarBrands_pkey" PRIMARY KEY (id);


--
-- TOC entry 2934 (class 2606 OID 17620)
-- Name: Cars Cars_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Cars"
    ADD CONSTRAINT "Cars_pkey" PRIMARY KEY (id);


--
-- TOC entry 2920 (class 2606 OID 17094)
-- Name: FuelTypes FuelTypes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."FuelTypes"
    ADD CONSTRAINT "FuelTypes_pkey" PRIMARY KEY (id);


--
-- TOC entry 2922 (class 2606 OID 17102)
-- Name: GearBoxTypes GearBoxTypes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."GearBoxTypes"
    ADD CONSTRAINT "GearBoxTypes_pkey" PRIMARY KEY (id);


--
-- TOC entry 2924 (class 2606 OID 17113)
-- Name: Offices Offices_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Offices"
    ADD CONSTRAINT "Offices_pkey" PRIMARY KEY (id);


--
-- TOC entry 2930 (class 2606 OID 17143)
-- Name: People People_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."People"
    ADD CONSTRAINT "People_pkey" PRIMARY KEY (id);


--
-- TOC entry 2926 (class 2606 OID 17121)
-- Name: Roles Roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Roles"
    ADD CONSTRAINT "Roles_pkey" PRIMARY KEY (id);


--
-- TOC entry 2932 (class 2606 OID 17208)
-- Name: UserRoles UserRoles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."UserRoles"
    ADD CONSTRAINT "UserRoles_pkey" PRIMARY KEY (id);


--
-- TOC entry 2928 (class 2606 OID 17132)
-- Name: Users Users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Users"
    ADD CONSTRAINT "Users_pkey" PRIMARY KEY (id);


--
-- TOC entry 2938 (class 2606 OID 17621)
-- Name: Cars Cars_carBrandId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Cars"
    ADD CONSTRAINT "Cars_carBrandId_fkey" FOREIGN KEY ("carBrandId") REFERENCES public."CarBrands"(id);


--
-- TOC entry 2939 (class 2606 OID 17626)
-- Name: Cars Cars_fuelTypeId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Cars"
    ADD CONSTRAINT "Cars_fuelTypeId_fkey" FOREIGN KEY ("fuelTypeId") REFERENCES public."FuelTypes"(id);


--
-- TOC entry 2940 (class 2606 OID 17631)
-- Name: Cars Cars_gearBoxTypeId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Cars"
    ADD CONSTRAINT "Cars_gearBoxTypeId_fkey" FOREIGN KEY ("gearBoxTypeId") REFERENCES public."GearBoxTypes"(id);


--
-- TOC entry 2941 (class 2606 OID 17636)
-- Name: Cars Cars_officeId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Cars"
    ADD CONSTRAINT "Cars_officeId_fkey" FOREIGN KEY ("officeId") REFERENCES public."Offices"(id);


--
-- TOC entry 2942 (class 2606 OID 17641)
-- Name: Cars Cars_personId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Cars"
    ADD CONSTRAINT "Cars_personId_fkey" FOREIGN KEY ("personId") REFERENCES public."People"(id);


--
-- TOC entry 2935 (class 2606 OID 17144)
-- Name: People People_userId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."People"
    ADD CONSTRAINT "People_userId_fkey" FOREIGN KEY ("userId") REFERENCES public."Users"(id);


--
-- TOC entry 2937 (class 2606 OID 17214)
-- Name: UserRoles UserRoles_roleId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."UserRoles"
    ADD CONSTRAINT "UserRoles_roleId_fkey" FOREIGN KEY ("roleId") REFERENCES public."Roles"(id);


--
-- TOC entry 2936 (class 2606 OID 17209)
-- Name: UserRoles UserRoles_userId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."UserRoles"
    ADD CONSTRAINT "UserRoles_userId_fkey" FOREIGN KEY ("userId") REFERENCES public."Users"(id);


-- Completed on 2021-11-19 13:49:23

INSERT INTO public."CarBrands" (id, name) values (1, 'BMW');
INSERT INTO public."CarBrands" (id, name) values (2, 'Hyundai');
INSERT INTO public."CarBrands" (id, name) values (3, 'Volvo');
--
INSERT INTO public."FuelTypes" values (1, 'Gasoline');
INSERT INTO public."FuelTypes" values  (2, 'Natural gas');
INSERT INTO public."FuelTypes" values  (3, 'Diesel');
--
INSERT INTO public."GearBoxTypes" values (1, 'Automatic');
INSERT INTO public."GearBoxTypes" values (2, '6 way');
INSERT INTO public."GearBoxTypes" values (3, '5 way');
--
INSERT INTO public."Offices" values (1, 'Cracow', 'Krakowska', '30-000', '123456789');
INSERT INTO public."Offices" values (2, 'Warszawa', 'Warszawska', '03-887', '123738592');
INSERT INTO public."Offices" values (3, 'Poznań', 'Poznańska', '60-008', '975372536');
--
INSERT INTO public."Roles" values (1, 'admin');
INSERT INTO public."Roles" values (2, 'client');
--
INSERT INTO public."Users" values (1, 'jan_kowalski@wp.pl', '$2b$08$66MXaeX7xzueXuosFr5QfeXveseA.iQJ40tH0QYSClxF9H9yjgWeW');
INSERT INTO public."Users" values (2, 'Krzysztof_Nowak@onet.pl', '$2b$08$g9V4kCf6bL.Pg5GWlO0V3eNMipRXq4fum1Doko.V0x4fZSJJILPGK');
--
INSERT INTO public."UserRoles" values (1, 1, 1);
INSERT INTO public."UserRoles" values (2, 1, 2);
INSERT INTO public."UserRoles" values (3, 2, 2);
--
INSERT INTO public."People" values (1, 'Jan', 'Kowalski', 'jan_kowalski@wp.pl', '163571325', 1);
INSERT INTO public."People" values (2, 'Krzysztof', 'Nowak', 'Krzysztof_Nowak@onet.pl', '746376921', 2);
--
INSERT INTO public."Cars" values (1, 'v1', '200', '10000', '120000', 1, 1, 2, 1, 2);
-- PostgreSQL database dump complete
--

