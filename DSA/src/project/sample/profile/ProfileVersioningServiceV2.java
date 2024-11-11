package project.sample.profile;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Profile {
    private final Map<String, String> fields;

    public Profile() {
        this.fields = new HashMap<>();
    }

    public Profile(Profile previousVersion) {
        this.fields = new HashMap<>(previousVersion.fields);
    }

    public void updateField(String fieldName, String fieldValue) {
        fields.put(fieldName, fieldValue);
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public String getField(String fieldName) {
        return fields.get(fieldName);
    }
}

public class ProfileVersioningServiceV2 {

    private final Map<Integer, TreeMap<Integer, Profile>> profiles = new HashMap<>();

    // Updates the profile with a new version
    public void update(int profileId, String fieldName, String fieldValue) {
        TreeMap<Integer, Profile> profileVersions = profiles.getOrDefault(profileId, new TreeMap<>());

        int latestVersion = profileVersions.isEmpty() ? 0 : profileVersions.lastKey();
        Profile newProfile;

        if (latestVersion == 0) {
            // If this is the first version
            newProfile = new Profile();
        } else {
            // Create a new profile based on the latest version
            newProfile = new Profile(profileVersions.get(latestVersion));
        }

        // Update or add the field in the new profile version
        newProfile.updateField(fieldName, fieldValue);

        // Add the new version to the profile versions map
        profileVersions.put(latestVersion + 1, newProfile);
        profiles.put(profileId, profileVersions);
    }

    // Retrieve the profile at a specific version
    public Map<String, String> get(int profileId, int version) {
        TreeMap<Integer, Profile> profileVersions = profiles.get(profileId);
        if (profileVersions == null || !profileVersions.containsKey(version)) {
            throw new IllegalArgumentException("Profile or version not found");
        }

        return profileVersions.get(version).getFields();
    }

    // Retrieve a specific field from a profile version
    public String getField(int profileId, int version, String fieldName) {
        TreeMap<Integer, Profile> profileVersions = profiles.get(profileId);
        if (profileVersions == null || !profileVersions.containsKey(version)) {
            throw new IllegalArgumentException("Profile or version not found");
        }

        return profileVersions.get(version).getField(fieldName);
    }
}

