package qa.engine.bot.sdk.ask.model;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 将json转化为bean结构.
 * 
 * @author panxinbing
 *
 */
public class JsonRootBean {
    /**
     * 将sdk调用返回的json数据转化为bean
     * 
     * @param input
     * @return
     */
    public static ResponseAnswer jsonToBean(JSONObject input) {
        Map<String, Object> classMap = new HashMap<>();
        classMap.put("answers", Answers.class);
        classMap.put("resolution", Resolution.class);
        classMap.put("knowledgeMedia", KnowledgeMedia.class);
        classMap.put("listTextMenu", MenuItem.class);
        classMap.put("menuAns", MenuAns.class);
        classMap.put("textMenu", TextMenu.class);
        ResponseAnswer dataModel = (ResponseAnswer) JSONObject.toBean(input, ResponseAnswer.class, classMap);
        return dataModel;
    }
}
