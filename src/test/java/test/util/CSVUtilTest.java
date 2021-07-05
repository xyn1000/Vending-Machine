package test.util;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import util.CSVUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import static util.MD5Util.salt;

public class CSVUtilTest {
    /**
     *
     * Method: createCSV(List<String> head,List<List<String>> data, String path, String filename)
     * Method: writeLine(List<String> line, BufferedWriter csvWriter)
     *
     */
    @Test
    public void testCreateCSV() throws Exception {
        CSVUtil csvUtil = new CSVUtil();
        List<String> head=new ArrayList<String>();
        head.add("1");
        head.add("2");

        List<List<String>> data=new ArrayList<List<String>>();
        data.add(new ArrayList<String>());

        String filename="test";
        String path=System.getProperty("user.dir");
        File test= csvUtil.createCSV(head,data,path,filename);
        Assert.assertNotNull(test);

    }

}
