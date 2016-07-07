package ro.teamnet.zth.api.em;

/**
 * Created by user on 7/7/2016.
 * * -	create in same folder class Condition with following private fields: columnName (type String), value (type Object).
 * Create getters and setters for these fields;
 */
public class Condition {
    private String columnName;
    private Object value;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
