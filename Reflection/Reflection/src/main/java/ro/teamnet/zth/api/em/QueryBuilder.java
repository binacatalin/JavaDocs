package ro.teamnet.zth.api.em;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 7/7/2016.
 * create private fields tableName(type Object), queryColumns(type List<ColumnInfo>), queryType(type QueryType), conditions(type List<Condition>);
 */
public class QueryBuilder {

    private Object tableName;
    private List<ColumnInfo> queryColumns;
    private QueryType queryType;
    private List<Condition> conditions;

    /**
     *
     * @param condition
     */
    public void addConditions(Condition condition) {
        conditions.add(condition);
    }

    /**
     * Create a public method getValueForQuery(Object value) which returns a string object.
     * If object type is String then return the value between ‘’. If the object type is date use:
     * DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
     * return "TO_DATE('"+dateFormat.format((Date)value)+"','mm-dd-YYYY'";
     *
     * @param value
     * @return
     */
    public String getValueForQuery(Object value) {
        if (value instanceof String)
            return (String) value;

        DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
        return "TO_DATE('" + dateFormat.format((Date) value) + "','mm-dd-YYYY'";
    }

    /**
     * create a public method addCondition(Condition condition) which will return a QueryBuilder and will set all conditions necessary for a query
     *
     * @param condition
     * @return
     */
    public QueryBuilder addCondition(Condition condition) {
        conditions.add(condition);
        return this;
    }

    /**
     * create a public method setTableName(Object tableName) which will return a QueryBuilder and will set table name necessary for a query
     *
     * @param tableName
     * @return
     */
    public QueryBuilder setTableName(Object tableName) {
        this.tableName = (String) tableName;
        return this;
    }


    /**
     * create a public method addQueryColumns(List<ColumnInfo> queryColumns) which will return a QueryBuilder and
     * will set all columns necessary for a query
     *
     * @param queryColumns
     * @return
     */
    public QueryBuilder addQueryColumns(List<ColumnInfo> queryColumns) {
        this.queryColumns.addAll(queryColumns);
        return this;
    }

    /**
     * create a public method setQueryType(QueryType queryType) which will return a QueryBuilder and will set the type of the query
     *
     * @param queryType
     * @return
     */
    public QueryBuilder setQueryType(QueryType queryType) {
        this.queryType = queryType;
        return this;
    }

//    create 4 private methods: createSelectQuery(), createDeleteQuery(), createUpdateQuery(), createInsertQuery().
// In these methods you will create sql statements using StringBuilder(ref: https://docs.oracle.com/javase/tutorial/java/data/buffers.html ).

    /**
     * CREATE TABLE LOCATIONS (
     * LOCATION_ID    NUMBER PRIMARY KEY,
     * STREET_ADDRESS VARCHAR2(40),
     * POSTAL_CODE    VARCHAR2(12),
     * CITY           VARCHAR2(30) NOT NULL,
     * STATE_PROVINCE  VARCHAR2(25)
     * );
     */
    private String createSelectQuery() {
        StringBuilder sb = new StringBuilder("CREATE TABLE LOCATIONS(");
        sb.append("LOCATION_ID    NUMBER PRIMARY KEY,");
        sb.append("STREET_ADDRESS VARCHAR2(40),");
        sb.append("STREET_ADDRESS VARCHAR2(40)");
        sb.append("POSTAL_CODE    VARCHAR2(12),");
        sb.append("STATE_PROVINCE  VARCHAR2(25)");
        sb.append(");");

        return new String(sb);
    }

    /**
     *
     */
    private String createDeleteQuery() {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append("FROM LOCATIONS");

        return new String(sb);
    }

    /**
     * UPDATE EMPLOYEES
     * SET SALARY=SALARY + 0.3 * SALARY
     * WHERE DEPARTMENT_ID = 50;
     */
    private String createUpdateQuery() {
        StringBuilder sb = new StringBuilder("UPDATE LOCATIONS");
        sb.append("SET STREET_ADDRESS = NULL");
        sb.append("WHERE LOCATION_ID = 2;");

        return new String(sb);
    }

    /**
     * insert into departments values(
     * TAB_DEPARTMENTS_SEQ.nextval, 'Administration', 1700
     * );
     */
    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder("INSERT INTO LOCATIONS values(");
        sb.append("1000, \'1297 Via Cola di Rie\', \'00989\', \'Roma\', NULL);");

        return new String(sb);
    }
//value.getClass.eq(BigDec)...

    /**
     * create public method createQuery() which will return a String.
     * In this method you will check the query type and call one of the methods defined above
     *
     * @return
     */
    public String createQuery(QueryType queryType) {
        if (queryType == QueryType.SELECT) {
            return createSelectQuery();
        } else if (queryType == QueryType.INSERT) {
            return createInsertQuery();
        } else if (queryType == QueryType.UPDATE) {
            return createUpdateQuery();
        } else if (queryType == QueryType.DELETE) {
            return createDeleteQuery();
        }

        return null;
    }
}
