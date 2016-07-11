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
    public Long getNextIdVal(String tableName, String columnIdName) {
        Connection connection = DBManager.getConnection();
        ResultSet res = null;
        String sqlQuery = "SELECT MAX(" + columnIdName + ") FROM " + tableName;
        Long ret = -2l;

        try (Statement stmt = connection.createStatement()) {
            res = stmt.executeQuery(sqlQuery);
            res.next();
            ret = res.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret + 1;
    }

    @Override
    public <T> T findById(Class<T> entityClass, Long id) {
        Connection c = DBManager.getConnection();
        QueryBuilder queryBuilder = new QueryBuilder();
        String query;

        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
        List<Field> fields = EntityUtils.getFieldsByAnnotations(entityClass, Id.class);

        String fieldName = ((Id)fields.get(0).getDeclaredAnnotation(Id.class)).name();
        System.out.println(fields);
        Condition cond = new Condition();
        cond.setValue(id);
        cond.setColumnName(fieldName);

        queryBuilder.setTableName(tableName);
        queryBuilder.setQueryType(QueryType.SELECT);
        queryBuilder.addQueryColumns(columns);
        queryBuilder.addCondition(cond);

        query = queryBuilder.createQuery();
        System.out.println(query);

        Statement stmt = null;
        try {
            stmt = c.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            T elem = entityClass.newInstance();
            while (resultSet.next()) {
                Field f;
                for (ColumnInfo col : columns) {
                    f = entityClass.getDeclaredField(col.getColumnName());
                    f.setAccessible(true);
                    f.set(elem, EntityUtils.castFromSqlType(resultSet.getObject(col.getDbName()), f.getType()));
                }
            }
            return elem;
        } catch (Exception e) {
            System.out.println("cannot get the value");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> Object insert(T entity) { //Jeni
        /* Create a connection to DB */;
        long ii = 0L;
        try(Connection conn = DBManager.getConnection()){
            String tableName = EntityUtils.getTableName(entity.getClass());
            List<ColumnInfo> listColumns = EntityUtils.getColumns(entity.getClass());
            /* create a QueryBuilder object  in which you have to set the name of the table, columns, query type */
            QueryBuilder qb = new QueryBuilder();

            for (ColumnInfo column : listColumns) {
                if (column.isId() == true) {
                    ii = getNextIdVal(tableName, column.getColumnName());
                    column.setValue(ii);
                }
                else {
                    Field field = entity.getClass().getDeclaredField(column.getColumnName());
                    field.setAccessible(true);
                    //column.setValue((ColumnInfo)(field.get(entity)));
                    Object value = field.get(entity);
                    column.setValue((EntityUtils.getSqlValue(value)));
                }
            }
            /* create a QueryBuilder object  in which you have to set the name of the table, columns, query type */
            //QueryBuilder qb = new QueryBuilder();
            qb.setTableName(tableName);
            qb.addQueryColumns(listColumns);
            qb.setQueryType(QueryType.INSERT);
            /* call createQuery() */
            String query = qb.createQuery();
            /* create a Statement object and execute the query */
            try (Statement stmt = conn.createStatement( )){
                stmt.executeQuery(query);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }catch(SQLException | NoSuchFieldException | IllegalAccessException ex){
            ex.printStackTrace();
        }

        return findById(entity.getClass(), ii);
    }



    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        return null;
    }
}
