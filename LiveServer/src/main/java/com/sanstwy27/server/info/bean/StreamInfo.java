package com.sanstwy27.server.info.bean;

/**
 * @author Sanstwy27
 * @create 9/29/2020
 */

public class StreamInfo {

    String name;
    String title;
    String language;
    int viewerCount;
    String avatarUrl;
    String streamUrl;
    String thumbnailUrl;

    public StreamInfo() {
    }

    public StreamInfo(String name, String title, String language, int viewerCount, String avatarUrl, String streamUrl, String thumbnailUrl) {
        this.name = name;
        this.title = title;
        this.language = language;
        this.viewerCount = viewerCount;
        this.avatarUrl = avatarUrl;
        this.streamUrl = streamUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getViewerCount() {
        return viewerCount;
    }

    public void setViewerCount(int viewerCount) {
        this.viewerCount = viewerCount;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public String toString() {
        return "StreamInfo{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", viewerCount=" + viewerCount +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", streamUrl='" + streamUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
