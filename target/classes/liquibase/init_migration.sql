CREATE SEQUENCE event_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS "event"
(
    id           BIGINT NOT NULL DEFAULT nextval('event_id_seq') PRIMARY KEY,
    title    VARCHAR(255) NOT NULL,
    status   VARCHAR(255) NOT NULL,
    country  VARCHAR(255) NOT NULL,
    county   VARCHAR(255) NOT NULL,
    periodStart TIMESTAMP NOT NULL,
    periodEnd TIMESTAMP NOT NULL,
    englishMsg TEXT,
    swahiliMsg TEXT,
    amharicMsg TEXT,
    arabicMsg TEXT,
    somaliMsg TEXT,
    frenchMsg TEXT,
    oromoMsg TEXT,
    tigrinyaMsg TEXT,
    lugandaMsg TEXT,
    kinyarwandaMsg TEXT,
    kirundiMsg TEXT,
    karamojongMsg TEXT,
    pokotMsg TEXT,
    nuerAndDinkaMsg TEXT,
    createdAt TIMESTAMP NOT NULL,
    updatedAt TIMESTAMP NOT NULL,
    updatedBy UUID
    );

INSERT INTO public."event"
( title, status, country, county, periodstart, periodend, englishMsg, createdat, "updatedat", updatedby)
VALUES( 'LIGHT_RAINFALL', 'DRAFT', 'Kenya', 'Western', TO_TIMESTAMP('2024-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-01-07 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
       'A powerful storm system is moving across the region, creating conditions favorable for tornadoes. Wind speeds could reach up to 80 mph, accompanied by large hail and frequent lightning. Heavy rainfall could also lead to flash flooding in low-lying areas.',
       NOW(), NOW(), 'a3af8add-0665-407e-a4a1-b61c42aacc11'::uuid);


