## FactSheet (Technologies, Techniques, other Stuff)
### Tools
* Linux environment
* intelliJ ultimate
* PostgreSQL (PG) Core
* pgadmin
* JDBC Driver for PG
* optional: 
  * DBeaver
  * Postman
  
* Dia for the ER-models and diagrams

...to be continued and linked ;)


Windows: Download one of the Releases from this link: https://www.postgresql.org/download/.
After that, follow the instructions and keep everything checked. At the end of the Installation, you will
get asked for launching Stack-Builder, check this box. There you choose your Installation of PostgreSQL and check under Database drivers the pgJDBC Driver. 

You know have successfully installed PostgreSQL, pgAdmin4 and the JDBC Driver for PG. Now Profit.


Ubuntu: Create the file "/etc/apt/sources.list.d/pgdg.list" and add this line:
        "deb http://apt.postgresql.org/pub/repos/apt/ placeholder-pgdg main"
Replace "placeholder" with your version, i.e. eoan, bionic, etc. Then, Import the repository signing key, and update the package lists,
by excuting (1), (2) and (3):
(1) wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add -
(2) sudo apt-get update
(3) apt-get install postgresql-11 postgresql-client-11 pgadmin4

After that, switch your role to postgres by "sudo su postgres" and connect with "psql -U postgres postgres".
Now Profit.

- Things to add: JDBC Driver vor PG in Ubuntu?
- How the fuck get i out of the postgres role? i don'r remind specifiy it, neither the password lul.

        
JDBC Driver: 
(1) Download the "....jar" File from the Postgres Site or Locate It in your Postgres folder.
(2) Start a new Project with an IDE of your Choice
(3) Configue the Project in its settings: Add the .jar als Library Ressource
(4) Profit. XD

Now you can use "import java.sql.*" to get Access to Database-Related Functions
Here`s how you Initialize the Connection:

Connection connection = DriverManager.getConnection("jdbc:postgres:databasename" , "Username", "Password");

After that you can start to write Database Statements:
        
        
