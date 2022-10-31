DROP SCHEMA IF EXISTS mockmvc;
CREATE SCHEMA mockmvc;
USE mockmvc;

CREATE TABLE house_assignment(
  id INT AUTO_INCREMENT NOT NULL,
  house VARCHAR(255),
  wing VARCHAR(255),
  assigned_bed INT,
  PRIMARY KEY(id)
);

CREATE TABLE student (
  id BIGINT AUTO_INCREMENT NOT NULL,
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  house_assignment_id INT,
  PRIMARY KEY(id),
  FOREIGN KEY (house_assignment_id) REFERENCES house_assignment(id)
);

CREATE TABLE spell (
	id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255),
    level VARCHAR(50),
    PRIMARY KEY(id)
);

CREATE TABLE students_cast_spells (
	id INT AUTO_INCREMENT NOT NULL,
	student_id BIGINT,
    spell_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (spell_id) REFERENCES spell(id)
);

CREATE TABLE user (
	id INT AUTO_INCREMENT NOT NULL,
    username VARCHAR(255),
    password VARCHAR(255),
    PRIMARY KEY(id)
);

CREATE TABLE role (
	id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255),
    user INT,
    PRIMARY KEY(id),
    FOREIGN KEY (user) REFERENCES user(id)
);

INSERT INTO house_assignment (house, wing, assigned_bed) VALUES
( 'GRYFFINDOR', 'NORTH', 52),
( 'SLYTHERIN', 'SOUTH', 14),
( 'GRYFFINDOR', 'SOUTH', 24);

INSERT INTO student (first_name, last_name, house_assignment_id) VALUES
( 'Harry', 'Potter', 23),
( 'Draco', 'Malfoy', 24),
( 'Hermione', 'Granger', 25);