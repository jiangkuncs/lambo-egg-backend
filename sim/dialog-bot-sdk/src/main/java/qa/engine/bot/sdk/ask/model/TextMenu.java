package qa.engine.bot.sdk.ask.model;

import java.util.List;

/**
 * 文字菜单.
 * 
 * @author panxinbing
 *
 */
public class TextMenu {

    // 文字菜单ID
    private String menuId;
    // 开始语
    private String beginStatement;
    // 结束语
    private String endStatement;
    // 文字菜单对象
    private List<MenuItem> listTextMenu;
    // 文字菜单类型
    private String menuType;
    // 答案ID（当文字菜单类型是FOLD为NULL，当文字菜单类型是ITEM为答案ID）
    private String ansId;

    private MenuAns menuAns;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getBeginStatement() {
        return beginStatement;
    }

    public void setBeginStatement(String beginStatement) {
        this.beginStatement = beginStatement;
    }

    public String getEndStatement() {
        return endStatement;
    }

    public void setEndStatement(String endStatement) {
        this.endStatement = endStatement;
    }

    public List<MenuItem> getListTextMenu() {
        return listTextMenu;
    }

    public void setListTextMenu(List<MenuItem> listTextMenu) {
        this.listTextMenu = listTextMenu;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getAnsId() {
        return ansId;
    }

    public void setAnsId(String ansId) {
        this.ansId = ansId;
    }

    public MenuAns getMenuAns() {
        return menuAns;
    }

    public void setMenuAns(MenuAns menuAns) {
        this.menuAns = menuAns;
    }

}
