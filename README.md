Spring Boot, Spring MVC, Data JPA, H2
Три таблицы:
1.	Account – счета
2.	Transaction – проводки по счетам
3.	Join таблица

Три API:
    /api
1.	/increase-balance – увеличение баланса счета клиента
Обязательные POST параметры: accountTo, amount
2.	/reduce-balance – уменьшение баланса счета клинента
Обязательные POST параметры: accountFrom, amount.
3.	/transfer – перевод с одного счета на другой
Обязательные POST параметры: accountFrom, accountTo, amount

Интеграционные тесты: один тест на один метод
**Валидация входных параметров** (количество символов, число или нет, есть ли такой счет) **не сделана** 

Пример формата ответа: {"renewedAmount":null,"messages":["Transaction Sum: 900 greater than balance: 100.00"],"status":"ERROR"}

