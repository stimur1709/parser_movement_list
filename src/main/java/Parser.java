import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static List<Movements> parsingMovements(String MOVEMENT_LIST) {
        ArrayList<Movements> list = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(MOVEMENT_LIST));
            lines.remove(0);
            for (String line : lines) {
                String[] splitLine = line.split(",");
                for (int i = 0; i < splitLine.length; i++) {
                    if (isColumnPart(splitLine[i]) && i == 7) {
                        splitLine[i - 1] = (splitLine[i - 1] + "." + splitLine[i]).replace("\"", "");
                        splitLine[i] = splitLine[i + 1].replace("\"", "");
                    }
                    if (isColumnPart(splitLine[i]) && i == 8) {
                        splitLine[i - 1] = (splitLine[i - 1] + "." + splitLine[i]).replace("\"", "");
                    }
                }
                Movements movements = new Movements(editingDescription(splitLine[5]), Double.parseDouble(splitLine[6]),
                        Double.parseDouble(splitLine[7]));
                list.add(movements);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    private static boolean isColumnPart(String text) {
        String trimText = text.trim();
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }

    private static String editingDescription(String splitLine) {
        String[] name = splitLine.split(" {4}");
        String result;
        Pattern pattern = Pattern.compile("/");
        Matcher matcher = pattern.matcher(splitLine);
        if (matcher.find()) {
            result = name[1].substring(name[1].lastIndexOf("/")).replace("/", "");
        } else {
            result = name[1].substring(name[1].lastIndexOf("\\")).replace("\\", "");
        }
        return result.trim();
    }
}
