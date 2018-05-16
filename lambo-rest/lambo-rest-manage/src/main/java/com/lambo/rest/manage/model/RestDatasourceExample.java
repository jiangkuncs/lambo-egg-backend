package com.lambo.rest.manage.model;

import java.util.ArrayList;
import java.util.List;

public class RestDatasourceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RestDatasourceExample() {
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

        public Criteria andDsIdIsNull() {
            addCriterion("ds_id is null");
            return (Criteria) this;
        }

        public Criteria andDsIdIsNotNull() {
            addCriterion("ds_id is not null");
            return (Criteria) this;
        }

        public Criteria andDsIdEqualTo(String value) {
            addCriterion("ds_id =", value, "dsId");
            return (Criteria) this;
        }

        public Criteria andDsIdNotEqualTo(String value) {
            addCriterion("ds_id <>", value, "dsId");
            return (Criteria) this;
        }

        public Criteria andDsIdGreaterThan(String value) {
            addCriterion("ds_id >", value, "dsId");
            return (Criteria) this;
        }

        public Criteria andDsIdGreaterThanOrEqualTo(String value) {
            addCriterion("ds_id >=", value, "dsId");
            return (Criteria) this;
        }

        public Criteria andDsIdLessThan(String value) {
            addCriterion("ds_id <", value, "dsId");
            return (Criteria) this;
        }

        public Criteria andDsIdLessThanOrEqualTo(String value) {
            addCriterion("ds_id <=", value, "dsId");
            return (Criteria) this;
        }

        public Criteria andDsIdLike(String value) {
            addCriterion("ds_id like", value, "dsId");
            return (Criteria) this;
        }

        public Criteria andDsIdNotLike(String value) {
            addCriterion("ds_id not like", value, "dsId");
            return (Criteria) this;
        }

        public Criteria andDsIdIn(List<String> values) {
            addCriterion("ds_id in", values, "dsId");
            return (Criteria) this;
        }

        public Criteria andDsIdNotIn(List<String> values) {
            addCriterion("ds_id not in", values, "dsId");
            return (Criteria) this;
        }

        public Criteria andDsIdBetween(String value1, String value2) {
            addCriterion("ds_id between", value1, value2, "dsId");
            return (Criteria) this;
        }

        public Criteria andDsIdNotBetween(String value1, String value2) {
            addCriterion("ds_id not between", value1, value2, "dsId");
            return (Criteria) this;
        }

        public Criteria andDsNameIsNull() {
            addCriterion("ds_name is null");
            return (Criteria) this;
        }

        public Criteria andDsNameIsNotNull() {
            addCriterion("ds_name is not null");
            return (Criteria) this;
        }

        public Criteria andDsNameEqualTo(String value) {
            addCriterion("ds_name =", value, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameNotEqualTo(String value) {
            addCriterion("ds_name <>", value, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameGreaterThan(String value) {
            addCriterion("ds_name >", value, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameGreaterThanOrEqualTo(String value) {
            addCriterion("ds_name >=", value, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameLessThan(String value) {
            addCriterion("ds_name <", value, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameLessThanOrEqualTo(String value) {
            addCriterion("ds_name <=", value, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameLike(String value) {
            addCriterion("ds_name like", value, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameNotLike(String value) {
            addCriterion("ds_name not like", value, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameIn(List<String> values) {
            addCriterion("ds_name in", values, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameNotIn(List<String> values) {
            addCriterion("ds_name not in", values, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameBetween(String value1, String value2) {
            addCriterion("ds_name between", value1, value2, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameNotBetween(String value1, String value2) {
            addCriterion("ds_name not between", value1, value2, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsTypeIsNull() {
            addCriterion("ds_type is null");
            return (Criteria) this;
        }

        public Criteria andDsTypeIsNotNull() {
            addCriterion("ds_type is not null");
            return (Criteria) this;
        }

        public Criteria andDsTypeEqualTo(String value) {
            addCriterion("ds_type =", value, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeNotEqualTo(String value) {
            addCriterion("ds_type <>", value, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeGreaterThan(String value) {
            addCriterion("ds_type >", value, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ds_type >=", value, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeLessThan(String value) {
            addCriterion("ds_type <", value, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeLessThanOrEqualTo(String value) {
            addCriterion("ds_type <=", value, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeLike(String value) {
            addCriterion("ds_type like", value, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeNotLike(String value) {
            addCriterion("ds_type not like", value, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeIn(List<String> values) {
            addCriterion("ds_type in", values, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeNotIn(List<String> values) {
            addCriterion("ds_type not in", values, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeBetween(String value1, String value2) {
            addCriterion("ds_type between", value1, value2, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeNotBetween(String value1, String value2) {
            addCriterion("ds_type not between", value1, value2, "dsType");
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