ALTER TABLE TB_Doctor ADD status tinyint default 1;
UPDATE TB_Doctor set status = 1;