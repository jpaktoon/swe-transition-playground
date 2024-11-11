package project.sample.queriesusers;

import java.util.*;

public class QueriesUsersWithPrecompute {
    Map<String, Set<String>> userToQueries;
    Map<String, Set<String>> queryToUsers;
    private final Set<String> visitedQuery;
    private final Map<String, Set<String>> queryUserMap; // Simplified map

    public QueriesUsersWithPrecompute(Map<String, Set<String>> userToQueries, Map<String, Set<String>> queryToUsers) {
        this.userToQueries = userToQueries;
        this.queryToUsers = queryToUsers;
        this.visitedQuery = new HashSet<>();
        this.queryUserMap = new HashMap<>();
        buildQueryUserMap(); // Precompute the map on initialization
    }

    // Precompute the map with users who searched other queries alongside the target query
    private void buildQueryUserMap() {
        for (String query : queryToUsers.keySet()) {
            Set<String> usersForQuery = queryToUsers.get(query);
            Set<String> otherUsers = new HashSet<>(); // To store users who searched any other query
            for (String user : usersForQuery) {
                Set<String> queriesByUser = userToQueries.get(user);
                if (queriesByUser == null) continue;

                for (String otherQuery : queriesByUser) {
                    if (!otherQuery.equals(query)) {
                        otherUsers.add(user); // Add user who searched any other query
                    }
                }
            }
            queryUserMap.put(query, otherUsers); // Map the query to the set of other users
        }
    }

    // Method to find all queries by other users based on the precomputed map
    public void allQueriesByOthers(String targetUser, String targetQuery) {
        if (!queryUserMap.containsKey(targetQuery)) {
            System.out.println("No queries found for: " + targetQuery);
            return;
        }

        Set<String> users = queryUserMap.get(targetQuery);

        // Iterate over users and count the distinct ones who are not the targetUser
        for (String query : queryToUsers.keySet()) {
            if (!query.equals(targetQuery) && !visitedQuery.contains(query)) {
                Set<String> allUsersForQuery = queryToUsers.get(query);
                if (allUsersForQuery != null) {
                    long count = allUsersForQuery.stream().filter(user -> users.contains(user) && !user.equals(targetUser)).count();
                    if (count > 0) {
                        System.out.println(count + " " + query);
                    }
                }
                visitedQuery.add(query); // Mark query as visited
            }
        }
    }

    public static void main(String[] args) {
        // Example test case
        Map<String, Set<String>> userToQueries = new HashMap<>();
        userToQueries.put("A", new HashSet<>(Arrays.asList("python", "java", "software_engineer")));
        userToQueries.put("B", new HashSet<>(Arrays.asList("python", "java")));
        userToQueries.put("C", new HashSet<>(Arrays.asList("javascript", "java", "software_engineer")));
        //userToQueries.put("C", new HashSet<>());

        Map<String, Set<String>> queryToUsers = new HashMap<>();
        queryToUsers.put("python", new HashSet<>(Arrays.asList("A", "B")));
        queryToUsers.put("java", new HashSet<>(Arrays.asList("A", "B", "C")));
        queryToUsers.put("software_engineer", new HashSet<>(Arrays.asList("A", "C")));
        queryToUsers.put("javascript", new HashSet<>(Collections.singletonList("C")));

        QueriesUsersWithPrecompute queriesUsers = new QueriesUsersWithPrecompute(userToQueries, queryToUsers);
        queriesUsers.allQueriesByOthers("C", "python");
    }
}

