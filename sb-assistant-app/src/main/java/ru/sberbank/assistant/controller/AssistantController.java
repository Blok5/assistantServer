package ru.sberbank.assistant.controller;

import com.sb.api.kudago.client.KudagoClient;
import com.sb.api.kudago.model.Event;
import com.sb.api.kudago.model.ref.Category;
import com.sb.api.kudago.model.ref.Location;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Api(value = "Operations with events")
@RequestMapping(path = "api/v1")
public class AssistantController {

    private final KudagoClient kudagoClient;

    @Autowired
    public AssistantController(KudagoClient kudagoClient) {
        this.kudagoClient = kudagoClient;
    }

    @ApiOperation(value = "Test the api application is working correctly")
    @GetMapping(value = "/hello")
    public String sayHello() {
        return "Hello World";
    }

    @ApiOperation(value = "Get allowed values for enums")
    @GetMapping(value = "/allowedValues")
    public String getAllowedValues() {
        return "Location: "+ Location.validValues()+"\n"+
                "Category: "+ Category.validValues();
    }

    @ApiOperation(value = "Get random today event if you are lucky")
    @GetMapping(value = "/lucky", produces = "application/json;charset=UTF-8")
    public Event getLuckyEvent() {
        return kudagoClient.lucky();
    }

    @ApiOperation(value = "Search events by different filters")
    @GetMapping(value = "/search-events", produces = "application/json;charset=UTF-8")
    public List<Event> searchEvents( @RequestParam(name="dateTo",required=false) Date dateTo,
                                     @RequestParam(name="dateFrom",required=false) Date dateFrom,
                                     @ApiParam(allowableValues="spb,msk,nsk,ekb,nnv,kzn,vbg,smr,krd,sochi,ufa,krasnoyarsk,kev") @RequestParam(name="location",required=false) Location location,
                                     @RequestParam(name="isFree",required=false) String isFree,
                                     @ApiParam(allowableValues="cinema,concert,education,entertainment,exhibition,fashion,festival,kids,party,quest,theater,tour,yarmarki-razvlecheniya-yarmarki") @RequestParam(name="categories",required=false) Category categories){
                                     return kudagoClient.searchEvents(dateTo,dateFrom,location,isFree,categories);
    }

}
