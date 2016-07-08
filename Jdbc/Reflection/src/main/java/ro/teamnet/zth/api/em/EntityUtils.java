package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.annotation.Annotation;
import java.lang.annotation.IncompleteAnnotationException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 7/7/2016.
 */
public class EntityUtils {

    /**
     * create a private constructor EntityUtiles(), which will throw a new UnsupportedOperationException();
     */
    private EntityUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * create a public static method getTableName (Class entity) which will return the name of the DB table.
     * In this method you will return DB table name from annotation (@Table) or entity name if there is no annotation.
     * You will do that using reflection;
     *
     * @param entity
     * @return
     */
    public static String getTableName(Class entity) {
        Annotation a = entity.getAnnotation(Table.class);

        if (a == null)
            return entity.getName();

        return ((Table) a).name();
    }

    /**
     * create a public static method getColumns (Class entity) which will return a list of ColumnInfo.
     * In this method you will return a list of information about columns annotated @Column. Steps:
     * •	get all declared fields from class;
     * •	go throw each field and verify if it is annotated with @Column or @Id and populate ColumnInfo list;
     * •	return list;
     *
     * @param entity
     * @return
     */
    public static List<ColumnInfo> getColumns(Class entity) {
        List<ColumnInfo> columns = new ArrayList<>();
        Field[] fields = entity.getDeclaredFields();
        for(Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            ColumnInfo columnInfo = new ColumnInfo();
            columnInfo.setColumnName(field.getName());
            columnInfo.setColumnType(field.getType());
            if(column != null) {
                columnInfo.setDbName(column.name());
            } else {
                Id id = field.getAnnotation(Id.class);
                columnInfo.setDbName(id.name());
                columnInfo.setId(true);
            }
            columns.add(columnInfo);
        }
        return columns;
    }/*
    public static List<ColumnInfo> getColumns(Class entity) {
        List<ColumnInfo> list = new ArrayList<ColumnInfo>();
        Field[] fields = entity.getDeclaredFields();
        Annotation annColumn, annField;

        for (Field field : fields) {
            annColumn = field.getAnnotation(Column.class);
            annField = field.getAnnotation(Id.class);

            ColumnInfo ci = new ColumnInfo();
            ci.setColumnName(field.getName());
            ci.setColumnType(field.getType());

            if (annColumn != null) {
                ci.setDbName(((Column)annColumn).name());
            } else {
                ci.setDbName(((Id)annField).name());
                ci.setId(true);
            }

//            TODO
//            ci.setDbName(getTableName(entity));
//            ci.setId(annColumn instanceof Id);
//            unset
//            ci.setValue(new Object());

            list.add(ci);
        }
        return list;
    }*/

    /**
     * * •	if value is BigDecimal and wantedType is Integer then you will return an Interger;
     * •	if value is BigDecimal and wantedType is Long then you will return an Long;
     * •	if value is BigDecimal and wantedType is Float then you will return an Float;
     * •	if value is BigDecimal and wantedType is Double then you will return an Double;
     * •	if value is different from BigDecimal then the method will return that value;
     *
     * @param value
     * @param wantedType
     * @return
     */
    public static Object castFromSqlType(Object value, Class wantedType) {
        if (!(value instanceof BigDecimal)) {
            return value;
        }

        if (wantedType.equals(Integer.class)) {
            return ((BigDecimal) value).intValue();
        } else if (wantedType.equals(Long.class)) {
            return ((BigDecimal) value).longValue();
        } else if (wantedType.equals(Float.class)) {
            return ((BigDecimal) value).floatValue();
        } else if (wantedType.equals(Double.class)) {
            return ((BigDecimal) value).doubleValue();
        }

        return null;
    }

    /**
     * get declared fields for class “clazz”;
     * •	search fields with annotation “annotation” and add field in list;
     * •	return list;
     *
     * @param clazz
     * @param annotation
     * @return
     */
    static List<Field> getFieldsByAnnotations(Class clazz, Class annotation) {
        List<Field> list = new ArrayList<Field>();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.getAnnotation(annotation) != null) {
                list.add(field);
            }
        }

        return list;
    }


    /**
     * -	create a public static method getSqlValue(Object object), which will return an Object. Steps:
     * •	if object class is annotated with @Table, get the field annotated with @Id, set it accessible,
     * and return the object associated with the id field;
     * •	if object class is not annotated with @Table, return the object
     *
     * @param object
     * @return
     */
    public static Object getSqlValue(Object object) {
        Annotation ann = object.getClass().getAnnotation(Table.class);

        if (ann != null) {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getAnnotation(Id.class) != null) {
                    field.setAccessible(true);
                    return field;
                }
            }
        }

        return object;
    }
}
