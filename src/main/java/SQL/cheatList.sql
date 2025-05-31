Ключевые слова SQL — Шпаргалка
________________________________________
1. SELECT
Описание: Используется для выбора данных из базы данных.
Пример: SELECT name, age FROM users;
________________________________________
2. FROM
Описание: Определяет таблицу, из которой извлекаются данные.
Пример: SELECT * FROM employees;
________________________________________
3. WHERE
Описание: Фильтрует строки, которые соответствуют заданному условию.
Пример: SELECT * FROM products WHERE price > 100;
________________________________________
4. GROUP BY
Описание: Группирует строки, имеющие одинаковые значения в указанных столбцах. Используется с агрегатными функциями (SUM, COUNT, AVG, и т.д.).
Пример: SELECT department, COUNT(*) FROM employees GROUP BY department;
________________________________________
5. HAVING
Описание: Похож на WHERE, но применяется к результатам агрегирования после GROUP BY.
Пример: SELECT department, COUNT(*) FROM employees GROUP BY department HAVING COUNT(*) > 5;
________________________________________
6. ORDER BY
Описание: Сортирует результат по одному или нескольким столбцам.
Пример: SELECT name, salary FROM employees ORDER BY salary DESC;
________________________________________
7. LIMIT
Описание: Ограничивает количество возвращаемых строк.
Пример: SELECT * FROM customers LIMIT 10;
________________________________________
8. DISTINCT
Описание: Возвращает только уникальные значения.
Пример: SELECT DISTINCT city FROM customers;
________________________________________
9. JOIN
Описание: Объединяет строки из двух или более таблиц на основе связанного между ними поля.
•	INNER JOIN: Возвращает только совпадающие строки.
Пример: SELECT * FROM orders INNER JOIN customers ON orders.customer_id = customers.id;
•	LEFT JOIN: Все строки из левой таблицы и совпадения из правой.
Пример: SELECT * FROM customers LEFT JOIN orders ON customers.id = orders.customer_id;
•	RIGHT JOIN: Все строки из правой таблицы и совпадения из левой.
Пример: SELECT * FROM orders RIGHT JOIN customers ON orders.customer_id = customers.id;
•	FULL JOIN: Все строки из обеих таблиц.
Пример: SELECT * FROM customers FULL JOIN orders ON customers.id = orders.customer_id;
________________________________________
10. ON
Описание: Условие соединения таблиц (JOIN).
Пример: ...JOIN orders ON customers.id = orders.customer_id;
________________________________________
11. AS
Описание: Задает псевдоним (временное имя) для столбца или таблицы.
Пример: SELECT name AS customer_name FROM customers;
________________________________________
12. INSERT INTO
Описание: Вставляет новую строку в таблицу.
Пример: INSERT INTO users (name, age) VALUES ('Anna', 30);
________________________________________
13. UPDATE
Описание: Изменяет существующие строки.
Пример: UPDATE users SET age = 31 WHERE name = 'Anna';
________________________________________
14. DELETE
Описание: Удаляет строки из таблицы.
Пример: DELETE FROM users WHERE name = 'Anna';
________________________________________
15. CREATE TABLE
Описание: Создает новую таблицу.
Пример: CREATE TABLE users (id INT PRIMARY KEY, name TEXT, age INT);
________________________________________
16. ALTER TABLE
Описание: Изменяет структуру таблицы.
Пример: ALTER TABLE users ADD COLUMN email TEXT;
________________________________________
17. DROP TABLE
Описание: Удаляет таблицу вместе с её данными.
Пример: DROP TABLE users;
________________________________________
18. NULL
Описание: Представляет отсутствие значения.
Пример: SELECT * FROM users WHERE email IS NULL;
________________________________________
19. IS NULL / IS NOT NULL
Описание: Проверяет, является ли значение NULL.
Пример: SELECT * FROM orders WHERE shipped_date IS NOT NULL;
________________________________________
20. LIKE
Описание: Используется для поиска по шаблону (чаще всего с WHERE).
Пример: SELECT * FROM movies WHERE title LIKE '%Star%';
________________________________________
21. IN
Описание: Проверяет, содержится ли значение в списке.
Пример: SELECT * FROM users WHERE city IN ('Paris', 'Berlin');
________________________________________
22. BETWEEN
Описание: Проверяет, входит ли значение в диапазон.
Пример: SELECT * FROM products WHERE price BETWEEN 50 AND 100;
________________________________________
23. EXISTS
Описание: Проверяет, возвращает ли подзапрос хотя бы одну строку.
Пример: SELECT * FROM customers WHERE EXISTS (SELECT 1 FROM orders WHERE customers.id = orders.customer_id);
________________________________________
24. CASE
Описание: Условная логика (аналог if/else).
Пример: SELECT name, CASE WHEN age < 18 THEN 'Minor' ELSE 'Adult' END AS age_group FROM users;
________________________________________
25. UNION / UNION ALL
Описание: Объединяет результаты двух SELECT-запросов.
•	UNION — только уникальные строки.
•	UNION ALL — все строки, включая дубликаты.
Пример: SELECT name FROM employees UNION SELECT name FROM customers;