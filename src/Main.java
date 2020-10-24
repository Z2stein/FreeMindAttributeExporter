
public class Main {
	public static void main(String[] args) throws Exception {
		String inp;
		if (args.length == 0)
			inp = MyFileReader.getStringFromFile("C:/Users/Chris/Downloads/My Game.mm");
		else
			inp = MyFileReader.getStringFromFile(args[0]);
		String result = new Input().setLineSplitter("\\n").setInputStr(inp).splitString().packStrings().createAttr()
				.getResultString();
		MyFileWriter.writeFile("output.txt", result);
		System.out.println(result);
	}
}
