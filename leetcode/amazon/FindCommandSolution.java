import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * Implement the Linux Find Command
 * 
 * The API needs to support finding files of a given size and format.
 * 
 * Find all files greater than 5MB and/or find all XML files.
 */
public class FindCommandSolution {

  interface FileFilter {
    boolean apply(File file);
  }

  public class GTSizeFilter implements FileFilter {

    Optional<Long> sizeBytes;

    GTSizeFilter(Optional<Long> sizeBytes) {
      this.sizeBytes = sizeBytes;
    }

    @Override
    public boolean apply(File file) {
      if (sizeBytes.isPresent()) {
        return file.length() > sizeBytes.get();
      }
      return true;
    }
  }

  public class FileTypeFilter implements FileFilter {

    Optional<String> fileType;

    FileTypeFilter(Optional<String> fileType) {
      this.fileType = fileType;
    }

    @Override
    public boolean apply(File file) {
      if (fileType.isPresent()) {
        try {
          return Files.probeContentType(file.toPath()) == fileType.get();
        } catch (IOException e) {
          return true;
        }
      }
      return true;
    }
  }

  private void findFilesHelper(File folder, List<FileFilter> filters, List<File> result) {
    for (final File file : folder.listFiles()) {
      if (file.isDirectory()) {
        findFilesHelper(file, filters, result);
      } else {
        Optional<Boolean> appliedFilters = filters.stream().map(filter -> filter.apply(file)).reduce((l, r) -> l && r);
        if (appliedFilters.get()) {
          result.add(file);
        }
      }
    }
  }

  public List<File> findFiles(File folder, Optional<Long> greaterThanBytes, Optional<String> fileType) {
    List<File> result = new ArrayList<>();
    List<FileFilter> filters = Arrays
        .asList(new FileFilter[] { new GTSizeFilter(greaterThanBytes), new FileTypeFilter(fileType) });
    findFilesHelper(folder, filters, result);
    return result;
  }

  public static void main(String[] args) {

  }
}
