package project.sample.profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//Profile Field Retrieval Performance Test took 1 ms
//Profile Version Retrieval Performance Test took 0 ms
//Update - Out of memory

public class ProfileVersioningService {

    // version = index + 1;
    Map<Integer, ArrayList<Map<String, String>>> PROFILES = new ConcurrentHashMap<>();

    public void update(final int profileId, final String fieldName, final String fieldValue) {
        ArrayList<Map<String, String>> profile = PROFILES.get(profileId);
        if (profile != null) {
            Map<String, String> lastUpdate = profile.getLast();
            Map<String, String> newUpdate = new HashMap<>(lastUpdate);
            newUpdate.put(fieldName, fieldValue);
            profile.add(newUpdate);
        } else {
            Map<String, String> newField = new HashMap<>();
            newField.put(fieldName, fieldValue);
            profile = new ArrayList<>();
            profile.add(newField);
            PROFILES.put(profileId, profile);
        }
    }

    public Map<String, String> get(final int profileId, final int version) {
        try {
            return PROFILES.get(profileId).get(version - 1);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    public String getField(final int profileId, final int version, final String fieldName) {
        Map<String, String> profile = get(profileId, version);
        if (profile.containsKey(fieldName)) return profile.get(fieldName);
        else throw new IllegalArgumentException();
    }

    public void clear() {
        PROFILES.clear();
    }
}
