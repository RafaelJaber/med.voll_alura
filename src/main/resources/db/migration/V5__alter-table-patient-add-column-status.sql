ALTER TABLE TB_Patient ADD status tinyint default 1;
UPDATE TB_Patient set status = 1;