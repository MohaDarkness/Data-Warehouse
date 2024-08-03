package com.progresssoft.mohannadatmeh.clustereddata_warehouse.controller;

import com.progresssoft.mohannadatmeh.clustereddata_warehouse.DTO.FXDealFilterDTO;
import com.progresssoft.mohannadatmeh.clustereddata_warehouse.model.FXDeal;
import com.progresssoft.mohannadatmeh.clustereddata_warehouse.service.FXDealService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fxdeals")
public class FXDealController {
  private final FXDealService fxDealService;

  @Autowired
  public FXDealController(FXDealService fxDealService) {
    this.fxDealService = fxDealService;
  }

  @PostMapping
  public void saveFxDeal(@RequestBody FXDeal fxDeal) {
    fxDealService.save(fxDeal);
  }

  @GetMapping
  public List<FXDeal> fetchAll() {
    return fxDealService.findAll();
  }

  @GetMapping("/{id}")
  public FXDeal fetchById(@PathVariable Integer id) {
    return fxDealService.findById(id);
  }

  @GetMapping("/from/{fromDatetime}/to/{toDatetime}")
  public List<FXDeal> fetchByDatetime(
      @PathVariable String fromDatetime, @PathVariable String toDatetime) {
    return fxDealService.findByDatetime(fromDatetime, toDatetime);
  }

  @GetMapping("/least/{leastAmount}/max/{maxAmount}")
  public List<FXDeal> fetchByAmount(@PathVariable Long leastAmount, @PathVariable Long maxAmount) {
    return fxDealService.findByAmount(leastAmount, maxAmount);
  }

  @GetMapping("/from-currency/{currencyIso}")
  public List<FXDeal> fetchByFromCurrencyIso(@PathVariable String currencyIso) {
    return fxDealService.findByFromCurrency(currencyIso);
  }

  @GetMapping("/to-currency/{currencyIso}")
  public List<FXDeal> fetchByToCurrencyIso(@PathVariable String currencyIso) {
    return fxDealService.findByToCurrency(currencyIso);
  }

  @GetMapping("/from-currency/{fromCurrencyIso}/to-currency/{toCurrencyIso}")
  public List<FXDeal> fetchByCurrenciesIso(
      @PathVariable String fromCurrencyIso, @PathVariable String toCurrencyIso) {
    return fxDealService.findByCurrencies(fromCurrencyIso, toCurrencyIso);
  }

  @GetMapping("/multi-filter")
  public List<FXDeal> fetchByMultiFilters(@RequestBody FXDealFilterDTO fxDealFilterDTO) {
    fxDealFilterDTO.applyDefaults();
    return fxDealService.findMultiFilters(fxDealFilterDTO);
  }
}
