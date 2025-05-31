Воронков Никита, [5/3/2025 12:18 PM]
1.
Теперь попробуйте отфильтровать данные о счетах — их хранит таблица invoice. Выгрузите из таблицы поле с суммой заказа total и поле с идентификатором покупателя customer_id. Счета должны быть оформлены в трёх городах: Дублине (англ. Dublin), Лондоне (англ. London) и Париже (англ. Paris). Информацию о городе хранит поле billing_city.

Воронков Никита, [5/3/2025 12:18 PM]
2.
Из таблицы invoice выгрузите поля total и customer_id. Значение total должно быть больше или равно 5, а значение customer_id должно равняться 40 или 46.
3.
Теперь объедините условия. Из таблицы invoice выгрузите поля total и customer_id. Счёт должен быть оформлен в Дублине (англ. Dublin), Лондоне (англ. London) или Париже (англ. Paris). Значение total должно быть больше или равно 5, а значение customer_id должно равняться 40 или 46.
4.
Чтобы выполнить задачу, программисту приходится много работать с описанием базы. В следующих заданиях не будет указаний на конкретные поля. Попробуйте найти поля сами, пользуясь схемой базы данных и её описанием.
Выгрузите фамилии и номера телефонов пользователей из таблицы client. В итоговую таблицу должны войти данные о пользователях, которые живут в США (англ. USA) или Франции (англ. France). Удостоверьтесь, что пользователь обращался в поддержку к сотруднику с идентификатором 3.
5.
Из таблицы movie выгрузите названия фильмов, стоимость аренды которых не превышает двух долларов, а срок аренды составляет больше шести дней. Выгруженные фильмы не должны относиться к рейтингам PG или PG-13.


1. SELECT total, customer_id
FROM invoice
WHERE billing_city = Dublin
OR billing_city = London
OR billing_city = Paris;

2. SELECT total, customer_id
FROM invoice
WHERE total >= 5
AND (customer_id = 40
OR customer_id = 46);

3. SELECT total, customer_id
FROM invoice
WHERE (billing_city = Dublin
OR billing_city = London
OR billing_city = Paris)
AND total >= 5
AND (customer_id = 40
OR customer_id = 46);

4. SELECT family, phone_number
FROM client
WHERE (country = 'USA'
OR country = 'France')
AND id = 3;

5. SELECT title
FROM movie
WHERE money <= 2
AND rent > 6
AND NOT rating = PG
AND NOT rating = PG-13;



Из таблицы invoice выгрузите поле billing_city с городами оформления счёта. Выгрузите только те записи, в которых на месте индекса стоит пропуск. Данные с индексами хранит поле billing_postal_code.

Дополните предыдущий запрос. Исключите из выдачи записи с пропусками в поле billing_state. Выберите записи, в которых сумма заказа в поле total не ниже 15 долларов.

Из таблицы client выгрузите имена (first_name), фамилии (last_name) и страну выставления счёта (country) для покупателей, которые не указали информацию о месте работы (company) и регионе проживания (state), а также телефон (phone) и факс (fax).


SELECT billing_city
FROM invoice
WHERE billing_postal_code IS NULL
AND billing_state IS NOT NULL
AND total >= 15;

SELECT first_name, last_name, country
FROM client
WHERE company IS NULL
AND state IS NULL
AND phone IS NULL
AND fax IS NULL;




1.
Группировка помогает сравнивать данные. Но сначала нужно получить срез. Напишите запрос, который выгрузит общую выручку (поле total) в США (англ. USA). Информацию о стране хранит поле billing_country.

2.
Теперь можно проверить, как отличаются данные по городам. Посчитайте общую выручку, количество заказов, среднюю выручку для каждого города США. Нужное поле — billing_city.



1.2. SELECT SUM(total), COUNT(id), biliing_city, AVG(total) FROM invoice WHERE billing_country = 'USA' GROUP BY biliing_city;



1.
Отберите пять самых крупных заказов из таблицы invoice и выведите всю информацию о них.

2.
Отберите пятерых самых активных клиентов в США с
25
25 мая
2011
2011 по
25
25 сентября
2011
2011. Дату хранит поле invoice_date, тип данных поля — varchar. Выведите два поля: идентификатор клиента и количество заказов.

3.
Нужно посмотреть продажи по годам. Выгрузите таблицу, в которую войдут:
год покупки;
минимальная сумма заказа;
максимальная сумма заказа;
общая сумма выручки;
количество заказов;
средняя выручка на уникального покупателя, округлённая до ближайшего целого числа.
Отсортируйте таблицу по году покупки от большего к меньшему. Отберите только те записи, в которых в поле billing_country указаны страны: США (англ. USA), Великобритания (англ. United Kingdom) и Германия (англ. Germany).1.
Отберите пять самых крупных заказов из таблицы invoice и выведите всю информацию о них.



1. SELECT * FROM invoice ORDER BY total DESC LIMIT 5;

2. SELECT customer_id, COUNT(customer_id)
FROM invoice
WHERE CAST(invoice_date AS date)
BETWEEN '2011.05.25' AND '2011.09.25'
AND billing_country = 'USA'
GROUP BY customer_id
ORDER BY COUNT(customer_id) DESC LIMIT 5;

3. SELECT EXTRACT(YEAR FROM CAST(invoice_date AS year)), MIN(total), MAX(total), SUM(total), COUNT(invoice_id), ROUND(SUM(total) / COUNT(DISTINCT(customer_id))
FROM invoice
GROUP BY EXTRACT(YEAR FROM CAST(invoice_date AS year))
ORDER BY EXTRACT(YEAR FROM CAST(invoice_date AS year)) DESC;



1.
Сравните фильмы разных возрастных рейтингов. Найдите среднее значение цены аренды фильма в поле rental_rate для каждого рейтинга (поле rating). Оставьте в таблице только те записи, в которых среднее значение rental_rate больше 3.

2.
Изучите заказы, которые оформили в сентябре 2011 года. Сравните общую сумму выручки (поле total) за каждый день этого месяца: выведите день в формате '2021-09-01' и сумму. Информацию о дате заказа хранит поле invoice_date. Не забудьте изменить тип данных в этом поле, чтобы использовать операторы для работы с датой. Оставьте в таблице только те значения суммы, которые больше 1 и меньше 10.




1.
Нужно объединить данные двух таблиц: track и invoice_line. Таблица track хранит информацию о музыкальных треках в магазине,
названия треков указаны в поле name. Таблица invoice_line содержит данные о купленных треках, их стоимость указана в
поле unit_price. В обеих таблицах есть поле track_id — в нём содержатся идентификаторы музыкальных треков.
Выгрузите таблицу, в которой названию трека будет соответствовать его стоимость. Отберите все уникальные записи.
Если какой-либо из треков не покупали или у купленного трека нет названия — такие записи не должны войти в таблицу.
Оставьте в итоговой таблице первые 20 записей.

SELECT DISTINCT t.name, il.unit_price
FROM track AS t
INNER JOIN invoice_line AS il ON t.track_id = il.track_id
LIMIT 20;

2.
Нужно дополнить запрос: добавьте поле с идентификатором плейлиста playlist_id. Такое поле можно получить из таблицы playlist_track.
В этой таблице собраны идентификаторы плейлистов и треков (поле track_id). Условие остаётся прежним: если идентификаторы
треков не совпадают во всех трёх таблицах, такие треки не должны войти в итоговую таблицу. Выведите первые 20 записей.

SELECT DISTINCT t.name, il.unit_price, pt.playlist_id
FROM track AS t
INNER JOIN invoice_line AS il ON t.track_id = il.track_id
INNER JOIN playlist_track AS pt ON il.track_id = pt.track_id
LIMIT 20;

3.
Идентификатор плейлиста теперь указан в итоговой таблице. Но что это за плейлисты — непонятно.
Эту информацию можно взять в четвёртой таблице — playlist. Таблица содержит поле playlist_id с
идентификатором плейлиста и поле name — с его названием. Добавьте в итоговую таблицу поле name с
названием плейлиста. Так как поле name уже используется, задайте для нового столбца псевдоним playlist_name.
Условия те же: данные без совпадения не должны попасть в таблицу. Ограничьте вывод первыми 20 записями.

SELECT DISTINCT t.name, il.unit_price, pt.playlist_id, p.name AS playlist_name
FROM track AS t
INNER JOIN invoice_line AS il ON t.track_id = il.track_id
INNER JOIN playlist_track AS pt ON il.track_id = pt.track_id
INNER JOIN playlist AS p ON pt.playlist_id = p.playlist_id
LIMIT 20;

4.
Теперь проанализируйте получившуюся таблицу. Нужно посчитать суммарную стоимость треков для каждого плейлиста.
Отобразите в таблице два поля: playlist_name с названием плейлиста и total_revenue с суммарной стоимостью.
Отсортируйте данные по значению в поле total_revenue от большего к меньшему.

SELECT p.name AS playlist_name, SUM(unit_price) AS total_revenue
FROM track AS t
INNER JOIN invoice_line AS il ON t.track_id = il.track_id
INNER JOIN playlist_track AS pt ON il.track_id = pt.track_id
INNER JOIN playlist AS p ON pt.playlist_id = p.playlist_id
GROUP BY playlist_name ORDER BY total_revenue DESC
LIMIT 20;

5.
Массовую любовь к музыке 90-х можно понять. А как обстоит дело с жанрами — какие популярнее? Сгруппируйте данные по
жанрам и посчитайте количество заказов. Выведите на экран два поля: одно с названием жанра, второе — с количеством
купленных треков в этом жанре. Отсортируйте таблицу по убыванию количества заказов.

SELECT g.name, COUNT(il.track_id)
FROM genre AS g
INNER JOIN track AS t ON g.genre_id = t.genre_id
INNER JOIN invoice_line AS il ON t.track_id = il.track_id
GROUP BY g.name
GROUP BY il.track_id;

6.
Отберите уникальные категории фильмов, в которых снималась Ума Вуд (англ. Uma Wood). Для связи таблиц используйте
таблицы-посредники film_category и film_actor.

SELECT DISTINCT c.name
FROM movie AS m
INNER JOIN film_category AS fc ON m.film_id = fc.film_id
INNER JOIN film_actor AS fa ON fc.film_id = fa.film_id
INNER JOIN category AS c ON fc.category_id = c.category_id
INNER JOIN actor AS a ON fa.actor_id = a.actor_id
WHERE a.first_name = 'Uma' AND a.last_name = 'WOOD';