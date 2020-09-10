package br.cefetmg.address.utils;

import java.util.*;
import java.util.stream.Collectors;

public class Utils {


    public static String stringOr(Object a, Object b) {
        if (a == null || a.toString().replaceAll("\\s", "").equals("")) {
            if (b == null) {
                return null;
            }
            return b.toString();
        }

        return a.toString();
    }

    public static String tablify(List<List<String>> df) {
        ArrayList<Integer> maxLengths = new ArrayList<>(df.stream().map(l -> Collections.max(l.stream().map(String::length).collect(Collectors.toList()))).collect(Collectors.toList()));

        ArrayList<ArrayList<String>> rdf = new ArrayList<>();

        StringBuilder out = new StringBuilder();

        int i = 0;
        for(var col: df){
            int j = 0;
            for(var v: col){
                if (rdf.size() <= j){
                    rdf.add(new ArrayList<>());
                }
                rdf.get(j).add(String.format("%-" + (maxLengths.get(i) + 1) + "s", v));
                j++;
            }
            i++;
        }

        boolean firstRow = true;
        for(var row: rdf){
            out.append("| ").append(String.join(" | ", row)).append("|").append("\n");
            if(firstRow){
                for(i = 0; i < row.size(); i++){
                    int repeats = maxLengths.get(i) + 2;
                    if(i != row.size() - 1){
                        repeats += 1;
                    }
                    out.append("|").append("-".repeat(repeats));
                }
                out.append("|").append("\n");
                firstRow = false;
            }
        }

        return out.toString();
    }

    public static String tablify(String[][] df) {
        List<List<String>> newDf = Arrays.stream(df).map(Arrays::asList).collect(Collectors.toList());

        return tablify(newDf);
    }

    public static String tablify(Map<String, List<Object>> df){
        List<List<String>> newDf = new ArrayList<>();

        for (var k : df.keySet()) {
            List<String> currentColumn = new ArrayList<>();

            currentColumn.add(k);
            currentColumn.addAll(df.get(k).stream().map(Object::toString).collect(Collectors.toList()));

            newDf.add(currentColumn);
        }

        return tablify(newDf);
    }
}
