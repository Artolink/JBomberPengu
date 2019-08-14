package model.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Work with file.
 */
public class FileWorker implements FileWorkerInterface {
    private static final String FILEEXTENSION = ".json";
    private String fileName;
    private String content;

    /**
     * Constructor for FileWorker. Takes the fileName to work with
     * 
     * @param fileName name of the file without extension
     */
    public FileWorker(final String fileName) {
        this.fileName = fileName;
        this.content = "";
    }

    @Override
    public final boolean isEmpty() {
        return this.content.isEmpty();
    }

    @Override
    public final void setContent(final String content) {
        this.content = content;
    }

    @Override
    public final void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    @Override
    public final String getFileName() {
        return this.fileName;
    }

    @Override
    public final String load() throws IOException {
        final File file = this.createIfNotExists();
        final FileReader fileReader = new FileReader(file);
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        this.content = "";
        while (line != null) {
            this.content += line;
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        fileReader.close();
        return this.content;
    }

    @Override
    public final void save() throws IOException {
        final File file = this.createIfNotExists();
        final FileWriter fileWriter = new FileWriter(file);
        final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(this.content);
        bufferedWriter.close();
        fileWriter.close();
    }

    private File createIfNotExists() throws IOException {
        final File file = new File(this.fileName + FILEEXTENSION);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException("File could not be created");
            }
        }
        return file;
    }
}
