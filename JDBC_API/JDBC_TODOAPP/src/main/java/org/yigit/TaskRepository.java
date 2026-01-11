package org.yigit;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TaskRepository will handle all database operations related to tasks, such as adding, updating, deleting, and retrieving tasks.
public class TaskRepository {

    private static DataSource getDataSource() {
        // Create a HikariDataSource without try-with-resources so that it remains open
        // if db file not exists, it will be created automatically in the project root directory
        // AUTO_SERVER=TRUE allows multiple connections to the same database from different processes
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:h2:./todo;AUTO_SERVER=TRUE"); // set the JDBC URL for H2 database
        if(hikariDataSource.isClosed()) {
            System.out.println("DataSource is closed.");
        } else {
            System.out.println("DataSource is open.");
        }
        return hikariDataSource;
    }

    public static void initializeDatabase() {
        try (Connection con = getDataSource().getConnection()) {
            String createTableSQL = """
                        create table if not exists task (
                            id identity primary key,
                            name varchar(255) not null
                        )
                        """;
            // identity is auto-incrementing primary key in H2 database
            var statement = con.createStatement(); // create a statement object to execute SQL queries
            statement.execute(createTableSQL); // execute the create table SQL
        } catch (SQLException e) {
            throw new RuntimeException("Error initializing database", e);
        }
    }

    // PreparedStatement is used when you want to execute the same SQL multiple times with different parameters. faster for repeated queries
    // Safe against SQL injection attacks as parameters are set separately

    public static void addTask(Task task) {
        // try-with-resources to ensure connection is closed automatically
        try(Connection con = getDataSource().getConnection()) {
            String insertSQL = "insert into task (name) values (?)";
            var ps = con.prepareStatement(insertSQL);
            ps.setString(1, task.getName()); // getter for task name
            ps.execute();
            System.out.println("Task " + task.getName() + " added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding task: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void updateTask(Task task) {
        try(Connection con = getDataSource().getConnection()) {
            String updateSQL = "update task set name = ? where id = ?";
            var ps = con.prepareStatement(updateSQL);
            ps.setString(1, task.getName());
            ps.setInt(2, task.getId());
            ps.executeUpdate();
            System.out.println("Task with id " + task.getId() + " updated successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error in updating table", e);
        }
    }

    public static void deleteTask(Task task) {
        try(Connection con = getDataSource().getConnection()) {
            String deleteSQL = "delete from task where id = ?";
            var ps = con.prepareStatement(deleteSQL);
            ps.setInt(1, task.getId());
            ps.executeUpdate();
            System.out.println("Task with id " + task.getId() + " deleted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error in deleting task from table", e);
        }
    }

    public static void deleteAll() {
        try(Connection con = getDataSource().getConnection()) {
            String deleteAllSQL = "delete from task";
            var s = con.createStatement();
            s.executeUpdate(deleteAllSQL);
            System.out.println("All tasks deleted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error in deleting all tasks from table", e);
        }
    }

    // returns a list of all tasks in the database
    public static List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try (Connection con = getDataSource().getConnection()) {
            String selectAllSQL = "select * from task";
            var s = con.createStatement();

            // executeQuery is used for select statements and returns a ResultSet object, which is a table of data representing the result of the query
            var rs = s.executeQuery(selectAllSQL);

            Task task = null;
            int id;
            String name;
            while(rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("name");
                task = new Task(id, name);
                tasks.add(task);
            }
            return tasks;
        } catch (SQLException e) {
            throw new RuntimeException("Error in getting all tasks from table", e);
        }
    }

    public static Task getTaskById(int taskIdSearch) {
        try(Connection con = getDataSource().getConnection()) {
            String selectByIdSQL = "select * from task where id = ?";
            var ps = con.prepareStatement(selectByIdSQL);
            ps.setInt(1, taskIdSearch);
            // ps will return only one record as id is primary key
            var rsTask = ps.executeQuery();
            if(rsTask.next()) {
                int id = rsTask.getInt("id");
                String name = rsTask.getString("name");
                return new Task(id, name);
            } else {
                return null; // no task found with the given id
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting task by id!" ,e);
        }
    }


}
