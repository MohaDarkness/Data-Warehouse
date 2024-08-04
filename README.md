# Overview

## Project Description

A database of FX deals used by users to analyze these data, user can add new FX deals and can filter deals in various ways.

## Technology

Database: MySQL

RestAPI: MVC SpringBoot

Environment: Docker-compose

## Database (MySQL)

We have two table, `fx_deals` and `iso_currency_codes`, where the first is to store the FX deals themselves the other one storying the Iso codes of all currencies that are going to be used in the `fx_deals` table (foreign key).

`iso_currency_codes`

|     |     |     |
| --- | --- | --- |
| CHAR(3) currency_code (PRIMARY) | CHAR(3) currency\_iso\_code | VARCHAR(50) country_fullname |

`fx_deals`

|     |     |     |     |     |
| --- | --- | --- | --- | --- |
| INT id (PRIMARY) | Char(3) from\_iso\_currency | CHAR(3) to\_iso\_currency | DATETIME deal_time | DECIMAL(19, 3) amount |

*where **from\_iso\_currency** and **to\_iso\_currency** are foreign keys referrenced to **iso\_currency\_codes.currency_code***

# API

- ## POST /api/v1/fxdeals
    
    - To store new FX Deal into the databse
    - Parameters: **@RequestBody**   
        {  
           "fromCurrencyIso": "XXX",  
           "toCurrencyIso": "XXX",  
           "dealTime": "YYYY-MM-DD HH-MM-SS",  
           "amount": 0  
        }
- ## GET /api/v1/fxdeals
    
    - To get all  FX Deals from the database
    - No parameters needed
- ## GET /api/v1/fxdeals/{id}
    
    - To get a FX Deal by its unique ID
    - Parameters: **@PathVariable** id
    - example: `http://localhost:8080/api/v1/fxdeals/3`
- ## GET /api/v1/fxdeals**/from/{fromDatetime}/to/{toDatetime}
    
    - To get FX Deals withing a period of datetime
    - parameters: **@PathVariable** fromDatetime and toDatetime both in format "YYYY-MM-DD HH:MM:SS"
    - example: `http://localhost:8080/api/v1/fxdeals/from/2020-01-01%2012:30:00/to/2025-01-01%2012:30:00`
- ## GET /api/v1/fxdeals/least/{leastAmount}/max/{maxAmount}**
    
    - To get FX Deals within a range of amount
    - parameters: **@PathVariable** leastAmount and maxAmount both are decimals
    - example: `http://localhost:8080/api/v1/fxdeals/least/0/max/9000000000`
- ## GET /api/v1/fxdeals/from-currency/{currencyIso}
    
    - To get FX Deals with specific from_currency
    - parameters: **@PathVariable** currencyIso
    - example: `http://localhost:8080/api/v1/fxdeals/from-currency/JOD`
- ## GET /api/v1/fxdeals/to-currency/{currencyIso}
    
    - To get FX Deals with specific to_currency
    - parameters: **@PathVariable** currencyIso
    - example: `http://localhost:8080/api/v1/fxdeals/to-currency/USD`
- ## GET /api/v1/fxdeals/from-currency/{fromCurrencyIso}/to-currency/{toCurrencyIso}
    
    - To get FX Deals with specific to\_currency and from\_currency
    - parameters: **@PathVariable** fromCurrency and toCurrency
    - example: `http://localhost:8080/api/v1/fxdeals/from-currency/JOD/to-currency/USD`
- ## GET /api/v1/fxdeals/multi-filter
    
    - To get FX Deals filtered by all previous filters by once
        
    - parameters: **@RequestBody**   
        {  
        "toCurrencyIso": "JOD",
        
        "fromCurrencyIso": "___",
        
        "fromDatetime": "2020-08-01 10:03:26",
        
        "toDatetime": "2024-08-01 10:03:26",
        
        "leastAmount": "80000",
        
        "maxAmount": "80000"
        
        }
        
    - You can use three underscors ___ as currency as "any currency"
        
    - You can leave any field empty - even currency - and it will put the default values by default
        
        {  
        "toCurrencyIso": "___",
        
        "fromCurrencyIso": "___",
        
        "fromDatetime": "1960-01-01 00:00:00",
        
        "toDatetime": "2070-01-01 00:00:00",
        
        "leastAmount": 0,
        
        "maxAmount": 9223372036854775807
        
        }
