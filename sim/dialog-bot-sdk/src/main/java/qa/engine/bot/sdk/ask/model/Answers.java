package qa.engine.bot.sdk.ask.model;

/**
 * 答案相关的bean选项.
 * 
 * @author panxinbing
 *
 */
public class Answers {

    // 会话id
    private String id;
    // 会话主题
    private String title;
    // 返回的答案
    private Object answer;
    // 返回的连接
    private String url;
    // 返回答案的类型
    private String type;
    // 返回答案的得分
    private String score;
    // 存贮位置信息
    private Resolution resolution;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getAnswer() {
        return answer;
    }

    public void setAnswer(Object answer) {
        this.answer = answer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

}
