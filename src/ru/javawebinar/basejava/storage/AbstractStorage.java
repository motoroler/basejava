package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void clear() {
        System.out.println("The storage was cleared");
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storageSet(index, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storageGet(index);
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract Resume storageGet(int index);

    protected abstract void storageSet(int index, Resume resume);

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            fillDeletedElement(index);
            System.out.printf("%s resume was deleted\n", uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void save(Resume resume) {
        checkStorageLimitCondition(resume);
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            insertElement(resume, index);
            System.out.printf("New resume %s was added to the storage\n", resume);
        }
        incrementSize();
    }

    protected void incrementSize() {
    }

    protected void checkStorageLimitCondition(Resume resume) {
    }

    protected abstract void insertElement(Resume resume, int index);

    protected int getIndex(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storageGet(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    protected abstract void fillDeletedElement(int index);
}
