package app.helperClass;

import app.entity.Employee;
import app.entity.Pairs;

import java.util.*;

public class MatchCalculator {

    public void calculate(Set<Employee> employeeSet,
                          List<List<Employee>> currentResults,
                          List<List<List<Employee>>> finalResults) {
        if (employeeSet.size() < 2) {
            finalResults.add(new ArrayList<>(currentResults));
            return;
        }
        List<Employee> list = new ArrayList<Employee>(employeeSet);
        Employee first = list.remove(0);
        for (int i = 0; i < list.size(); i++) {
            Employee second = list.get(i);
            Set<Employee> nextSet = new LinkedHashSet<>(list);
            nextSet.remove(second);

            List<Employee> pair = Arrays.asList(first, second);
            currentResults.add(pair);
            calculate(nextSet, currentResults, finalResults);
            currentResults.remove(pair);
        }
    }

    public int computePairPercent(List<Employee> pair) {

        int percent = 0;
        if (pair.get(0).getDivision().equals(pair.get(1).getDivision())) {
            percent += 30;
        }
        if (pair.get(0).getTimezone().equals(pair.get(1).getTimezone())) {
            percent += 40;
        }
        if (Math.abs(pair.get(0).getAge() - (pair.get(1).getAge())) <= 5) {
            percent += 30;
        }
        return percent;
    }

    public double computeSetAverage(List<Pairs> pairsList) {
        return pairsList.stream().mapToDouble(Pairs::getPercent).sum() / pairsList.size();
    }
}
