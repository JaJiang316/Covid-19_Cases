package com.example.covidcases;

public class CovidDataReportModel {
    String deathsconfirmed;
    String deathIncrease;
    String positive;
    String positiveIncrease;
    String recovered;
    String state;

    public CovidDataReportModel(){
    }

    public CovidDataReportModel(String deathsconfirmed, String deathIncrease, String positive, String positiveIncrease, String recovered, String state) {
        this.deathsconfirmed = deathsconfirmed;
        this.deathIncrease = deathIncrease;
        this.positive = positive;
        this.positiveIncrease = positiveIncrease;
        this.recovered = recovered;
        this.state = state;
    }

    @Override
    public String toString() {
        return "State: " + state + "\n" +
                "Deaths Confirmed: " + deathsconfirmed + "\n" +
                "New Deaths: " + deathIncrease + "\n" +
                "Total Cases: " + positive + "\n" +
                "New Cases: " + positiveIncrease + "\n" +
                "Recovered: " + recovered;
    }


    public String getDeathsconfirmed() {
        return deathsconfirmed;
    }

    public void setDeathsconfirmed(String deathsconfirmed) {
        if(deathsconfirmed.equals("null")){
            this.deathsconfirmed = "No Data";
        }
        else{
            this.deathsconfirmed = deathsconfirmed;
        }
    }

    public String getDeathIncrease() {
        return deathIncrease;
    }

    public void setDeathIncrease(String deathIncrease) {
        if(deathIncrease.equals("null")){
            this.deathIncrease = "No Data";
        }
        else{
            this.deathIncrease = deathIncrease;
        }
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(String positive) {
        if(positive.equals("null")){
            this.positive = "No Data";
        }
        else{
            this.positive = positive;
        }
    }

    public String getPositiveIncrease() {
        return positiveIncrease;
    }

    public void setPositiveIncrease(String positiveIncrease) {
        if(positiveIncrease.equals("null")){
            this.positiveIncrease = "No Data";
        }
        else{
            this.positiveIncrease = positiveIncrease;
        }
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        if(recovered.equals("null")){
            this.recovered = "No Data";
        }
        else{
            this.recovered = recovered;
        }
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
