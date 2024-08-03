package com.progresssoft.mohannadatmeh.clustereddata_warehouse.repository;

import com.progresssoft.mohannadatmeh.clustereddata_warehouse.DTO.FXDealFilterDTO;
import com.progresssoft.mohannadatmeh.clustereddata_warehouse.model.FXDeal;
import java.util.List;

public interface FXDealDAO {
  void save(FXDeal fxDeal);

  List<FXDeal> findAll();

  FXDeal findById(int id);

  List<FXDeal> findByCurrencies(String fromCurrencyIso, String toCurrencyIso);

  List<FXDeal> findByFromCurrency(String currencyIso);

  List<FXDeal> findByToCurrency(String currencyIso);

  List<FXDeal> findByDatetime(String dateFrom, String dateTo);

  List<FXDeal> findByAmount(Long leastAmount, Long maxAmount);

  List<FXDeal> findMultiFilters(FXDealFilterDTO fxDealFilterDTO);
}
