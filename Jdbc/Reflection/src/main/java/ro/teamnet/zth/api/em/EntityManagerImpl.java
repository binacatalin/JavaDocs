package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.database.DBManager;
import ro.teamnet.zth.appl.domain.Employee;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 7/8/2016.
 */
public class EntityManagerImpl implements EntityManager {

    @Override
    /**
     * -	create a connection to DB;
     -	create a statement object and execute a query that returns the maximum value of the id column incremented by 1;
     */
    public Integer getNextIdVal(String tableName, String columnIdName) {
        Connection connection = DBManager.getConnection();
        ResultSet res = null;
        String sqlQuery = "";//"SELECT MAX(EMPLOYEE_ID) FROM EMPLOYEES";
        QueryBuilder qb = new QueryBuilder();
        Integer ret = -2;

//        Conditia din query
        Condition condition = new Condition();
        condition.setColumnName(columnIdName);
        List<ColumnInfo> columns, queryCol = new ArrayList<ColumnInfo>();

        columns = EntityUtils.getColumns(Employee.class);
//        List<Field> lst =EntityUtils.getFieldsByAnnotations(Employee.class, Id.class);
        for (ColumnInfo ci : columns) {
            if (ci.isId()) {
                ci.setValue("MAX(" + ci.getValue() + ")");
                queryCol.add(ci);
                sqlQuery = qb.setQueryType(QueryType.SELECT).setTableName(tableName).addQueryColumns(queryCol).createQuery();
                break;
            }
        }
        System.out.println("---"+sqlQuery);
        try (Statement stmt = connection.createStatement()) {
            res = stmt.executeQuery(sqlQuery);
            res.next();
            ret = res.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret + 1;
    }

    @Override
    public <T> T findById(Class<T> entityClass, Long id) {
        return null;
    }

    @Override
    public <T> Object insert(T entity) {
        return null;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        return null;
    }
}
