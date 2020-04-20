package hu.danceschool.domain.service;

import hu.danceschool.domain.model.Dance;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataParser {

    public List<Dance> parse(List<String> lines) {
        return IntStream.iterate(0, i -> i + 3).limit(lines.size() / 3)
                .mapToObj(i -> lines.subList(i, i + 3))
                .map(this::createDance)
                .collect(Collectors.toList());
    }

    private Dance createDance(List<String> lines) {
        return new Dance(lines.get(0), lines.get(1), lines.get(2));
    }
}
