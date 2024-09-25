package computer.hardware.components.drive;
import computer.software.file.shared.Capacity;
import computer.hardware.components.ComponentType;
import computer.software.file.shared.File;
import computer.software.file.shared.FileNotFoundException;
import computer.software.file.shared.FileStorage;

public class HDDDrive implements Drive {
    private final String name;
    private final FileStorage fileStorage;


    public HDDDrive(String name, Capacity storageCapacity) {
        this.name = name;
        fileStorage = new FileStorage(storageCapacity);
    }

    @Override
    public void addFile(File file) {
        fileStorage.addFile(file);
    }

    @Override
    public void removeFile(File file) {
        fileStorage.removeFile(file);
    }

    @Override
    public void listFiles() {
        fileStorage.listFiles();
    }

    @Override
    public File findFile(String fileName) {
        try {
            return fileStorage.findFile(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ComponentType getType() {
        return ComponentType.DRIVE;
    }
}
