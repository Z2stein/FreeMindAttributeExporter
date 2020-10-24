
public class Main {
	public static void main(String[] args) throws Exception {
		String inp;
		if (args.length == 0)
			inp = MyFileReader.getStringFromFile("C:/Users/Chris/Downloads/My Game.mm");
		else
			inp = MyFileReader.getStringFromFile(args[0]);
		Input input = new Input().setLineSplitter("\\n").setInputStr(inp).splitString().packStrings().createAttr();
				 String result	=input.getResultString();
		MyFileWriter.writeFile("attributesSorted.txt", result);
		MyFileWriter.writeFile("nodeAndAttrs.txt", input.getNodeAttrList());
		MyFileWriter.writeFile("AttrAndVals.txt", input.getValAttrList());
		System.out.println(
				input.getNodeAttrList()
				);
	}
}
