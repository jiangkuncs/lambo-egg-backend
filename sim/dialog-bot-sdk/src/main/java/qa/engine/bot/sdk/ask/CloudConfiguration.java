package qa.engine.bot.sdk.ask;

/**
 * cloud中的配置参数
 *
 * @author panxinbing
 */
public class CloudConfiguration {

    // 访问的url路径
    private String url;
    // uri路径
    private String uri;
    // 连接头信息
    private String contentTypeHeader;
    // 连接超时时间
    private int connectionTimeout;
    // 读物超时时间
    private int readTimeout;
    // 回答问题的数量
    private String answer_number;
    // 回答问题的主题
    private String topic;

    //服务的返回类值类型（url或者答案）
    private boolean invokeServParam;

   

    private String xAUEHeader;
    private String xTXEHeader;
    private String xAUFHeader;

    public String getAnswer_number() {
        return answer_number;
    }

    public void setAnswer_number(String answer_number) {
        this.answer_number = answer_number;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentTypeHeader() {
        return this.contentTypeHeader;
    }

    public void setContentTypeHeader(String contentTypeHeader) {
        this.contentTypeHeader = contentTypeHeader;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getConnectionTimeout() {
        return this.connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public String getxAUEHeader() {
        return xAUEHeader;
    }

    public void setxAUEHeader(String xAUEHeader) {
        this.xAUEHeader = xAUEHeader;
    }

    public String getxTXEHeader() {
        return xTXEHeader;
    }

    public void setxTXEHeader(String xTXEHeader) {
        this.xTXEHeader = xTXEHeader;
    }

    public String getxAUFHeader() {
        return xAUFHeader;
    }

    public void setxAUFHeader(String xAUFHeader) {
        this.xAUFHeader = xAUFHeader;
    }

    public boolean isInvokeServParam() {
        return invokeServParam;
    }

    public void setInvokeServParam(boolean invokeServParam) {
        this.invokeServParam = invokeServParam;
    }

}
