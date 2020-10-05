package com.sanstwy27.server.info.bean;

/**
 * @author Sanstwy27
 * @create 9/29/2020
 */

public class StreamInfo {

    String id;
    String streamerName;
    String streamerAcct;
    String title;
    String language;
    int viewerCount;
    String avatarUrl;
    String streamUrl;
    String thumbnailUrl;

    public StreamInfo() {
    }

    public StreamInfo(String id, String streamerName, String streamerAcct, String title, String language, int viewerCount, String avatarUrl, String streamUrl, String thumbnailUrl) {
        this.id = id;
        this.streamerName = streamerName;
        this.streamerAcct = streamerAcct;
        this.title = title;
        this.language = language;
        this.viewerCount = viewerCount;
        this.avatarUrl = avatarUrl;
        this.streamUrl = streamUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreamerName() {
        return streamerName;
    }

    public void setStreamerName(String streamerName) {
        this.streamerName = streamerName;
    }

    public String getStreamerAcct() {
        return streamerAcct;
    }

    public void setStreamerAcct(String streamerAcct) {
        this.streamerAcct = streamerAcct;
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
                "name='" + streamerName + '\'' +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", viewerCount=" + viewerCount +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", streamUrl='" + streamUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
