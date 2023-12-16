CREATE TABLE IF NOT EXISTS task (
    id SERIAL PRIMARY KEY,
    description VARBINARY(250) NOT NULL,
    status BOOLEAN NOT NULL,
    email VARCHAR(150) NOT NULL
);

DROP TABLE task;

CREATE TABLE IF NOT EXISTS task (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    description VARCHAR(250) NOT NULL,
                                    status BOOLEAN NOT NULL,
                                    email VARCHAR(150) NOT NULL
);