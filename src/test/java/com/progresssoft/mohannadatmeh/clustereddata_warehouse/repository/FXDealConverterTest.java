package com.progresssoft.mohannadatmeh.clustereddata_warehouse.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.progresssoft.mohannadatmeh.clustereddata_warehouse.entity.FXDealDbo;
import com.progresssoft.mohannadatmeh.clustereddata_warehouse.model.FXDeal;
import org.junit.jupiter.api.Test;

class FXDealConverterTest {
  FXDealConverter converter = new FXDealConverter();

  @Test
  void toModel_success() {
    FXDealDbo fxDealDbo =
        FXDealDbo.builder()
            .id(1)
            .dealTime("2019-01-01 00:00:00")
            .amount(150000L)
            .toCurrencyIso("JOD")
            .fromCurrencyIso("USD")
            .build();
    FXDeal fxDeal = converter.toModel(fxDealDbo);
    assertEquals(1, fxDeal.getId());
  }

  @Test
  void toDbo_success() {
    FXDeal fxDeal = new FXDeal(1, "USD", "JOD", "2019-01-01 00:00:00", 150000L);
    FXDealDbo fxDealDbo = converter.toDbo(fxDeal);
    //        fxDealDbo.setId(1);
    assertEquals("USD", fxDealDbo.getFromCurrencyIso());
  }
}
