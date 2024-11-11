package project.sample.profile;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class ProfileVersioningServiceTest {

    private ProfileVersioningServicePerf profileService;

    @BeforeEach
    public void setUp() {
        profileService = new ProfileVersioningServicePerf();
    }

    @AfterEach
    public void tearDown() {
        profileService.clear();
    }

    // Test storing initial profile and creating first version
    @Test
    public void testCreateInitialVersion() {
        profileService.update(1, "name", "Alice");

        Map<String, String> profile = profileService.get(1, 1);
        assertEquals(1, profile.size());
        assertEquals("Alice", profile.get("name"));
    }

    // Test updating profile creates a new version
    @Test
    public void testUpdateCreatesNewVersion() {
        profileService.update(1, "name", "Alice");
        profileService.update(1, "age", "30");

        Map<String, String> profileV1 = profileService.get(1, 1);
        Map<String, String> profileV2 = profileService.get(1, 2);

        assertEquals(1, profileV1.size());
        assertEquals("Alice", profileV1.get("name"));

        assertEquals(2, profileV2.size());
        assertEquals("Alice", profileV2.get("name"));
        assertEquals("30", profileV2.get("age"));
    }

    // Test updating existing field
    @Test
    public void testUpdateExistingField() {
        profileService.update(1, "name", "Alice");
        profileService.update(1, "name", "Alice Updated");

        Map<String, String> profileV2 = profileService.get(1, 2);

        assertEquals(1, profileV2.size());
        assertEquals("Alice Updated", profileV2.get("name"));
    }

    // Test retrieving specific field by version
    @Test
    public void testGetSpecificFieldByVersion() {
        profileService.update(1, "name", "Alice");
        profileService.update(1, "age", "30");

        assertEquals("Alice", profileService.getField(1, 1, "name"));
        assertEquals("30", profileService.getField(1, 2, "age"));
    }

    // Test getting non-existent version throws exception
    @Test
    public void testGetNonExistentVersionThrowsException() {
        profileService.update(1, "name", "Alice");

        assertThrows(IllegalArgumentException.class, () -> {
            profileService.get(1, 99);
        });
    }

    // Test getting non-existent field throws exception
    @Test
    public void testGetNonExistentFieldThrowsException() {
        profileService.update(1, "name", "Alice");

        assertThrows(IllegalArgumentException.class, () -> {
            profileService.getField(1, 1, "age");
        });
    }
}

