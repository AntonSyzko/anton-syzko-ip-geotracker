package com.geoip.track.service;

/**
 * Created by Admin on 17.10.2016.
 */
public class VisitorNotFoundException extends RuntimeException {
    public VisitorNotFoundException(String message) {
        super(message);
    }
}
