package com.geoip.track.repository;

import com.geoip.track.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 17.10.2016.
 */
public interface VisitorRepository  extends JpaRepository<Visitor, Long> {

    public List<Visitor> findByipAddress(String ipAddress);
    public List<Visitor> findByCountryCode(String countryCode);
    public List<Visitor> findByCountryName(String countryName);
    public List<Visitor> findByRegion(String region);
    public List<Visitor> findByRegionName(String regionName);
    public List<Visitor> findByCity(String city);
    public List<Visitor> findByPostalCode(String postalCode);
    public List<Visitor> findByLatitude(String latitude);
    public List<Visitor> findByLongtitude(String longtitude);
    public List<Visitor> findByVisitedAt(Date visitedAt);




}
