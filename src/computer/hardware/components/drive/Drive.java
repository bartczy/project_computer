package computer.hardware.components.drive;
import computer.hardware.components.Components;
import computer.software.file.shared.File;
import computer.software.file.shared.FileNotFoundException;

public interface Drive extends Components {
    void addFile(File file);
    void removeFile(File file);
    void listFiles();
    File findFile(String fileName) throws FileNotFoundException;
}
