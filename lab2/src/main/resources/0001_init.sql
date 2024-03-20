CREATE TABLE owners (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255),
                        date_of_birth DATE
);

CREATE TABLE cats (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255),
                      date_of_birth DATE,
                      breed VARCHAR(255),
                      color VARCHAR(255),
                      owner_id INTEGER REFERENCES owners(id)
);

CREATE TABLE cat_friends (
                             cat_id INTEGER REFERENCES cats(id),
                             friend_id INTEGER REFERENCES cats(id),
                             PRIMARY KEY (cat_id, friend_id)
);
