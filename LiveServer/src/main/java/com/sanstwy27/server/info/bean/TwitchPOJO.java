package com.sanstwy27.server.info.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * @author Sanstwy27
 * @create 9/30/2020
 */

@JsonPropertyOrder({
        "data",
        "pagination"
})
public class TwitchPOJO {

    @JsonProperty("data")
    private List<TwitchChannel> data;

    @JsonProperty("pagination")
    private TwitchPagination pagination;

    @JsonProperty("data")
    public List<TwitchChannel> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<TwitchChannel> data) {
        this.data = data;
    }

    @JsonProperty("pagination")
    public TwitchPagination getPagination() {
        return pagination;
    }

    @JsonProperty("pagination")
    public void setPagination(TwitchPagination pagination) {
        this.pagination = pagination;
    }
}
