package qa.engine.bot.sdk.ask.model;

import java.util.List;

/**
 * 地理位置信息.
 * 
 * @author panxinbing
 *
 */
public class Resolution {

    // 存贮位置信息
    private List<Values> values;

    public List<Values> getValues() {
        return values;
    }

    public void setValues(List<Values> values) {
        this.values = values;
    }

}
