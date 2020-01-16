package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapStorage extends AbstractStorage {
    private final static Map<Integer, Resume> storage = new HashMap<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void doDelete(int id) {
        storage.remove(id);
    }

    @Override
    protected Resume doGet(int id) {
        return storage.get(id);
    }

    @Override
    protected void doUpdate(int id, Resume resume) {
        storage.put(id, resume);
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
    protected void doSave(Resume resume, int id) {
        storage.put(-id, resume);
    }

    @Override
    protected int getId(String uuid) {
        int hashCode = Objects.hashCode(uuid);
        if (storage.containsKey(hashCode)) {
            return hashCode;
        }
        return -hashCode;
    }
}
