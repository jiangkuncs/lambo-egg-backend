package com.lambo.rest.manage.model;

import java.util.ArrayList;
import java.util.List;

public class RestStruExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RestStruExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andStruIdIsNull() {
            addCriterion("stru_id is null");
            return (Criteria) this;
        }

        public Criteria andStruIdIsNotNull() {
            addCriterion("stru_id is not null");
            return (Criteria) this;
        }

        public Criteria andStruIdEqualTo(String value) {
            addCriterion("stru_id =", value, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdNotEqualTo(String value) {
            addCriterion("stru_id <>", value, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdGreaterThan(String value) {
            addCriterion("stru_id >", value, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdGreaterThanOrEqualTo(String value) {
            addCriterion("stru_id >=", value, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdLessThan(String value) {
            addCriterion("stru_id <", value, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdLessThanOrEqualTo(String value) {
            addCriterion("stru_id <=", value, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdLike(String value) {
            addCriterion("stru_id like", value, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdNotLike(String value) {
            addCriterion("stru_id not like", value, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdIn(List<String> values) {
            addCriterion("stru_id in", values, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdNotIn(List<String> values) {
            addCriterion("stru_id not in", values, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdBetween(String value1, String value2) {
            addCriterion("stru_id between", value1, value2, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdNotBetween(String value1, String value2) {
            addCriterion("stru_id not between", value1, value2, "struId");
            return (Criteria) this;
        }

        public Criteria andStruNameIsNull() {
            addCriterion("stru_name is null");
            return (Criteria) this;
        }

        public Criteria andStruNameIsNotNull() {
            addCriterion("stru_name is not null");
            return (Criteria) this;
        }

        public Criteria andStruNameEqualTo(String value) {
            addCriterion("stru_name =", value, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameNotEqualTo(String value) {
            addCriterion("stru_name <>", value, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameGreaterThan(String value) {
            addCriterion("stru_name >", value, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameGreaterThanOrEqualTo(String value) {
            addCriterion("stru_name >=", value, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameLessThan(String value) {
            addCriterion("stru_name <", value, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameLessThanOrEqualTo(String value) {
            addCriterion("stru_name <=", value, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameLike(String value) {
            addCriterion("stru_name like", value, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameNotLike(String value) {
            addCriterion("stru_name not like", value, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameIn(List<String> values) {
            addCriterion("stru_name in", values, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameNotIn(List<String> values) {
            addCriterion("stru_name not in", values, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameBetween(String value1, String value2) {
            addCriterion("stru_name between", value1, value2, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameNotBetween(String value1, String value2) {
            addCriterion("stru_name not between", value1, value2, "struName");
            return (Criteria) this;
        }

        public Criteria andStruUrlIsNull() {
            addCriterion("stru_url is null");
            return (Criteria) this;
        }

        public Criteria andStruUrlIsNotNull() {
            addCriterion("stru_url is not null");
            return (Criteria) this;
        }

        public Criteria andStruUrlEqualTo(String value) {
            addCriterion("stru_url =", value, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlNotEqualTo(String value) {
            addCriterion("stru_url <>", value, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlGreaterThan(String value) {
            addCriterion("stru_url >", value, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlGreaterThanOrEqualTo(String value) {
            addCriterion("stru_url >=", value, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlLessThan(String value) {
            addCriterion("stru_url <", value, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlLessThanOrEqualTo(String value) {
            addCriterion("stru_url <=", value, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlLike(String value) {
            addCriterion("stru_url like", value, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlNotLike(String value) {
            addCriterion("stru_url not like", value, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlIn(List<String> values) {
            addCriterion("stru_url in", values, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlNotIn(List<String> values) {
            addCriterion("stru_url not in", values, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlBetween(String value1, String value2) {
            addCriterion("stru_url between", value1, value2, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlNotBetween(String value1, String value2) {
            addCriterion("stru_url not between", value1, value2, "struUrl");
            return (Criteria) this;
        }

        public Criteria andIsLeafIsNull() {
            addCriterion("is_leaf is null");
            return (Criteria) this;
        }

        public Criteria andIsLeafIsNotNull() {
            addCriterion("is_leaf is not null");
            return (Criteria) this;
        }

        public Criteria andIsLeafEqualTo(String value) {
            addCriterion("is_leaf =", value, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafNotEqualTo(String value) {
            addCriterion("is_leaf <>", value, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafGreaterThan(String value) {
            addCriterion("is_leaf >", value, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafGreaterThanOrEqualTo(String value) {
            addCriterion("is_leaf >=", value, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafLessThan(String value) {
            addCriterion("is_leaf <", value, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafLessThanOrEqualTo(String value) {
            addCriterion("is_leaf <=", value, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafLike(String value) {
            addCriterion("is_leaf like", value, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafNotLike(String value) {
            addCriterion("is_leaf not like", value, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafIn(List<String> values) {
            addCriterion("is_leaf in", values, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafNotIn(List<String> values) {
            addCriterion("is_leaf not in", values, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafBetween(String value1, String value2) {
            addCriterion("is_leaf between", value1, value2, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafNotBetween(String value1, String value2) {
            addCriterion("is_leaf not between", value1, value2, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andRestIdIsNull() {
            addCriterion("rest_id is null");
            return (Criteria) this;
        }

        public Criteria andRestIdIsNotNull() {
            addCriterion("rest_id is not null");
            return (Criteria) this;
        }

        public Criteria andRestIdEqualTo(String value) {
            addCriterion("rest_id =", value, "restId");
            return (Criteria) this;
        }

        public Criteria andRestIdNotEqualTo(String value) {
            addCriterion("rest_id <>", value, "restId");
            return (Criteria) this;
        }

        public Criteria andRestIdGreaterThan(String value) {
            addCriterion("rest_id >", value, "restId");
            return (Criteria) this;
        }

        public Criteria andRestIdGreaterThanOrEqualTo(String value) {
            addCriterion("rest_id >=", value, "restId");
            return (Criteria) this;
        }

        public Criteria andRestIdLessThan(String value) {
            addCriterion("rest_id <", value, "restId");
            return (Criteria) this;
        }

        public Criteria andRestIdLessThanOrEqualTo(String value) {
            addCriterion("rest_id <=", value, "restId");
            return (Criteria) this;
        }

        public Criteria andRestIdLike(String value) {
            addCriterion("rest_id like", value, "restId");
            return (Criteria) this;
        }

        public Criteria andRestIdNotLike(String value) {
            addCriterion("rest_id not like", value, "restId");
            return (Criteria) this;
        }

        public Criteria andRestIdIn(List<String> values) {
            addCriterion("rest_id in", values, "restId");
            return (Criteria) this;
        }

        public Criteria andRestIdNotIn(List<String> values) {
            addCriterion("rest_id not in", values, "restId");
            return (Criteria) this;
        }

        public Criteria andRestIdBetween(String value1, String value2) {
            addCriterion("rest_id between", value1, value2, "restId");
            return (Criteria) this;
        }

        public Criteria andRestIdNotBetween(String value1, String value2) {
            addCriterion("rest_id not between", value1, value2, "restId");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLike(String value) {
            addCriterion("parent_id like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotLike(String value) {
            addCriterion("parent_id not like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<String> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andIsUseIsNull() {
            addCriterion("is_use is null");
            return (Criteria) this;
        }

        public Criteria andIsUseIsNotNull() {
            addCriterion("is_use is not null");
            return (Criteria) this;
        }

        public Criteria andIsUseEqualTo(String value) {
            addCriterion("is_use =", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotEqualTo(String value) {
            addCriterion("is_use <>", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseGreaterThan(String value) {
            addCriterion("is_use >", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseGreaterThanOrEqualTo(String value) {
            addCriterion("is_use >=", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLessThan(String value) {
            addCriterion("is_use <", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLessThanOrEqualTo(String value) {
            addCriterion("is_use <=", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLike(String value) {
            addCriterion("is_use like", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotLike(String value) {
            addCriterion("is_use not like", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseIn(List<String> values) {
            addCriterion("is_use in", values, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotIn(List<String> values) {
            addCriterion("is_use not in", values, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseBetween(String value1, String value2) {
            addCriterion("is_use between", value1, value2, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotBetween(String value1, String value2) {
            addCriterion("is_use not between", value1, value2, "isUse");
            return (Criteria) this;
        }

        public Criteria andOrderSeqIsNull() {
            addCriterion("order_seq is null");
            return (Criteria) this;
        }

        public Criteria andOrderSeqIsNotNull() {
            addCriterion("order_seq is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSeqEqualTo(Integer value) {
            addCriterion("order_seq =", value, "orderSeq");
            return (Criteria) this;
        }

        public Criteria andOrderSeqNotEqualTo(Integer value) {
            addCriterion("order_seq <>", value, "orderSeq");
            return (Criteria) this;
        }

        public Criteria andOrderSeqGreaterThan(Integer value) {
            addCriterion("order_seq >", value, "orderSeq");
            return (Criteria) this;
        }

        public Criteria andOrderSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_seq >=", value, "orderSeq");
            return (Criteria) this;
        }

        public Criteria andOrderSeqLessThan(Integer value) {
            addCriterion("order_seq <", value, "orderSeq");
            return (Criteria) this;
        }

        public Criteria andOrderSeqLessThanOrEqualTo(Integer value) {
            addCriterion("order_seq <=", value, "orderSeq");
            return (Criteria) this;
        }

        public Criteria andOrderSeqIn(List<Integer> values) {
            addCriterion("order_seq in", values, "orderSeq");
            return (Criteria) this;
        }

        public Criteria andOrderSeqNotIn(List<Integer> values) {
            addCriterion("order_seq not in", values, "orderSeq");
            return (Criteria) this;
        }

        public Criteria andOrderSeqBetween(Integer value1, Integer value2) {
            addCriterion("order_seq between", value1, value2, "orderSeq");
            return (Criteria) this;
        }

        public Criteria andOrderSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("order_seq not between", value1, value2, "orderSeq");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}