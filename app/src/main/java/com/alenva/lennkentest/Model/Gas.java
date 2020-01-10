package com.alenva.lennkentest.Model;

public class Gas {
    private String name;
    private String street;
    private String postalcode;
    private String rfc;
    private String regular;
    private String premium;
    private String longitude;
    private String latitude;



    public Gas() {
    }

    public Gas(String name, String street, String postalcode, String rfc, String regular, String premium, String longitude, String latitude) {
        this.name = name;
        this.street = street;
        this.postalcode = postalcode;
        this.rfc = rfc;
        this.regular = regular;
        this.premium = premium;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
