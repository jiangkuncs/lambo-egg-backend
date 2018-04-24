package com.lambo.ndp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedBackExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FeedBackExample() {
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

        public Criteria andRecordidIsNull() {
            addCriterion("recordId is null");
            return (Criteria) this;
        }

        public Criteria andRecordidIsNotNull() {
            addCriterion("recordId is not null");
            return (Criteria) this;
        }

        public Criteria andRecordidEqualTo(Integer value) {
            addCriterion("recordId =", value, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidNotEqualTo(Integer value) {
            addCriterion("recordId <>", value, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidGreaterThan(Integer value) {
            addCriterion("recordId >", value, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidGreaterThanOrEqualTo(Integer value) {
            addCriterion("recordId >=", value, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidLessThan(Integer value) {
            addCriterion("recordId <", value, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidLessThanOrEqualTo(Integer value) {
            addCriterion("recordId <=", value, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidIn(List<Integer> values) {
            addCriterion("recordId in", values, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidNotIn(List<Integer> values) {
            addCriterion("recordId not in", values, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidBetween(Integer value1, Integer value2) {
            addCriterion("recordId between", value1, value2, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidNotBetween(Integer value1, Integer value2) {
            addCriterion("recordId not between", value1, value2, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordobjectidIsNull() {
            addCriterion("recordObjectId is null");
            return (Criteria) this;
        }

        public Criteria andRecordobjectidIsNotNull() {
            addCriterion("recordObjectId is not null");
            return (Criteria) this;
        }

        public Criteria andRecordobjectidEqualTo(String value) {
            addCriterion("recordObjectId =", value, "recordobjectid");
            return (Criteria) this;
        }

        public Criteria andRecordobjectidNotEqualTo(String value) {
            addCriterion("recordObjectId <>", value, "recordobjectid");
            return (Criteria) this;
        }

        public Criteria andRecordobjectidGreaterThan(String value) {
            addCriterion("recordObjectId >", value, "recordobjectid");
            return (Criteria) this;
        }

        public Criteria andRecordobjectidGreaterThanOrEqualTo(String value) {
            addCriterion("recordObjectId >=", value, "recordobjectid");
            return (Criteria) this;
        }

        public Criteria andRecordobjectidLessThan(String value) {
            addCriterion("recordObjectId <", value, "recordobjectid");
            return (Criteria) this;
        }

        public Criteria andRecordobjectidLessThanOrEqualTo(String value) {
            addCriterion("recordObjectId <=", value, "recordobjectid");
            return (Criteria) this;
        }

        public Criteria andRecordobjectidLike(String value) {
            addCriterion("recordObjectId like", value, "recordobjectid");
            return (Criteria) this;
        }

        public Criteria andRecordobjectidNotLike(String value) {
            addCriterion("recordObjectId not like", value, "recordobjectid");
            return (Criteria) this;
        }

        public Criteria andRecordobjectidIn(List<String> values) {
            addCriterion("recordObjectId in", values, "recordobjectid");
            return (Criteria) this;
        }

        public Criteria andRecordobjectidNotIn(List<String> values) {
            addCriterion("recordObjectId not in", values, "recordobjectid");
            return (Criteria) this;
        }

        public Criteria andRecordobjectidBetween(String value1, String value2) {
            addCriterion("recordObjectId between", value1, value2, "recordobjectid");
            return (Criteria) this;
        }

        public Criteria andRecordobjectidNotBetween(String value1, String value2) {
            addCriterion("recordObjectId not between", value1, value2, "recordobjectid");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andRatecountIsNull() {
            addCriterion("rateCount is null");
            return (Criteria) this;
        }

        public Criteria andRatecountIsNotNull() {
            addCriterion("rateCount is not null");
            return (Criteria) this;
        }

        public Criteria andRatecountEqualTo(Integer value) {
            addCriterion("rateCount =", value, "ratecount");
            return (Criteria) this;
        }

        public Criteria andRatecountNotEqualTo(Integer value) {
            addCriterion("rateCount <>", value, "ratecount");
            return (Criteria) this;
        }

        public Criteria andRatecountGreaterThan(Integer value) {
            addCriterion("rateCount >", value, "ratecount");
            return (Criteria) this;
        }

        public Criteria andRatecountGreaterThanOrEqualTo(Integer value) {
            addCriterion("rateCount >=", value, "ratecount");
            return (Criteria) this;
        }

        public Criteria andRatecountLessThan(Integer value) {
            addCriterion("rateCount <", value, "ratecount");
            return (Criteria) this;
        }

        public Criteria andRatecountLessThanOrEqualTo(Integer value) {
            addCriterion("rateCount <=", value, "ratecount");
            return (Criteria) this;
        }

        public Criteria andRatecountIn(List<Integer> values) {
            addCriterion("rateCount in", values, "ratecount");
            return (Criteria) this;
        }

        public Criteria andRatecountNotIn(List<Integer> values) {
            addCriterion("rateCount not in", values, "ratecount");
            return (Criteria) this;
        }

        public Criteria andRatecountBetween(Integer value1, Integer value2) {
            addCriterion("rateCount between", value1, value2, "ratecount");
            return (Criteria) this;
        }

        public Criteria andRatecountNotBetween(Integer value1, Integer value2) {
            addCriterion("rateCount not between", value1, value2, "ratecount");
            return (Criteria) this;
        }

        public Criteria andRecorduserIsNull() {
            addCriterion("recordUser is null");
            return (Criteria) this;
        }

        public Criteria andRecorduserIsNotNull() {
            addCriterion("recordUser is not null");
            return (Criteria) this;
        }

        public Criteria andRecorduserEqualTo(String value) {
            addCriterion("recordUser =", value, "recorduser");
            return (Criteria) this;
        }

        public Criteria andRecorduserNotEqualTo(String value) {
            addCriterion("recordUser <>", value, "recorduser");
            return (Criteria) this;
        }

        public Criteria andRecorduserGreaterThan(String value) {
            addCriterion("recordUser >", value, "recorduser");
            return (Criteria) this;
        }

        public Criteria andRecorduserGreaterThanOrEqualTo(String value) {
            addCriterion("recordUser >=", value, "recorduser");
            return (Criteria) this;
        }

        public Criteria andRecorduserLessThan(String value) {
            addCriterion("recordUser <", value, "recorduser");
            return (Criteria) this;
        }

        public Criteria andRecorduserLessThanOrEqualTo(String value) {
            addCriterion("recordUser <=", value, "recorduser");
            return (Criteria) this;
        }

        public Criteria andRecorduserLike(String value) {
            addCriterion("recordUser like", value, "recorduser");
            return (Criteria) this;
        }

        public Criteria andRecorduserNotLike(String value) {
            addCriterion("recordUser not like", value, "recorduser");
            return (Criteria) this;
        }

        public Criteria andRecorduserIn(List<String> values) {
            addCriterion("recordUser in", values, "recorduser");
            return (Criteria) this;
        }

        public Criteria andRecorduserNotIn(List<String> values) {
            addCriterion("recordUser not in", values, "recorduser");
            return (Criteria) this;
        }

        public Criteria andRecorduserBetween(String value1, String value2) {
            addCriterion("recordUser between", value1, value2, "recorduser");
            return (Criteria) this;
        }

        public Criteria andRecorduserNotBetween(String value1, String value2) {
            addCriterion("recordUser not between", value1, value2, "recorduser");
            return (Criteria) this;
        }

        public Criteria andRecorddateIsNull() {
            addCriterion("recordDate is null");
            return (Criteria) this;
        }

        public Criteria andRecorddateIsNotNull() {
            addCriterion("recordDate is not null");
            return (Criteria) this;
        }

        public Criteria andRecorddateEqualTo(Date value) {
            addCriterion("recordDate =", value, "recorddate");
            return (Criteria) this;
        }

        public Criteria andRecorddateNotEqualTo(Date value) {
            addCriterion("recordDate <>", value, "recorddate");
            return (Criteria) this;
        }

        public Criteria andRecorddateGreaterThan(Date value) {
            addCriterion("recordDate >", value, "recorddate");
            return (Criteria) this;
        }

        public Criteria andRecorddateGreaterThanOrEqualTo(Date value) {
            addCriterion("recordDate >=", value, "recorddate");
            return (Criteria) this;
        }

        public Criteria andRecorddateLessThan(Date value) {
            addCriterion("recordDate <", value, "recorddate");
            return (Criteria) this;
        }

        public Criteria andRecorddateLessThanOrEqualTo(Date value) {
            addCriterion("recordDate <=", value, "recorddate");
            return (Criteria) this;
        }

        public Criteria andRecorddateIn(List<Date> values) {
            addCriterion("recordDate in", values, "recorddate");
            return (Criteria) this;
        }

        public Criteria andRecorddateNotIn(List<Date> values) {
            addCriterion("recordDate not in", values, "recorddate");
            return (Criteria) this;
        }

        public Criteria andRecorddateBetween(Date value1, Date value2) {
            addCriterion("recordDate between", value1, value2, "recorddate");
            return (Criteria) this;
        }

        public Criteria andRecorddateNotBetween(Date value1, Date value2) {
            addCriterion("recordDate not between", value1, value2, "recorddate");
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