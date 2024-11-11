package project.sample.queriesusers;

import java.util.*;

public class QueryAnalyzer {

    public static Map<String, Integer> findCommonQueryUsers(
            HashMap<String, Set<String>> userToQueries,
            HashMap<String, Set<String>> queryToUsers,
            String userId, String query) {

        // A map to store the result with the query and the count of distinct users
        Map<String, Integer> result = new HashMap<>();

        // If the input query is not in the queryToUsers map, return empty result
        if (!queryToUsers.containsKey(query)) {
            return result;
        }

        // Get the set of users who searched the input query
        Set<String> usersWhoSearchedInputQuery = queryToUsers.get(query);

        // Iterate over all queries in the queryToUsers map (not just user's queries)
        for (String otherQuery : queryToUsers.keySet()) {
            if (otherQuery.equals(query)) {
                // Skip the input query itself
                continue;
            }

            // Get the set of users who searched this other query
            Set<String> usersWhoSearchedOtherQuery = queryToUsers.get(otherQuery);

            // Find the intersection between users who searched both the input query and the other query
            Set<String> commonUsers = new HashSet<>(usersWhoSearchedInputQuery);
            commonUsers.retainAll(usersWhoSearchedOtherQuery);

            // If there are common users, add the count to the result map
            if (!commonUsers.isEmpty()) {
                result.put(otherQuery, commonUsers.size());
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Example test case
        HashMap<String, Set<String>> userToQueries = new HashMap<>();
        userToQueries.put("A", new HashSet<>(Arrays.asList("python", "java", "software_engineer")));
        userToQueries.put("B", new HashSet<>(Arrays.asList("python", "java")));
        //userToQueries.put("C", new HashSet<>(Arrays.asList("javascript", "java", "software_engineer")));
        userToQueries.put("C", new HashSet<>());

        HashMap<String, Set<String>> queryToUsers = new HashMap<>();
        queryToUsers.put("python", new HashSet<>(Arrays.asList("A", "B")));
        queryToUsers.put("java", new HashSet<>(Arrays.asList("A", "B", "C")));
        queryToUsers.put("software_engineer", new HashSet<>(Arrays.asList("A", "C")));
        queryToUsers.put("javascript", new HashSet<>(Collections.singletonList("C")));

        // Input: userId = "C", query = "python"
        Map<String, Integer> result = findCommonQueryUsers(userToQueries, queryToUsers, "C", "python");

        // Print the output
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            System.out.println(entry.getValue() + " " + entry.getKey());
        }
    }
}

