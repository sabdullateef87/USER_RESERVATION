DELIMITER $$
DROP PROCEDURE IF EXISTS sp_create_new_user $$
CREATE PROCEDURE sp_create_new_user(
	in userName varchar(100),
    in firstName varchar(100),
    in lastName varchar(100),
    in email varchar(100),
    in address varchar(250),
    in phoneNumber varchar(100))
 BEGIN
 DECLARE v_user_id INT UNSIGNED DEFAULT 0;
 INSERT INTO user_table(userName, firstName, lastName, email, address, phoneNumber)
 VALUES(userName, firstName, lastName, email, address, phoneNumber);
 SET v_user_id = last_insert_id();
 SELECT * FROM user_table WHERE id = v_user_id;
 END$$
DELIMITER ;