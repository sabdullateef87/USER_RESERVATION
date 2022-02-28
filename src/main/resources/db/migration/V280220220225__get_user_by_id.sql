USE user_reservation_db;
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_get_user_by_id
$$
CREATE PROCEDURE sp_get_user_by_id(in _id INT)
BEGIN
	SELECT * FROM user_table WHERE id = _id;
END
$$