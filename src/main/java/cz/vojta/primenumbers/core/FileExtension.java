package cz.vojta.primenumbers.core;

public enum FileExtension {

    XLS("xls"),
    XLSX("xlsx"),
    UNSUPPORTED("unsupported");

    private final String extension;

    FileExtension(String extension) {
        this.extension = extension;
    }

    public static FileExtension resolveFileExtension(String extension) {
        for (FileExtension fileExtension : FileExtension.values()) {
            if (fileExtension.extension.equals(extension) ) {
                return fileExtension;
            }
        }

        return UNSUPPORTED;
    }

    public String getExtension() {
        return extension;
    }
}
