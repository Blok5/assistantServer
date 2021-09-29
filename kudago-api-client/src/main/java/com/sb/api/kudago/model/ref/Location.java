package com.sb.api.kudago.model.ref;

public enum Location {
    SPB("spb"),
    MSK("msk"),
    NSK("nsk"),
    EKB("ekb"),
    NNV("nnv"),
    KZN("kzn"),
    VGB("vbg"),
    SMR("smr"),
    KRD("krd"),
    SOCHI("sochi"),
    UFA("ufa"),
    KRNOSK("krasnoyarsk"),
    KEV("kev");

    private String apiVal;

    private Location(String apiVal) {
        this.apiVal = apiVal;
    }

    public String getApiVal() {
        return apiVal;
    }

    public static  Location fromApiVal(String apiVal){
        for (Location location: values()){
            if(location.apiVal.equals(apiVal)){
                return location;
            }
        }
        throw new IllegalArgumentException("Unknown location "+ apiVal);
    }
    //для генерации аннотации allowedValues
    public static  String validValues() {
        StringBuilder res= new StringBuilder();
        for (Location location : values()) {
            res.append(location.apiVal).append(",");
        }
        return res.substring(0,res.length()-1);
    }


}
