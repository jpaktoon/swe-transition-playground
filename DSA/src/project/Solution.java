package project;

import java.util.*;

// Change this class however you want. Print your output to STDOUT
public class Solution { // 11/20

    public Solution() {
    }

    private final HashMap<String, ArrayList<Profile>> profiles = new HashMap<>(); // all

    private final HashMap<String, ArrayList<Profile>> cacheProfiles = new HashMap<>(); // last 10
    private final HashMap<String, Integer> cacheVersions = new HashMap<>();

    public static class Profile {
        private final TreeMap<String, String> fields = new TreeMap<>();

        Profile(String fieldName, String fieldValue) {
            fields.put(fieldName, fieldValue);
        }

        Profile(Profile previousVersion, String fieldName, String fieldValue){
            // Clone the previousVersion
            fields.putAll(previousVersion.fields);
            // override if exist or put directly if not
            fields.put(fieldName, fieldValue);
        }

        void PrintField(){
            for (Map.Entry<String, String> entry : fields.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        }

        TreeMap<String, String> getFields(){
            return fields;
        }
    }

    public void update(String profileId, String fieldName, String fieldValue) {
        if (profiles.containsKey(profileId)) {
            ArrayList<Profile> userProfiles = profiles.get(profileId);
            Profile lastUpdate = userProfiles.get(userProfiles.size() - 1);
            Profile newVersion = new Profile(lastUpdate, fieldName, fieldValue);
            userProfiles.add(newVersion);
            profiles.put(profileId, userProfiles);

            // last 10
            int CACHE_SIZE = 3;
            int startIndex = userProfiles.size() - CACHE_SIZE;
            // update cache
            if (startIndex > 0) {
                cacheProfiles.put(profileId, new ArrayList<>(userProfiles.subList(startIndex, startIndex + CACHE_SIZE)));
                cacheVersions.put(profileId, startIndex + 1);
            }
        } else {
            // new profile
            ArrayList<Profile> profile = new ArrayList<>();
            profile.add(new Profile(fieldName, fieldValue));
            profiles.put(profileId, profile);
        }
    }

    private Profile getProfile(String profileId, int version) {
        if (version == 0 || !profiles.containsKey(profileId)) return null;

        if (!cacheVersions.isEmpty() && cacheVersions.containsKey(profileId) && cacheVersions.get(profileId) <= version) {
            int retriveIndexInCache = version - cacheVersions.get(profileId);
            ArrayList<Profile> cache = cacheProfiles.get(profileId);
            if (retriveIndexInCache > cache.size() - 1) {
                return null;
            } else {
                return cacheProfiles.get(profileId).get(retriveIndexInCache);
            }
        }

        if (profiles.containsKey(profileId)) {
            ArrayList<Profile> userProfiles = profiles.get(profileId);
            if (version > userProfiles.size() || version == 0) {
                return null;
            } else {
                return userProfiles.get(version - 1);
            }
        } else {
            return null;
        }
    }

    public void get(String profileId, int version) {
        System.out.println("Profile for " + profileId + " at version " + version + ":");
        Profile profile = getProfile(profileId, version);
        if (profile != null) {
            //System.out.println("Profile for " + profileId + " at version " + version + ":");
            profile.PrintField();
        } else {
            System.out.println("Invalid request!");
        }
    }

    public void getField(String profileId, int version, String fieldName) {
        System.out.println(fieldName + " for " + profileId + " at version " + version + ":");
        Profile profile = getProfile(profileId, version);
        if (profile != null) {
            TreeMap<String, String> fields = profile.getFields();
            if (fields.containsKey(fieldName)) {
                //System.out.println("Profile for " + profileId + " at version " + version + ":");
                String value = fields.get(fieldName);
                System.out.println(value);
            } else {
                System.out.println("Invalid request!");
            }
        } else {
            System.out.println("Invalid request!");
        }
    }

    public void printall(String profileId){
        if (profiles.containsKey(profileId)) {
            ArrayList<Profile> userProfiles = profiles.get(profileId);
            for (int i = 0; i < userProfiles.size(); i++){
                System.out.println("DEBUG: " + profileId + " version " + (i+1));
                userProfiles.get(i).PrintField();
            }
        } else {
            System.out.println("DEBUG: no profiles");
        }
    }

    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        Solution sol = new Solution();
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

