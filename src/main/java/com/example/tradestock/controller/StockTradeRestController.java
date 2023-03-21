package com.example.tradestock.controller;

import com.example.tradestock.entity.StockTrade;
import com.example.tradestock.repository.StockTradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StockTradeRestController {

    @Autowired
    StockTradeRepository stockTradeRepository;

    @PostMapping("/trades")
    public ResponseEntity<StockTrade> addTrade(@RequestBody StockTrade stockTrade){

        stockTradeRepository.save(stockTrade);
        return new ResponseEntity<>(stockTrade, HttpStatus.CREATED);
    }

    @GetMapping("/trades")
    public ResponseEntity<List<StockTrade>> allTrades(){

        List<StockTrade> stockTradeList = stockTradeRepository.findAll();
        if(stockTradeList.size() > 0)
            stockTradeList.sort((s1, s2) -> (int) (s1.getId() - s2.getId()));

        return new ResponseEntity<>(stockTradeList, HttpStatus.OK);
    }

    @GetMapping("/trades/{id}")
    public ResponseEntity<StockTrade> getTrade(@PathVariable Long id){

        Optional<StockTrade> optionalStockTrade = stockTradeRepository.findById(id);
        if(optionalStockTrade.isPresent()) {
            StockTrade realStockTrade = optionalStockTrade.get();
            return new ResponseEntity<>(realStockTrade, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/trades/{id}")
    public ResponseEntity<StockTrade> deleteTrade(@PathVariable Long id){

        Optional<StockTrade> optionalStockTrade = stockTradeRepository.findById(id);
        if(optionalStockTrade.isPresent()){

            StockTrade deleteStockTrade = optionalStockTrade.get();
            stockTradeRepository.deleteById(id);
            return new ResponseEntity<>(deleteStockTrade, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PutMapping("/trades/{id}")
    public ResponseEntity<StockTrade> putTrade(@RequestBody StockTrade stockTrade, @PathVariable Long id){

        Optional<StockTrade> optionalStockTrade = stockTradeRepository.findById(id);
        if(optionalStockTrade.isPresent()){

            StockTrade updatedStockTrade = optionalStockTrade.get();

            updatedStockTrade.setPrice(stockTrade.getPrice());
            updatedStockTrade.setShares(stockTrade.getShares());
            updatedStockTrade.setSymbol(stockTrade.getSymbol());
            updatedStockTrade.setType(stockTrade.getType());
            updatedStockTrade.setUserId(stockTrade.getUserId());
            updatedStockTrade.setTimestamp(stockTrade.getTimestamp());

            stockTradeRepository.save(updatedStockTrade);

            return new ResponseEntity<>(updatedStockTrade, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PatchMapping("/trades/{id}")
    public ResponseEntity<StockTrade> patchTrade(@PathVariable Long id){
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

}
