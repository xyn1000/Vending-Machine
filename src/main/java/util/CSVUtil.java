package util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVUtil {
    public static File createCSV(List<String> head,List<List<String>> data, String path, String filename){
        File csv = null;
        BufferedWriter csvWriter = null;
        try{
            // creates new csv file in given path
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = df.format(new Date());
            csv =new File(path + filename + date + ".csv");
            file.createNewFile();

            // creates writer for recognising the separator clearly
            csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csv), "UTF-8"), 1024);

            // writes head
            writeLine(head,csvWriter);

            // writes data
            for (List<String> line : data){
                writeLine(line,csvWriter);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csv;
    }


    private static void writeLine(List<String> line, BufferedWriter csvWriter) throws IOException {
        for (String section : line) {
            StringBuffer sb = new StringBuffer();
            String lineJoined = sb.append("\"").append(section).append("\",").toString();
            csvWriter.write(lineJoined);
        }
        csvWriter.newLine();
    }

//    public static void main(String[] args){
//        List<String> test = new ArrayList<>();
//        test.add("1");
//        test.add("2");
//        test.add("3");
//        List<String> a = new ArrayList<>();
//        List<List<String>> b = new ArrayList<>();
//        a.add("first");
//        a.add("second");
//        a.add("third");
//        b.add(a);
//        b.add(a);
//
//        File directory = new File("");
//        System.out.println(directory.getAbsolutePath());
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date = df.format(new Date());
//        createCSV(test,b,directory.getAbsolutePath().concat("/build/reports/"),"CurrentItem ".concat(date));
//    }

}
