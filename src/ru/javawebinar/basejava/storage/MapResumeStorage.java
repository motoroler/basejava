package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class MapResumeStorage extends AbstractStorage {
    private final static Map<String, Resume> storage = new HashMap<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void doDelete(Object id) {
        storage.remove(((Resume) id).getUuid());
    }

    @Override
    protected Resume doGet(Object id) {
        return storage.get(((Resume) id).getUuid());
    }

    @Override
    protected void doUpdate(Object id, Resume resume) {
        storage.put(((Resume) id).getUuid(), resume);
    }

    @Override
    protected void doSave(Resume resume, Object id) {
        storage.put(resume.getUuid(), resume);
        System.out.printf("New resume %s was added to the storage\n", resume);
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Object id) {
        return !Objects.isNull(id);
    }

    @Override
    protected Stream<Resume> doAllSorted() {
        return storage.values().stream();
    }
}
