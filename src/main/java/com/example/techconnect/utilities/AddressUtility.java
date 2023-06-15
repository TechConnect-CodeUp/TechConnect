package com.example.techconnect.utilities;

import org.springframework.stereotype.Component;

@Component
public class AddressUtility {

    public String concatenateAddress(String street, String city, String state, String country) {

        return street + ", " + city + ", " + state + ", " + country;


    }


}
