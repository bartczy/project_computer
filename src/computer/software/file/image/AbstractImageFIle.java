package computer.software.file.image;

import computer.software.file.shared.AbstractFile;
import computer.software.file.shared.FileType;

public class AbstractImageFIle extends AbstractFile {

    public AbstractImageFIle(String name, int size) {
        super(name, size);
    }
    @Override
    public FileType getType() {
        return FileType.IMAGE;
    }
}
