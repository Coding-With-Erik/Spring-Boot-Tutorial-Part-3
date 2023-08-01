ALTER TABLE book DROP COLUMN author;

CREATE TABLE author(
    id serial primary key,
    name varchar(200) not null
);

CREATE TABLE book_author(
    book_id integer references book(id) on delete cascade not null,
    author_id integer references author(id) on delete cascade not null,
    unique(book_id, author_id)
);