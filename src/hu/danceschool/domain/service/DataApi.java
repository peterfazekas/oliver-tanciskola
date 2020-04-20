package hu.danceschool.domain.service;

import hu.danceschool.domain.model.Dance;

import java.util.List;

public class DataApi {

    private final FileReader fileReader;
    private final DataParser dataParser;

    public DataApi(FileReader fileReader, DataParser dataParser) {
        this.fileReader = fileReader;
        this.dataParser = dataParser;
    }

    public List<Dance> getData(String input) {
        return dataParser.parse(fileReader.read(input));
    }
}
