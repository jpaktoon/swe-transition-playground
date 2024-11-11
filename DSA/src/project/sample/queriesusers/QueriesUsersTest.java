package project.sample.queriesusers;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class QueriesUsersTest {

    private Map<String, Set<String>> userToQueries;
    private Map<String, Set<String>> queryToUsers;
    private QueriesUsers queriesUsers;

    @Before
    public void setUp() {
        userToQueries = new HashMap<>();
        queryToUsers = new HashMap<>();
    }

    @Test
    public void testBasicHappyPath() {
        // Happy path example
        userToQueries.put("A", new HashSet<>(Arrays.asList("python", "java", "software_engineer")));
        userToQueries.put("B", new HashSet<>(Arrays.asList("python", "java")));
        userToQueries.put("C", new HashSet<>(Arrays.asList("javascript", "java", "software_engineer")));

        queryToUsers.put("python", new HashSet<>(Arrays.asList("A", "B")));
        queryToUsers.put("java", new HashSet<>(Arrays.asList("A", "B", "C")));
        queryToUsers.put("software_engineer", new HashSet<>(Arrays.asList("A", "C")));
        queryToUsers.put("javascript", new HashSet<>(Collections.singletonList("C")));

        queriesUsers = new QueriesUsers(userToQueries, queryToUsers);

        // Simulating the expected output using print statements
        queriesUsers.allQueriesByOthers("C", "python");
        // Expected output:
        // 2 java
        // 1 software_engineer
    }

    @Test
    public void testEmptyUserAndQueryMaps() {
        // Edge case: empty maps
        queriesUsers = new QueriesUsers(new HashMap<>(), new HashMap<>());
        queriesUsers.allQueriesByOthers("A", "python");
        // Expected: No output because there are no users or queries
    }

    @Test
    public void testTargetUserNotInMap() {
        // Edge case: target user does not exist
        userToQueries.put("A", new HashSet<>(Arrays.asList("python", "java")));
        queryToUsers.put("python", new HashSet<>(Arrays.asList("A")));

        queriesUsers = new QueriesUsers(userToQueries, queryToUsers);

        queriesUsers.allQueriesByOthers("C", "python");
        // Expected: No output because target user "C" does not exist
    }

    @Test
    public void testTargetQueryNotInMap() {
        // Edge case: target query does not exist
        userToQueries.put("A", new HashSet<>(Arrays.asList("python", "java")));
        queryToUsers.put("java", new HashSet<>(Arrays.asList("A")));

        queriesUsers = new QueriesUsers(userToQueries, queryToUsers);

        queriesUsers.allQueriesByOthers("A", "nonexistent_query");
        // Expected: No output because the query does not exist
    }

    @Test
    public void testUserWithNoQueries() {
        // Edge case: user has no queries
        userToQueries.put("A", new HashSet<>());
        queryToUsers.put("python", new HashSet<>(Arrays.asList("A")));

        queriesUsers = new QueriesUsers(userToQueries, queryToUsers);

        queriesUsers.allQueriesByOthers("A", "python");
        // Expected: No output because user A has no queries
    }

    @Test
    public void testQueryWithNoUsers() {
        // Edge case: query has no users
        userToQueries.put("A", new HashSet<>(Arrays.asList("python")));
        queryToUsers.put("python", new HashSet<>());

        queriesUsers = new QueriesUsers(userToQueries, queryToUsers);

        queriesUsers.allQueriesByOthers("A", "python");
        // Expected: No output because there are no users associated with the query
    }

    @Test(timeout = 10000)
    public void testPerformanceWithLargeDataset() {
        // Performance case: large dataset

        // Generate a large number of users and queries
        for (int i = 0; i < 5_000_000; i++) {
            String userId = "User" + i;
            Set<String> queries = new HashSet<>(Arrays.asList("query" + i, "common_query"));
            userToQueries.put(userId, queries);

            for (String query : queries) {
                queryToUsers.computeIfAbsent(query, k -> new HashSet<>()).add(userId);
            }
        }

        queriesUsers = new QueriesUsers(userToQueries, queryToUsers);

        // Ensure the method performs well under large data
        queriesUsers.allQueriesByOthers("User5000", "common_query");
        // This should complete under the time limit (1 second)
    }

    @Test
    public void testMultipleCommonQueries() {
        // Test case where multiple users share common queries
        userToQueries.put("A", new HashSet<>(Arrays.asList("python", "java")));
        userToQueries.put("B", new HashSet<>(Arrays.asList("python", "javascript")));
        userToQueries.put("C", new HashSet<>(Arrays.asList("java", "javascript")));

        queryToUsers.put("python", new HashSet<>(Arrays.asList("A", "B")));
        queryToUsers.put("java", new HashSet<>(Arrays.asList("A", "C")));
        queryToUsers.put("javascript", new HashSet<>(Arrays.asList("B", "C")));

        queriesUsers = new QueriesUsers(userToQueries, queryToUsers);

        // Expected to see that users A and C share "java"
        queriesUsers.allQueriesByOthers("B", "python");
        // Expected output:
        // 1 java
        // 1 javascript
    }
}

