package com.lambo.ndp.model;

import java.util.ArrayList;
import java.util.List;

public class SubjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SubjectExample() {
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

        public Criteria andSubjectIdIsNull() {
            addCriterion("SUBJECT_ID is null");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIsNotNull() {
            addCriterion("SUBJECT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectIdEqualTo(Integer value) {
            addCriterion("SUBJECT_ID =", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotEqualTo(Integer value) {
            addCriterion("SUBJECT_ID <>", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdGreaterThan(Integer value) {
            addCriterion("SUBJECT_ID >", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SUBJECT_ID >=", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdLessThan(Integer value) {
            addCriterion("SUBJECT_ID <", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("SUBJECT_ID <=", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIn(List<Integer> values) {
            addCriterion("SUBJECT_ID in", values, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotIn(List<Integer> values) {
            addCriterion("SUBJECT_ID not in", values, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdBetween(Integer value1, Integer value2) {
            addCriterion("SUBJECT_ID between", value1, value2, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SUBJECT_ID not between", value1, value2, "subjectId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Integer value) {
            addCriterion("CATEGORY_ID =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Integer value) {
            addCriterion("CATEGORY_ID <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Integer value) {
            addCriterion("CATEGORY_ID >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CATEGORY_ID >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Integer value) {
            addCriterion("CATEGORY_ID <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("CATEGORY_ID <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Integer> values) {
            addCriterion("CATEGORY_ID in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Integer> values) {
            addCriterion("CATEGORY_ID not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("CATEGORY_ID between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CATEGORY_ID not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIsNull() {
            addCriterion("SUBJECT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIsNotNull() {
            addCriterion("SUBJECT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectNameEqualTo(String value) {
            addCriterion("SUBJECT_NAME =", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotEqualTo(String value) {
            addCriterion("SUBJECT_NAME <>", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameGreaterThan(String value) {
            addCriterion("SUBJECT_NAME >", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_NAME >=", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLessThan(String value) {
            addCriterion("SUBJECT_NAME <", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_NAME <=", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLike(String value) {
            addCriterion("SUBJECT_NAME like", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotLike(String value) {
            addCriterion("SUBJECT_NAME not like", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIn(List<String> values) {
            addCriterion("SUBJECT_NAME in", values, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotIn(List<String> values) {
            addCriterion("SUBJECT_NAME not in", values, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameBetween(String value1, String value2) {
            addCriterion("SUBJECT_NAME between", value1, value2, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_NAME not between", value1, value2, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectDescIsNull() {
            addCriterion("SUBJECT_DESC is null");
            return (Criteria) this;
        }

        public Criteria andSubjectDescIsNotNull() {
            addCriterion("SUBJECT_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectDescEqualTo(String value) {
            addCriterion("SUBJECT_DESC =", value, "subjectDesc");
            return (Criteria) this;
        }

        public Criteria andSubjectDescNotEqualTo(String value) {
            addCriterion("SUBJECT_DESC <>", value, "subjectDesc");
            return (Criteria) this;
        }

        public Criteria andSubjectDescGreaterThan(String value) {
            addCriterion("SUBJECT_DESC >", value, "subjectDesc");
            return (Criteria) this;
        }

        public Criteria andSubjectDescGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_DESC >=", value, "subjectDesc");
            return (Criteria) this;
        }

        public Criteria andSubjectDescLessThan(String value) {
            addCriterion("SUBJECT_DESC <", value, "subjectDesc");
            return (Criteria) this;
        }

        public Criteria andSubjectDescLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_DESC <=", value, "subjectDesc");
            return (Criteria) this;
        }

        public Criteria andSubjectDescLike(String value) {
            addCriterion("SUBJECT_DESC like", value, "subjectDesc");
            return (Criteria) this;
        }

        public Criteria andSubjectDescNotLike(String value) {
            addCriterion("SUBJECT_DESC not like", value, "subjectDesc");
            return (Criteria) this;
        }

        public Criteria andSubjectDescIn(List<String> values) {
            addCriterion("SUBJECT_DESC in", values, "subjectDesc");
            return (Criteria) this;
        }

        public Criteria andSubjectDescNotIn(List<String> values) {
            addCriterion("SUBJECT_DESC not in", values, "subjectDesc");
            return (Criteria) this;
        }

        public Criteria andSubjectDescBetween(String value1, String value2) {
            addCriterion("SUBJECT_DESC between", value1, value2, "subjectDesc");
            return (Criteria) this;
        }

        public Criteria andSubjectDescNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_DESC not between", value1, value2, "subjectDesc");
            return (Criteria) this;
        }

        public Criteria andTableIdIsNull() {
            addCriterion("TABLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTableIdIsNotNull() {
            addCriterion("TABLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTableIdEqualTo(Integer value) {
            addCriterion("TABLE_ID =", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotEqualTo(Integer value) {
            addCriterion("TABLE_ID <>", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdGreaterThan(Integer value) {
            addCriterion("TABLE_ID >", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("TABLE_ID >=", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdLessThan(Integer value) {
            addCriterion("TABLE_ID <", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdLessThanOrEqualTo(Integer value) {
            addCriterion("TABLE_ID <=", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdIn(List<Integer> values) {
            addCriterion("TABLE_ID in", values, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotIn(List<Integer> values) {
            addCriterion("TABLE_ID not in", values, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdBetween(Integer value1, Integer value2) {
            addCriterion("TABLE_ID between", value1, value2, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("TABLE_ID not between", value1, value2, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableCodeIsNull() {
            addCriterion("TABLE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andTableCodeIsNotNull() {
            addCriterion("TABLE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andTableCodeEqualTo(String value) {
            addCriterion("TABLE_CODE =", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeNotEqualTo(String value) {
            addCriterion("TABLE_CODE <>", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeGreaterThan(String value) {
            addCriterion("TABLE_CODE >", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeGreaterThanOrEqualTo(String value) {
            addCriterion("TABLE_CODE >=", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeLessThan(String value) {
            addCriterion("TABLE_CODE <", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeLessThanOrEqualTo(String value) {
            addCriterion("TABLE_CODE <=", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeLike(String value) {
            addCriterion("TABLE_CODE like", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeNotLike(String value) {
            addCriterion("TABLE_CODE not like", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeIn(List<String> values) {
            addCriterion("TABLE_CODE in", values, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeNotIn(List<String> values) {
            addCriterion("TABLE_CODE not in", values, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeBetween(String value1, String value2) {
            addCriterion("TABLE_CODE between", value1, value2, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeNotBetween(String value1, String value2) {
            addCriterion("TABLE_CODE not between", value1, value2, "tableCode");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(String value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(String value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(String value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLike(String value) {
            addCriterion("CREATE_TIME like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotLike(String value) {
            addCriterion("CREATE_TIME not like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<String> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<String> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(String value1, String value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(String value1, String value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeIsNull() {
            addCriterion("PERIOD_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeIsNotNull() {
            addCriterion("PERIOD_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeEqualTo(String value) {
            addCriterion("PERIOD_TYPE =", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeNotEqualTo(String value) {
            addCriterion("PERIOD_TYPE <>", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeGreaterThan(String value) {
            addCriterion("PERIOD_TYPE >", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PERIOD_TYPE >=", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeLessThan(String value) {
            addCriterion("PERIOD_TYPE <", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeLessThanOrEqualTo(String value) {
            addCriterion("PERIOD_TYPE <=", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeLike(String value) {
            addCriterion("PERIOD_TYPE like", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeNotLike(String value) {
            addCriterion("PERIOD_TYPE not like", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeIn(List<String> values) {
            addCriterion("PERIOD_TYPE in", values, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeNotIn(List<String> values) {
            addCriterion("PERIOD_TYPE not in", values, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeBetween(String value1, String value2) {
            addCriterion("PERIOD_TYPE between", value1, value2, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeNotBetween(String value1, String value2) {
            addCriterion("PERIOD_TYPE not between", value1, value2, "periodType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeIsNull() {
            addCriterion("ORGAN_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOrganTypeIsNotNull() {
            addCriterion("ORGAN_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOrganTypeEqualTo(String value) {
            addCriterion("ORGAN_TYPE =", value, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeNotEqualTo(String value) {
            addCriterion("ORGAN_TYPE <>", value, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeGreaterThan(String value) {
            addCriterion("ORGAN_TYPE >", value, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ORGAN_TYPE >=", value, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeLessThan(String value) {
            addCriterion("ORGAN_TYPE <", value, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeLessThanOrEqualTo(String value) {
            addCriterion("ORGAN_TYPE <=", value, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeLike(String value) {
            addCriterion("ORGAN_TYPE like", value, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeNotLike(String value) {
            addCriterion("ORGAN_TYPE not like", value, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeIn(List<String> values) {
            addCriterion("ORGAN_TYPE in", values, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeNotIn(List<String> values) {
            addCriterion("ORGAN_TYPE not in", values, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeBetween(String value1, String value2) {
            addCriterion("ORGAN_TYPE between", value1, value2, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeNotBetween(String value1, String value2) {
            addCriterion("ORGAN_TYPE not between", value1, value2, "organType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIsNull() {
            addCriterion("SUBJECT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIsNotNull() {
            addCriterion("SUBJECT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeEqualTo(String value) {
            addCriterion("SUBJECT_TYPE =", value, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNotEqualTo(String value) {
            addCriterion("SUBJECT_TYPE <>", value, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeGreaterThan(String value) {
            addCriterion("SUBJECT_TYPE >", value, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_TYPE >=", value, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeLessThan(String value) {
            addCriterion("SUBJECT_TYPE <", value, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_TYPE <=", value, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeLike(String value) {
            addCriterion("SUBJECT_TYPE like", value, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNotLike(String value) {
            addCriterion("SUBJECT_TYPE not like", value, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIn(List<String> values) {
            addCriterion("SUBJECT_TYPE in", values, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNotIn(List<String> values) {
            addCriterion("SUBJECT_TYPE not in", values, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeBetween(String value1, String value2) {
            addCriterion("SUBJECT_TYPE between", value1, value2, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_TYPE not between", value1, value2, "subjectType");
            return (Criteria) this;
        }

        public Criteria andRateCountIsNull() {
            addCriterion("RATE_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andRateCountIsNotNull() {
            addCriterion("RATE_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andRateCountEqualTo(Integer value) {
            addCriterion("RATE_COUNT =", value, "rateCount");
            return (Criteria) this;
        }

        public Criteria andRateCountNotEqualTo(Integer value) {
            addCriterion("RATE_COUNT <>", value, "rateCount");
            return (Criteria) this;
        }

        public Criteria andRateCountGreaterThan(Integer value) {
            addCriterion("RATE_COUNT >", value, "rateCount");
            return (Criteria) this;
        }

        public Criteria andRateCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("RATE_COUNT >=", value, "rateCount");
            return (Criteria) this;
        }

        public Criteria andRateCountLessThan(Integer value) {
            addCriterion("RATE_COUNT <", value, "rateCount");
            return (Criteria) this;
        }

        public Criteria andRateCountLessThanOrEqualTo(Integer value) {
            addCriterion("RATE_COUNT <=", value, "rateCount");
            return (Criteria) this;
        }

        public Criteria andRateCountIn(List<Integer> values) {
            addCriterion("RATE_COUNT in", values, "rateCount");
            return (Criteria) this;
        }

        public Criteria andRateCountNotIn(List<Integer> values) {
            addCriterion("RATE_COUNT not in", values, "rateCount");
            return (Criteria) this;
        }

        public Criteria andRateCountBetween(Integer value1, Integer value2) {
            addCriterion("RATE_COUNT between", value1, value2, "rateCount");
            return (Criteria) this;
        }

        public Criteria andRateCountNotBetween(Integer value1, Integer value2) {
            addCriterion("RATE_COUNT not between", value1, value2, "rateCount");
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