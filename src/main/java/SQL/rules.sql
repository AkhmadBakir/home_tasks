Логическая последовательность выполнения SQL-запроса
    1.	FROM
    Определяет таблицы и представления, из которых будут извлекаться данные.
    Выполняются JOIN-ы, создаются временные таблицы.
    2.	ON
    Условие соединения таблиц при использовании JOIN. Применяется к объединённым таблицам из FROM.
    3.	JOIN
    Соединяет таблицы согласно условиям ON.
    4.	WHERE
    Фильтрует строки после объединения таблиц. Применяется до агрегации.
    5.	GROUP BY
    Группирует строки по указанным столбцам для последующих агрегатных операций (SUM(), COUNT(), и т.п.).
    6.	HAVING
    Фильтрует агрегированные группы (в отличие от WHERE, который фильтрует строки до агрегации).
    7.	SELECT
    Определяет, какие столбцы (или выражения) попадут в результат.
    8.	DISTINCT
    Удаляет дубликаты из результата (после SELECT).
    9.	ORDER BY
    Сортирует результат по одному или нескольким столбцам.
    10.	LIMIT / OFFSET
    Ограничивает количество строк и задаёт смещение.
        Пример запроса
        SELECT
            c.name AS customer_name,
            COUNT(o.id) AS total_orders
        FROM
            customers c
        JOIN
            orders o ON c.id = o.customer_id
        WHERE
            c.country = 'USA'
        GROUP BY
            c.name
        HAVING
            COUNT(o.id) > 5
        ORDER BY
            total_orders DESC
        LIMIT 10;
Что делает запрос (в порядке выполнения):
    1.	FROM customers c
    2.	JOIN orders o ON c.id = o.customer_id
    3.	WHERE c.country = 'USA'
    4.	GROUP BY c.name
    5.	HAVING COUNT(o.id) > 5
    6.	SELECT c.name, COUNT(o.id)
    7.	ORDER BY total_orders DESC
    8.	LIMIT 10
Советы
    •	Сначала продумывайте таблицы (FROM, JOIN), потом условия (WHERE, HAVING), и только потом — что выводить (SELECT).
    •	Не путайте WHERE и HAVING: WHERE — до группировки, HAVING — после.
    •	ORDER BY работает с псевдонимами, WHERE — нет.
    •	SELECT * — плохо, особенно в продакшене. Лучше указывать нужные столбцы явно.

