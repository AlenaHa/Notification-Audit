package com.infouaic.a6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.*;

public class LoggingService {
    public static final String LOG_FOLDER_NAME  = "logs";
    public static final String LOG_ARCHIVE_NAME = "log_history.zip";

	private Logger logger = LogManager.getLogger(LoggingService.class.getName());

    public org.apache.logging.log4j.Logger getLogger() {
    	return this.logger;
	}

	public void deleteFilesOlderThanNdays(int daysBack, final String directoryPath) {
		File directory = new File(directoryPath);

		if(directory.exists()){
			File[] listFiles = directory.listFiles();

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, daysBack * -1);
			long purgeTime = cal.getTimeInMillis();

			for(File listFile : listFiles) {
				if(listFile.lastModified() < purgeTime) {
                    this.addFileToZip(LOG_ARCHIVE_NAME, listFile.getAbsolutePath());

                    if (listFile.delete()) {
                        logger.info("File successful deleted. " + listFile.getAbsolutePath());
                    }
                    else {
                        logger.fatal("Delete operation is failed.");
                    }
                }
			}
		}
	}

    private void addFileToZip(String zipFile, String fileName) {
        Map<String, String> env = new HashMap<>();
        env.put("create", "true");

        Path path = Paths.get(zipFile);
        URI uri = URI.create("jar:" + path.toUri());

        try (FileSystem fs = FileSystems.newFileSystem(uri, env))
        {
            String[] parts = fileName.split("\\\\");

            Path inZipFile = fs.getPath(parts[parts.length - 1]);
            Path fileToAdd = Paths.get(fileName);

            Files.copy(fileToAdd, inZipFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
		LoggingService loggingService = new LoggingService();

		int i = 200;
		while (i > 0) {
			loggingService.getLogger().info("salut boss");
			loggingService.getLogger().debug("interesant");
			loggingService.getLogger().warn("eroare");
			loggingService.getLogger().fatal("fatal");

			i--;
		}

        LogManager.shutdown();

        loggingService.deleteFilesOlderThanNdays(1, LOG_FOLDER_NAME);
	}
}