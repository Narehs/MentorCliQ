package app.service;

import app.entity.Employee;
import app.helperClass.MatchCalculator;
import app.entity.PairSet;
import app.entity.Pairs;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatcherService {

    private final FileService fileService;

    public MatcherService(FileService fileService) {
        this.fileService = fileService;
    }

    public Map<String, Object> getMatches(){
        ArrayList<PairSet> pairSets = getPairSet(getResults(fileService.readFile()));
        PairSet maxAverageSet = getMaxAverageSet(pairSets);
        Map<String, Object> pairSetMap = new HashMap<>();
        pairSetMap.put("all", pairSets);
        pairSetMap.put("maxSet",maxAverageSet);
        pairSetMap.put("maxIndex", pairSets.indexOf(maxAverageSet) + 1);
        return pairSetMap;
    }

    public ArrayList<List<List<Employee>>> getResults(List<Employee> employees) {
        MatchCalculator calculator = new MatchCalculator();
        Set<Employee> set = new LinkedHashSet<>(employees);

        ArrayList<List<List<Employee>>> results = new ArrayList<>();
        calculator.calculate(set, new ArrayList<>(), results);
        return results;
    }

    public ArrayList<PairSet> getPairSet(ArrayList<List<List<Employee>>> results) {
        MatchCalculator calculator = new MatchCalculator();
        ArrayList<PairSet> employeeSets = new ArrayList<>();

        results.forEach(pairSet -> {
            List<Pairs> pairsList = new ArrayList<>();
            pairSet.forEach(pair -> pairsList.add(new Pairs(pair.get(0), pair.get(1), calculator.computePairPercent(pair))));
            employeeSets.add(new PairSet(pairsList, calculator.computeSetAverage(pairsList)));
        });
        return employeeSets;
    }

    public PairSet getMaxAverageSet(ArrayList<PairSet> pairSets){
        PairSet pairSet = pairSets.get(0);
        for (int i = 1; i <pairSets.size(); i++) {
            if (pairSets.get(i).getAverage()>pairSet.getAverage()){
                pairSet = pairSets.get(i);
            }
        }
        return pairSet;
    }
}
