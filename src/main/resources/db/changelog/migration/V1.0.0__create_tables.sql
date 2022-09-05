CREATE TABLE students
(
    id              SERIAL PRIMARY KEY,
    first_name      VARCHAR,
    last_name       VARCHAR,
    university      VARCHAR,
    test1_score     NUMERIC,
    test2_score     NUMERIC,
    test3_score     NUMERIC,
    test4_score     NUMERIC
);