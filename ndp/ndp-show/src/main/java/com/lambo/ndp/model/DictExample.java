package com.lambo.ndp.model;

import java.util.ArrayList;
import java.util.List;

public class DictExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DictExample() {
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

        public Criteria andDictIdIsNull() {
            addCriterion("DICT_ID is null");
            return (Criteria) this;
        }

        public Criteria andDictIdIsNotNull() {
            addCriterion("DICT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDictIdEqualTo(String value) {
            addCriterion("DICT_ID =", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdNotEqualTo(String value) {
            addCriterion("DICT_ID <>", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdGreaterThan(String value) {
            addCriterion("DICT_ID >", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdGreaterThanOrEqualTo(String value) {
            addCriterion("DICT_ID >=", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdLessThan(String value) {
            addCriterion("DICT_ID <", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdLessThanOrEqualTo(String value) {
            addCriterion("DICT_ID <=", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdLike(String value) {
            addCriterion("DICT_ID like", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdNotLike(String value) {
            addCriterion("DICT_ID not like", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdIn(List<String> values) {
            addCriterion("DICT_ID in", values, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdNotIn(List<String> values) {
            addCriterion("DICT_ID not in", values, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdBetween(String value1, String value2) {
            addCriterion("DICT_ID between", value1, value2, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdNotBetween(String value1, String value2) {
            addCriterion("DICT_ID not between", value1, value2, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictKeyIsNull() {
            addCriterion("DICT_KEY is null");
            return (Criteria) this;
        }

        public Criteria andDictKeyIsNotNull() {
            addCriterion("DICT_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andDictKeyEqualTo(String value) {
            addCriterion("DICT_KEY =", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyNotEqualTo(String value) {
            addCriterion("DICT_KEY <>", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyGreaterThan(String value) {
            addCriterion("DICT_KEY >", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyGreaterThanOrEqualTo(String value) {
            addCriterion("DICT_KEY >=", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyLessThan(String value) {
            addCriterion("DICT_KEY <", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyLessThanOrEqualTo(String value) {
            addCriterion("DICT_KEY <=", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyLike(String value) {
            addCriterion("DICT_KEY like", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyNotLike(String value) {
            addCriterion("DICT_KEY not like", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyIn(List<String> values) {
            addCriterion("DICT_KEY in", values, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyNotIn(List<String> values) {
            addCriterion("DICT_KEY not in", values, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyBetween(String value1, String value2) {
            addCriterion("DICT_KEY between", value1, value2, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyNotBetween(String value1, String value2) {
            addCriterion("DICT_KEY not between", value1, value2, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictNameIsNull() {
            addCriterion("DICT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDictNameIsNotNull() {
            addCriterion("DICT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDictNameEqualTo(String value) {
            addCriterion("DICT_NAME =", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameNotEqualTo(String value) {
            addCriterion("DICT_NAME <>", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameGreaterThan(String value) {
            addCriterion("DICT_NAME >", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameGreaterThanOrEqualTo(String value) {
            addCriterion("DICT_NAME >=", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameLessThan(String value) {
            addCriterion("DICT_NAME <", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameLessThanOrEqualTo(String value) {
            addCriterion("DICT_NAME <=", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameLike(String value) {
            addCriterion("DICT_NAME like", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameNotLike(String value) {
            addCriterion("DICT_NAME not like", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameIn(List<String> values) {
            addCriterion("DICT_NAME in", values, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameNotIn(List<String> values) {
            addCriterion("DICT_NAME not in", values, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameBetween(String value1, String value2) {
            addCriterion("DICT_NAME between", value1, value2, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameNotBetween(String value1, String value2) {
            addCriterion("DICT_NAME not between", value1, value2, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictDescIsNull() {
            addCriterion("DICT_DESC is null");
            return (Criteria) this;
        }

        public Criteria andDictDescIsNotNull() {
            addCriterion("DICT_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andDictDescEqualTo(String value) {
            addCriterion("DICT_DESC =", value, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescNotEqualTo(String value) {
            addCriterion("DICT_DESC <>", value, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescGreaterThan(String value) {
            addCriterion("DICT_DESC >", value, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescGreaterThanOrEqualTo(String value) {
            addCriterion("DICT_DESC >=", value, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescLessThan(String value) {
            addCriterion("DICT_DESC <", value, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescLessThanOrEqualTo(String value) {
            addCriterion("DICT_DESC <=", value, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescLike(String value) {
            addCriterion("DICT_DESC like", value, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescNotLike(String value) {
            addCriterion("DICT_DESC not like", value, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescIn(List<String> values) {
            addCriterion("DICT_DESC in", values, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescNotIn(List<String> values) {
            addCriterion("DICT_DESC not in", values, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescBetween(String value1, String value2) {
            addCriterion("DICT_DESC between", value1, value2, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescNotBetween(String value1, String value2) {
            addCriterion("DICT_DESC not between", value1, value2, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictValueIsNull() {
            addCriterion("DICT_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andDictValueIsNotNull() {
            addCriterion("DICT_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andDictValueEqualTo(String value) {
            addCriterion("DICT_VALUE =", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueNotEqualTo(String value) {
            addCriterion("DICT_VALUE <>", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueGreaterThan(String value) {
            addCriterion("DICT_VALUE >", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueGreaterThanOrEqualTo(String value) {
            addCriterion("DICT_VALUE >=", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueLessThan(String value) {
            addCriterion("DICT_VALUE <", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueLessThanOrEqualTo(String value) {
            addCriterion("DICT_VALUE <=", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueLike(String value) {
            addCriterion("DICT_VALUE like", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueNotLike(String value) {
            addCriterion("DICT_VALUE not like", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueIn(List<String> values) {
            addCriterion("DICT_VALUE in", values, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueNotIn(List<String> values) {
            addCriterion("DICT_VALUE not in", values, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueBetween(String value1, String value2) {
            addCriterion("DICT_VALUE between", value1, value2, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueNotBetween(String value1, String value2) {
            addCriterion("DICT_VALUE not between", value1, value2, "dictValue");
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