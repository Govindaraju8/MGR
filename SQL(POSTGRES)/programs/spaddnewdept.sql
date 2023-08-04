CREATE or replace PROCEDURE spAddNewDept(
 dno int,dnm VARCHAR(50),lc VARCHAR(50),td out int)
AS $$
BEGIN
     INSERT INTO dept VALUES (dno,dnm,lc);
     select count(*) into td from dept;
END;
$$ language plpgsql;
