package controller.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 */
public class FileWorker {
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
    }

    /**
     * Check the content of file readed.
     * 
     * @return true if nothing readed, false otherwise.
     */
    public boolean isEmpty() {
        return this.content.isEmpty();
    }

    /**
     * Set the content to save on the file specified on constructor.
     * 
     * @param content String to save
     */
    public void setContent(final String content) {
        this.content = content;
    }

    /**
     * Set the name of the file without extension.
     * 
     * @param fileName String Name of the file
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Get the fileName.
     * 
     * @return String file name
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Get content of file.
     * 
     * @return String content on file
     * @throws IOException caused by error reading the file
     */
    public String load() throws IOException {
        File file = this.createIfNotExists();
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

    /**
     * Save the content setted with setContent() on file.
     * 
     * @throws IOException caused by error writing on file
     */
    public void save() throws IOException {
        File file = this.createIfNotExists();
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
