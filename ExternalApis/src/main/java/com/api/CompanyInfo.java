package com.api;

/**
 * CompanyInfo stores all the information returned from the various web services
 * Methods are provided so the information can be fetched from across the project
 * @author Dan Aves
 */

public class CompanyInfo {
    //User entered company name
    private static String companyname;
    //Stock code returned from ApiOne
    private static String stockcode;
    //Last days closing price returned from ApiTwo
    private static Double currentPrice;
    //Price from 50 days ago
    private static Double pastPrice;
    //Percentage change caluclated in web service three
    private static String percenageChange;
    //Error handling
    private static Boolean invalidCompany;
    //Date for last days closing
    private static String currentDate;
    //Time period for last days closing
    private static String currentTime;
    //Date for 50 days ago
    private static String pastDate;
    //Time for 50 days ago
    private static String pastTime;


    public static String getCurrentDate() {
        return currentDate;
    }

    public static void setCurrentDate(String currentDate) {
        CompanyInfo.currentDate = currentDate;
    }

    public static String getCurrentTime() {
        return currentTime;
    }

    public static void setCurrentTime(String currentTime) {
        CompanyInfo.currentTime = currentTime;
    }

    public static String getPastDate() {
        return pastDate;
    }

    public static void setPastDate(String pastDate) {
        CompanyInfo.pastDate = pastDate;
    }

    public static String getPastTime() {
        return pastTime;
    }

    public static void setPastTime(String pastTime) {
        CompanyInfo.pastTime = pastTime;
    }

    public static Boolean getInvalidCompany() {
        return invalidCompany;
    }

    public static void setInvalidCompany(Boolean invalidCompany) {
        CompanyInfo.invalidCompany = invalidCompany;
    }

    public static String getPercenageChange() {
        return percenageChange;
    }

    public static void setPercenageChange(String percenageChange) {
        CompanyInfo.percenageChange = percenageChange;
    }

    public static Double getCurrentPrice() {
        return currentPrice;
    }

    public static void setCurrentPrice(Double currentPrice) {
        CompanyInfo.currentPrice = currentPrice;
    }

    public static Double getPastPrice() {
        return pastPrice;
    }

    public static void setPastPrice(Double pastPrice) {
        CompanyInfo.pastPrice = pastPrice;
    }

    public String getStockcode() {
        return stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode;
    }

    public String getCompanyName(){
        return companyname;
    }

    public void setCompanyName(String companyname){
        this.companyname = companyname;
    }
}
