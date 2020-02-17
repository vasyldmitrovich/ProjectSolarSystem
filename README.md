- 1 Open this project, in IntelliJIDEA Ultimate Edition.
- 2 Install MySql: https://www.mysqltutorial.org/install-mysql/
- 3 Connecting to a database: https://www.jetbrains.com/help/idea/connecting-to-a-database.html#
- 4 On schemas click right mouse button - Run Sql Script... and
choice /resources/init_db_schemas.sql 
- 5 After that run main method  in package org.solarsystem.web.controller.PlanetController.java
- 7 Configuration TomCat from Шаг 2. Конфигурируем Intellij IDEA для Deploy 
https://devcolibri.com/intellij-idea-%D0%B4%D0%B5%D0%BF%D0%BB%D0%BE%D0%B9-%D0%BD%D0%B0-tomcat/
- 8 Click Edit Configuration - on the left-side click - plus - TomCat
Local - on the right-side Deployment - plus - Artifact - ProjectSolarSystem_war
Application context set "/"
- 9 Add folders css and js (with bootstrap and jquery) 
click click - External Source and add folders css fonts and js
- 9 Otherwise ...
