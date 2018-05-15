package qa.engine.bot.sdk.ask.model;

import java.util.List;

/**
 * 问答回复.
 * 
 * @author panxinbing
 *
 */
public class ResponseAnswer {
    // 询问的问题
    private String question;
    // 前端搜索为问答库的内容的时候topic等于question，和机器人聊天的时候为null
    private String topic;
    // 格式
    private String format;
    // 使用的语言
    private String lang;
    // app_id
    private String app_id;
    // 会话id
    private String sessionid;
    // 判断是否为重复问题
    private String status;
    // 提示信息
    private String reminder;
    // 返回的结果
    private List<Answers> answers;
    // 分词结果
    private List<String> segment;
    // 相似的句子
    private String similarSentence;
    // 最相似得分
    private String bestSimilarityScore;
    // 素材id
    private String mediaId;
    private String mediaSummary;

    // 素材
    private KnowledgeMedia knowledgeMedia;

    private String catId;
    // 素材类型
    private String mediaType;
    //
    private String ansType;
    // 文字菜单
    private TextMenu textMenu;

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getReminder() {
        return reminder;
    }

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;
    }

    public List<Answers> getAnswers() {
        return answers;
    }

    public void setSegment(List<String> segment) {
        this.segment = segment;
    }

    public List<String> getSegment() {
        return segment;
    }

    public void setSimilarSentence(String similarSentence) {
        this.similarSentence = similarSentence;
    }

    public String getSimilarSentence() {
        return similarSentence;
    }

    public void setBestSimilarityScore(String bestSimilarityScore) {
        this.bestSimilarityScore = bestSimilarityScore;
    }

    public String getBestSimilarityScore() {
        return bestSimilarityScore;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaSummary(String mediaSummary) {
        this.mediaSummary = mediaSummary;
    }

    public String getMediaSummary() {
        return mediaSummary;
    }

    public KnowledgeMedia getKnowledgeMedia() {
        return knowledgeMedia;
    }

    public void setKnowledgeMedia(KnowledgeMedia knowledgeMedia) {
        this.knowledgeMedia = knowledgeMedia;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getAnsType() {
        return ansType;
    }

    public void setAnsType(String ansType) {
        this.ansType = ansType;
    }

    public TextMenu getTextMenu() {
        return textMenu;
    }

    public void setTextMenu(TextMenu textMenu) {
        this.textMenu = textMenu;
    }

}
