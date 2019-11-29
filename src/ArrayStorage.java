import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int arraySize = 10_000;
    private Resume[] storage = new Resume[arraySize];
    private int nextFreeElement = 0;

    private boolean isAddable() {
        if (nextFreeElement >= arraySize) {
            System.out.println("WARNING! Storage is full!");
            return false;
        } else {
            return true;
        }
    }

    void clear() {
        Arrays.fill(storage, null);
        System.out.println("The storage was cleared");
        nextFreeElement = 0;
    }

    void save(Resume r) {
        if (isAddable()) {
            storage[nextFreeElement++] = r;
            System.out.printf("New resume %s was added to the storage\n", r);
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < nextFreeElement; i++) {
            if (storage[i].toString().equals(uuid)) return storage[i];
        }
        System.out.printf("Resume %s was not found\n", uuid);
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < nextFreeElement; i++) {
            if (storage[i].toString().equals(uuid)) {
                for (int j = i; j < nextFreeElement - 1; j++) {
                    storage[j] = storage[j + 1];// remove storage[i] with uuid and shift storage[i+1] and above to the storage's beginning
                }
                nextFreeElement--;
                System.out.printf("%s resume was deleted\n", uuid);
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, nextFreeElement);
    }

    int size() {
        return nextFreeElement;
    }
}
