package project.sample.profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ProfileVersioningServicePerf {

    private final HashMap<Integer, ArrayList<Profile>> profiles = new HashMap<>(); // all

    private final HashMap<Integer, ArrayList<Profile>> cacheProfiles = new HashMap<>(); // last 10
    private final HashMap<Integer, Integer> cacheVersions = new HashMap<>();

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

    public void update(int profileId, String fieldName, String fieldValue) {
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

    private Profile getProfile(int profileId, int version) {

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

    public Map<String, String> get(int profileId, int version) {
        // System.out.println("Profile for " + profileId + " at version " + version + ":");
        Profile profile = getProfile(profileId, version);
        if (profile != null) {
            //System.out.println("Profile for " + profileId + " at version " + version + ":");
            //profile.PrintField();
            return profile.fields;
        } else {
            //System.out.println("Invalid request!");
            throw new IllegalArgumentException();
        }
    }

    public String getField(int profileId, int version, String fieldName) {
        //System.out.println(fieldName + " for " + profileId + " at version " + version + ":");
        Profile profile = getProfile(profileId, version);
        if (profile != null) {
            TreeMap<String, String> fields = profile.getFields();
            if (fields.containsKey(fieldName)) {
                //System.out.println("Profile for " + profileId + " at version " + version + ":");
                String value = fields.get(fieldName);
                //System.out.println(value);
                return value;
            } else {
                //System.out.println("Invalid request!");
                throw new IllegalArgumentException();
            }
        } else {
            //System.out.println("Invalid request!");
            throw new IllegalArgumentException();
        }
    }

    public void clear() {
        profiles.clear();
        cacheProfiles.clear();
        cacheVersions.clear();
    }
}
