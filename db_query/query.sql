-- Create the database
CREATE
DATABASE IF NOT EXISTS company_employee;

-- Use the database
USE
company_employee;

-- Create the company table
CREATE TABLE IF NOT EXISTS company
(
    id
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
) NOT NULL,
    country VARCHAR
(
    255
)
    );

-- Create the employee table
CREATE TABLE IF NOT EXISTS employee
(
    id
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
) NOT NULL,
    surname VARCHAR
(
    255
) NOT NULL,
    email VARCHAR
(
    255
) NOT NULL,
    company_id INT NOT NULL,
    pic_name VARCHAR
(
    255
),
    FOREIGN KEY
(
    company_id
) REFERENCES company
(
    id
)
    );

-- Create the user table
CREATE TABLE IF NOT EXISTS user
(
    id
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
) NOT NULL,
    surname VARCHAR
(
    255
) NOT NULL,
    email VARCHAR
(
    255
) NOT NULL,
    password VARCHAR
(
    255
) NOT NULL,
    type ENUM
(
    'ADMIN',
    'USER'
) NOT NULL
    );
