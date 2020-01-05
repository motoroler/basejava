package ru.javawebinar.basejava.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("CV " + uuid + " doesn't exist", uuid);
    }
}
