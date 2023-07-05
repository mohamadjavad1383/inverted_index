import org.example.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TestController {

    @Test
    public void negativeFoundInDocs() {
        HashMap<String, List<String>> map = new HashMap<>();
        map.put("salam", new ArrayList<>() {{
            add("document 0");
            add("document 1");
        }});
        map.put("bar", new ArrayList<>() {{
            add("document 0");
        }});
        map.put("to", new ArrayList<>() {{
            add("document 0");
        }});
        map.put("chetori", new ArrayList<>() {{
            add("document 1");
        }});
        Set<String> docs = new HashSet<>() {{
            add("document 0");
            add("document 1");
        }};
        ArrayList<String> neg = new ArrayList<>() {{
            add("salam");
        }};
        docs = Controller.getInstance().deleteNeg(neg, map, docs);
        Assertions.assertEquals(docs, new HashSet<>());
    }

    @Test
    public void twoDifferentDocumentsOR() {
        HashMap<String, List<String>> map = new HashMap<>();
        map.put("salam", new ArrayList<>() {{
            add("document 0");
            add("document 1");
        }});
        map.put("bar", new ArrayList<>() {{
            add("document 0");
        }});
        map.put("to", new ArrayList<>() {{
            add("document 0");
        }});
        map.put("chetori", new ArrayList<>() {{
            add("document 1");
        }});
        Set<String> docs = new HashSet<>();
        ArrayList<String> or = new ArrayList<>() {{
            add("bar");
            add("chetori");
        }};
        docs = Controller.getInstance().addOr(or, map, docs);
        Assertions.assertEquals(docs, new HashSet<>(Arrays.asList("document 0", "document 1")));
    }

    @Test
    public void addOneNormalWord() {
        HashMap<String, List<String>> map = new HashMap<>();
        map.put("salam", new ArrayList<>() {{
            add("document 0");
            add("document 1");
        }});
        map.put("bar", new ArrayList<>() {{
            add("document 0");
        }});
        map.put("to", new ArrayList<>() {{
            add("document 0");
        }});
        map.put("chetori", new ArrayList<>() {{
            add("document 1");
        }});
        ArrayList<String> norm = new ArrayList<>() {{
            add("bar");
        }};
        Set<String> docs = Controller.getInstance().addNormal(norm, map);
        Assertions.assertEquals(docs, new HashSet<>(List.of("document 0")));
    }
}
