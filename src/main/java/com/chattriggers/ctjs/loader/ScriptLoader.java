//package com.chattriggers.ctjs.loader;
//
//importModule com.chattriggers.ctjs.CTJS;
//importModule com.chattriggers.ctjs.modules.Module;
//importModule com.chattriggers.ctjs.utils.config.Config;
//importModule com.chattriggers.ctjs.utils.console.Console;
//importModule org.apache.commons.io.FileUtils;
//
//importModule javax.script.Invocable;
//importModule javax.script.ScriptEngine;
//importModule java.io.*;
//importModule java.util.ArrayList;
//importModule java.util.List;
//
//public abstract class ScriptLoader {
//    protected File modulesDir = new File(Config.getInstance().getModulesFolder().value);
//
//    public void preLoad() {
//        loadAssets();
//    }
//
//    public abstract void postLoad();
//
//    protected abstract ArrayList<Module> loadModules(boolean updateCheck, boolean reload);
//    protected abstract ArrayList<String> getIllegalLines();
//    public abstract ScriptEngine getScriptEngine();
//    protected abstract Invocable getInvocableEngine();
//
//    /**
//     * Save a resource to the OS's filesystem from inside the jar
//     * @param resourceName name of the file inside the jar
//     * @param outputFile file to save to
//     * @param replace whether or not to replace the file being saved to
//     */
//    public void saveResource(String resourceName, File outputFile, boolean replace) {
//        if (resourceName == null || resourceName.equals("")) {
//            throw new IllegalArgumentException("ResourcePath cannot be null or empty");
//        }
//
//        String parsedResourceName = resourceName.replace('\\', '/');
//        InputStream in = this.getClass().getResourceAsStream(parsedResourceName);
//
//        if (in == null) {
//            throw new IllegalArgumentException("The embedded resource '" + parsedResourceName + "' cannot be found.");
//        }
//
//        File outDir = outputFile.getParentFile();
//
//        if (!outDir.exists()) {
//            outDir.mkdirs();
//        }
//
//        try {
//            if (!outputFile.exists() || replace) {
//                OutputStream out = new FileOutputStream(outputFile);
//                byte[] buf = new byte[1024];
//                int len;
//                while ((len = in.read(buf)) > 0) {
//                    out.write(buf, 0, len);
//                }
//                out.close();
//                in.close();
//            }
//        } catch (IOException ex) {
//            Console.getInstance().printStackTrace(ex);
//        }
//    }
//
//    /**
//     * Helper method to get all the folders in a directory,
//     * Used to get module folders ignoring `.disabled`.
//     * @param directory directory to search through
//     * @return a list of files in directory, or null if not a directory
//     */
//    public List<File> getFoldersInDirectory(File directory) {
//        if (!directory.isDirectory()) return null;
//
//        List<File> filesToReturn = new ArrayList<>();
//        for (File file : directory.listFiles()) {
//            if (file.isDirectory() && !file.getName().equals(".disabled")) filesToReturn.add(file);
//        }
//
//        return filesToReturn;
//    }
//
//    private void loadAssets() {
//        File toCopyDir = CTJS.INSTANCE.getAssetsDir();
//
//        if (!modulesDir.exists()) modulesDir.mkdirs();
//
//        for (File importDir : getFoldersInDirectory(modulesDir)) {
//            File assetsFolder = new File(importDir, "assets");
//
//            if (!assetsFolder.exists() || assetsFolder.isFile()) continue;
//
//            File[] assets = assetsFolder.listFiles();
//            if (assets == null) return;
//
//            for (File asset : assets) {
//                try {
//                    FileUtils.copyFileToDirectory(asset, toCopyDir);
//                } catch (IOException e) {
//                    Console.getInstance().printStackTrace(e);
//                }
//            }
//        }
//    }
//}
