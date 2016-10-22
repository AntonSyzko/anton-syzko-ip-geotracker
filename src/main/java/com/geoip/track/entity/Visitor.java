package com.geoip.track.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
/**
 * Created by Admin on 17.10.2016.
 */
@Entity
@Table(name = "visitor")
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ip_address")
    private String ipAddress;


    @Column(name = "country_code")
    private String countryCode;



    @Column(name = "country_name")
    private String countryName;

    @Column(name = "region")
    private String region;

    @Column(name = "region_name")
    private String regionName;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;


    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longtitude")
    private String longtitude;

    @Column(name = "visited_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd-MMM-YYYY")
    private Date visitedAt;


    public Long getId() {
        return id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public Date getVisitedAt() {
        return visitedAt;
    }

    public void setVisitedAt(Date visitedAt) {
        this.visitedAt = visitedAt;
    }


    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Visitor{");
        sb.append("ipAddress='").append(ipAddress).append('\'');
        sb.append(", countryCode='").append(countryCode).append('\'');
        sb.append(", countryName='").append(countryName).append('\'');
        sb.append(", region='").append(region).append('\'');
        sb.append(", regionName='").append(regionName).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", postalCode='").append(postalCode).append('\'');
        sb.append(", latitude='").append(latitude).append('\'');
        sb.append(", longtitude='").append(longtitude).append('\'');
        sb.append(", visitedAt=").append(visitedAt);
        sb.append('}');
        return sb.toString();
    }
}
