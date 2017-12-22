Spring Boot, Spring MVC, Data JPA, H2
Три таблицы:
1.	Person – клиенты
2.	Account – счета
3.	Join таблица

Исходя из задания можно было сделать просто таблицу со счетами без использования клиента и поиска среди его счетов, переданного как параметр, но хотелось попробовать связи таблиц через JPA.

Три API:
1.	/increase-balance – увеличение баланса счета клиента
Обязательные POST параметры: personIdTo, accountNumberTo, sum
2.	/reduce-balance – уменьшение баланса счета клинента
Обязательные POST параметры: personIdFrom, accountNumberFrom, sum.
3.	/transaction – перевод с одного счета на другой
Обязательные POST параметры: personIdFrom, accountNumberFrom, accountNumberTo, sum


Пример формата ответа: {"renewedAmount":null,"message":"Transaction Sum: 900 greater than balance: 100.00","status":"ERROR"}

