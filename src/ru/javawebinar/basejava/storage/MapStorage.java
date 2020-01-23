package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final static Map<String, Resume> storage = new HashMap<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void doDelete(Object id) {
        storage.remove((String) id);
    }

    @Override
    protected Resume doGet(Object id) {
        return storage.get((String) id);
    }

    @Override
    protected void doUpdate(Object id, Resume resume) {
        storage.put((String) id, resume);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(Resume[]::new);
    }

    @Override
    protected void doSave(Resume resume, Object id) {
        storage.put((String) id, resume);
    }

    @Override
    protected Object getId(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object id) {
        return (storage.containsKey(id));
    }
}
