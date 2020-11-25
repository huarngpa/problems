import java.util.*;

/**
 * Design In-Memory File System
 * 
 * Design an in-memory file system to simulate the following functions:
 * 
 * ls: Given a path in string format. If it is a file path, return a list that
 * only contains this file's name. If it is a directory path, return the list of
 * file and directory names in this directory. Your output (file and directory
 * names together) should in lexicographic order.
 * 
 * mkdir: Given a directory path that does not exist, you should make a new
 * directory according to the path. If the middle directories in the path don't
 * exist either, you should create them as well. This function has void return
 * type.
 * 
 * addContentToFile: Given a file path and file content in string format. If the
 * file doesn't exist, you need to create that file containing given content. If
 * the file already exists, you need to append given content to original
 * content. This function has void return type.
 * 
 * readContentFromFile: Given a file path, return its content in string format.
 */
public class InMemoryFileSystem {

  interface FileLike {

    public boolean isDirectory();

    public List<String> ls(String path);

    public void mkdir(String path);

    public void addContentToFile(String filePath, String content);

    public String readContentFromFile(String filePath);
  }

  class FileSystem implements FileLike {

    public Map<String, FileLike> files = new HashMap<>();

    public FileSystem() {
    }

    public boolean isDirectory() {
      return true;
    }

    private String buildKey(String[] dirs, int start) {
      String result = "";
      for (int i = start; i < dirs.length; i++) {
        result = result + "/" + dirs[i];
      }
      return result;
    }

    public List<String> ls(String path) {
      String[] dirs = path.split("/");
      List<String> result;
      if (dirs.length > 1) {
        String currentLevel = dirs[1];
        FileLike file = files.get(currentLevel);
        result = file.ls(buildKey(dirs, 2));
      } else {
        result = new ArrayList<>(files.keySet());
        Collections.sort(result);
      }
      return result;
    }

    public void mkdir(String path) {
      String[] dirs = path.split("/");
      if (dirs.length > 1) {
        String currentLevel = dirs[1];
        FileLike current = files.computeIfAbsent(currentLevel, k -> new FileSystem());
        current.mkdir(buildKey(dirs, 2));
      }
    }

    public void addContentToFile(String filePath, String content) {
      String[] dirs = filePath.split("/");
      if (dirs.length > 2) {
        String currentLevel = dirs[1];
        FileLike file = files.get(currentLevel);
        file.addContentToFile(buildKey(dirs, 2), content);
      } else {
        String fileName = dirs[dirs.length - 1];
        FileLike file = files.computeIfAbsent(fileName, k -> new File(fileName));
        file.addContentToFile(fileName, content);
      }
    }

    public String readContentFromFile(String filePath) {
      String[] dirs = filePath.split("/");
      String currentLevel = dirs[1];
      FileLike file = files.get(currentLevel);
      if (dirs.length > 2) {
        return file.readContentFromFile(buildKey(dirs, 2));
      }
      return file.readContentFromFile("");
    }
  }

  class File implements FileLike {

    String name = "";
    String contents = "";

    public File(String name) {
      this.name = name;
    }

    public boolean isDirectory() {
      return false;
    }

    public List<String> ls(String path) {
      return new ArrayList<>(Arrays.asList(name));
    }

    public void mkdir(String path) {
      throw new RuntimeException("Not implemented");
    }

    public void addContentToFile(String filePath, String content) {
      addContentToFile(content);
    }

    public void addContentToFile(String content) {
      contents = contents + content;
    }

    public String readContentFromFile(String filePath) {
      return readContentFromFile();
    }

    public String readContentFromFile() {
      return contents;
    }
  }

  public static void main(String[] args) {

  }
}
