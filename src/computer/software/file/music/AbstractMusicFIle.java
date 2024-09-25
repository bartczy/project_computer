package computer.software.file.music;

import computer.software.file.shared.AbstractFile;
import computer.software.file.shared.FileType;

public abstract class AbstractMusicFIle extends AbstractFile implements MusicFile{
    protected String bandName;
    protected String title;

    public AbstractMusicFIle(String name, int size, String bandName, String title) {
        super(name, size);
        this.bandName = bandName;
        this.title = title;
    }

    @Override
    public FileType getType() {
        return FileType.MUSIC;
    }
}
