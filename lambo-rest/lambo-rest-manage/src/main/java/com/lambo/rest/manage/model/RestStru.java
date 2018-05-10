package com.lambo.rest.manage.model;

public class RestStru {
    private String struId;

    private String struName;

    private String isLeaf;

    private String restId;

    private String parentId;

    private String isUse;

    public String getStruId() {
        return struId;
    }

    public void setStruId(String struId) {
        this.struId = struId == null ? null : struId.trim();
    }

    public String getStruName() {
        return struName;
    }

    public void setStruName(String struName) {
        this.struName = struName == null ? null : struName.trim();
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf == null ? null : isLeaf.trim();
    }

    public String getRestId() {
        return restId;
    }

    public void setRestId(String restId) {
        this.restId = restId == null ? null : restId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse == null ? null : isUse.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", struId=").append(struId);
        sb.append(", struName=").append(struName);
        sb.append(", isLeaf=").append(isLeaf);
        sb.append(", restId=").append(restId);
        sb.append(", parentId=").append(parentId);
        sb.append(", isUse=").append(isUse);
        sb.append("]");
        return sb.toString();
    }
}