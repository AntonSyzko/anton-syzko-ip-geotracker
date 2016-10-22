package com.geoip.track.service;

/**
 * Created by Admin on 22.10.2016.
 */
public class VisitorHasBeenAlreadyRegisteredException extends RuntimeException{
    public VisitorHasBeenAlreadyRegisteredException(String message) {
        super(message);
    }

}
