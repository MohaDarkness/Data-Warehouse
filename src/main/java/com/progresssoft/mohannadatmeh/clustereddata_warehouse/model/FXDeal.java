package com.progresssoft.mohannadatmeh.clustereddata_warehouse.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class FXDeal {
  private int id;
  @NonNull private String fromCurrencyIso;
  @NonNull private String toCurrencyIso;
  @NonNull private String dealTime;
  private long amount;

  public FXDeal(
      int id,
      @NonNull String fromCurrencyIso,
      @NonNull String toCurrencyIso,
      @NonNull String dealTime,
      long amount) {
    this.id = id;
    if (fromCurrencyIso.length() > 3) {
      throw new IllegalArgumentException("Currency Iso must be 3 characters.");
    }
    this.fromCurrencyIso = fromCurrencyIso;
    if (toCurrencyIso.length() > 3) {
      throw new IllegalArgumentException("Currency Iso must be 3 characters.");
    }
    this.toCurrencyIso = toCurrencyIso;
    this.dealTime = dealTime;
    if (amount < 0) {
      throw new IllegalArgumentException("Amount cannot be negative.");
    }
    this.amount = amount;
  }
}
