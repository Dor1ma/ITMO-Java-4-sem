CREATE TABLE IF NOT EXISTS owners (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255),
                        date_of_birth DATE,
                        username VARCHAR(255),
                        password VARCHAR(255),
                        role VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS cats (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255),
                      date_of_birth DATE,
                      breed VARCHAR(255),
                      color VARCHAR(255),
                      owner_id INTEGER REFERENCES owners(id)
);

CREATE TABLE IF NOT EXISTS cat_friends (
                             cat_id INTEGER REFERENCES cats(id),
                             friend_id INTEGER REFERENCES cats(id),
                             PRIMARY KEY (cat_id, friend_id)
);

