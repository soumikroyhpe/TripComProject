package com.poc.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.poc.baseClass.BaseTest;

public class FolderChecking extends BaseTest {
	
	public static void moveToArchive(String pathName, String processName) throws IOException
	{
		//String Folderpath = BaseTest.getParentDir() + pathName;
		String Folderpath= prop.getProperty("ProjectHomePath") + pathName;
		File f = new File(Folderpath);
		if(f.exists())
		{
			System.out.println("Folder "+f.getCanonicalPath()+" exist.");
			
			String renamingTestResultFolder = processName + "_" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
					.format(new Date());
			
			System.out.println("Renaming Test Result Folder name : "+renamingTestResultFolder);
			
			//String sourcePath= BaseTest.getParentDir() + prop.getProperty("screenshotPath") + renamingTestResultFolder;
			String sourcePath= prop.getProperty("ProjectHomePath") + prop.getProperty("screenshotPath") + renamingTestResultFolder;
			
			System.out.println("Sourcepath folder path : "+sourcePath);
			
			//String targetPath = BaseTest.getParentDir() + prop.getProperty("PathTillArchiveFolder") +renamingTestResultFolder;
			
			String targetPath = prop.getProperty("ProjectHomePath") + prop.getProperty("PathTillArchiveFolder") +renamingTestResultFolder;
			
			System.out.println("Targetpath folder path : "+targetPath);
			
			try {
				Files.move(f.toPath(), Paths.get(targetPath));
				System.out.println("Existing Test Result folder is renamed & moved to Archive Folder !!!!");
				
				new File(Folderpath).mkdir();
				
			}
			catch (java.nio.file.NoSuchFileException e) {
				
				System.out.println("Existing Test Result Folder failed to move : "+e.getMessage());
			}
		}
		else
		{
			System.out.println("Error,Folder does not exist!! New folder is created ");
			try {
				new File(Folderpath).mkdir();
				
			}catch (Exception e) {
				
			}
		}
	}

}
