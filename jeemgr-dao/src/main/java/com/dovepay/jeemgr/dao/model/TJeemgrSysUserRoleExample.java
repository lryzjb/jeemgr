package com.dovepay.jeemgr.dao.model;

import java.util.ArrayList;
import java.util.List;

public class TJeemgrSysUserRoleExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    private int startIndex = -1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    private int endIndex = -1;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    public TJeemgrSysUserRoleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    public void setStartIndex(int startIndex) {
        this.startIndex=startIndex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    public void setEndIndex(int endIndex) {
        this.endIndex=endIndex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNull() {
            addCriterion("ROLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("ROLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(String value) {
            addCriterion("ROLE_ID =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(String value) {
            addCriterion("ROLE_ID <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(String value) {
            addCriterion("ROLE_ID >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE_ID >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(String value) {
            addCriterion("ROLE_ID <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(String value) {
            addCriterion("ROLE_ID <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLike(String value) {
            addCriterion("ROLE_ID like", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotLike(String value) {
            addCriterion("ROLE_ID not like", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<String> values) {
            addCriterion("ROLE_ID in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<String> values) {
            addCriterion("ROLE_ID not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(String value1, String value2) {
            addCriterion("ROLE_ID between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(String value1, String value2) {
            addCriterion("ROLE_ID not between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andOnlineSignIsNull() {
            addCriterion("ONLINE_SIGN is null");
            return (Criteria) this;
        }

        public Criteria andOnlineSignIsNotNull() {
            addCriterion("ONLINE_SIGN is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineSignEqualTo(String value) {
            addCriterion("ONLINE_SIGN =", value, "onlineSign");
            return (Criteria) this;
        }

        public Criteria andOnlineSignNotEqualTo(String value) {
            addCriterion("ONLINE_SIGN <>", value, "onlineSign");
            return (Criteria) this;
        }

        public Criteria andOnlineSignGreaterThan(String value) {
            addCriterion("ONLINE_SIGN >", value, "onlineSign");
            return (Criteria) this;
        }

        public Criteria andOnlineSignGreaterThanOrEqualTo(String value) {
            addCriterion("ONLINE_SIGN >=", value, "onlineSign");
            return (Criteria) this;
        }

        public Criteria andOnlineSignLessThan(String value) {
            addCriterion("ONLINE_SIGN <", value, "onlineSign");
            return (Criteria) this;
        }

        public Criteria andOnlineSignLessThanOrEqualTo(String value) {
            addCriterion("ONLINE_SIGN <=", value, "onlineSign");
            return (Criteria) this;
        }

        public Criteria andOnlineSignLike(String value) {
            addCriterion("ONLINE_SIGN like", value, "onlineSign");
            return (Criteria) this;
        }

        public Criteria andOnlineSignNotLike(String value) {
            addCriterion("ONLINE_SIGN not like", value, "onlineSign");
            return (Criteria) this;
        }

        public Criteria andOnlineSignIn(List<String> values) {
            addCriterion("ONLINE_SIGN in", values, "onlineSign");
            return (Criteria) this;
        }

        public Criteria andOnlineSignNotIn(List<String> values) {
            addCriterion("ONLINE_SIGN not in", values, "onlineSign");
            return (Criteria) this;
        }

        public Criteria andOnlineSignBetween(String value1, String value2) {
            addCriterion("ONLINE_SIGN between", value1, value2, "onlineSign");
            return (Criteria) this;
        }

        public Criteria andOnlineSignNotBetween(String value1, String value2) {
            addCriterion("ONLINE_SIGN not between", value1, value2, "onlineSign");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated do_not_delete_during_merge Tue Nov 20 16:55:39 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_JEEMGR_SYS_USER_ROLE
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
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