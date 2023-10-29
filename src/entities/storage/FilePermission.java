package entities.storage;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FilePermission {
    private List<String> readPermission;

    public FilePermission() {
        readPermission = new LinkedList<>();
    }

    public void grantUserReadAccess(String userId) {
        readPermission.add(userId);
    }

    public boolean doesUserHasReadAccess(String userId) {
        return readPermission.contains(userId);
    }
}
