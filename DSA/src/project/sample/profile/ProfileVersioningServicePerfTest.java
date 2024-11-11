package project.sample.profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProfileVersioningServicePerfTest {

    private ProfileVersioningServicePerf profileService;

    @BeforeEach
    public void setUp() {
        profileService = new ProfileVersioningServicePerf();
    }

    // Measure performance of updating profile with large number of fields
    @Test
    public void testProfileUpdatePerformance() {
        int numProfiles = 1000;
        int numUpdatesPerProfile = 1000;
        String baseFieldName = "field";
        String baseFieldValue = "value";

        long startTime = System.currentTimeMillis();

        // Updating multiple profiles with multiple fields
        for (int i = 1; i <= numProfiles; i++) {
            for (int j = 1; j <= numUpdatesPerProfile; j++) {
                profileService.update(i, baseFieldName + j, baseFieldValue + j);
            }
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Profile Update Performance Test took " + duration + " ms");
    }

    // Measure performance of retrieving large number of profile versions
    @Test
    public void testProfileVersionRetrievalPerformance() {
        int profileId = 1;
        int numUpdates = 1000;
        String baseFieldName = "field";
        String baseFieldValue = "value";

        // Update the profile with multiple versions
        for (int i = 1; i <= numUpdates; i++) {
            profileService.update(profileId, baseFieldName + i, baseFieldValue + i);
        }

        long startTime = System.currentTimeMillis();

        // Retrieve all versions of the profile
        for (int version = 1; version <= numUpdates; version++) {
            profileService.get(profileId, version);
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Profile Version Retrieval Performance Test took " + duration + " ms");
    }

    // Measure performance of retrieving specific fields from large number of versions
    @Test
    public void testProfileFieldRetrievalPerformance() {
        int profileId = 1;
        int numUpdates = 1000;
        String baseFieldName = "field";
        String baseFieldValue = "value";

        // Update the profile with multiple versions
        for (int i = 1; i <= numUpdates; i++) {
            profileService.update(profileId, baseFieldName + i, baseFieldValue + i);
        }

        long startTime = System.currentTimeMillis();

        // Retrieve specific fields from the profile
        for (int version = 1; version <= numUpdates; version++) {
            profileService.getField(profileId, version, baseFieldName + version);
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Profile Field Retrieval Performance Test took " + duration + " ms");
    }

    // Test concurrent access with multiple threads
//    @Test
//    public void testConcurrentProfileUpdates() throws InterruptedException {
//        int numThreads = 10;
//        int numProfiles = 1000;
//        int numUpdatesPerProfile = 1000;
//        String baseFieldName = "field";
//        String baseFieldValue = "value";
//
//        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
//
//        long startTime = System.currentTimeMillis();
//
//        // Simulate multiple threads updating profiles
//        for (int t = 0; t < numThreads; t++) {
//            executorService.submit(() -> {
//                for (int i = 1; i <= numProfiles; i++) {
//                    for (int j = 1; j <= numUpdatesPerProfile; j++) {
//                        profileService.update(i, baseFieldName + j, baseFieldValue + j);
//                    }
//                }
//            });
//        }
//
//        // Shutdown executor and wait for threads to finish
//        executorService.shutdown();
//        executorService.awaitTermination(1, TimeUnit.MINUTES);
//
//        long endTime = System.currentTimeMillis();
//        long duration = endTime - startTime;
//
//        System.out.println("Concurrent Profile Update Performance Test took " + duration + " ms");
//    }
}

