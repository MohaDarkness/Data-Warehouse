package com.progresssoft.mohannadatmeh.clustereddata_warehouse.repository;

import com.progresssoft.mohannadatmeh.clustereddata_warehouse.entity.FXDealDbo;
import com.progresssoft.mohannadatmeh.clustereddata_warehouse.model.FXDeal;
import org.springframework.stereotype.Component;

@Component
public class FXDealConverter {
  public FXDeal toModel(FXDealDbo dbo) {
    return new FXDeal(
        dbo.getId(),
        dbo.getFromCurrencyIso(),
        dbo.getToCurrencyIso(),
        dbo.getDealTime(),
        dbo.getAmount());
  }

  public FXDealDbo toDbo(FXDeal fxDeal) {
    return FXDealDbo.builder()
        .dealTime(fxDeal.getDealTime())
        .amount(fxDeal.getAmount())
        .fromCurrencyIso(fxDeal.getFromCurrencyIso())
        .toCurrencyIso(fxDeal.getToCurrencyIso())
        .build();
  }
}
