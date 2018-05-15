package qa.engine.bot.sdk.ask;

/**
 * 初始化sdk请求对象
 *
 * @author panxinbing
 */
public class AskRequest {
    // 应用id
    private String appKey;
    // 应用秘钥
    private String appSecret;
    // 输入的询问内容
    private String question;
    // 用户级别
    private String type;
    // 输入用户Id
    private String userId;
    // 使用的平台
    private String platform;
    // 当前会话的sessionId
    private String sessionId;

    public AskRequest() {
    }

    // 请求对象初始化的构造函数
    public AskRequest(String appKey, String appSecret, String question, String type, String userId, String platform) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.question = question;
        this.type = type;
        // 智能问答项目中的userId相当于sessionId
        this.userId = userId;
        this.platform = platform;

        // 设置智能问答项目的sessionId
        this.sessionId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return this.appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
