package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

/**
 * Created by user on 7/7/2016.
 * -	create a public class Department.java, with the following private fields: id (Long), departmentName (String), location (Location);
 * -	create getters and setters for fields above;
 * -	override methods equals() and toString() (ALT+INSRT-> select equals()/toString()
 * -> select all fields -> OK
 * -	annotate columns defined with the correct annotation
 * <p>
 * Example:
 *
 * @Id(name = "department_id")
 * private Long id;
 */
@Table(name = "DEPARTMENTS")
public class Department {
    @Id(name = "DEPARTMENT_ID")
    private Long id;

    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;

    @Column(name = "LOCATION_ID")
    private Long location;

    //    ------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (!id.equals(that.id)) return false;
        if (!departmentName.equals(that.departmentName)) return false;
        return location.equals(that.location);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + departmentName.hashCode();
        result = 31 * result + location.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Departament{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", location=" + location +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getLocation() {
        return location;
    }

    public void setLocation(Long location) {
        this.location = location;
    }


}
