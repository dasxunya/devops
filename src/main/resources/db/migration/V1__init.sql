CREATE TABLE IF NOT EXISTS flight (
id bigserial PRIMARY KEY NOT NULL,
number text NOT NULL,
origin text NOT NULL,
destination text NOT NULL,
arrival timestamptz NOT NULL,
departure timestamptz NOT NULL
);