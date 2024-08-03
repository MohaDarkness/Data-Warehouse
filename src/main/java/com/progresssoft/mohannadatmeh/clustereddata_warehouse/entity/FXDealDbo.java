package com.progresssoft.mohannadatmeh.clustereddata_warehouse.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "fx_deals")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FXDealDbo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "from_iso_currency", nullable = false)
  private String fromCurrencyIso;

  @Column(name = "to_iso_currency", nullable = false)
  private String toCurrencyIso;

  @Column(name = "deal_time", nullable = false)
  private String dealTime;

  @Column(name = "amount", nullable = false)
  private long amount;
}
