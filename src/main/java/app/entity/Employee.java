package app.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "email", "division", "age", "timezone" })
public class Employee {

    private String name;
    private String email;
    private String division;
    private int age;
    private int timezone;

    public Employee() {}

    public Employee(String name, String email, String division, int age, int timezone) {
      this.name = name;
      this.age = age;
      this.email = email;
      this.division = division;
      this.timezone = timezone;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return name + " ";
    }
}
