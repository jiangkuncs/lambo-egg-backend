package qa.engine.bot.sdk.ask.model;

/**
 * 地点标记.
 * 
 * @author panxinbing
 *
 */
public class Values {

    // 地区代码编号
    private String code;
    // 地区名称
    private String name;
    // 类型
    private String type;
    // 经度
    private String longitude;
    // 纬度
    private String latitude;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

}
