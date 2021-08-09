select * from points_event;

CREATE SEQUENCE public.pointsevent_seq
    INCREMENT 1
    START 1000
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.pointsevent_seq
    OWNER TO postgres;


insert into points_event(id,event_date,num_of_times) values( nextval('pointsevent_seq'), now(), 3);
insert into points_event(id,event_date,num_of_times) values( nextval('pointsevent_seq'), now(), 4);

delete from points_event where id < 1000;