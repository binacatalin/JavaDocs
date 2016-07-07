package ro.teamnet.zth.api.em;

/**
 * Created by user on 7/7/2016.
 * -	create in same folder a class ColumnInfo with following private fields: columnName (type String), columnType (type Class),
 * dbName (type String), isId (type boolean), value (type Object). Create getters and setters for these fields.

 */
public class ColumnInfo {
    private String columnName;
    private Class columnType;
    private String dbName;
    private boolean isId;
    private Object value;

    @Override
    public String toString() {
        return "ColumnInfo{" +
                "columnName='" + columnName + '\'' +
                ", columnType=" + columnType +
                ", dbName='" + dbName + '\'' +
                ", isId=" + isId +
                ", value=" + value +
                '}';
    }

    public Class getColumnType() {
        return columnType;
    }

    public void setColumnType(Class columnType) {
        this.columnType = columnType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public boolean isId() {
        return isId;
    }

    public void setId(boolean id) {
        isId = id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
