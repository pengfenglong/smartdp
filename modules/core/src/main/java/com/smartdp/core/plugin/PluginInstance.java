package com.smartdp.core.plugin;

/**
 * 插件实例
 *
 * @author pengfenglong
 */
public class PluginInstance {

    private PluginClassLoader loader;
    private String fileName;
    private String name;
    private String version;
    private Class<?> entry;
    private String author;
    private String description;
    private String entryClass;

    public PluginInstance() {
    }

    public PluginClassLoader getLoader() {
        return loader;
    }

    public void setLoader(PluginClassLoader loader) {
        this.loader = loader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Class<?> getEntry() {
        return entry;
    }

    public void setEntry(Class<?> entry) {
        this.entry = entry;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public Plugin getPluginInstance() throws Exception {
        return (Plugin) entry.newInstance();
    }

    public void setEntryClass(String entryClass) {
        this.entryClass = entryClass;
    }

    public String getEntryClass() {
        return entryClass;
    }
}
