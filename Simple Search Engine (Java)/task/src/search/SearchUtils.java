package search;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SearchUtils {
    public enum SearchMode {
        ALL, ANY, NONE
    }
    final private List<String> data;
    final private Map<String, Set<Integer>> dataMap;

    public SearchUtils(String filePath) {
        data = new ArrayList<>();
        dataMap = new HashMap<>();
        try {
            int index = 0;
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                data.add(line);
                for (String key: line.split(" ")) {
                    Set<Integer> indexList = dataMap.get(key.toLowerCase());
                    if (indexList == null) {
                        indexList = new HashSet<>();
                    }
                    indexList.add(index);
                    dataMap.put(key.toLowerCase(), indexList);
                }
                index += 1;
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<String> search(String[] keywords, SearchMode mode) {
        return switch (mode) {
            case ALL -> {
                Set<Integer> indexList = searchAll(keywords);
                yield IntStream.range(0, data.size())
                        .filter(indexList::contains)
                        .mapToObj(data::get)
                        .collect(Collectors.toList());
            }
            case ANY -> {
                Set<Integer> indexList = searchAny(keywords);
                yield IntStream.range(0, data.size())
                        .filter(indexList::contains)
                        .mapToObj(data::get)
                        .collect(Collectors.toList());
            }
            case NONE -> {
                Set<Integer> indexList = searchAny(keywords);
                yield IntStream.range(0, data.size())
                        .filter(i -> !indexList.contains(i))
                        .mapToObj(data::get)
                        .collect(Collectors.toList());
            }
        };
    }

    public List<String> getAllData() {
        return this.data;
    }

    private Set<Integer> searchAll(String[] keywords) {
        Set<Integer> indexList = new HashSet<>();
        for (String keyword : keywords) {
            Set<Integer> indexes = dataMap.get(keyword.toLowerCase());
            if (indexes == null) {
                return new HashSet<>();
            }
            if (indexList.isEmpty()) {
                indexList = indexes;
            } else {
                boolean hasSame = indexList.retainAll(indexes);
                if (!hasSame) {
                    return new HashSet<>();
                }
            }
        }
        return indexList;
    }

    private Set<Integer> searchAny(String[] keywords) {
        Set<Integer> indexList = new HashSet<>();
        for (String keyword : keywords) {
            Set<Integer> indexes = dataMap.get(keyword.toLowerCase());
            if (indexes == null) {
                continue;
            }
            indexList.addAll(indexes);
        }
        return indexList;
    }
}
