DELIMITER $$
CREATE DEFINER=`CSCI5308_8_DEVINT_USER`@`%` PROCEDURE `spGetCourse`(IN courseID VARCHAR(40))
BEGIN
SELECT * FROM Courses WHERE Courses.CourseID=courseID;
END$$

DELIMITER ;