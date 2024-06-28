package mockwb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class employeesRelationship {

//    employees = [
//            "1,Bill,Engineer",
//            "2,Joe,HR",
//            "3,Sue,Product",
//            "4,Mike,Sales"
//            "5,Adam,Engineer"
//            ]
//
//    friendship = [
//            "1,2",
//            "1,3",
//            "3,4",
//            ]
//
//    employeeFriendshipMap = {
//        "1": ["2","3"],
//        "2": ["1"]
//        "3": ["4","1"]
//        "4": ["3"]
//        "5": []
//    }

    public static Map<String, HashSet<String>> giveRelations(String[] employees, String[] friendship) {
        List<String> employeeIds = Arrays.stream(employees).map(employee -> employee.split(",")[0]).toList();
        Map<String, HashSet<String>> employeeFriendshipMap = new HashMap<>();

        for (String employeeId : employeeIds) {
            employeeFriendshipMap.put(employeeId, new HashSet<>());
        }

        for (String groups : friendship) {
            String[] persons = groups.split(",");
            //String[] friends = groups.split(",");
            for (String person : persons) {
//                for (String friend : friends) {
//                    if (!person.equals(friend) && employeeFriendshipMap.containsKey(person)) {
//                        employeeFriendshipMap.get(person).add(friend);
//                    }
//                }
                Set<String> friends = Arrays.stream(persons).filter(x -> !x.equals(person)).collect(Collectors.toSet());
                employeeFriendshipMap.get(person).addAll(friends);
            }
        }
        return employeeFriendshipMap;
    }

    public static void addElementToMap(Map<String, ArrayList<String>> map, String key, String value) {
        // Using computeIfAbsent to handle the logic in one line
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }

    public static void main(String[] args) {
        String[] employees = { "1,Bill,Engineer",
                "2,Joe,HR",
                "3,Sue,Product",
                "4,Mike,Sales",
                "5,Adam,Engineer"};
        String[] friendship = {
                "1,2",
                "1,3",
                "3,4"};

        Map<String, HashSet<String>> employeeFriendshipMap = giveRelations(employees, friendship);
        System.out.println(employeeFriendshipMap);
    }
}
