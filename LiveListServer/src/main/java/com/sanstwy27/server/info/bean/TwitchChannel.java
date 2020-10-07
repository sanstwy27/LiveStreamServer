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
    // Channel Info
    "user_id",
    "user_name",
    "title",
    "viewer_count",
    "language",
    "thumbnail_url",
    // Streamer Info
    "login",
    "display_name",
    "description",
    "profile_image_url",
    "offline_image_url"
})
public class TwitchChannel {

    @JsonProperty("user_id")
    String userId;
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
    @JsonProperty("login")
    String login;
    @JsonProperty("display_name")
    String displayName;
    @JsonProperty("description")
    String description;
    @JsonProperty("profile_image_url")
    String profileImageUrl;
    @JsonProperty("offline_image_url")
    String offlineImageUrl;

    /**
     * Channel Info
     * @return
     */
    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    /**
     * Streamer Info
     * @return
     */
    @JsonProperty("login")
    public String getLogin() {
        return login;
    }

    @JsonProperty("login")
    public void setLogin(String login) {
        this.login = login;
    }

    @JsonProperty("display_name")
    public String getDisplayName() {
        return displayName;
    }

    @JsonProperty("display_name")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("profile_image_url")
    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    @JsonProperty("profile_image_url")
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    @JsonProperty("offline_image_url")
    public String getOfflineImageUrl() {
        return offlineImageUrl;
    }

    @JsonProperty("offline_image_url")
    public void setOfflineImageUrl(String offlineImageUrl) {
        this.offlineImageUrl = offlineImageUrl;
    }

    @Override
    public String toString() {
        return "TwitchChannel{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", title='" + title + '\'' +
                ", viewerCount='" + viewerCount + '\'' +
                ", language='" + language + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", login='" + login + '\'' +
                ", displayName='" + displayName + '\'' +
                ", description='" + description + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", offlineImageUrl='" + offlineImageUrl + '\'' +
                '}';
    }
}
