import org.example.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Testing {


    @Test
    public void map() {
        ArrayList<String> documents = new ArrayList<>(){
            {
                add("salam bar to");
                add("chetori salam");
            }
        };
        HashMap<String, ArrayList<String>> allWords = Main.createMap(documents);
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        map.put("salam", new ArrayList<>(){{
            add("document 0");
            add("document 1");
        }});
        map.put("bar", new ArrayList<>(){{
            add("document 0");
        }});
        map.put("to", new ArrayList<>(){{
            add("document 0");
        }});
        map.put("chetori", new ArrayList<>(){{
            add("document 1");
        }});
        Assertions.assertEquals(allWords, map);
    }

    @Test
    public void testNeg1() {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        map.put("salam", new ArrayList<>(){{
            add("document 0");
            add("document 1");
        }});
        map.put("bar", new ArrayList<>(){{
            add("document 0");
        }});
        map.put("to", new ArrayList<>(){{
            add("document 0");
        }});
        map.put("chetori", new ArrayList<>(){{
            add("document 1");
        }});
        Set<String> docs = new HashSet<>() {{
            add("document 0");
            add("document 1");
        }};
        ArrayList<String> neg = new ArrayList<>() {{
            add("salam");
        }};
        Main.del_neg(neg, map, docs);
        Assertions.assertEquals(docs, new HashSet<>());
    }

    @Test
    public void testOr() {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        map.put("salam", new ArrayList<>(){{
            add("document 0");
            add("document 1");
        }});
        map.put("bar", new ArrayList<>(){{
            add("document 0");
        }});
        map.put("to", new ArrayList<>(){{
            add("document 0");
        }});
        map.put("chetori", new ArrayList<>(){{
            add("document 1");
        }});
        Set<String> docs = new HashSet<>();
        ArrayList<String> or = new ArrayList<>() {{
            add("bar");
            add("chetori");
        }};
        Main.add_or(or, map, docs);
        Assertions.assertEquals(docs, new HashSet<>(Arrays.asList("document 0", "document 1")));
    }

    @Test
    public void testNormal() {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        map.put("salam", new ArrayList<>(){{
            add("document 0");
            add("document 1");
        }});
        map.put("bar", new ArrayList<>(){{
            add("document 0");
        }});
        map.put("to", new ArrayList<>(){{
            add("document 0");
        }});
        map.put("chetori", new ArrayList<>(){{
            add("document 1");
        }});
        ArrayList<String> norm = new ArrayList<>() {{
            add("bar");
        }};
        Set<String> docs = Main.add_normal(norm, map);
        Assertions.assertEquals(docs, new HashSet<>(List.of("document 0")));
    }
}
