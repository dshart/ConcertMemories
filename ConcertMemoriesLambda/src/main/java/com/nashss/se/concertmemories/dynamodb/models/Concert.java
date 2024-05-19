package com.nashss.se.concertmemories.dynam

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a record in the playlists table.
 */
@DynamoDBTable(tableName = "concerts")
public class Concert {
    private String emailAddress;
    private String bandName;
    private String tourName;
    private String dateAttended;
    private List<String> openingActs;
    private List<String> setList;
    private List<String> memories;

    @DynamoDBHashKey(attributeName = "email_address")
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @DynamoDBAttribute(attributeName = "band_name")
    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    @DynamoDBAttribute(attributeName = "tour_name")
    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    @DynamoDBRangeKey(attributeName = "date_attended")
    public String getDateAttended() {
        return dateAttended;
    }

    public void setDateAttended(String dateAttended) {
        this.dateAttended = dateAttended;
    }

    /**
     * Returns the list of opening acts for this concert, null if there are none.
     *
     * @return List of opening acts for this concert
     */

    @DynamoDBAttribute(attributeName = "opening_acts")
    @DynamoDBTypeConverted(converter = com.nashss.se.concertmemories.converters.OpeningActsListConverter.class)
    public List<String> getOpeningActs() {
        return openingActs;
    }

    public void setOpeningActs(List<String> openingActs) {
        this.openingActs = openingActs;
    }

    @DynamoDBAttribute(attributeName = "set_list")
    @DynamoDBTypeConverted(converter = com.nashss.se.concertmemories.converters.SetListConverter.class)
    public List<String> getSetList() {
       return setList;
    }

    public void setSetList(List<String> setList) {
        this.setList = setList;
    }

    @DynamoDBAttribute(attributeName = "memories")
    @DynamoDBTypeConverted(converter = com.nashss.se.concertmemories.converters.MemoriesConverter.class)
    public List<String> getMemories() {
        return memories;
    }

    public void setMemories(List<String> memories) {
        this.memories = memories;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Concert)) return false;
        Concert concert = (Concert) o;
        return Objects.equals(emailAddress, concert.emailAddress) && Objects.equals(dateAttended, concert.dateAttended);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress, dateAttended);
    }
}