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
    protected void doDelete(int index) {
        storage.remove(index);
    }

    @Override
    protected Resume doGet(int index) {
        return storage.get(index);
    }

    @Override
    protected void doUpdate(int index, Resume resume) {
        storage.set(index, resume);
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
    protected void doSave(Resume resume, int index) {
        storage.add(resume);
        System.out.printf("New resume %s was added to the storage\n", resume);
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
