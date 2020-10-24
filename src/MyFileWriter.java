import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyFileWriter {

	public static void writeFile(String path,String content) throws IOException {
		Files.write(Paths.get(path),content.getBytes() );
	}

}
