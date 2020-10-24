import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyFileReader {

	public static String getStringFromFile(String path) throws IOException {
		
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, StandardCharsets.US_ASCII);
		
		
	}

	
	
}
