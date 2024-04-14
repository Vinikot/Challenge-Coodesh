select * from employees;
select * from salaries;

--Query que retorna a quantidade de funcionários separados por sexo.
SELECT gender, COUNT(*) AS employee_count
FROM employees
GROUP BY gender;

--Query que retorna a quantidade de funcionários distintos por sexo, ano e ano de nascimento.
SELECT e.gender, YEAR(e.birth_date) AS birth_year, YEAR(e.hire_date) AS hire_year, COUNT(DISTINCT e.emp_no) AS distinct_employee_count
FROM employees AS e
JOIN salaries AS s ON e.emp_no = s.emp_no
GROUP BY e.gender, YEAR(e.birth_date), YEAR(e.hire_date);

--Query que retorna a média, min e max de salário por sexo.
SELECT gender, AVG(salary) AS avg_salary, MIN(salary) AS min_salary, MAX(salary) AS max_salary
FROM employees
JOIN salaries ON employees.emp_no = salaries.emp_no
GROUP BY gender;