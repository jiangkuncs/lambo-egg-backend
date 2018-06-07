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

        public Criteria andDsIpIsNull() {
            addCriterion("ds_ip is null");
            return (Criteria) this;
        }

        public Criteria andDsIpIsNotNull() {
            addCriterion("ds_ip is not null");
            return (Criteria) this;
        }

        public Criteria andDsIpEqualTo(String value) {
            addCriterion("ds_ip =", value, "dsIp");
            return (Criteria) this;
        }

        public Criteria andDsIpNotEqualTo(String value) {
            addCriterion("ds_ip <>", value, "dsIp");
            return (Criteria) this;
        }

        public Criteria andDsIpGreaterThan(String value) {
            addCriterion("ds_ip >", value, "dsIp");
            return (Criteria) this;
        }

        public Criteria andDsIpGreaterThanOrEqualTo(String value) {
            addCriterion("ds_ip >=", value, "dsIp");
            return (Criteria) this;
        }

        public Criteria andDsIpLessThan(String value) {
            addCriterion("ds_ip <", value, "dsIp");
            return (Criteria) this;
        }

        public Criteria andDsIpLessThanOrEqualTo(String value) {
            addCriterion("ds_ip <=", value, "dsIp");
            return (Criteria) this;
        }

        public Criteria andDsIpLike(String value) {
            addCriterion("ds_ip like", value, "dsIp");
            return (Criteria) this;
        }

        public Criteria andDsIpNotLike(String value) {
            addCriterion("ds_ip not like", value, "dsIp");
            return (Criteria) this;
        }

        public Criteria andDsIpIn(List<String> values) {
            addCriterion("ds_ip in", values, "dsIp");
            return (Criteria) this;
        }

        public Criteria andDsIpNotIn(List<String> values) {
            addCriterion("ds_ip not in", values, "dsIp");
            return (Criteria) this;
        }

        public Criteria andDsIpBetween(String value1, String value2) {
            addCriterion("ds_ip between", value1, value2, "dsIp");
            return (Criteria) this;
        }

        public Criteria andDsIpNotBetween(String value1, String value2) {
            addCriterion("ds_ip not between", value1, value2, "dsIp");
            return (Criteria) this;
        }

        public Criteria andDsPortIsNull() {
            addCriterion("ds_port is null");
            return (Criteria) this;
        }

        public Criteria andDsPortIsNotNull() {
            addCriterion("ds_port is not null");
            return (Criteria) this;
        }

        public Criteria andDsPortEqualTo(Integer value) {
            addCriterion("ds_port =", value, "dsPort");
            return (Criteria) this;
        }

        public Criteria andDsPortNotEqualTo(Integer value) {
            addCriterion("ds_port <>", value, "dsPort");
            return (Criteria) this;
        }

        public Criteria andDsPortGreaterThan(Integer value) {
            addCriterion("ds_port >", value, "dsPort");
            return (Criteria) this;
        }

        public Criteria andDsPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("ds_port >=", value, "dsPort");
            return (Criteria) this;
        }

        public Criteria andDsPortLessThan(Integer value) {
            addCriterion("ds_port <", value, "dsPort");
            return (Criteria) this;
        }

        public Criteria andDsPortLessThanOrEqualTo(Integer value) {
            addCriterion("ds_port <=", value, "dsPort");
            return (Criteria) this;
        }

        public Criteria andDsPortIn(List<Integer> values) {
            addCriterion("ds_port in", values, "dsPort");
            return (Criteria) this;
        }

        public Criteria andDsPortNotIn(List<Integer> values) {
            addCriterion("ds_port not in", values, "dsPort");
            return (Criteria) this;
        }

        public Criteria andDsPortBetween(Integer value1, Integer value2) {
            addCriterion("ds_port between", value1, value2, "dsPort");
            return (Criteria) this;
        }

        public Criteria andDsPortNotBetween(Integer value1, Integer value2) {
            addCriterion("ds_port not between", value1, value2, "dsPort");
            return (Criteria) this;
        }

        public Criteria andDsDatabaseIsNull() {
            addCriterion("ds_database is null");
            return (Criteria) this;
        }

        public Criteria andDsDatabaseIsNotNull() {
            addCriterion("ds_database is not null");
            return (Criteria) this;
        }

        public Criteria andDsDatabaseEqualTo(String value) {
            addCriterion("ds_database =", value, "dsDatabase");
            return (Criteria) this;
        }

        public Criteria andDsDatabaseNotEqualTo(String value) {
            addCriterion("ds_database <>", value, "dsDatabase");
            return (Criteria) this;
        }

        public Criteria andDsDatabaseGreaterThan(String value) {
            addCriterion("ds_database >", value, "dsDatabase");
            return (Criteria) this;
        }

        public Criteria andDsDatabaseGreaterThanOrEqualTo(String value) {
            addCriterion("ds_database >=", value, "dsDatabase");
            return (Criteria) this;
        }

        public Criteria andDsDatabaseLessThan(String value) {
            addCriterion("ds_database <", value, "dsDatabase");
            return (Criteria) this;
        }

        public Criteria andDsDatabaseLessThanOrEqualTo(String value) {
            addCriterion("ds_database <=", value, "dsDatabase");
            return (Criteria) this;
        }

        public Criteria andDsDatabaseLike(String value) {
            addCriterion("ds_database like", value, "dsDatabase");
            return (Criteria) this;
        }

        public Criteria andDsDatabaseNotLike(String value) {
            addCriterion("ds_database not like", value, "dsDatabase");
            return (Criteria) this;
        }

        public Criteria andDsDatabaseIn(List<String> values) {
            addCriterion("ds_database in", values, "dsDatabase");
            return (Criteria) this;
        }

        public Criteria andDsDatabaseNotIn(List<String> values) {
            addCriterion("ds_database not in", values, "dsDatabase");
            return (Criteria) this;
        }

        public Criteria andDsDatabaseBetween(String value1, String value2) {
            addCriterion("ds_database between", value1, value2, "dsDatabase");
            return (Criteria) this;
        }

        public Criteria andDsDatabaseNotBetween(String value1, String value2) {
            addCriterion("ds_database not between", value1, value2, "dsDatabase");
            return (Criteria) this;
        }

        public Criteria andDsUserIsNull() {
            addCriterion("ds_user is null");
            return (Criteria) this;
        }

        public Criteria andDsUserIsNotNull() {
            addCriterion("ds_user is not null");
            return (Criteria) this;
        }

        public Criteria andDsUserEqualTo(String value) {
            addCriterion("ds_user =", value, "dsUser");
            return (Criteria) this;
        }

        public Criteria andDsUserNotEqualTo(String value) {
            addCriterion("ds_user <>", value, "dsUser");
            return (Criteria) this;
        }

        public Criteria andDsUserGreaterThan(String value) {
            addCriterion("ds_user >", value, "dsUser");
            return (Criteria) this;
        }

        public Criteria andDsUserGreaterThanOrEqualTo(String value) {
            addCriterion("ds_user >=", value, "dsUser");
            return (Criteria) this;
        }

        public Criteria andDsUserLessThan(String value) {
            addCriterion("ds_user <", value, "dsUser");
            return (Criteria) this;
        }

        public Criteria andDsUserLessThanOrEqualTo(String value) {
            addCriterion("ds_user <=", value, "dsUser");
            return (Criteria) this;
        }

        public Criteria andDsUserLike(String value) {
            addCriterion("ds_user like", value, "dsUser");
            return (Criteria) this;
        }

        public Criteria andDsUserNotLike(String value) {
            addCriterion("ds_user not like", value, "dsUser");
            return (Criteria) this;
        }

        public Criteria andDsUserIn(List<String> values) {
            addCriterion("ds_user in", values, "dsUser");
            return (Criteria) this;
        }

        public Criteria andDsUserNotIn(List<String> values) {
            addCriterion("ds_user not in", values, "dsUser");
            return (Criteria) this;
        }

        public Criteria andDsUserBetween(String value1, String value2) {
            addCriterion("ds_user between", value1, value2, "dsUser");
            return (Criteria) this;
        }

        public Criteria andDsUserNotBetween(String value1, String value2) {
            addCriterion("ds_user not between", value1, value2, "dsUser");
            return (Criteria) this;
        }

        public Criteria andDsPasswordIsNull() {
            addCriterion("ds_password is null");
            return (Criteria) this;
        }

        public Criteria andDsPasswordIsNotNull() {
            addCriterion("ds_password is not null");
            return (Criteria) this;
        }

        public Criteria andDsPasswordEqualTo(String value) {
            addCriterion("ds_password =", value, "dsPassword");
            return (Criteria) this;
        }

        public Criteria andDsPasswordNotEqualTo(String value) {
            addCriterion("ds_password <>", value, "dsPassword");
            return (Criteria) this;
        }

        public Criteria andDsPasswordGreaterThan(String value) {
            addCriterion("ds_password >", value, "dsPassword");
            return (Criteria) this;
        }

        public Criteria andDsPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("ds_password >=", value, "dsPassword");
            return (Criteria) this;
        }

        public Criteria andDsPasswordLessThan(String value) {
            addCriterion("ds_password <", value, "dsPassword");
            return (Criteria) this;
        }

        public Criteria andDsPasswordLessThanOrEqualTo(String value) {
            addCriterion("ds_password <=", value, "dsPassword");
            return (Criteria) this;
        }

        public Criteria andDsPasswordLike(String value) {
            addCriterion("ds_password like", value, "dsPassword");
            return (Criteria) this;
        }

        public Criteria andDsPasswordNotLike(String value) {
            addCriterion("ds_password not like", value, "dsPassword");
            return (Criteria) this;
        }

        public Criteria andDsPasswordIn(List<String> values) {
            addCriterion("ds_password in", values, "dsPassword");
            return (Criteria) this;
        }

        public Criteria andDsPasswordNotIn(List<String> values) {
            addCriterion("ds_password not in", values, "dsPassword");
            return (Criteria) this;
        }

        public Criteria andDsPasswordBetween(String value1, String value2) {
            addCriterion("ds_password between", value1, value2, "dsPassword");
            return (Criteria) this;
        }

        public Criteria andDsPasswordNotBetween(String value1, String value2) {
            addCriterion("ds_password not between", value1, value2, "dsPassword");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
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