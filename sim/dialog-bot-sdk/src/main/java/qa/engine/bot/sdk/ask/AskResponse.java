package qa.engine.bot.sdk.ask;

import java.util.HashMap;
import java.util.Map;

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONObject;
import qa.engine.bot.sdk.ask.model.Answers;
import qa.engine.bot.sdk.ask.model.Resolution;
import qa.engine.bot.sdk.ask.model.ResponseAnswer;

public class AskResponse implements ResponseRender {

    /* 返回结果的状态码 */
    private int status;

    // 包含了返回的json数据
    private String content;

    // 返回结果对象
    private ResponseAnswer responseAnswer;

    // 返回结果的答案
    private String answer;

    /**
     * 构造方法
     *
     * @param status
     *            传入执行结果的状态码
     * @param body
     *            传入返回结果的肢体部分
     */
    public AskResponse(int status, String body) {
        render(status, body);
    }

    /**
     * @param httpStatus
     *            传入执行结果的状态码
     * @param body
     *            传入返回结果的肢体部分
     */
    public void render(int httpStatus, String body) {
        this.status = httpStatus;
        String bodyStr = body;

        // 如果执行成功，使用xml解析数据
        if (this.status == 200) {
            this.content = bodyStr;
            parseJson(bodyStr);
            if (this.content.indexOf("?xml") > -1)
                try {
                    parseJson(bodyStr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        } else if (this.status == 401) {
            this.content = ("auth result:身份信息有误");
        } else {
            this.content = "server error";
        }
    }

    /** todo 对返回的json结果进行处理 */
    public ResponseAnswer parseJson(String inputStr) {
        JSONObject input = JSONObject.fromObject(inputStr);
        Map<String, Object> classMap = new HashMap<>();
        classMap.put("answers", Answers.class);
        classMap.put("resolution", Resolution.class);
        ResponseAnswer dataModel = (ResponseAnswer) JSONObject.toBean(input, ResponseAnswer.class, classMap);
        this.responseAnswer = dataModel;
        if (dataModel.getAnswers() != null && (!dataModel.getAnswers().isEmpty())
                && dataModel.getAnswers().size() > 0) {
            Object answerObject = dataModel.getAnswers().get(0).getAnswer();
            if (answerObject instanceof MorphDynaBean) {
                int start = ((MorphDynaBean) answerObject).toString().indexOf("{");
                int end = ((MorphDynaBean) answerObject).toString().indexOf("}");
                this.answer = ((MorphDynaBean) answerObject).toString().substring(start, end + 1);
            } else {
                this.answer = String.valueOf(answerObject);
            }
        }
        return dataModel;
    }

    /**
     * @param httpStatus
     *            传入执行结果的状态码
     * @param body
     *            传入返回结果的肢体部分
     */
    public void render(int httpStatus, byte[] body) {

    }

    public int getStatus() {
        return this.status;
    }

    public String getContent() {
        return this.content;
    }

    public ResponseAnswer getResponseAnswer() {
        return responseAnswer;
    }

    public void setResponseAnswer(ResponseAnswer responseAnswer) {
        this.responseAnswer = responseAnswer;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return super.toString();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
