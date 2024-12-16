//import java.io.*;
//import java.util.HashMap;
//
//public class Anas {
//    public static void main(String[] args) {
//        System.out.println(register("islam" , "456"));
//    }
//    public static String register(String userName,String password){
//        HashMap<String,String> map1 = (HashMap<String, String>) readHashMapFromFile("Anas.txt");
//        if (!map1.containsKey(userName)){
//            map1.put(userName,password);
//            writeHashMapToFile(map1,"Anas.txt");
//            File folder = new File("Users\\"+userName);
//            if (folder.mkdir()) {
//                return folder.getAbsolutePath();
//            }
//        }
//        System.out.println("Username already exists");
//        return null;
//    }
//
//    public static void writeHashMapToFile(HashMap<String, String> map, String filePath) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
//            for (HashMap.Entry<String, String> entry : map.entrySet()) {
//                writer.write(entry.getKey() + "-->" + entry.getValue());
//                writer.newLine();
//            }
//            System.out.println("HashMap has been written to " + filePath);
//        } catch (IOException e) {
//            System.err.println("Error writing HashMap to file: " + e.getMessage());
//        }
//    }
//    public static HashMap<String, String> readHashMapFromFile(String filePath) {
//        HashMap<String, String> map = new HashMap<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split("-->", 2);
//                if (parts.length == 2) {
//                    map.put(parts[0], parts[1]);
//                }
//            }
//
//        } catch (IOException e) {
//            System.err.println("Error reading HashMap from file: " + e.getMessage());
//        }
//        return map;
//    }
//
//
//    public static boolean logIn(String userName,String password) throws Exception {
//        HashMap<String,String> map = readHashMapFromFile("Anas.txt");
//        if (map.containsKey(userName)){
//            if (map.get(userName).equals(password)){
//                return true;
//            }
//            else {
//                throw new Exception("Wrong password");
//            }
//        }
//        throw new Exception("Username don't exist");
//    }
//
//
//}

