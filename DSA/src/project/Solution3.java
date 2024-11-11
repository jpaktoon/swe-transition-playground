package project;
import java.util.*;


/**
 * Summary:
 * - use TreeMap to not do any sorting on get
 * - Profile must update fields, not th function
 */
public class Solution3 {
    public Solution3() {

    }

    private class FieldData {
        public Integer version;
        public String value;

        public FieldData(Integer version, String value) {
            this.version = version;
            this.value = value;
        }

        public int getVersion() {
            return this.version;
        }
    }

    private class Profile {
        public Integer availableVersions;
        public Map<String, TreeSet<FieldData>> fields;
        public String profileId;


        public Profile(String profileId) {
            this.profileId = profileId;
            this.availableVersions = 0;
            this.fields = new TreeMap<>();
        }

    }

    /**
     * "pf1" {
     * _____versions: 4
     * _____fields:
     * ________"skills" : TreeSet [ { 1: "Java" },{ 4: "Java,Python" }]
     * ________"workexperience" : TreeSet [ { 3: "Indeed" } ]
     * ________"education" : TreeSet [ { 2: "UT" }]
     * }
     */
    Map<String, Profile> storage =  new HashMap<>();

    public void update(String profileId, String fieldName, String fieldValue) {
        Profile currentProfile = storage.getOrDefault(profileId, new Profile(profileId));
        if (currentProfile.fields.containsKey(fieldName)) {
            TreeSet<FieldData> currentField = currentProfile.fields.get(fieldName);
            currentField.add(new FieldData(currentProfile.availableVersions + 1, fieldValue));
        } else {
            TreeSet<FieldData> newFieldData = new TreeSet<>(Comparator.comparingInt(FieldData::getVersion));
            newFieldData.add(new FieldData(currentProfile.availableVersions + 1, fieldValue));
            currentProfile.fields.put(fieldName, newFieldData);
        }
        currentProfile.availableVersions = currentProfile.availableVersions + 1;
        storage.put(profileId, currentProfile);
    }

    public void get(String profileId, int version) {
        System.out.println("Profile for " + profileId + " at version " + version + ":");
        // fields sorted lexigrphically one line
        if (version == 0 || !storage.containsKey(profileId) || version > storage.get(profileId).availableVersions) {
            System.out.println("Invalid request!");
            return;
        }
        Profile profile = storage.get(profileId);
        for (String fieldName : profile.fields.keySet()) {
            String fieldValue = getFieldValue(profileId, version, fieldName);
            if (!Objects.equals(fieldValue, "")) {
                System.out.println(fieldName + " - " + fieldValue);
            }
        }
    }

    public void getField(String profileId, int version, String fieldName) {
        System.out.println(fieldName + " for " + profileId + " at version " + version + ":");
        String fieldValue = getFieldValue(profileId, version, fieldName);
        if (!Objects.equals(fieldValue, "")) {
            System.out.println(fieldValue);
        } else {
            System.out.println("Invalid request!");
        }
    }

    private String getFieldValue(String profileId, int version, String fieldName) {
        if (version == 0 || !storage.containsKey(profileId) || !storage.get(profileId).fields.containsKey(fieldName) || version > storage.get(profileId).availableVersions) {
            return "";
        }
        TreeSet<FieldData> currentFieldData = storage.get(profileId).fields.get(fieldName);
        // pick first version that is less or equal to the requested version
        // use floor() / binary search to find index faster
        FieldData targetFieldData = currentFieldData.floor(new FieldData(version, null));
        return targetFieldData == null ? "" : targetFieldData.value;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.update("pf1", "skills", "Java");
        solution.update("pf1", "apple", "Python");
        solution.update("pf2", "workxp", "Indeed");
        solution.get("pf1", 2);
        /**
         * update pf1 skills Java
         * update pf1 education UT
         * update pf2 workexperience Indeed
         * update pf1 skills Java,Python
         * get pf2 1
         * get pf1 0
         * get pf1 2
         * get pf1 3
         * get pf1 4
         * get pf3 1
         * getfield pf1 3 skills
         * getfield pf1 3 education
         * getfield pf3 1 skills
         */
        // My:
        /**
         * Profile for pf2 at version 1:
         * workexperience - Indeed
         * Profile for pf1 at version 0:
         * Invalid request!
         * Profile for pf1 at version 2:
         * education - UT
         * skills - Java
         * Profile for pf1 at version 3:
         * education - UT
         * skills - Java,Python
         * Profile for pf1 at version 4:
         * Invalid request!
         * Profile for pf3 at version 1:
         * Invalid request!
         * skills for pf1 at version 3:
         * Java,Python
         * education for pf1 at version 3:
         * UT
         * skills for pf3 at version 1:
         * Invalid request!
         * Invalid request!
         */


        // Expected:
        /**
         * Profile for pf2 at version 1:
         * workexperience - Indeed
         * Profile for pf1 at version 0:
         * Invalid request!
         * Profile for pf1 at version 2:
         * education - UT
         * skills - Java
         * Profile for pf1 at version 3:
         * education - UT
         * skills - Java,Python
         * Profile for pf1 at version 4:
         * Invalid request!
         * Profile for pf3 at version 1:
         * Invalid request!
         * skills for pf1 at version 3:
         * Java,Python
         * education for pf1 at version 3:
         * UT
         * skills for pf3 at version 1:
         * Invalid request!
         */
    }
}


