INSERT INTO USER VALUES (nextval('user_sequence'), sysdate(), 'Bob');
INSERT INTO USER VALUES (nextval('user_sequence'), sysdate(), 'Brad');
INSERT INTO USER VALUES (nextval('user_sequence'), sysdate(), 'Simon');

INSERT INTO POST VALUES (nextval('post_sequence'), 'Bob 1st Post', 1);
INSERT INTO POST VALUES (nextval('post_sequence'), 'Bob 2nd Post', 1);
