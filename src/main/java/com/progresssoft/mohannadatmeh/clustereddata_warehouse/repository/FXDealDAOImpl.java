package com.progresssoft.mohannadatmeh.clustereddata_warehouse.repository;

import com.progresssoft.mohannadatmeh.clustereddata_warehouse.DTO.FXDealFilterDTO;
import com.progresssoft.mohannadatmeh.clustereddata_warehouse.entity.FXDealDbo;
import com.progresssoft.mohannadatmeh.clustereddata_warehouse.model.FXDeal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class FXDealDAOImpl implements FXDealDAO {
  private final EntityManager entityManager;
  private final FXDealConverter fxDealConverter;

  @Autowired
  public FXDealDAOImpl(EntityManager entityManager, FXDealConverter fxDealConverter) {
    this.entityManager = entityManager;
    this.fxDealConverter = fxDealConverter;
  }

  @Override
  @Transactional
  public void save(FXDeal fxDeal) {
    log.info("Saving FXDeal: {}", fxDeal);
    entityManager.persist(fxDealConverter.toDbo(fxDeal));
    log.info("FXDeal saved: {}", fxDeal);
  }

  @Override
  public List<FXDeal> findAll() {
    log.info("Fetching all FXDeals");
    TypedQuery<FXDealDbo> query = entityManager.createQuery("FROM FXDealDbo", FXDealDbo.class);
    List<FXDeal> deals = query.getResultList().stream().map(fxDealConverter::toModel).toList();
    log.info("Found {} FXDeals", deals.size());
    return deals;
  }

  @Override
  public FXDeal findById(int id) {
    log.info("Finding FXDeal by id: {}", id);
    FXDealDbo fxDealDbo = entityManager.find(FXDealDbo.class, id);
    if (fxDealDbo == null) {
      log.warn("Entity with id {} not found", id);
      return null;
    }
    log.info("Found FXDeal: {}", fxDealDbo);
    return fxDealConverter.toModel(fxDealDbo);
  }

  @Override
  public List<FXDeal> findByCurrencies(String fromCurrencyIso, String toCurrencyIso) {
    log.info("Finding FXDeals by currencies: from {} to {}", fromCurrencyIso, toCurrencyIso);
    TypedQuery<FXDealDbo> query =
        entityManager.createQuery(
            "FROM FXDealDbo WHERE fromCurrencyIso=:fromCurrencyIso AND toCurrencyIso=:toCurrencyIso",
            FXDealDbo.class);
    query.setParameter("fromCurrencyIso", fromCurrencyIso);
    query.setParameter("toCurrencyIso", toCurrencyIso);
    List<FXDeal> deals = query.getResultList().stream().map(fxDealConverter::toModel).toList();
    log.info("Found {} FXDeals", deals.size());
    return deals;
  }

  @Override
  public List<FXDeal> findByFromCurrency(String currencyIso) {
    log.info("Finding FXDeals by fromCurrency: {}", currencyIso);
    TypedQuery<FXDealDbo> query =
        entityManager.createQuery(
            "FROM FXDealDbo WHERE fromCurrencyIso=:currencyIso", FXDealDbo.class);
    query.setParameter("currencyIso", currencyIso);
    List<FXDeal> deals = query.getResultList().stream().map(fxDealConverter::toModel).toList();
    log.info("Found {} FXDeals", deals.size());
    return deals;
  }

  @Override
  public List<FXDeal> findByToCurrency(String currencyIso) {
    log.info("Finding FXDeals by toCurrency: {}", currencyIso);
    TypedQuery<FXDealDbo> query =
        entityManager.createQuery(
            "FROM FXDealDbo WHERE toCurrencyIso=:currencyIso", FXDealDbo.class);
    query.setParameter("currencyIso", currencyIso);
    List<FXDeal> deals = query.getResultList().stream().map(fxDealConverter::toModel).toList();
    log.info("Found {} FXDeals", deals.size());
    return deals;
  }

  @Override
  public List<FXDeal> findByDatetime(String dateFrom, String dateTo) {
    log.info("Finding FXDeals between dates: {} and {}", dateFrom, dateTo);
    TypedQuery<FXDealDbo> query =
        entityManager.createQuery(
            "FROM FXDealDbo WHERE dealTime BETWEEN :dateFrom AND :dateTo", FXDealDbo.class);
    query.setParameter("dateFrom", dateFrom);
    query.setParameter("dateTo", dateTo);
    List<FXDeal> deals = query.getResultList().stream().map(fxDealConverter::toModel).toList();
    log.info("Found {} FXDeals", deals.size());
    return deals;
  }

  @Override
  public List<FXDeal> findByAmount(Long leastAmount, Long maxAmount) {
    log.info("Finding FXDeals with amount between {} and {}", leastAmount, maxAmount);
    TypedQuery<FXDealDbo> query =
        entityManager.createQuery(
            "FROM FXDealDbo WHERE amount BETWEEN :leastAmount AND :maxAmount", FXDealDbo.class);
    query.setParameter("leastAmount", leastAmount);
    query.setParameter("maxAmount", maxAmount);
    List<FXDeal> deals = query.getResultList().stream().map(fxDealConverter::toModel).toList();
    log.info("Found {} FXDeals", deals.size());
    return deals;
  }

  @Override
  public List<FXDeal> findMultiFilters(FXDealFilterDTO fxDealFilterDTO) {
    log.info("Finding FXDeals with multiple filters: {}", fxDealFilterDTO);
    TypedQuery<FXDealDbo> query =
        entityManager.createQuery(
            "FROM FXDealDbo WHERE "
                + "fromCurrencyIso like :fromCurrencyIso AND "
                + "toCurrencyIso like :toCurrencyIso AND "
                + "dealTime BETWEEN :dateFrom AND :dateTo AND "
                + "amount BETWEEN :leastAmount AND :maxAmount",
            FXDealDbo.class);
    query.setParameter("fromCurrencyIso", fxDealFilterDTO.getFromCurrencyIso());
    query.setParameter("toCurrencyIso", fxDealFilterDTO.getToCurrencyIso());
    query.setParameter("dateFrom", fxDealFilterDTO.getFromDatetime());
    query.setParameter("dateTo", fxDealFilterDTO.getToDatetime());
    query.setParameter("leastAmount", fxDealFilterDTO.getLeastAmount());
    query.setParameter("maxAmount", fxDealFilterDTO.getMaxAmount());
    List<FXDeal> deals = query.getResultList().stream().map(fxDealConverter::toModel).toList();
    log.info("Found {} FXDeals", deals.size());
    return deals;
  }
}
