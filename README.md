# **# Selenium tests in Docker**


### **TASK:**

Используя любой язык программирования необходимо написать следующие автотесты для сайта https://www.w3schools.com/sql/trysql.asp?filename=trysql_select_all
1. Вывести все строки таблицы Customers и убедиться, что запись с ContactName равной 'Giovanni Rovelli' имеет Address = 'Via Ludovico il Moro 22'.
2. Вывести только те строки таблицы Customers, где city='London'. Проверить, что в таблице ровно 6 записей.
3. Добавить новую запись в таблицу Customers и проверить, что эта запись добавилась.
4. Обновить все поля (кроме CustomerID) в любой записи таблицы Customers и проверить, что изменения записались в базу.
5. Придумать собственный автотест и реализовать (тут все ограничивается только вашей фантазией).
   Заполнить поле ввода можно с помощью js кода, используя объект window.editor.
   Требования:
- Для реализации обязательно использовать Selenium WebDriver
- Тесты должны запускаться в docker контейнере
- Код автотестов нужно выложить в любой git-репозиторий



### **INFO:**

1) run tests: mvn clean test |  docker compose up -d
2) open docker debug UI for chrome: http://localhost:7900/  OR http://localhost:4444/ui#  
3) The folder "allure-results" contains docker allure result. Open the folder via Explorer and execute
<allure serve> command.
NOTE: need to install allure to the host machine.
4) Execute locally allure report: <mvn allure:serve>