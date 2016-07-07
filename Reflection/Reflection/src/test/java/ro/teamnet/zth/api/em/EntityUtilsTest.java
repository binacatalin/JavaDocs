package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.appl.domain.Department;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Location;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Created by user on 7/7/2016.
 */
public class EntityUtilsTest {

    @Test
    public void testGetTableNameMethod() {
        String tableName = EntityUtils.getTableName(Department.class).toLowerCase();
        assertEquals("Table name should be departments!", "departments", tableName);
    }

    @Test
    public void testColumnInfoListMethod() {
        List<ColumnInfo> columnInfoList = EntityUtils.getColumns(Department.class);
        assertEquals("List size:", 3, columnInfoList.size());
    }

    @Test
    public void testCastFromSqlTypeMethod() {
        Object objInteger = EntityUtils.castFromSqlType(new BigDecimal(123), Integer.class);
        assertEquals("castFromSqlType for Integer", new Integer(123), (Integer) objInteger);
        Object objByte = EntityUtils.castFromSqlType(new BigDecimal(123), Byte.class);
        assertEquals("castFromSqlType for Byte", new BigDecimal(123), objByte);
    }

    @Test
    public void testGetFieldsByAnnotationsMethod() {
        List<Field> fildList = EntityUtils.getFieldsByAnnotations(Location.class, Column.class);
        assertEquals("getFieldsByAnnotations: ", 4, fildList.size());
    }

    @Test
    public void testGetSqlValueMethod() {
        Department depart = new Department();
        depart.setId(new Long(12));

        Object field = EntityUtils.getSqlValue(depart);
        boolean test = field.toString().contains("Long");
        assertEquals("getSqlValue: ", true, test);

        Object goguString = EntityUtils.getSqlValue(new String("Gogu"));
        assertEquals("getSqlValue: ", "Gogu", goguString);
    }
}
