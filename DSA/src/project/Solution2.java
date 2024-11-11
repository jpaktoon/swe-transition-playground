package project;

import java.util.*;

// Change this class however you want. Print your output to STDOUT
public class Solution2 { // 10/20

    public Solution2() {
    }

    private final HashMap<String, ArrayList<Profile>> profiles = new HashMap<>();

    public static class Profile implements Comparable<Profile> {
        String fieldName;
        String fieldValue;

        public Profile(String fieldName, String fieldValue) {
            this.fieldName = fieldName;
            this.fieldValue = fieldValue;
        }

        @Override
        public int compareTo(Profile other) {
            return this.fieldName.compareTo(other.fieldName);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true; // Same object reference
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false; // Different object types
            }
            Profile other = (Profile) obj; // Cast to Person object
            return this.fieldName.equals(other.fieldName); // Compare name
        }
    }

    public void update(String profileId, String fieldName, String fieldValue) {
        Profile profile = new Profile(fieldName, fieldValue);
        ArrayList<Profile> userProfiles = new ArrayList<>();
        if (profiles.containsKey(profileId)) {
            userProfiles = profiles.get(profileId);
        }
        userProfiles.add(profile);
        profiles.put(profileId, userProfiles);
    }

    private TreeMap<String, String> aggregateResult(String profileId, int version){
        if (profiles.containsKey(profileId) && profiles.get(profileId).size() >= version) {
            ArrayList<Profile> userProfiles = new ArrayList<>(profiles.get(profileId).subList(0, version));
            final TreeMap<String, String> aggResults = new TreeMap<>();
            for (Profile profile: userProfiles) {
                //auto sort and override
                aggResults.put(profile.fieldName, profile.fieldValue);
            }
            return aggResults;
        } else {
            return null;
        }
    }

    public void get(String profileId, int version) {
        System.out.println("Profile for " + profileId + " at version " + version + ":");
        TreeMap<String, String> aggResults = aggregateResult(profileId, version);
        if (aggResults!=null && !aggResults.isEmpty()) {
            for (Map.Entry<String, String> entry : aggResults.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        } else {
            System.out.println("Invalid request!");
        }
    }

    public void getField(String profileId, int version, String fieldName) {
        System.out.println(fieldName + " for " + profileId + " at version " + version + ":");
        TreeMap<String, String> aggResults = aggregateResult(profileId, version);
        if (aggResults!=null && !aggResults.isEmpty()) {
            for (Map.Entry<String, String> entry : aggResults.entrySet()) {
                if (entry.getKey().equals(fieldName)) {
                    System.out.println(entry.getValue());
                    return;
                }
            }
        }
        System.out.println("Invalid request!");
    }

    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        Solution2 sol = new Solution2();
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            String queryType = in.next();
            String profileId = in.next();
            if ("update".equals(queryType)) {
                String fieldName = in.next();
                String fieldValue = in.next();
                //System.out.println("Received cmd ===> " + queryType + " " + profileId + " " + fieldName + " " + fieldValue);
                sol.update(profileId, fieldName, fieldValue);
                //sol.printall(profileId);
            } else if ("get".equals(queryType)) {
                int version = in.nextInt();
                //System.out.println("Received cmd ===> " + queryType + " " + profileId + " "  + version);
                sol.get(profileId, version);
                //sol.printall(profileId);
            } else if ("getfield".equals(queryType)) {
                int version = in.nextInt();
                String fieldName = in.next();
                //System.out.println("Received cmd ===> " + queryType + " " + profileId + " "  + version + " " + fieldName);
                sol.getField(profileId, version, fieldName);
                //sol.printall(profileId);
            }
        }
    }
}
