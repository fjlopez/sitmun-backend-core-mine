package org.sitmun.plugin.core.service;

import org.junit.Before;
import org.junit.Test;
import org.sitmun.plugin.core.domain.DatabaseConnection;
import org.sitmun.plugin.core.web.exceptions.DatabaseConnectionDriverNotFoundException;
import org.sitmun.plugin.core.web.exceptions.DatabaseSQLException;

import static org.junit.Assert.*;

public class DatabaseConnectionTesterServiceTest {

  private DatabaseConnectionTesterService sut;

  @Before
  public void setup() {
    sut = new DatabaseConnectionTesterService();
  }

  @Test
  public void testDriver() {
    DatabaseConnection connection = DatabaseConnection.builder().setDriver("org.h2.Driver").build();
    assertTrue(sut.testDriver(connection));
  }

  @Test
  public void testDriverException() {
    DatabaseConnection connection = DatabaseConnection.builder().setDriver("org.h2.DriverX").build();
    DatabaseConnectionDriverNotFoundException exception = assertThrows(DatabaseConnectionDriverNotFoundException.class, () -> sut.testDriver(connection));
    assertEquals("org.h2.DriverX", exception.getCause().getLocalizedMessage());
  }

  @Test
  public void testNullDriverException() {
    DatabaseConnection connection = DatabaseConnection.builder().setDriver(null).build();
    DatabaseConnectionDriverNotFoundException exception = assertThrows(DatabaseConnectionDriverNotFoundException.class, () -> sut.testDriver(connection));
    assertEquals("null", exception.getCause().getLocalizedMessage());
  }

  @Test
  public void testConnection() {
    DatabaseConnection connection = DatabaseConnection.builder()
      .setDriver("org.h2.Driver")
      .setUrl("jdbc:h2:mem:testdb")
      .setName("sa")
      .setPassword("password")
      .build();
    sut.testDriver(connection);
    assertTrue(sut.testConnection(connection));
  }

  @Test
  public void testConnectionException() {
    DatabaseConnection connection = DatabaseConnection.builder()
      .setDriver("org.h2.Driver")
      .setUrl("jdb:h2:mem:testdb")
      .setName("sa")
      .setPassword("password")
      .build();
    sut.testDriver(connection);
    DatabaseSQLException exception = assertThrows(DatabaseSQLException.class, () -> sut.testConnection(connection));
    assertEquals("No suitable driver found for jdb:h2:mem:testdb", exception.getCause().getLocalizedMessage());
  }
}