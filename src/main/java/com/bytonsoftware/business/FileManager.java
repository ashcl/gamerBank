package com.bytonsoftware.business;

import java.io.File;
import java.io.IOException;

public class FileManager {

    public static void main(String[] args) {
        /*File startFile = new File("testFolder");
		listFiles(startFile, 0);
		if (addDirectory(startFile, "asdf").exists()) {
			System.out.println("Directories created!");
		}
		File fileToRemove = new File("testFolder/asdf");
		remove(fileToRemove);
		try {
			addFile(new File("testFolder"), "permissionsChanged.txt");
		} catch (IOException e) {

		}
		changePermission(new File("testFolder/permissionsChanged.txt"), true,
				true, false);*/
    }

    public static void listFiles(File startFile, int indentLevel) {
        // Get the amount of indention needed for each listing
        String indention = "";
        for (int i = 0; i < indentLevel; i++) {
            indention = indention + "\t";
        }
        // Print the starter folder's name
        System.out.println(indention + startFile.getName());
        // Get all subdirectories and files in the given start file
        File[] subFiles = startFile.listFiles();
        // Iterate through the files and check for subdirectories to check in
        for (int i = 0; i < subFiles.length; i++) {
            // If the File is a directory, iterate through it.
            if (subFiles[i].isDirectory()) {
                listFiles(subFiles[i], indentLevel + 1);
            } else {
                System.out.println(indention + "\t" + subFiles[i].getName());
            }
        }

    }

    public static File addDirectory(File parentDir, String dirName) throws IOException {
        File newDir = null;
        if (parentDir.exists()) {
            // Check if parentDir is a directory
            if (parentDir.isDirectory()) {
                // Create the new directory
                newDir = new File(parentDir.getPath() + "/" + dirName);
                if (!newDir.exists()) {
                    boolean success = newDir.mkdirs();
                    if (!success) {
                        newDir = null;
                        throw new IOException();
                    }
                } else {
                    System.out.println("Directory already exists!");
                    newDir = null;
                }// end dir exists if
            }// end parent directory is a directory if
        }// end parent directory exists if
        return newDir;
    }// end addDirectory

    public static File addFile(File parentDir, String fileName)
            throws IOException {
        File newFile = null;
        if (parentDir.exists()) {
            // Check if parentDir is a directory
            if (parentDir.isDirectory()) {
                // Create the new file
                newFile = new File(parentDir.getPath() + "/" + fileName);
                if (!newFile.exists()) {
                    boolean success = newFile.createNewFile();
                    if (!success) {
                        newFile = null;
                        throw new IOException();
                    }

                } else {
                    System.out.println("File already exists!");
                    newFile = null;
                }// end file exists if
            }// end parent directory is a directory if
        }// end parent directory exists if
        return newFile;
    }

    public static void remove(File itemToRemove) {
        // Check if the file exists
        if (itemToRemove.exists()) {
            // Check if the file is a directory
            if (itemToRemove.isDirectory()) {
                File[] files = itemToRemove.listFiles();
                for (int i = 0; i < files.length; i++) {
                    remove(files[i]);
                }
                itemToRemove.delete();
            } else if (itemToRemove.isFile()) {
                // Remove the file from the system
                itemToRemove.delete();
            }
        }
    }

    public static boolean rename(File originalName, File newName) {
        if (!newName.exists()) {
            return originalName.renameTo(newName);
        } else {
            return false;
        }
    }

    public static boolean changePermission(File input, boolean readable,
                                           boolean writable, boolean executable) {
        // Create a return boolean to determine if all processes succeeded
        boolean success = false;
        // Change the permissions of the file based on passed in parameters
        success = input.setReadable(readable);
        if (success) {
            success = input.setWritable(writable);
            if (success) {
                success = input.setExecutable(executable);
            }

        }
        return success;
    }
}
