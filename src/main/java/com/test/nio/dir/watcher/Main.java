package com.test.nio.dir.watcher;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //define a folder root
        Path myDir = Paths.get("g:/temp");       
        while(true){
        try {
           WatchService watcher = myDir.getFileSystem().newWatchService();
           myDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, 
           StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

           WatchKey watckKey = watcher.take();

           List<WatchEvent<?>> events = watckKey.pollEvents();
           for (WatchEvent event : events) {
        	   @SuppressWarnings("unchecked")
				Path name = ((WatchEvent<Path>) event).context();
				Path child = myDir.resolve(name);
                if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                    System.out.println("Created: " + event.context().toString());
                        try {
                            if (Files.isRegularFile(child) && Files.exists(child)) {
                            	System.out.println("name: "+ name);
                            	Path newDirPath = myDir.resolve("temp");
                            	Files.move(child, newDirPath.resolve(name), StandardCopyOption.REPLACE_EXISTING);
                            	System.out.println("File moved");
                            	/*if(Files.exists(child)){
                            		Files.delete(child);
                            	}*/
                            }
                        } catch (IOException x) {
                            // do something useful
                        	x.printStackTrace();
                        }
                }
                if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                    System.out.println("Delete: " + event.context().toString());
                }
                if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                    System.out.println("Modify: " + event.context().toString());
                }
            }
           
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
        }
    }
}