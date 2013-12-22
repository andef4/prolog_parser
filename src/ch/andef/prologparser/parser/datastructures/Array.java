package ch.andef.prologparser.parser.datastructures;

import java.util.ArrayList;
import java.util.List;

class Array implements DataStructure {
    private List<DataStructure> data = new ArrayList<DataStructure>();

    public List<DataStructure> getData() {
        return data;
    }
}