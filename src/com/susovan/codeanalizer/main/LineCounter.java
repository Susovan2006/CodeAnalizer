package com.susovan.codeanalizer.main;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;


//write a java utility class that will scan the files and folders and list down all the unique file extensions. return the list of Sting of extensions that are max 5 byte long and it should ignore the exclution list of extensions and files without any extension.
public class LineCounter {
	public static List<FileData> countFilesAndLinesByExtension(Path path, List<String> extensions) throws IOException {
        Map<String, FileData> result = new HashMap<>();
        FileData totalData = new FileData("Total", 0, 0);
        scanDirectory(path, result, extensions, totalData);
        List<FileData> resultList = new ArrayList<>(result.values());
        resultList.add(totalData);
        resultList.sort(Comparator.comparingInt(FileData::getLineCount));
        return resultList;
    }
	
	public static String sanitizeForHtml(String input) {
        if (input == null) {
            return null;
        }
        return input.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#x27;")
                    .replace("/", "&#x2F;");
    }

    private static void scanDirectory(Path path, Map<String, FileData> result, List<String> extensions, FileData totalData) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    scanDirectory(entry, result, extensions, totalData);
                } else {
                    String extension = getFileExtension(entry);
                    if (extensions.contains(extension)) {
                        int lineCount = countLines(entry);
                        result.putIfAbsent(extension, new FileData(extension, 0, 0));
                        FileData data = result.get(extension);
                        data.incrementFileCount();
                        data.addLines(lineCount);
                        totalData.incrementFileCount();
                        totalData.addLines(lineCount);
                    }
                }
            }
        }catch(Exception ex) {
        	//ex.printStackTrace();
        }
    }

    private static String getFileExtension(Path file) {
        String fileName = file.toString();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    private static int countLines(Path file) throws IOException {
        return (int) Files.lines(file).count();
    }

    public static class FileData {
        private String extension;
        private int fileCount;
        private int lineCount;

        public FileData(String extension, int fileCount, int lineCount) {
            this.extension = extension;
            this.fileCount = fileCount;
            this.lineCount = lineCount;
        }

        public void incrementFileCount() {
            this.fileCount++;
        }

        public void addLines(int lines) {
            this.lineCount += lines;
        }

        public String getExtension() {
            return extension;
        }

        public int getFileCount() {
            return fileCount;
        }

        public int getLineCount() {
            return lineCount;
        }
    }
}
