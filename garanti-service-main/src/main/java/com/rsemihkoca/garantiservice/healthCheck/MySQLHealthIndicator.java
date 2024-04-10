package com.rsemihkoca.garantiservice.healthCheck;

import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@ConditionalOnEnabledHealthIndicator("mysql")
public class MySQLHealthIndicator implements HealthIndicator {

    private final DataSource dataSource;

    @Autowired
    public MySQLHealthIndicator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Health health() {
        try (Connection connection = dataSource.getConnection()) {
            // Check if the connection is valid
            if (connection.isValid(2)) {
                return Health.up().withDetail("message", "Database connection is valid").build();
            } else {
                return Health.down().withDetail("Error", "Database connection is not valid").build();
            }
        } catch (SQLException e) {
            // Connection failed
            return Health.down(e).build();
        }
    }
}
