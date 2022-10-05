package com.promineotech.jeep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.service.JeepSalesService;
import lombok.extern.slf4j.Slf4j;


@RestController     //must have this inside implementing class or it won't work in interface
@Slf4j              //lombok logger annotation

public class DefaultJeepSalesController implements JeepSalesController {
    
    @Autowired
    private JeepSalesService jeepSalesService;

    //@Override     - gave me an error below and told me to remove this annotation
    public List<Jeep> fetchJeeps(JeepModel model, String trim) {
        log.info("model={}, trim={}", model, trim);
        return jeepSalesService.fetchJeeps(model, trim);
    }//end  LIST fetchJeeps
    
    
}// end CLASS DefaultJeepSalesController
