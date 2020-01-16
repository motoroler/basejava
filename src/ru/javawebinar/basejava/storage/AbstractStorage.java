package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume doOperation(Resume resume, String operation, String uuid, StorageException exception) {
        int id = getId(uuid);
        if (id >= 0) {
            switch (operation) {
                case ("update"):
                    doUpdate(id, resume);
                    break;
                case ("get"):
                    return doGet(id);
                case ("delete"):
                    doDelete(id);
                    System.out.printf("%s resume was deleted\n", uuid);
                    break;
                case ("save"):
                    throw exception;
            }
        } else {
            switch (operation) {
                case ("update"):
                case ("get"):
                case ("delete"):
                    throw exception;
                case ("save"):
                    doSave(resume, id);
                    break;
            }
        }
        return null;
    }

    @Override
    public void update(Resume resume) {
        doOperation(resume, "update", resume.getUuid(), new NotExistStorageException(resume.getUuid()));
    }

    protected abstract int getId(String uuid);

    @Override
    public Resume get(String uuid) {
        return doOperation(null, "get", uuid, new NotExistStorageException(uuid));
    }

    protected abstract Resume doGet(int id);

    protected abstract void doUpdate(int id, Resume resume);

    @Override
    public void save(Resume resume) {
        doOperation(resume, "save", resume.getUuid(), new ExistStorageException(resume.getUuid()));
    }

    protected abstract void doSave(Resume resume, int id);

    @Override
    public void delete(String uuid) {
        doOperation(null, "delete", uuid, new NotExistStorageException(uuid));
    }

    protected abstract void doDelete(int id);
}
