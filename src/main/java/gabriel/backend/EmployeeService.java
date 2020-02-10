package gabriel.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeService {
    @Autowired
    private JdbcTemplate  jdbcTemplate;

    public List findAll() {

        try {

            return jdbcTemplate.query(
                    "SELECT firstname, lastname, email, title FROM employees",
                    (rs, rowNum) -> new Employee(rs.getString("firstname"),
                    rs.getString("lastname"), rs.getString("email"),
                    rs.getString("title")));

        } catch (Exception e) {

            return new ArrayList();

        }

    }
}
