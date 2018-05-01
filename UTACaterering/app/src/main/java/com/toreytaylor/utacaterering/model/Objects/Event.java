package com.toreytaylor.utacaterering.model.Objects;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "Event",
    foreignKeys =
        @ForeignKey(entity = SystemUser.class,
                parentColumns = "userId",
                childColumns = "customerId",
                onDelete = CASCADE,
                onUpdate = CASCADE),
    indices =
        @Index(value = "customerId")
        )
public class Event {
    @NonNull
    @PrimaryKey
    private final String eventId;

    private String nameOfEvent;

    private int sizeOfParty;

    private String customerId;

    private String drinkVenue;

    private String formality;

    private Boolean approval = false;

    private String foodVenue;

    private String mealType;

    private int estPrice;

    private String staff;

    private String hall;

    private int month;
    private int day;
    private int year;
    private Long startTime;
    private Long endTime;

    public Event(String eventId){
        this.eventId = eventId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setFoodVenue(String foodVenue) {
        this.foodVenue = foodVenue;
    }

    public String getFoodVenue() {
        return foodVenue;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getMealType() {
        return mealType;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setNameOfEvent(String nameOfEvent) {
        this.nameOfEvent = nameOfEvent;
    }

    public void setEstPrice(int estPrice) {
        this.estPrice = estPrice;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public String getHall() {
        return hall;
    }

    public int getEstPrice() {
        return estPrice;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getStaff() {
        return staff;
    }

    public String getNameOfEvent() {
        return nameOfEvent;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setSizeOfParty(int sizeOfParty) {
        this.sizeOfParty = sizeOfParty;
    }

    public int getSizeOfParty() {
        return sizeOfParty;
    }

    public void setDrinkVenue(String drinkVenue) {
        this.drinkVenue = drinkVenue;
    }

    public String getDrinkVenue() {
        return drinkVenue;
    }

    public void setFormality(String formality) {
        this.formality = formality;
    }

    public String getFormality() {
        return formality;
    }

    public void setApproval(Boolean approval) {
        this.approval = approval;
    }

    public Boolean getApproval() {
        return approval;
    }
}
