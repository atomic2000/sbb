DROP SCHEMA IF EXISTS sbb CASCADE;
CREATE SCHEMA sbb;

SELECT * FROM sbb.question;
SELECT * FROM sbb.answer;

DELETE FROM sbb.question WHERE id = 1;
DELETE FROM sbb.answer WHERE id = 6;

SELECT * FROM sbb.question WHERE id = 1;