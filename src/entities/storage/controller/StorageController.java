package entities.storage.controller;

import entities.storage.File;
import entities.user.User;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StorageController {
    private static StorageController instance;
    private Map<String, File> storedFiles;

    private StorageController() {
        storedFiles = new TreeMap<>();
    }

    private String getId(String key, String uploadedBy) {
        return String.format("%s:%s", key, uploadedBy);
    }

    public static StorageController getInstance() {
        if (instance == null) {
            synchronized (StorageController.class) {
                if (instance == null) {
                    instance = new StorageController();
                }
            }
        }
        return instance;
    }

    public String grantUserReadAccess(String key, String uploadedBy, String user) {
        File file = storedFiles.get(getId(key, uploadedBy));
        System.out.println("granting user read permission" + file);
        if (file != null) {
            file.grantUserReadAccess(user);
        }
        return getId(key, uploadedBy);
    }
    public void uploadFile(String id, String uploadedBy, String content) {
        File file = new File(uploadedBy, content);
        storedFiles.put(getId(id, uploadedBy), file);
    }

    public String downloadFile(User user, String fileId) {
        File file = storedFiles.get(fileId);
        if (file == null) {
            System.out.println("file does not exist");
        } else if (!file.doesUserHasReadPermission(user.getId())) {
            System.out.println("user hasn't purchased the book");
        }  else {
            return file.getContent();
        }
        return null;
    }
}
