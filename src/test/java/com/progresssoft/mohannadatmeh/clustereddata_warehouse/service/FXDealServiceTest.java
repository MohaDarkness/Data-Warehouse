package com.progresssoft.mohannadatmeh.clustereddata_warehouse.service;

import static org.junit.jupiter.api.Assertions.*;

import com.progresssoft.mohannadatmeh.clustereddata_warehouse.model.FXDeal;
import com.progresssoft.mohannadatmeh.clustereddata_warehouse.repository.FXDealDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FXDealServiceTest {
  @Mock private FXDealDAO fxDealDAO;

  @InjectMocks FXDealService fxDealService;

  @Test
  void findById_success() {
    Mockito.when(fxDealDAO.findById(1))
        .thenReturn(new FXDeal(1, "USD", "JOD", "2019-01-01 00:00:00", 150000L));
    FXDeal fxDeal = fxDealService.findById(1);
    assertEquals(fxDeal.getId(), 1);
  }

  @Test
  void findByToCurrency() {}
}
