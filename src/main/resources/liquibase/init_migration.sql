CREATE SEQUENCE IF NOT EXISTS threat_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS "threat"
(
    id           BIGINT NOT NULL DEFAULT nextval('threat_seq') PRIMARY KEY,
    type    VARCHAR(255) NOT NULL,
    severity   VARCHAR(255) NOT NULL,
    status   VARCHAR(255) NOT NULL, --in progress/ broadcasted/ checked/ unchehecked
    country  VARCHAR(255) NOT NULL,
    county   VARCHAR(255) NOT NULL,
    subCounty  VARCHAR(255) NOT NULL,
    range   VARCHAR(255) NOT NULL,
    notes VARCHAR(255),
    periodStart TIMESTAMP NOT NULL,
    periodEnd TIMESTAMP NOT NULL,
    createdAt TIMESTAMP NOT NULL
);


CREATE SEQUENCE broadcast_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS "broadcast"
(
    id           BIGINT NOT NULL DEFAULT nextval('broadcast_id_seq') PRIMARY KEY,
    broadcastUUID UUID NOT NULL,
    title    VARCHAR(255) NOT NULL,
    status   VARCHAR(255) NOT NULL,
    country  VARCHAR(255) NOT NULL,
    county   VARCHAR(255) NOT NULL,
    periodStart TIMESTAMP NOT NULL,
    periodEnd TIMESTAMP NOT NULL,
    englishMsg TEXT,
    swahiliMsg TEXT,
    createdAt TIMESTAMP NOT NULL DEFAULT NOW()
    );
