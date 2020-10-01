package com.sanstwy27.server.info.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Sanstwy27
 * @create 9/30/2020
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "cursor"
})
public class TwitchPagination {
    @JsonProperty("cursor")
    private String cursor;

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    @Override
    public String toString() {
        return "TwitchPagination{" +
                "cursor='" + cursor + '\'' +
                '}';
    }
}
