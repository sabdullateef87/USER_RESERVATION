DELIMITER $$
DROP PROCEDURE IF EXISTS sp_delete_user_by_id
$$
CREATE PROCEDURE sp_delete_user_by_id(in _id INT)
BEGIN
	select * from user_table where id = _id;
	DELETE FROM user_table WHERE id = _id;
END
$$