package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.LinkedList;

public class ListStorage extends AbstractStorage {
    private final static LinkedList<Resume> storage = new LinkedList<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void doDelete(int id) {
        storage.remove(id);
    }

    @Override
    protected Resume doGet(int index) {
        return storage.get(index);
    }

    @Override
    protected void doUpdate(int id, Resume resume) {
        storage.set(id, resume);
    }

    @Override
    public void clear() {
        storage.clear();
        System.out.println("The storage was cleared");
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(Resume[]::new);
    }

    @Override
    protected void doSave(Resume resume, int id) {
        storage.add(resume);
        System.out.printf("New resume %s was added to the storage\n", resume);
    }

    @Override
    protected int getId(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
