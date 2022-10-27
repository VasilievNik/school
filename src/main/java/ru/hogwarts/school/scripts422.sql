CREATE TABLE users (
    user_name TEXT PRIMARY KEY,
    user_age TEXT,
    user_license BOOLEAN,
    user_car TEXT
);

CREATE TABLE cars (
    car_name TEXT,
    car_model TEXT ,
    car_cost INTEGER
);

SELECT users.user_name, users.user_age, cars.car_name, cars.car_model
FROM cars
    INNER JOIN cars ON users.user_car = cars.car_name





SELECT student.name, student.age, student.avatar_id, student.faculty_name, faculty.name
FROM faculty
    INNER JOIN faculty ON student.faculty_name = faculty.name

SELECT student.name, student.age, student.avatar_id, student.faculty_name, faculty.name
FROM faculty
    INNER JOIN faculty ON student.faculty_name = faculty.name 