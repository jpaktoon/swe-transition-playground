package project.sample.queriesusers;

import java.util.*;

public class QueriesUsers {
    Map<String, Set<String>> userToQueries;
    Map<String, Set<String>> queryToUsers;
    private final Set<String> visitedQuery;

    public QueriesUsers(Map<String, Set<String>> userToQueries, Map<String, Set<String>> queryToUsers) {
        this.userToQueries = userToQueries;
        this.queryToUsers = queryToUsers;
        this.visitedQuery = new HashSet<>();
    }

    public void allQueriesByOthers(String targetUser, String targetQuery) {
        Set<String> users = this.queryToUsers.get(targetQuery);
        if (users == null) {
            System.out.println("No users found for query: " + targetQuery);
            return;
        }

        for (String user : users) { // Iterate over users who searched the target query
            if (!user.equals(targetUser)) {
                Set<String> queries = this.userToQueries.get(user);
                if (queries == null) continue;

                for (String query : queries) { // Iterate over their queries
                    if (!visitedQuery.contains(query) && !query.equals(targetQuery)) {
                        Set<String> allUsersForQuery = this.queryToUsers.get(query);
                        if (allUsersForQuery != null) {
                            long count = allUsersForQuery.stream().filter(x -> !x.equals(targetUser)).count();
                            System.out.println(count + " " + query);
                        }
                    }
                    visitedQuery.add(query); // Mark query as visited after processing
                }
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

        QueriesUsers queriesUsers = new QueriesUsers(userToQueries, queryToUsers);
        queriesUsers.allQueriesByOthers("C", "python");
    }
}
