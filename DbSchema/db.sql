DROP SCHEMA IF EXISTS sbb CASCADE;
CREATE SCHEMA sbb;

SELECT * FROM sbb.question;
SELECT * FROM sbb.answer;

DELETE FROM sbb.question WHERE id = 1;

SELECT * FROM sbb.question WHERE id = 1;