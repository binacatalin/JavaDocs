package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.database.DBManager;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        //connect to DB
        Connection conn = DBManager.getConnection();

        //get table name, columns using the methods from EntityUtils class
        String tablename = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> list = new ArrayList<ColumnInfo>();
        list = EntityUtils.getColumns(entityClass);

        //create a QueryBuilder object  in which you have to set the name of the table, columns, query type
        QueryBuilder builder = new QueryBuilder();
        builder.setTableName((Object) tablename);
        builder.setQueryType(QueryType.SELECT);
        builder.addQueryColumns(list);

        //call createQuery() method from QueryBuilder.java
        String res = builder.createQuery();

        //create a resultSet object using Statement and execute the query obtained above
        ResultSet result = null;
        try (Statement smtm = conn.createStatement()){
            result = smtm.executeQuery(res);

            //create an ArrayList of type T
            ArrayList<T> array = new ArrayList<T>();
           /*  -	while the resultSet has any values (resultSet.next()) then:
           you have to create an instance of type T;
           iterate through ColumnInfo list and obtain the field of the instance using getColumnName().
           Ex: instance.getClass().getDeclaredField(column.getColumnName());
           make the field accessible;
           set the value of the field with the value obtained from resultSet object;
           add the instance in ArrayList;
      */

            while (result.next()){
                T ceva = null;
                ceva = entityClass.newInstance();
                for(ColumnInfo elem : list){
                    Field field = ceva.getClass().getDeclaredField(elem.getColumnName());
                    field.setAccessible(true);
                    field.set(ceva,EntityUtils.castFromSqlType(result.getObject(elem.getDbName()),field.getType()));
                    //array.add(ceva);
                }
                array.add(ceva);
            }

            return array;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public <T> T update(T entity) {
        return null;
    }

    @Override
    public void delete(Object entity) {

    }


    @Override
    /**
     * @author Catalin
     * @param entityClass
     * @param params
     * @param <T>
     * @return
     */
    public <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params) {
        return null;
    }
}
