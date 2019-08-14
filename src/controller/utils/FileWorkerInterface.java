package controller.utils;

import java.io.IOException;

/**
 * Work with file.
 */
public interface FileWorkerInterface {

    /**
     * Check the content of file read.
     * 
     * @return true if nothing read, false otherwise.
     */
    boolean isEmpty();

    /**
     * Set the content to save on the file specified on constructor.
     * 
     * @param content String to save
     */
    void setContent(String content);

    /**
     * Set the name of the file without extension.
     * 
     * @param fileName String Name of the file
     */
    void setFileName(String fileName);

    /**
     * Get the fileName.
     * 
     * @return String file name
     */
    String getFileName();

    /**
     * Get content of file.
     * 
     * @return String content on file
     * @throws IOException caused by error reading the file
     */
    String load() throws IOException;

    /**
     * Save the content set with setContent() on file.
     * 
     * @throws IOException caused by error writing on file
     */
    void save() throws IOException;

}
