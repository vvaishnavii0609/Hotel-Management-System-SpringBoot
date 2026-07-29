package com.example.finalproject.dtos;

public class HotelFilterRequest {

    private Double minRating;

    private String sortBy;

    private String sortDirection;

    public HotelFilterRequest() {
    }

    public HotelFilterRequest(Double minRating,
                              String sortBy,
                              String sortDirection) {
        this.minRating = minRating;
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
    }

    public Double getMinRating() {
        return minRating;
    }

    public void setMinRating(Double minRating) {
        this.minRating = minRating;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }
}