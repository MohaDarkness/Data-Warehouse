package com.progresssoft.mohannadatmeh.clustereddata_warehouse.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FXDealFilterDTO {
  private String toCurrencyIso;
  private String fromCurrencyIso;
  private String fromDatetime;
  private String toDatetime;
  private Long leastAmount;
  private Long maxAmount;

  public void applyDefaults() {
    if (toCurrencyIso == null || toCurrencyIso.isEmpty()) {
      toCurrencyIso = "___";
    }
    if (fromCurrencyIso == null || fromCurrencyIso.isEmpty()) {
      fromCurrencyIso = "___";
    }
    if (fromDatetime == null || fromDatetime.isEmpty()) {
      fromDatetime = "1960-01-01 00:00:00";
    }
    if (toDatetime == null || toDatetime.isEmpty()) {
      toDatetime = "2070-01-01 00:00:00";
    }
    if (leastAmount == null) {
      leastAmount = 0L;
    }
    if (maxAmount == null) {
      maxAmount = Long.MAX_VALUE;
    }
  }
}
