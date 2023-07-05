//import org.example.Main;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.*;
//
//public class Testing {
//
//
//    @Test
//    public void map() {
//        ArrayList<String> documents = new ArrayList<>(){
//            {
//                add("salam bar to");
//                add("chetori salam");
//            }
//        };
//        HashMap<String, ArrayList<String>> allWords = Main.createMap(documents);
//        HashMap<String, ArrayList<String>> map = new HashMap<>();
//        map.put("salam", new ArrayList<>(){{
//            add("document 0");
//            add("document 1");
//        }});
//        map.put("bar", new ArrayList<>(){{
//            add("document 0");
//        }});
//        map.put("to", new ArrayList<>(){{
//            add("document 0");
//        }});
//        map.put("chetori", new ArrayList<>(){{
//            add("document 1");
//        }});
//        Assertions.assertEquals(allWords, map);
//    }
//
//    @Test
//    public void testNeg1() {
//        HashMap<String, ArrayList<String>> map = new HashMap<>();
//        map.put("salam", new ArrayList<>(){{
//            add("document 0");
//            add("document 1");
//        }});
//        map.put("bar", new ArrayList<>(){{
//            add("document 0");
//        }});
//        map.put("to", new ArrayList<>(){{
//            add("document 0");
//        }});
//        map.put("chetori", new ArrayList<>(){{
//            add("document 1");
//        }});
//        ArrayList<String> neg = new ArrayList<>() {{
//            add("salam");
//        }};
//        Set<String> docs = new HashSet<>() {{
//            add("document 0");
//            add("document 1");
//        }};
//        Main.del_neg(neg, map, docs);
//        Assertions.assertEquals(docs, new HashSet<>(){{
//        }});
//    }
//}
