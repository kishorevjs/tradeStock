package com.example.tradestock.service;

import com.example.tradestock.repository.StockTradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    @Autowired
    StockTradeRepository stockTradeRepository;

}
