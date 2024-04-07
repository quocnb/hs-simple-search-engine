package search;

import java.io.File;
import java.util.*;

public class SearchUtils {
    final private List<String> data;
    final private Map<String, List<Integer>> dataMap;

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
                    List<Integer> indexList = dataMap.get(key);
                    if (indexList == null) {
                        indexList = new ArrayList<>();
                    }
                    indexList.add(index);
                    dataMap.put(key, indexList);
                }
                index += 1;
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<String> search(String keyword) {
        List<String> result = new ArrayList<>();
        List<Integer> indexList = dataMap.get(keyword);
        if (indexList != null) {
            for (Integer index : indexList) {
                result.add(data.get(index));
            }
        }
        return result;
    }

    public List<String> getAllData() {
        return this.data;
    }
}
