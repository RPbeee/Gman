package net.github.rpbeee.gman;

import jdk.tools.jlink.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ManualControl {

    private Path file;
    private String fileName;
    private String title;
    private Plugin plugin;

    public void ManualControl (Plugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        return;
    }

    public List<String> getString() {
        this.file = Paths.get(fileName);
        List<String> manual = null;
        try {
            manual = Files.readAllLines(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return manual;
    }

    public boolean manualExists() {
        File manual = new File(this.fileName);
        return manual.exists();
    }

}
