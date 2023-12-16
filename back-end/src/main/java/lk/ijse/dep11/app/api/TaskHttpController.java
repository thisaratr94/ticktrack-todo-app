package lk.ijse.dep11.app.api;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lk.ijse.dep11.app.dto.TaskDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PreDestroy;
import java.sql.*;

@RestController
@RequestMapping("api/v1/tasks")
@CrossOrigin
public class TaskHttpController {

    private final HikariDataSource pool;

    public TaskHttpController() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/ticktrack");
        config.setUsername("root");
        config.setPassword("sql23");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.addDataSourceProperty("maximumPoolSize", 10);
        pool = new HikariDataSource(config);
    }

    @PreDestroy
    public void destroy() {
        pool.close();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json", consumes = "application/json")
    public TaskDto createTask(@RequestBody @Validated(TaskDto.Create.class) TaskDto task) {
        try(Connection connection = pool.getConnection()) {
            PreparedStatement stm = connection.prepareStatement("INSERT INTO task(description, status, email) VALUES (?, FALSE, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, task.getDescription());
            stm.setString(2, task.getEmail());
            stm.executeUpdate();
            ResultSet generatedKeys = stm.getGeneratedKeys();
            generatedKeys.next();
            int id = generatedKeys.getInt(1);
            task.setId(id);
            task.setStatus(false);
            return task;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(consumes = "application/json", value = "/{id}")
    public void updateTask(@PathVariable int id,
                           @RequestBody @Validated(TaskDto.Update.class) TaskDto task) {
       
    }

    @DeleteMapping
    public void deleteTask() {
        System.out.println("deleteTask()");
    }

    @GetMapping
    public void getAllTasks() {
        System.out.println("getAllTasks()");
    }
}
