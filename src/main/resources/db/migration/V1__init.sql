CREATE TABLE IF NOT EXISTS flight (
id bigserial PRIMARY KEY NOT NULL,
number text NOT NULL,
origin text NOT NULL,
destination text NOT NULL
);

CREATE TABLE IF NOT EXISTS flight_history (
id bigserial PRIMARY KEY NOT NULL,
flight_id int NOT NULL,
status text NOT NULL,
arrival timestamptz DEFAULT NULL,
departure timestamptz DEFAULT NULL,
FOREIGN KEY (flight_id)  REFERENCES flight (id)
);