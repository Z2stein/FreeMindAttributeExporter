import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Input {
	String inputStr;

	String[] splittedStr = null;

	private List<List<String>> packs;
	private HashMap<String, ArrayList<String>> attributes;
	private HashMap<String, ArrayList<String>> attrValues;

	private String lineSplitter;
	public Input setInputStr(String inputStr) {
		this.inputStr = inputStr;
		return this;
	}

	public Input splitString() throws Exception {
		if (inputStr == null)
			throw new Exception("Input String is null");
		splittedStr = inputStr.split(lineSplitter);
		return this;
	}

	public Input createAttr() {
		
		attributes = new HashMap<>();
		attrValues = new HashMap<>();
		for (List<String> nodePack : packs) {
			String currentNodeName = getLineValue(nodePack.get(0),"TEXT=\"");
			for (String lineInPack : nodePack) {
				if(!lineInPack.contains("<attribute")) continue;
				String currentAttribute = getLineValue(lineInPack, "NAME=\"");
				String currentAttrValue = getLineValue(lineInPack, "VALUE=\"");
				
				if (!attributes.containsKey(currentAttribute)) attributes.put(currentAttribute, new ArrayList<>());
				if (!attributes.get(currentAttribute).contains(currentNodeName)) attributes.get(currentAttribute).add(currentNodeName);
				
				if (!attrValues.containsKey(currentAttribute)) attrValues.put(currentAttribute, new ArrayList<>());
				if (!attrValues.get(currentAttribute).contains(currentAttrValue)) attrValues.get(currentAttribute).add(currentAttrValue);
			}
		}
		return this;
	}
	 
	private String getLineValue(String stringLine,String startKey) {
		int startIndex = stringLine.indexOf(startKey);
		int endIndex = stringLine.indexOf("\"", startIndex+startKey.length());
		return stringLine.substring(startIndex+startKey.length(), endIndex);
	}

	public Input packStrings() throws Exception {

		String startArgument = "<node";
		String endArgument = "/node>";

		packs = new Stack<>();

		for (int i = 0; i < splittedStr.length - 1; i++) {
			// check Validity
			if (splittedStr[i].indexOf("<") != splittedStr[i].lastIndexOf("<"))
				throw new Exception("There are at least two times '<' in the String Line");

			// check if next contains startArgument too
			if (splittedStr[i + 1].contains(startArgument))
				continue;

			if (splittedStr[i].contains(startArgument)) {
				List<String> subPack = new Stack<>();
				packs.add(subPack);
				boolean endOfNode = true;
				boolean containsAttribute = false;
				int j = 0;
				while (endOfNode) {
					String currentLineString = splittedStr[i + j];
					subPack.add(currentLineString);
					j++;
					endOfNode = !splittedStr[i + j - 1].contains(endArgument);
					if (currentLineString.contains("<attribute"))
						containsAttribute = true;
				}

				if (!containsAttribute)
					packs.remove(subPack);

			}
		}
		return this;
	}


	public Input setLineSplitter(String lineSplitter) {
		this.lineSplitter=lineSplitter;
		return this;
	}

	public String getResultString() {
		String separator = System.lineSeparator();
		String result = "";
		Set<String> tempAttributes = attributes.keySet();
		for (String attr : tempAttributes) {
			result = result + attr +separator ;
			for (String node : attributes.get(attr)) {
				result = result+"   "+node+separator;
			}
		}
		
		return result;
	}
	public String getValAttrList() {
		String result = "";
		for(String attr : attrValues.keySet()) {
			for(String val:attrValues.get(attr)) {
				result =  result+attr+","+ val+System.lineSeparator();
			}
		}
	
		return result;
	}
	public String getNodeAttrList() {
		String result = "";
		for(String attr : attributes.keySet()) {
			for(String node:attributes.get(attr)) {
				result =  result+node+","+ attr+System.lineSeparator();
			}
		}
	
		return result;
	}
	
}
