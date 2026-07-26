--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5
-- Dumped by pg_dump version 14.5

-- Started on 2026-07-26 16:53:19

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
-- TOC entry 209 (class 1259 OID 42220)
-- Name: project; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.project (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    actual_start_date timestamp(6) without time zone,
    actual_end_date timestamp(6) without time zone,
    category_id integer NOT NULL,
    status_id integer NOT NULL,
    planned_start_date timestamp without time zone NOT NULL,
    planned_end_date timestamp without time zone NOT NULL,
    description character varying(255)
);


ALTER TABLE public.project OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 42317)
-- Name: project_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.project ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.project_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 214 (class 1259 OID 42345)
-- Name: task; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.task (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    project_id bigint NOT NULL,
    parent_task_id bigint,
    total_required_man_hour double precision NOT NULL,
    completed_man_hour double precision NOT NULL,
    status_id integer,
    actual_start_date timestamp without time zone,
    actual_end_date timestamp without time zone,
    category_id integer,
    planned_start_date timestamp without time zone NOT NULL,
    planned_end_date timestamp without time zone NOT NULL,
    priority integer NOT NULL
);


ALTER TABLE public.task OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 42344)
-- Name: task_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.task ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.task_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 210 (class 1259 OID 42251)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role_id integer
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 42285)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public."user" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3320 (class 0 OID 42220)
-- Dependencies: 209
-- Data for Name: project; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.project (id, name, actual_start_date, actual_end_date, category_id, status_id, planned_start_date, planned_end_date, description) FROM stdin;
51	Smart Greenhouse System	\N	\N	0	1	2026-08-15 09:00:00	2026-12-01 18:00:00	IoT-based greenhouse monitoring system.
52	CRM System	\N	\N	1	0	2026-07-01 09:00:00	2026-10-01 18:00:00	Customer relationship management system.
55	Smart Parking System	\N	\N	0	2	2026-08-20 09:00:00	2026-12-20 18:00:00	IoT solution for smart parking management.
57	Food Delivery App	\N	\N	2	0	2026-05-10 09:00:00	2026-08-25 18:00:00	Online food ordering and delivery application.
59	Smart Irrigation System	\N	\N	0	2	2026-08-22 09:00:00	2026-12-30 18:00:00	Automated irrigation system using IoT sensors.
62	Image Classification	\N	\N	3	0	2026-04-01 09:00:00	2026-07-20 18:00:00	Deep learning project for image classification.
63	Energy Monitoring System	\N	\N	0	1	2026-09-05 09:00:00	2027-01-15 18:00:00	IoT-based energy consumption monitoring.
64	Library Management System	\N	\N	1	0	2026-05-20 09:00:00	2026-08-30 18:00:00	Digital library management platform.
54	Fraud Detection Model	\N	\N	3	0	2026-06-15 09:00:00	2026-09-15 18:00:00	AI model for detecting fraudulent transactions.
48	E-Commerce Platform	\N	\N	1	2	2026-08-01 09:00:00	2026-11-15 18:00:00	Development of a modern e-commerce web application.
49	Smart Home Mobile App	\N	\N	2	1	2026-08-10 09:00:00	2026-10-20 18:00:00	Mobile application for controlling smart home devices.
50	Customer Segmentation	\N	\N	3	2	2026-08-05 09:00:00	2026-09-30 18:00:00	Machine learning project for customer segmentation.
53	Fitness Tracker	\N	\N	2	2	2026-08-12 09:00:00	2026-11-30 18:00:00	Cross-platform fitness tracking application.
56	HR Management Portal	\N	\N	1	1	2026-09-01 09:00:00	2026-12-15 18:00:00	Human resources management web portal.
58	Sales Forecasting	\N	\N	3	1	2026-08-18 09:00:00	2026-11-18 18:00:00	Machine learning model for sales forecasting.
60	Booking System	\N	\N	1	2	2026-08-07 09:00:00	2026-10-25 18:00:00	Online reservation management platform.
61	Mobile Banking App	\N	\N	2	2	2026-08-11 09:00:00	2026-12-05 18:00:00	Secure mobile banking application.
65	Travel Planner	\N	\N	2	1	2026-09-10 09:00:00	2026-12-20 18:00:00	Mobile application for travel planning.
66	Sentiment Analysis ..	2026-07-26 16:33:25.564983	2026-07-26 16:33:56.503408	3	2	2026-08-03 09:00:00	2026-10-18 18:00:00	Natural language processing project for sentiment analysis.
\.


--
-- TOC entry 3325 (class 0 OID 42345)
-- Dependencies: 214
-- Data for Name: task; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.task (id, name, project_id, parent_task_id, total_required_man_hour, completed_man_hour, status_id, actual_start_date, actual_end_date, category_id, planned_start_date, planned_end_date, priority) FROM stdin;
48	subtask	66	47	0	0	2	2026-07-26 16:33:51.752396	2026-07-26 16:33:51.753394	0	2026-07-26 13:02:17.918	2026-07-26 13:02:17.918	1
47	task1	66	\N	1	0	2	2026-07-26 16:33:25.564983	2026-07-26 16:33:56.501407	0	2026-07-26 13:02:17.918	2026-07-26 13:02:17.918	1
41	q	25	\N	0	0	1	2026-07-26 16:18:51.039047	\N	0	2026-07-26 13:02:17.918	2026-07-26 13:02:17.918	1
29	qqsds	17	\N	10	0	0	\N	\N	0	2025-08-31 12:06:18.609	2025-08-31 12:06:18.609	1
31	22	17	29	11	0	0	\N	\N	0	2025-08-31 12:06:18.609	2025-08-31 12:06:18.609	2
33	11	10	\N	10	10	2	2025-09-21 17:34:25.549746	2025-09-21 17:34:25.549746	0	2025-09-21 14:33:18.852	2025-09-21 14:33:18.852	1
32	22	17	29	1	0	2	\N	\N	0	2025-08-31 12:15:33.639	2025-08-31 12:15:33.639	1
36		18	\N	0	0	0	\N	\N	0	2026-07-26 13:02:17.918	2026-07-26 13:02:17.918	1
44	fg	26	42	0	0	2	2026-07-26 16:22:02.131717	2026-07-26 16:22:10.636066	0	2026-07-26 13:02:17.918	2026-07-26 13:02:17.918	1
43	df	26	42	0	0	2	2026-07-26 16:21:29.38234	2026-07-26 16:22:18.784036	0	2026-07-26 13:02:17.918	2026-07-26 13:02:17.918	2
38	oo	18	35	0	0	0	\N	\N	0	2026-07-26 13:02:17.918	2026-07-26 13:02:17.918	1
42	l	26	\N	0	0	2	2026-07-26 16:20:52.955618	2026-07-26 16:22:29.518359	0	2026-07-26 13:02:17.918	2026-07-26 13:02:17.918	1
37	ıııı	18	35	6	0	0	\N	\N	0	2026-07-26 13:02:17.918	2026-07-26 13:02:17.918	2
35	ttpp	18	\N	2	2	1	2026-07-26 16:06:35.656139	\N	0	2026-07-26 13:02:17.918	2026-07-26 13:02:17.918	1
40	oo	23	39	1	1	1	2026-07-26 16:16:08.225955	\N	0	2026-07-26 13:02:17.918	2026-07-26 13:02:17.918	1
39	t	23	\N	0	0	1	2026-07-26 16:17:18.720544	\N	0	2026-07-26 13:02:17.918	2026-07-26 13:02:17.918	1
45	uu	27	\N	0	0	2	2026-07-26 16:24:30.474329	2026-07-26 16:24:48.041035	0	2026-07-26 13:02:17.918	2026-07-26 13:02:17.918	1
46	jj	27	45	0	0	2	2026-07-26 16:24:35.932088	2026-07-26 16:27:00.825201	0	2026-07-26 13:02:17.918	2026-07-26 13:02:17.918	1
\.


--
-- TOC entry 3321 (class 0 OID 42251)
-- Dependencies: 210
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (id, name, password, role_id) FROM stdin;
4	string	J1fLPK/DmvRRq7Jpe+ebSrYdY9dNhbBBhinejCaBG1KfPzeA0BUAY/9Vor7udMTsECoqJzGh8ffxDUc60Ypqhw==	5
5	read	7gIcWqlMVfHbvihyAGGNOGeZ8hzk41r3HJ50dCZ+uvX95UNupE1onIq9nbsk522pST+YJFPK2YfRygA/nrnvNA==	0
6	modify	tsDk8Ow58+3c2/73VhFzWcrF2HnDUHowlgjjT7UBsGNFl0kmuVLnSDmVtcBEVtGljqMiATsCHu7V21SQHpuG0w==	1
\.


--
-- TOC entry 3331 (class 0 OID 0)
-- Dependencies: 212
-- Name: project_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.project_id_seq', 67, true);


--
-- TOC entry 3332 (class 0 OID 0)
-- Dependencies: 213
-- Name: task_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.task_id_seq', 48, true);


--
-- TOC entry 3333 (class 0 OID 0)
-- Dependencies: 211
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_seq', 6, true);


--
-- TOC entry 3175 (class 2606 OID 42226)
-- Name: project project_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_pkey PRIMARY KEY (id);


--
-- TOC entry 3179 (class 2606 OID 42349)
-- Name: task task_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_pkey PRIMARY KEY (id);


--
-- TOC entry 3177 (class 2606 OID 42257)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 3180 (class 2606 OID 42350)
-- Name: task parent_task_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT parent_task_id_fkey FOREIGN KEY (parent_task_id) REFERENCES public.task(id) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2026-07-26 16:53:19

--
-- PostgreSQL database dump complete
--

