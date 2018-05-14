package com.lambo.rest.client.constant;

/**
 * @ClassName OprationTypeEnum
 * @Descirption TODO
 * @Author sunzhen
 * @Date 2018/5/12 9:15
 **/
public enum OprationTypeEnum {

    SELECT_LIST("selectList",1),
    SELECT_ONE("selectOne",2),
    INSERT("insert",3),
    UPDATE("update",4),
    DELETE("delete",5),
    ;


    private String name;
    private int index;


    /**
     * 构造方法
     * @param name
     * @param index
     */
    OprationTypeEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }


}
