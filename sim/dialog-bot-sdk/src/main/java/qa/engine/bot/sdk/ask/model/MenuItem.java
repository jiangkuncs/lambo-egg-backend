package qa.engine.bot.sdk.ask.model;

import java.sql.Timestamp;

/**
 * 文字菜单的子菜单.
 * 
 * @author panxinbing
 *
 */
public class MenuItem {
    // 文字菜单ID
    private String menuId;
    // 文字菜单父项ID
    private String parentId;
    // 文字菜单类型（FOLD或ITEM）
    private String menuType;
    // 文字菜单名称
    private String menuName;
    // 答案ID（当文字菜单类型是FOLD为NULL，当文字菜单类型是ITEM为答案ID）
    private String ansId;
    // 文字菜单层级序列
    private String menuSequence;
    // 开始语
    private String beginStatement;
    // 结束语
    private String endSatement;
    // 是否激活
    private String isActive;
    // 租户ID
    private String tenantId;
    // 用户ID
    private String userId;
    // 创建时间
    private Timestamp createTime;
    // 更新时间
    private Timestamp updateTime;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getAnsId() {
        return ansId;
    }

    public void setAnsId(String ansId) {
        this.ansId = ansId;
    }

    public String getMenuSequence() {
        return menuSequence;
    }

    public void setMenuSequence(String menuSequence) {
        this.menuSequence = menuSequence;
    }

    public String getBeginStatement() {
        return beginStatement;
    }

    public void setBeginStatement(String beginStatement) {
        this.beginStatement = beginStatement;
    }

    public String getEndSatement() {
        return endSatement;
    }

    public void setEndSatement(String endSatement) {
        this.endSatement = endSatement;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

}
