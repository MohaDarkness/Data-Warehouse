package com.progresssoft.mohannadatmeh.clustereddata_warehouse.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FXDealTest {
  @Test
  void constructor_success() {
    FXDeal fxDeal = new FXDeal(1, "USD", "JOD", "2024-01-01 15:38:00", 150000L);
    assertEquals(1, fxDeal.getId());
    assertEquals("USD", fxDeal.getFromCurrencyIso());
    assertEquals("JOD", fxDeal.getToCurrencyIso());
    assertEquals("2024-01-01 15:38:00", fxDeal.getDealTime());
    assertEquals(150000L, fxDeal.getAmount());
  }

  @Test
  void constructor_negativeAmount_failure() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new FXDeal(1, "USD", "JOD", "2024-01-01 15:38:00", -150000L));
  }

  @Test
  @DisplayName("fromCurrency length greater than 3 chars")
  void constructor_invalidFromCurrency_failure() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new FXDeal(1, "USDD", "JOD", "2024-01-01 15:38:00", 150000L));
  }

  @Test
  @DisplayName("toCurrency length greater than 3 chars")
  void constructor_invalidToCurrency_failure() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new FXDeal(1, "USD", "JODD", "2024-01-01 15:38:00", 150000L));
  }
}
