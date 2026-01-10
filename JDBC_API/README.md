# JDBC API
- This directory contains code examples and explanations related to the JDBC (Java Database Connectivity) API. 
- JDBC is a standard Java API for connecting to relational databases and executing SQL queries.

## JDBC Drivers
- A JDBC driver is a JDBC API implementation that enables Java applications to interact with a specific database.
- There are 4 types of JDBC drivers:
  1. **JDBC-ODBC Bridge Driver:** contains a mapping of JDBC calls to ODBC calls.
  2. **Native-API Driver:** converts JDBC calls into database-specific calls using client-side libraries for databases like Oracle, PostgreSQL, MySQL etc.
  3. **Network Protocol Driver:** uses a middleware server to convert JDBC calls into database-specific calls.
  4. **Thin Driver (Pure Java Driver):** converts JDBC calls directly into database-specific calls.
- Most used and modern is Thin Driver (Pure Java Driver) as it is platform-independent and does not require any native libraries.

## JDBC Architecture
- **Application**: a Java application or servlet that uses JDBC to interact with a database.
- **JDBC API**: provides methods for connecting to a database, executing SQL queries, and processing results.
  - Interfaces like `Driver`, `ResultSet`, `PreparedStatement`, `Connection`, etc. that helps to manage database tasks.
  - Classes like `DriverManager`, `SQLException`, etc. that provide implementations for the interfaces.
- **JDBC Driver Manager**: manages a list of database drivers and establishes connections between the application and the database.
- **JDBC Drivers**: database-specific implementations that handle interactions with the database.

## JDBCTemplate
- `JdbcTemplate` is a class provided by the Spring Framework that simplifies the use of JDBC.
- It handles the creation and release of resources, such as database connections, and provides methods for executing SQL queries and updates.
- Benefits of using `JdbcTemplate`:
  - Reduces boilerplate code for database
  - Handles resource management automatically
  - Provides convenient methods for executing queries and updates

## DataSource vs DriverManager
- `DriverManager` is a basic service for managing a set of JDBC drivers. It is used to establish a connection to a database using a connection URL, username, and password.
- `DataSource` is a more advanced and flexible way to manage database connections. It provides connection pooling, distributed transactions, and is often used in enterprise applications.
- `DataSource` is preferred over `DriverManager` for production applications due to its scalability and performance benefits.
- Example: HikariCP, TomcatCP, Apache DBCP are popular connection pooling libraries that implement `DataSource`.

## What is Connection Pooling?
- Connection pooling is a technique used to improve the performance of database-driven applications by reusing a pool of established database connections.
- Instead of opening and closing a new connection for each database operation, connections are reused from a pool, reducing the overhead of establishing connections.
- Benefits of connection pooling:
  - Reduced connection creation time.
  - Improved application performance.
  - Better resource management.