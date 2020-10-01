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
    "user_name",
    "title",
    "viewer_count",
    "language",
    "thumbnail_url"
})
public class TwitchChannel {

    @JsonProperty("user_name")
    String userName;
    @JsonProperty("title")
    String title;
    @JsonProperty("viewer_count")
    String viewerCount;
    @JsonProperty("language")
    String language;
    @JsonProperty("thumbnail_url")
    String thumbnailUrl;

    @JsonProperty("user_name")
    public String getUserName() {
        return userName;
    }

    @JsonProperty("user_name")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("viewer_count")
    public String getViewerCount() {
        return viewerCount;
    }

    @JsonProperty("viewer_count")
    public void setViewerCount(String viewerCount) {
        this.viewerCount = viewerCount;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("thumbnail_url")
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @JsonProperty("thumbnail_url")
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public String toString() {
        return "TwitchChannel{" +
                "userName='" + userName + '\'' +
                ", title='" + title + '\'' +
                ", viewerCount='" + viewerCount + '\'' +
                ", language='" + language + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
