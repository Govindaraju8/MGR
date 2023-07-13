-- Create table king_emp as select * from ramu_emp;

-- CREATE TABLE king_emp (
--   empno INT,
--   ename VARCHAR(255),
--   job VARCHAR(255),
--   mgr INT,
--   hiredate DATE,
--   sal DECIMAL(8,2),
--   comm DECIMAL(8,2),
--   dept_no INT
-- );

-- INSERT INTO king_emp (empno, ename, job, mgr, hiredate, sal, comm, dept_no)
-- VALUES
--   (7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20', 1600.0, 300.0, 20),
--   (7521, 'WARD', 'SALESMAN', 7698, '1981-02-22', 1250.0, 500.0, 30),
--   (7566, 'JONES', 'MANAGER', 7839, '1981-04-02', 2975.0, NULL, 20),
--   (7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28', 1250.0, 1400.0, 30),
--   (7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01', 2850.0, NULL, 30),
--   (7782, 'CLARK', 'MANAGER', 7839, '1981-06-09', 2450.0, NULL, 10),
--   (7788, 'SCOTT', 'ANALYST', 7566, '1982-12-09', 3000.0, NULL, 20),
--   (7839, 'KING', 'PRESIDENT', NULL, '1981-11-17', 5000.0, NULL, 10),
--   (7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08', 1500.0, 0.0, 30),
--   (7876, 'ADAMS', 'CLERK', 7788, '1983-01-12', 1100.0, NULL, 20),
--   (7900, 'JAMES', 'CLERK', 7698, '1981-12-03', 950.0, NULL, 30),
--   (7902, 'FORD', 'ANALYST', 7566, '1981-12-03', 3000.0, NULL, 20),
--   (7934, 'MILLER', 'CLERK', 7782, '1982-01-23', 1300.0, NULL, 10),
--   (1234, 'SMITH', 'CLERK', 7902, '1980-12-17', 800.0, NULL, 20),
--   (7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20', 1600.0, 300.0, 30),
--   (7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20', 1600.0, 300.0, 10)

select * from king_emp;


-- INSERT INTO public.king_emp(
-- 	empno, ename, job, mgr, hiredate, sal, comm, dept_no)
-- 	VALUES (7499,'ALLEN', 'SALESMAN', 7698,'1981-02-20', 2000.00, 220.00, 10);

-- 1. List the employee numbers, name, job title and hiredate of the employees in department 10. 
-- select empno,ename,job,hiredate from king_emp where dept_no=10;

-- 2. Select the name & salary of all employees who are working as clerks. 
-- select ename,sal from king_emp where job='CLERK'

-- 3. List the names, job title & salary of employees who were hired on December 17, 1980. 
-- select ename,job, sal from king_emp where hiredate = '1980-12-17';

-- 4. Select the name, salary, and commission of employees whose commission is greater than their salary. 
-- select ename,sal,comm from king_emp where comm>sal

-- 5. Display the names, monthly-salary, daily-salary, and hourly-salary for all the employees. 
-- SELECT ename, sal as monthly_salary, sal/30 AS daily_salary, sal/30/8 AS hourly_salary
-- FROM king_emp;

-- 6. Assume that salary column in the table is monthly salary, and the number of working days 
-- are 22 and the number of working hours per day are 8. Rename the columns as MONTHLY, 
-- DAILY, 
-- AND HOURLY. 
-- SELECT ename, sal as monthly_salary, sal/22 AS daily_salary, sal/22/8 AS hourly_salary FROM king_emp;

-- 7. List the names and employee numbers of Managers who earn more than 2600/-. Display in 
-- alphabetical order of their names. 
-- select ename,empno from king_emp where job='MANAGER' order by ename;

-- 8. Select the information about Managers and President from the column job in the EMP 
-- table.Order the result by name
-- select empno from king_emp where job='MANAGER' or job='PRESIDENT' order by ename;

-- 9. List the employee names that don’t end in ‘S’. 
-- select ename from king_emp where ename not like '%S';

-- 10. List the employee names that start with ‘C’. 
-- select ename from king_emp where ename  like 'C%';

-- 11. List the name, job and department numbers of those employees whose names fall in the 
-- alphabetical range of ‘C’ to ‘F’. 
-- select ename,job,dept_no from king_emp where ename between 'C' and 'F' order by ename;

-- 12.Display the list of employee names starting with ‘I’ and ending with ‘R’. 
-- select ename from king_emp where ename like ('I%R')

-- 13. Display the employee names which have ‘TII’ or ‘LL’ in them. 
-- select ename from king_emp where ename LIKE '%TII%' OR ename LIKE '%LL%'

-- 14. Display the list of employees who were hired during 1983. 
-- select * from king_emp where EXTRACT(YEAR FROM hiredate) = 1983;

-- 15. Display the data shown as follows 
--  Smith has held the position of clerk in department number 20 since 13-JUNE-83. 
--  Allen has held the position of clerk in department number 20 since 13-JUNE-83. 
--  Ward has held the position of clerk in department number 20 since 13-JUNE-8
-- SELECT CONCAT(ename, ' has held the position of ', job, ' in department number ', dept_no, ' since ', TO_CHAR(hiredate, 'DD-MON-YY')) AS data
-- FROM king_emp
-- WHERE job = 'CLERK' AND dept_no = 20;


-- 16. List all rows from EMP table by converting the NULL values in COMM column to 0 (use NVL 
-- command) 
-- SELECT empno, ename, job, COALESCE(comm, 0) AS comm, dept_no
-- FROM king_emp;

-- 17. Write a query that will accept a given job title and displays all the records according to that 
-- title . 
-- \set given_job_title 'Manager'

-- SELECT *
-- FROM Employees
-- WHERE job = :'given_job_title';

-- 18. List all employees who don’t get any commission.
-- SELECT *
-- FROM king_emp
-- WHERE comm IS NULL OR comm = 0;

-- 19. Show that length names appear in the EMP table . Eliminate the duplicate lengths from the 
-- rows returned. Do not show the name themselves.
-- SELECT DISTINCT LENGTH(ename) AS name_length
-- FROM king_emp;

-- 20. List the names and hire-date of employees in department number 20. Display the hire-date 
-- formatted as ‘12-03-81’.
-- select ename,TO_CHAR(hiredate, 'DD-MM-YY') from king_emp where dept_no=20
-- select ename,TO_CHAR(hiredate, 'DD-MON-YY') from king_emp where dept_no=20

-- 21. How many months has the President worked for the company? Round to the nearest whole 
-- number of months. 
-- SELECT ROUND(EXTRACT(MONTH FROM AGE(CURRENT_DATE, hiredate))/12 * 12) AS months_worked
-- FROM king_emp
-- WHERE job = 'PRESIDENT';

-- 22. List the names of all the employees whose hire-date anniversary is in the month of 
-- December. 
-- SELECT ename
-- FROM king_emp
-- WHERE EXTRACT(MONTH FROM hiredate) = 12;

-- 23. List the employee names, jobs, and a job classification which you will supply. Translate the 
-- value stored in each 
-- job field(CLERK,MANAGER ETC.) to job classification number (1,2 etc). translate CLERK to 
-- 1, MANAGER to 3, 
-- PRESIDENT to 5 and all the other jobs to 2. Name the job the classification column 
-- JOBCLASS.

-- SELECT Ename, job,
--   CASE
--     WHEN job = 'CLERK' THEN 1
--     WHEN job = 'MANAGER' THEN 3
--     WHEN job = 'PRESIDENT' THEN 5
--     ELSE 2
--   END AS jobclass
-- FROM king_emp;

-- 24. Give SQL commands to find the average annual salary per job in each department. The sal 
-- figures in the EMP table 
--  are for each month.
-- SELECT job, dept_no, AVG(sal * 12) AS avg_annual_salary
-- FROM king_emp
-- GROUP BY job, dept_no;

-- 25. In one query, count the number of people in department 30 who receive a salary and the 
-- number of people who receive commission . 
-- SELECT 
--   COUNT(CASE WHEN sal IS NOT NULL THEN 1 END) AS count_salary,
--   COUNT(CASE WHEN comm IS NOT NULL THEN 1 END) AS count_commission
-- FROM king_emp
-- WHERE dept_no = 30;

-- 26. Compute the average, minimum and maximumsalaries of those groups of employees 
-- having the job of clerk or manager. 
-- SELECT job, AVG(sal) AS avg_salary, MIN(sal) AS min_salary, MAX(sal) AS max_salary
-- FROM king_emp
-- WHERE job IN ('CLERK', 'MANAGER')
-- GROUP BY job;

-- 27. Display the department numbers where more than 2 Clerks or 3 Managers are working. 
-- SELECT dept_no
-- FROM king_emp
-- WHERE job = 'CLERK' OR job = 'MANAGER'
-- GROUP BY dept_no
-- HAVING COUNT(CASE WHEN job = 'CLERK' THEN 1 END) > 2 OR COUNT(CASE WHEN job = 'MANAGER' THEN 1 END) > 3;

-- 28. Calculate the total compensation expense for each department for `one year. The SAL and 
-- COMM figures in EMP table are for a month. Assume that employees who don’t earn a 
-- commission, receive non-monetary benefits that are worth $100.00 a month. 
-- SELECT dept_no, SUM((sal + COALESCE(comm, 0) + CASE WHEN comm IS NULL THEN 100 ELSE 0 END) * 12) AS total_compensation
-- FROM king_emp
-- GROUP BY dept_no;

-- 29. Write a query to select the duplicated rows. 
-- select ename,empno,job,hiredate from king_emp group by ename,empno,job,hiredate having count(*)>1

-- 30. Write a query to find out the nth maximum salary. 
-- select sal FROM (
--   SELECT sal, ROW_NUMBER() OVER (ORDER BY sal DESC) AS row_num
--   FROM king_emp
-- ) AS subquery
-- WHERE row_num = 2(n value);

-- 31. Who was the employee hired in all the departments. 
-- SELECT ename
-- FROM king_emp
-- GROUP BY ename
-- HAVING COUNT(DISTINCT dept_no) = (SELECT COUNT(DISTINCT dept_no) FROM king_emp);

-- 32. How many employees work in New York. (NO DATA)

-- 33. Write a query to display as follows: 
--  Ename Date hired 
--  ****** ********* 
--  Smith June,thirteen 1983 
--  Jones October, thirty first 1983
-- SELECT 
--   ename, 
--   TO_CHAR(hiredate, 'FMMonth, DDth YYYY') AS date_hired
-- FROM 
--   king_emp;

-- 34. Print the list of employees, display just salary if the salary is more than 1500, if exactly 1500 
-- display on target, if less 
--  than 1500 display below 1500. 
-- SELECT 
--   ename,
--   CASE
--     WHEN sal > 1500 THEN 'More than 1500'
--     WHEN sal = 1500 THEN 'On target'
--     ELSE 'Below 1500'
--   END AS salary_status
-- FROM 
--   king_emp;


-- 35. Display as follows- employees hired on or before 15th of any month are paid on the last 
-- Friday of that month, those hired after the 15th are paid on the last Friday of the following 
-- month.. 
-- Print a list of employees, their hire-date and first pay date, sort on hire-date.

 
-- 36. Write query to list salary, (sal-avg(sal)) from emp table. 
-- SELECT sal, sal - AVG(sal) OVER () AS difference
-- FROM king_emp;

-- 37. Find the number of different employees and the number of different departments.
-- SELECT 
--   COUNT(DISTINCT empno) AS num_employees,
--   COUNT(DISTINCT dept_no) AS num_departments
-- FROM king_emp;

-- 38. Determine the average salary of employees. 
-- select avg(sal) from king_emp;

-- 39. List the department numbers, department names, location, local commission paid and 
-- total salary of each department. 
-- SELECT
--   dept_no,
--   SUM(COALESCE(comm, 0)) AS local_commission_paid,
--   SUM(sal) AS total_salary
-- FROM
--   king_emp
-- GROUP BY
--   dept_no;

-- 40. Display the average monthly salary bill for each job type within a department.
-- SELECT
--   dept_no,
--   job,
--   AVG(sal) AS average_monthly_salary_bill
-- FROM
--   king_emp
-- GROUP BY
--   dept_no,job;

-- 41. Display only those jobs where minimum salary is greater than or equal to 3000. 
-- select job from king_emp group by job having min(sal)>=3000;

-- 42. Find the average salary and total remuneration for each job class, remember salesmen earn 
-- commission .
-- SELECT  job, AVG(sal) AS average_salary,
--   SUM(COALESCE(sal, 0) + COALESCE(comm, 0)) AS total_remuneration
-- FROM king_emp  GROUP BY job;


-- 43. Find out the difference between the highest and lowest salary. 
-- select max(sal) - min(sal) as difference from king_emp;

-- 44. Find all the departments having more than 3 employees. 
-- SELECT dept_no, COUNT(*) AS num_employees
-- FROM king_emp  GROUP BY dept_no HAVING COUNT(*) > 3;

-- 45. Check whether all the employee numbers are indeed unique.
-- SELECT 
--   CASE 
--     WHEN COUNT(*) = COUNT(DISTINCT empno) THEN 'All employee numbers are unique'
--     ELSE 'Employee numbers are not unique'
--   END AS uniqueness_check
-- FROM 
--   king_emp;


-- 46. List the lowest paid employees working for each manager, exclude any group where the 
-- minimum salary is less than 1000, sort out the result by salary. 
-- SELECT mgr, MIN(sal) AS lowest_salary, empno, ename
-- FROM king_emp
-- WHERE sal >= 1000
-- GROUP BY mgr, empno, ename
-- ORDER BY lowest_salary;

-- 47. Display all employee names, department numbers, and department names. 

-- SELECT
--   ename,
--   dept_no
-- FROM
--   king_emp;

-- 48. Display all employee names and their department names in the order of their department 
-- names. 
-- 
-- SELECT
-- ename,
-- dept_no
-- FROM
--   king_emp
-- ORDER BY
--   dept_no;

-- 49. Display the department that has no employees. 

-- SELECT dept_no FROM king_emp
-- WHERE dept_no  NOT IN (SELECT DISTINCT dept_no FROM king_emp);

-- 50. Find all the employees who joined the company before their manager. 
-- SELECT
--   e1.empno AS employee_id,
--   e1.ename AS employee_name,
--   e1.hiredate AS employee_join_date,
--   e2.empno AS manager_id,
--   e2.ename AS manager_name,
--   e2.hiredate AS manager_join_date
-- FROM
--   king_emp e1
-- JOIN
--   king_emp e2 ON e1.mgr = e2.empno
-- WHERE
--   e1.hiredate < e2.hiredate;

-- 51. Find the employees who earn more than the lowest salary in each department. 
-- SELECT
--   e.empno,
--   e.ename,
--   e.sal,
--   e.dept_no
-- FROM
--   king_emp e
-- WHERE
--   e.sal > (SELECT MIN(sal) FROM king_emp WHERE dept_no = e.dept_no);

-- 52. Display the employees who earn more than the lowest salary in department 30. 
-- SELECT
--   empno,
--   ename,
--   sal,
--   dept_no
-- FROM
--   king_emp
-- WHERE
--   sal> (SELECT MIN(sal) FROM king_emp WHERE dept_no = 30) AND dept_no = 30;

-- 53. Find the employees who earn than every employee in department 30. 
-- SELECT
--   empno,
--   ename,
--   sal,
--   dept_no
-- FROM
--   king_emp
-- WHERE
--   sal > ALL (SELECT sal FROM king_emp WHERE dept_no = 30);

-- 54. Find the job with highest paid salary. 
-- SELECT
--   job
-- FROM
--   king_emp
-- WHERE
--   sal= (SELECT MAX(sal) FROM king_emp);

-- 55. Display the name, job, hire-date for all those employees whose salary is greater than the 
-- highest salary in each sales department. 
-- SELECT
--   e.ename AS employee_name,
--   e.job,
--   e.hiredate AS hire_date
-- FROM
--   king_emp e
-- WHERE
--   e.sal > ALL (SELECT MAX(sal) FROM king_emp WHERE dept_no = (SELECT dept_no FROM king_emp WHERE job = 'SALES'));


-- 56. Copy all information on department 10 from EMP table to another table.

-- CREATE TABLE another_emp AS
-- SELECT *
-- FROM king_emp
-- WHERE dept_no = 10;

-- 57. Write a query to list all the columns ,datatypes of the given table.

-- SELECT column_name, data_type
-- FROM information_schema.columns
-- WHERE table_name = 'another_emp';

-- 58. Write a query to list all the employees who are earning the least 3 salaries

-- SELECT *
-- FROM king_emp
-- ORDER BY sal
-- LIMIT 3;

-- 59. Write a query to list the deptnos which are having the highest max salaray 

-- SELECT dept_no
-- FROM king_emp
-- GROUP BY dept_no
-- HAVING MAX(sal) = (SELECT MAX(sal) FROM king_emp);


-- 60. Write a query to list last 10 rows from the table in which number of rows are not known
-- SELECT *
-- FROM king_emp
-- ORDER BY job DESC
-- LIMIT 10;




































































