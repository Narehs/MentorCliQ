package app.entity;

public class Pairs {
    private Employee firstEmployee;
    private Employee secondEmployee;
    private int percent;

    public Pairs(Employee firstEmployee, Employee secondEmployee, int percent) {
        this.firstEmployee = firstEmployee;
        this.secondEmployee = secondEmployee;
        this.percent = percent;
    }

    public Employee getFirstEmployee() {
        return firstEmployee;
    }

    public void setFirstEmployee(Employee firstEmployee) {
        this.firstEmployee = firstEmployee;
    }

    public Employee getSecondEmployee() {
        return secondEmployee;
    }

    public void setSecondEmployee(Employee secondEmployee) {
        this.secondEmployee = secondEmployee;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "Pairs{" +
                "firstUser=" + firstEmployee +
                ", secondUser=" + secondEmployee +
                ", percent=" + percent +
                '}';
    }
}
