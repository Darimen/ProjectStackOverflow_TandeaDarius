
drop table if exists votes;
drop table if exists questions_to_tags;
drop table if exists tags;
drop table if exists answer;
drop table if exists questions;
drop table if exists "user";

CREATE TABLE "user" (
    u_id BIGINT PRIMARY KEY,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    score INTEGER NOT NULL,
    banned BOOLEAN DEFAULT FALSE NOT NULL,
    email VARCHAR NOT NULL,
    phone_nr VARCHAR,
    phone_pf VARCHAR,
    surname VARCHAR,
    lastname VARCHAR,
    is_mod BOOLEAN DEFAULT FALSE NOT NULL,
    created_at TIMESTAMP NOT NULL,
    last_update TIMESTAMP NOT NULL
);

CREATE TABLE questions (
    q_id BIGINT PRIMARY KEY,
    author_id BIGINT NOT NULL,
    title VARCHAR NOT NULL,
    text VARCHAR NOT NULL,
    date TIMESTAMP NOT NULL,
    picture VARCHAR,
    last_update TIMESTAMP NOT NULL,
    visible BOOLEAN DEFAULT TRUE NOT NULL,
    FOREIGN KEY (author_id) REFERENCES "user" (u_id)
);

CREATE TABLE answer (
    a_id BIGINT PRIMARY KEY,
    author_id BIGINT NOT NULL,
    q_id BIGINT NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    text VARCHAR NOT NULL,
    picture VARCHAR,
    last_update TIMESTAMP NOT NULL,
    visible BOOLEAN DEFAULT TRUE NOT NULL,
    FOREIGN KEY (author_id) REFERENCES "user" (u_id),
    FOREIGN KEY (q_id) REFERENCES questions (q_id)
);

CREATE TABLE tags (
    tag_id BIGINT PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE questions_to_tags (
    q_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    FOREIGN KEY (q_id) REFERENCES questions (q_id),
    FOREIGN KEY (tag_id) REFERENCES tags (tag_id)
);

CREATE TABLE votes (
    v_id BIGINT PRIMARY KEY,
    u_id BIGINT NOT NULL,
    q_id BIGINT,
    a_id BIGINT,
    val SMALLINT NOT NULL,
    FOREIGN KEY (u_id) REFERENCES "user" (u_id),
    FOREIGN KEY (q_id) REFERENCES questions (q_id),
    FOREIGN KEY (a_id) REFERENCES answer (a_id)
);







Insert Into public."user" (u_id, username, password, score, email, created_at, last_update)
    Values (1, 'admin', 'admin', 0, 'admin@admin.com', now(), now());
Insert Into public."user" (u_id, username, password, score, email, created_at, last_update)
    Values (2, 'username', 'parola_parola', 0, 'user@admin.com', now(), now());
Insert Into public."user" (u_id, username, password, score, email, created_at, last_update)
    Values (3, 'moderator', 'moderator', 0, 'mod_@mod.ro', now(), now());


Insert Into questions (q_id, author_id, title, text, date, last_update)
    Values (1, 2, 'Title', 'Text', now(), now());
Insert Into questions (q_id, author_id, title, text, date, last_update)
    Values (2, 2, 'Title2', 'Text2', now(), now());


Insert Into answer (a_id, author_id, q_id, creation_date, text, last_update)
    Values (1, 2, 1, now(), 'Text', now());
Insert Into answer (a_id, author_id, q_id, creation_date, text, last_update)
    Values (2, 2, 1, now(), 'Text2', now());

Insert into votes(v_id, u_id, q_id, a_id, val)
    Values (1, 3, 1, NULL, 1);
Insert into votes(v_id, u_id, q_id, a_id, val)
    Values (2, 3, NULL, 1, 1);
Insert into votes(v_id, u_id, q_id, a_id, val)
    Values (3, 3, 1, NULL, -1);

Insert Into tags (tag_id, name)
    Values (1, 'tag1');
Insert Into tags (tag_id, name)
    Values (2, 'tag2');

INSERT INTO questions_to_tags(q_id, tag_id)
    VALUES (1, 1);
INSERT INTO questions_to_tags(q_id, tag_id)
    VALUES (2, 1);
INSERT INTO questions_to_tags(q_id, tag_id)
    VALUES (2, 2);
