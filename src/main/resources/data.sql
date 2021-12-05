DELETE FROM authority;
INSERT INTO authority (id, name)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO  authority (id, name)
VALUES (2, 'ROLE_USER');

DELETE FROM user;
INSERT INTO user (id, first_name, last_name, username, password, dob, email, gender)
VALUES (1, 'Nikola', 'Sandelić', 'nsandelic', '$2a$12$xgvMVz8FqeIwLo9slKStNO5zNP9G2oIkBTkZqmHKNumlOjYU17RHu', '1996-06-20', 'nikola.sandelic@hotmail.com', 'Male');
INSERT INTO user (id, first_name, last_name, username, password, dob, email, gender)
VALUES (2, 'Josh', 'Smith', 'jsmith', '$2a$12$Z.bMnG2CXTYsGzcd6LLNYOBRIpWwDUU7BxqOq51FMp0Q2EDzBlXL.', '1990-07-15', 'jsmith@gmail.com', 'Male');
INSERT INTO user (id, first_name, last_name, username, password, dob, email, gender)
VALUES (3, 'John', 'Doe', 'jdoe', '$2a$12$Z.bMnG2CXTYsGzcd6LLNYOBRIpWwDUU7BxqOq51FMp0Q2EDzBlXL.', '1992-10-25', 'jdoe@gmail.com', 'Male');
INSERT INTO user (id, first_name, last_name, username, password, dob, email, gender)
VALUES (4, 'Anna', 'Willson', 'awillson', '$2a$12$Z.bMnG2CXTYsGzcd6LLNYOBRIpWwDUU7BxqOq51FMp0Q2EDzBlXL.', '1995-01-30', 'awillson@gmail.com', 'Female');
INSERT INTO user (id, first_name, last_name, username, password, dob, email, gender)
VALUES (5, 'Jennie', 'Johnson', 'jjohnson', '$2a$12$Z.bMnG2CXTYsGzcd6LLNYOBRIpWwDUU7BxqOq51FMp0Q2EDzBlXL.', '1989-05-10', 'jjohnson@gmail.com', 'Female');




DELETE FROM user_authority;
INSERT INTO user_authority (user_id, authority_id)
VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id)
VALUES (1, 2);
INSERT INTO user_authority (user_id, authority_id)
VALUES (2, 2);
INSERT INTO user_authority (user_id, authority_id)
VALUES (3, 2);
INSERT INTO user_authority (user_id, authority_id)
VALUES (4, 2);
INSERT INTO user_authority (user_id, authority_id)
VALUES (5, 2);