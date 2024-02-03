# **# Selenium W3School tests in Docker**

## **Задача:**

Используя любой язык программирования необходимо написать следующие автотесты для сайта
https://www.w3schools.com/sql/trysql.asp?filename=trysql_select_all

1. Вывести все строки таблицы Customers и убедиться, что запись с ContactName равной 'Giovanni Rovelli' имеет
   Address = 'Via Ludovico il Moro 22'.
2. Вывести только те строки таблицы Customers, где city='London'. Проверить, что в таблице ровно 6 записей.
3. Добавить новую запись в таблицу Customers и проверить, что эта запись добавилась.
4. Обновить все поля (кроме CustomerID) в любой записи таблицы Customers и проверить, что изменения записались в базу.
5. Придумать собственный автотест и реализовать (тут все ограничивается только вашей фантазией).
   Заполнить поле ввода можно с помощью js кода, используя объект window.editor.

#### **Требования:**

- Для реализации обязательно использовать Selenium WebDriver
- Тесты должны запускаться в docker контейнере
- Код автотестов нужно выложить в любой git-репозиторий



_**#######################################################################################################**_


## **Решение задачи:**


#### _Общая инормация:_

1) Сайт https://www.w3schools.com/sql/ использует устаревший Web SQL Database standard который деактивирован по
   умолчанию
   в последних версиях Chrome браузера. Для запуска тестов используется докер образ _**Chrome ver: 113.0.5672.63**_.

- https://groups.google.com/a/chromium.org/g/blink-dev/c/fWYb6evVA-w/m/pziWcvboAgAJ?pli=1
- https://developer.chrome.com/blog/deprecating-web-sql?hl=ru

2) Пятый тест проверяет _**DELETE SQL**_ сценарий чтобы проверить основыне CRUD
   сценарии (https://ru.wikipedia.org/wiki/CRUD)

**_---------------------------------------------------------------------------------------------------------------_**

#### _Предусловия:_



1) Для просмотра выполняемых тестов в UI, откройте ссылку

   http://localhost:7999/

   (или IP адрес вашей хост машины где установлен docker, вместо localhost), где

   7999 порт - noVnc порт (Дебаг тестов в браузере. Если опция активирована, то дебаг будет работать только если
   запущена 1 нода браузера!)

Общая информация по запущенныи нодам Selenium Grid: http://localhost:4444/ui#

2) После окончания работы тестов в контейнере в корневой папке проекта/папке запуска docker-compose файла
   появятся папки:

- _**allure-results**_: папка с allure отчетом по тестам. Для запуска генерации HTML отчета нужно установить allure на
  вашу машину (https://allurereport.org/docs/gettingstarted-installation/) и выполнить команду из корневой папки:

  **_`allure serve`_**


- _**logs**_: папка с логами (app.log файл)


**_---------------------------------------------------------------------------------------------------------------_**


#### _Запуск тестов **[remote version]**:_



1) Запустить файл [_**docker-compose.yml**_]  из любой папки пк/корневой папки проекта командой:

**_`docker compose up`_**

или в фоновом режиме:

**_`docker compose up -d`_**

2) Дождаться завершения тестов - docker контейнер с именем [**_tests-container_**] будет  неактивен


3) Запустить отчет allure/проверить app.log файл.


4) Проверить по необходимости логи контейнера командой из корневой папки:

**_`docker compose logs test-runner`_**


**_---------------------------------------------------------------------------------------------------------------_**


#### _Запуск тестов **[local version]**:_



1) Запустить файл [_**docker-compose-dev.yml**_]  из любой папки пк/корневой папки проекта командой:

**_`docker compose -f docker-compose-dev.yml up`_**

или в фоновом режиме:

**_`docker compose -f docker-compose-dev.yml up`_**

2) Дождаться завершения тестов - docker контейнер с именем [**_tests-container_**] будет  неактивен


3) Запустить отчет allure/проверить app.log файл.


4) Проверить по необходимости логи контейнера командой:

**_`docker compose logs test-runner`_**