package com.isproj2.regainmobile.services.util;

import java.util.Comparator;

import org.springframework.expression.ParseException;

import com.isproj2.regainmobile.model.GreenZone;

public class GreenZoneDateSort implements Comparator<GreenZone> {

    @Override
    public int compare(GreenZone o1, GreenZone o2) {
        try {
            // compare for descending date order
            return o2.getDate().compareTo(o1.getDate());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
