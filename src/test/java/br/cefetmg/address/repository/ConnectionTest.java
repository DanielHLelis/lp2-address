package br.cefetmg.address.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConnectionTest {

  @Test
  void connection(){
     Assertions.assertDoesNotThrow(ConnectionFactory::getConnection, "ConnectionFactory exception check");
  }
}
