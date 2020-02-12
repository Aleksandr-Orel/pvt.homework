package Task;

public class StringOperation {
    public String replaceAllSymbols(String string, char oldSymbol, char newSymbol) {
        while (string.indexOf(oldSymbol) != -1) {
            string = string.replace(oldSymbol, newSymbol);
        } return string;
    }

    public boolean hasSomeString(String string, String subString) {
        return string.contains(subString);
    }

    public char getSomeSymbolFromString(String string, int positionOfSymbol) {
        return string.charAt(positionOfSymbol);
    }

    public String getSubstring(String string, int srcBegin, int srcEnd) {
       return string.substring(srcBegin, srcEnd);
    }

    public String getStringWithoutUpperCase(String string) {
        return string.toLowerCase();
    }
}
