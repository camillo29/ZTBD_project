# ZTBD_project
- Java 11
- JavaFX 11
- Flyway
- MongoAPI

----Polish/Polski----

Projekt na Zaawansowane Technologie Baz Danych mający na celu napisanie aplikacji porównującej czas wykonania funkcji CRUD dla PostgreSQL i MongoDB.

Instrukcja uruchomienia
1. Należy zainstalować bazę danych PostgreSQL oraz MongoDB
2. Za pomocą narzędzia pgAdmin założyć bazę danych dla PostgreSQL
3. Za pomocą narzędzia MongoCompass lub zwykłego mongosh założyć użytkownika oraz właczyć weryfikacje użytkowników w pliku konfiguracyjnym dla MongoDB.
	Przykład jak to zrobić: https://medium.com/mongoaudit/how-to-enable-authentication-on-mongodb-b9e8a924efac
4. Otworzyć projekt w IntelliJ, maven powinien zaciągnąć wszystkie potrzebne zależności.
5. Stworzyć nową konfigurację uruchomienia jeśli potrzeba. (App to klasa main)
6. Uruchomić aplikację.

----English/Angielski----

Project for Advanced Database Technologies class focused on implementing an app that will allow comparing CRUD functions execution time of PostgreSQL and MongoDB.

Instructions for running the application.
1. Install PostgreSQL and MongoDB databases.
2. With pgAdmin tool create PostgreSQL database.
3. With MongoCompass or mongosh create user and enable user authentication in config file for MongoDB.
	Example how to do it: https://medium.com/mongoaudit/how-to-enable-authentication-on-mongodb-b9e8a924efac
4. Open project in IntelliJ, maven should download all necessary dependencies.
5. Create new Run Configuration if needed. (App is main class)
6. Run the application.
