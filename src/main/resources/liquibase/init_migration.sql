CREATE SEQUENCE IF NOT EXISTS threat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS "threat"
(
    id           BIGINT NOT NULL DEFAULT nextval('threat_id_seq') PRIMARY KEY,
    threatUUID   UUID NOT NULL,
    type    VARCHAR(255) NOT NULL,
    severity   VARCHAR(255) NOT NULL,
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
    threat_id BIGINT NOT NULL,
    title    VARCHAR(255) NOT NULL,
    status   VARCHAR(255) NOT NULL,
    notes VARCHAR(255),
    channelType VARCHAR(50) NOT NULL,
    countryId BIGINT NOT NULL,
    countryName VARCHAR(255) NOT NULL,
    primaryLangMessage TEXT,
    secondaryLangMessage TEXT,
    periodStart TIMESTAMP NOT NULL,
    periodEnd TIMESTAMP NOT NULL,
    createdAt TIMESTAMP NOT NULL DEFAULT NOW(),
    createdBy UUID NOT NULL,
    initiated TIMESTAMP,
    FOREIGN KEY (threat_id) REFERENCES threat(id)
    );


CREATE SEQUENCE IF NOT EXISTS country_threat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS "country_threat" (
    id BIGINT NOT NULL DEFAULT nextval('country_threat_id_seq') PRIMARY KEY,
    threat_id BIGINT NOT NULL,
    countryId BIGINT NOT NULL,
    countryName  VARCHAR(255) NOT NULL,
    FOREIGN KEY (threat_id) REFERENCES threat(id)
    );


CREATE SEQUENCE IF NOT EXISTS county_country_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS "county_country" (
    id BIGINT NOT NULL DEFAULT nextval('county_country_id_seq') PRIMARY KEY,
    country_threat_id BIGINT NOT NULL,
    countyId BIGINT NOT NULL,
    countyName  VARCHAR(255) NOT NULL,
    FOREIGN KEY (country_threat_id) REFERENCES country_threat(id)
    );

CREATE SEQUENCE IF NOT EXISTS broadcast_county_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS "broadcast_county" (
    id BIGINT NOT NULL DEFAULT nextval('broadcast_county_id_seq') PRIMARY KEY,
    broadcast_id BIGINT NOT NULL,
    countyId BIGINT NOT NULL,
    countyName  VARCHAR(255) NOT NULL,
    FOREIGN KEY (broadcast_id) REFERENCES broadcast(id)
    );