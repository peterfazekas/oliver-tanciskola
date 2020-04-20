package hu.danceschool.contoller;

import hu.danceschool.domain.model.Dance;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DanceService {

    private final List<Dance> dances;

    public DanceService(List<Dance> dances) {
        this.dances = dances;
    }

    /**
     * 2. feladat
     */

    public String getFirstDanceName() {
        int first = 0;
        return dances.get(first).getDanceName();
    }

    public String getLastDanceName() {
        int last = dances.size() - 1;
        return dances.get(last).getDanceName();
    }

    /**
     * 3. feladat
     */

    public long countDances(String danceName) {
        return dances.stream()
                .filter(dance -> dance.isDance(danceName))
                .count();
    }

    /**
     * 4. feladat
     */
    public String getDancesByGirlName(String girlName) {
        return dances.stream()
                .filter(dance -> dance.isGirl(girlName))
                .map(Dance::getDanceName)
                .collect(Collectors.joining(", "));
    }

    /**
     * 5. feladat
     */
    public String getBoyName(String danceName, String girlName) {
        return getBoyNameByDanceAndGirlName(danceName, girlName)
                .map(boyName -> String.format("A %s bemutatóján %s párja %s volt.", danceName, girlName, boyName))
                .orElse(String.format("%s nem táncolt %s-t.", girlName, danceName));
    }

    private Optional<String> getBoyNameByDanceAndGirlName(String danceName, String girlName) {
        return dances.stream()
                .filter(dance -> dance.isDance(danceName) && dance.isGirl(girlName))
                .map(Dance::getBoyName)
                .findFirst();
    }

    /**
     * 6. feladat
     */
    public List<String> getNames() {
        return List.of(
                String.format("Lányok: %s", getNames(Dance::getGirlName)),
                String.format("Fiúk: %s", getNames(Dance::getBoyName)));
    }

    private String getNames(Function<Dance, String> names) {
        return dances.stream()
                .map(names)
                .distinct()
                .collect(Collectors.joining(", "));
    }

    /**
     * 7. feladat
     */

    public String getMostDanceGirlNames() {
        return String.format("A legtöbbet táncoló lányok: %s", getMostDanceNames(Dance::getGirlName));
    }

    public String getMostDanceBoyNames() {
        return String.format("A legtöbbet táncoló fiúk: %s", getMostDanceNames(Dance::getBoyName));
    }

    private String getMostDanceNames(Function<Dance, String> names) {
        Map<String, Long> nameDanceCountMap = createNameDanceCountMap(names);
        long maxValue = getMaxValue(nameDanceCountMap);
        return nameDanceCountMap.entrySet().stream()
                .filter(i -> i.getValue() == maxValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(", "));
    }

    private long getMaxValue(Map<String, Long> nameCountMap) {
        return nameCountMap.values().stream()
                .max(Comparator.naturalOrder())
                .get();
    }

    private Map<String, Long> createNameDanceCountMap(Function<Dance, String> names) {
        return dances.stream()
                .collect(Collectors.groupingBy(names, Collectors.counting()));
    }

}
