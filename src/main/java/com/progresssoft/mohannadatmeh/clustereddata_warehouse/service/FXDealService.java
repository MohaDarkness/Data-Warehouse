package com.progresssoft.mohannadatmeh.clustereddata_warehouse.service;

import com.progresssoft.mohannadatmeh.clustereddata_warehouse.DTO.FXDealFilterDTO;
import com.progresssoft.mohannadatmeh.clustereddata_warehouse.model.FXDeal;
import com.progresssoft.mohannadatmeh.clustereddata_warehouse.repository.FXDealDAO;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FXDealService {
  private final FXDealDAO fxDealDAO;

  @Autowired
  public FXDealService(FXDealDAO fxDealDAO) {
    this.fxDealDAO = fxDealDAO;
  }

  public void save(FXDeal fxDealDbo) {
    log.info("Saving FXDeal: {}", fxDealDbo);
    fxDealDAO.save(fxDealDbo);
    log.info("Saved FXDeal: {}", fxDealDbo);
  }

  public List<FXDeal> findAll() {
    log.info("Fetching all FXDeals..");
    List<FXDeal> deals = fxDealDAO.findAll();
    log.info("Found {} FXDeals", deals.size());
    return deals;
  }

  public FXDeal findById(int id) {
    log.info("Finding FXDeal by ID: {}", id);
    FXDeal deal = fxDealDAO.findById(id);
    log.info("Found FXDeal: {}", deal);
    return deal;
  }

  public List<FXDeal> findByCurrencies(String fromCurrencyIso, String toCurrencyIso) {
    log.info("Finding FXDeals by currencies: from {} to {}", fromCurrencyIso, toCurrencyIso);
    List<FXDeal> deals = fxDealDAO.findByCurrencies(fromCurrencyIso, toCurrencyIso);
    log.info("Found {} FXDeals", deals.size());
    return deals;
  }

  public List<FXDeal> findByFromCurrency(String currencyIso) {
    log.info("Finding FXDeals by fromCurrency: {}", currencyIso);
    List<FXDeal> deals = fxDealDAO.findByFromCurrency(currencyIso);
    log.info("Found {} FXDeals", deals.size());
    return deals;
  }

  public List<FXDeal> findByToCurrency(String currencyIso) {
    log.info("Finding FXDeals by toCurrency: {}", currencyIso);
    List<FXDeal> deals = fxDealDAO.findByToCurrency(currencyIso);
    log.info("Found {} FXDeals", deals.size());
    return deals;
  }

  public List<FXDeal> findByDatetime(String dateFrom, String dateTo) {
    log.info("Finding FXDeals by datetime range: {} to {}", dateFrom, dateTo);
    List<FXDeal> deals = fxDealDAO.findByDatetime(dateFrom, dateTo);
    log.info("Found {} FXDeals", deals.size());
    return deals;
  }

  public List<FXDeal> findByAmount(Long leastAmount, Long maxAmount) {
    log.info("Finding FXDeals by amount range: {} to {}", leastAmount, maxAmount);
    List<FXDeal> deals = fxDealDAO.findByAmount(leastAmount, maxAmount);
    log.info("Found {} FXDeals", deals.size());
    return deals;
  }

  public List<FXDeal> findMultiFilters(FXDealFilterDTO fxDealFilterDTO) {
    log.info("Finding FXDeals with multiple filters: {}", fxDealFilterDTO);
    List<FXDeal> deals = fxDealDAO.findMultiFilters(fxDealFilterDTO);
    log.info("Found {} FXDeals", deals.size());
    return deals;
  }
}
