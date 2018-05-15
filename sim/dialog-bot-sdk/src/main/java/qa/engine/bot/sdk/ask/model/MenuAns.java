package qa.engine.bot.sdk.ask.model;

/**
 * 文字菜单答案的bean结构.
 * 
 * @author panxinbing
 *
 */
public class MenuAns {

    // 答案媒体类型（T/GT/IMG/AU/VI）
    private String mediaType;

    // 文本类型答案（mediaType为T时）
    private String textAns;

    // KnowledgeMedia类型，但不包含素材文件（当mediaType为GT/IMG/AU/VI）
    private String knowledgeMedia;

    // 素材请求地址
    private String url;

    // 答案结束语
    private String endStatement;

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getTextAns() {
        return textAns;
    }

    public void setTextAns(String textAns) {
        this.textAns = textAns;
    }

    public String getKnowledgeMedia() {
        return knowledgeMedia;
    }

    public void setKnowledgeMedia(String knowledgeMedia) {
        this.knowledgeMedia = knowledgeMedia;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEndStatement() {
        return endStatement;
    }

    public void setEndStatement(String endStatement) {
        this.endStatement = endStatement;
    }

}
