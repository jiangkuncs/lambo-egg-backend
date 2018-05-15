package qa.engine.bot.sdk.ask.model;

import java.sql.Timestamp;

/**
 * 素材的bean结构.
 * 
 * @author panxinbing
 *
 */
public class KnowledgeMedia {

    // 素材ID
    private String mediaId;

    // 素材名称
    private String mediaName;

    // 素材摘要
    private String mediaSummary;

    // 素材类型
    private String mediaType;

    // 素材文件
    private String mediaFile;

    // 素材链接
    private String mediaUrl;

    // 素材水印
    private String mediaWatermark;

    // 租户id
    private String tenantId;

    // 用户Id
    private String userId;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getMediaSummary() {
        return mediaSummary;
    }

    public void setMediaSummary(String mediaSummary) {
        this.mediaSummary = mediaSummary;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaFile() {
        return mediaFile;
    }

    public void setMediaFile(String mediaFile) {
        this.mediaFile = mediaFile;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaWatermark() {
        return mediaWatermark;
    }

    public void setMediaWatermark(String mediaWatermark) {
        this.mediaWatermark = mediaWatermark;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

}
