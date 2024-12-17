DROP DATABASE lab_backend4;
CREATE DATABASE lab_backend4;
USE lab_backend4;

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL
);

CREATE TABLE categories (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            is_global BOOLEAN NOT NULL,
                            user_id BIGINT,
                            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE records (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         category_id BIGINT NOT NULL,
                         created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         amount DOUBLE NOT NULL CHECK (amount > 0),
                         FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                         FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);

CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_categories_user_id ON categories(user_id);
CREATE INDEX idx_records_user_id_category_id ON records(user_id, category_id);

INSERT INTO users (username) VALUES ('john_doe'), ('jane_smith');

INSERT INTO categories (name, is_global, user_id)
VALUES ('Food', FALSE, 1),
       ('Entertainment', TRUE, NULL);

INSERT INTO records (user_id, category_id, created, amount)
VALUES (1, 1, '2024-12-01 10:30:00', 150.50),
       (1, 1, '2024-12-02 11:00:00', 75.00),
       (2, 2, '2024-12-03 12:15:00', 200.00);