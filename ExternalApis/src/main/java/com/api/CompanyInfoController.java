package com.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * CompanyInfoController class handles the responses from the front end
 * The methods allow communication with app.module.js
 *
 * @author Dan Aves
 */

@RestController
public class CompanyInfoController {

    /**
     * Updates the companyName passed from the user interface
     * @param /postdata
     * @return  ResponseEntity<Object></Object>
     */

    @RequestMapping(value="/postdata",method= RequestMethod.POST)
    public ResponseEntity<Object> postData(@RequestBody String companyName) throws Exception{
        CompanyInfo companyInfo = new CompanyInfo();
        companyName = companyName.replace("\"", "");
        companyInfo.setCompanyName(companyName);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    /**
     * Calls the first Api and returns a stock code
     * @param /getfromapi1
     * @return  String
     */
    @RequestMapping(value="/getfromapi1",method=RequestMethod.GET)
    @ResponseBody
    public String getData() throws Exception{
        ApiOne instance = new ApiOne();
        instance.stockCode();

        CompanyInfo stockcode = new CompanyInfo();

        if(stockcode.getInvalidCompany() == true){
            return "invalid company";
        }
        else{
            return stockcode.getStockcode();
        }
    }

    /**
     * Calls the second Api and returns a list of variables
     * @param /getfromapi2
     * @return  Map<String>Object>
     */
    @RequestMapping(value="/getfromapi2")
    @ResponseBody
    public Map<String,Object> getData2() throws Exception{
        Map<String,Object> map=new HashMap<>();
        CompanyInfo companyinfo = new CompanyInfo();
        ApiTwo apiTwo = new ApiTwo();
        apiTwo.invokeAlphaVantage();

        if(companyinfo.getInvalidCompany() == true){
            map.put("invalid company", 1 );
            return map;
        }
        else{
            map.put(companyinfo.getCurrentDate(), 1);
            map.put(companyinfo.getCurrentPrice().toString(), 2);
            map.put(companyinfo.getCurrentTime(), 3);
            map.put(companyinfo.getPastDate(), 4);
            map.put(companyinfo.getPastPrice().toString(), 5);
            map.put(companyinfo.getPastTime(), 6);
            return map;
        }
    }


    /**
     * Calls the third Api and returns percentage change
     * @param /getfromapi3
     * @return String
     */
    @RequestMapping(value="/getfromapi3")
    @ResponseBody
    public String getData3() throws Exception{
        OwnApiClient apiClient = new OwnApiClient();
        apiClient.Client();
        CompanyInfo companyinfo = new CompanyInfo();
        return companyinfo.getPercenageChange();
    }
}
