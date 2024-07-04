-- Insert dummy data into Quiz table
INSERT INTO quiz (title, description) VALUES ('Java Basics', 'A quiz on basic Java concepts');
INSERT INTO quiz (title, description) VALUES ('Spring Framework', 'A quiz on Spring Framework');

-- Insert dummy data into Question table
INSERT INTO question (text, quiz_id) VALUES ('What is Java?', 1);
INSERT INTO question (text, quiz_id) VALUES ('What is Spring Boot?', 2);

-- Insert dummy data into Option table
INSERT INTO option (text, is_correct, question_id) VALUES ('A programming language', true, 1);
INSERT INTO option (text, is_correct, question_id) VALUES ('A coffee brand', false, 1);
INSERT INTO option (text, is_correct, question_id) VALUES ('A Java-based framework', true, 2);
INSERT INTO option (text, is_correct, question_id) VALUES ('A type of database', false, 2);
