DROP DATABASE IF EXISTS cadegouline;
CREATE DATABASE cadegouline;
USE cadegouline;
DROP TABLE IF EXISTS TRACK;
CREATE TABLE IF NOT EXISTS TRACK (
	ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    path_to_file TEXT NOT NULL,
    title TEXT NOT NULL,
    artist TEXT NOT NULL,
    album TEXT NOT NULL,
    featuring TEXT,
    old_or_new VARCHAR(3),
    counter INT DEFAULT(0),
    is_blocked BIT(1) DEFAULT(0)
);

DELIMITER $$
CREATE TRIGGER become_old BEFORE UPDATE ON cadegouline.TRACK
    FOR EACH ROW
BEGIN
    IF OLD.counter = 50 THEN
		SET NEW.oldOrNew = "OLD";
END IF;
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER block_a_track BEFORE UPDATE ON cadegouline.TRACK
    FOR EACH ROW
BEGIN
    IF (OLD.counter MOD 100) = 0 THEN
		SET NEW.isBlocked = true;
END IF;
END $$
DELIMITER ;

DROP TABLE IF EXISTS JINGLE;
CREATE TABLE IF NOT EXISTS JINGLE (
                                      ID BIGINT PRIMARY KEY AUTO_INCREMENT,
                                      path_to_file TEXT NOT NULL
);

DROP TABLE IF EXISTS PROGRAM;
CREATE TABLE IF NOT EXISTS PROGRAM (
                                       ID BIGINT PRIMARY KEY AUTO_INCREMENT,
                                       path_to_file TEXT NOT NULL,
                                       title TEXT NOT NULL,
                                       date_program DATETIME NOT NULL
);
