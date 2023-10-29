package entities.storage;

import java.util.List;

public class File {
    private String uploadedBy;
    private String content;
    private FilePermission filePermission;

    public File(String uploadedBy, String content) {
        this.uploadedBy = uploadedBy;
        this.content = content;
        this.filePermission = new FilePermission();
    }

    public String getContent() {
        return content;
    }

    public void grantUserReadAccess(String user) {
        filePermission.grantUserReadAccess(user);
    }
    public boolean doesUserHasReadPermission(String userId) {
        return filePermission.doesUserHasReadAccess(userId);
    }
}
