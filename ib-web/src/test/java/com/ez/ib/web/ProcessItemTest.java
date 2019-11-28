package com.ez.ib.web;

import com.ez.common.file.reader.FileProcess;
import com.ez.common.file.reader.FileProcessUtil;
import com.ez.common.file.reader.Rowdata;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * ClassName: ProcessItemTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-6-17 下午2:47 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class ProcessItemTest {

    @Test
    public void readerItem() throws Exception {

        String[] files = new String[]{"高中地理知识体系.xlsx",
                "高中化学知识体系.xlsx",
                "高中历史知识体系.xlsx",
                "高中生物知识体系.xlsx",
                "高中数学知识体系.xlsx",
                "高中物理知识体系.xlsx",
                "高中英语知识体系.xlsx",
                "高中语文知识体系.xlsx",
                "高中政治知识体系.xlsx"};
        int[] subjectIds = new int[]{1007, 1004, 1008, 1005, 1002, 1003, 1001, 1000, 1006};

        int level1Id = 10000;
        int level2Id = 20000;
        int level3Id = 30000;
        int idx = 0;
        for (String fileName : files) {
            level1Id++;
            level2Id++;
            level3Id++;

            FileProcess fileProcess = FileProcessUtil.getFileProcess("/home/liuyu/test/高中各科知识体系/" + fileName);
            List<MyData> myDatas = Lists.newArrayList();

            while (fileProcess.next()) {
                Rowdata rowdata = fileProcess.getRowdata();

                String data1 = rowdata.getData("一级知识点");
                String data2 = rowdata.getData("二级知识点");
                String data3 = rowdata.getData("三级知识点");

                if (StringUtils.isNotBlank(data1)) {
                    MyData myData = new MyData();
                    myData.id = level1Id++;
                    myData.level = 1;
                    myData.parentId = 0;
                    myData.data = data1;
                    myDatas.add(myData);
                }
                if (StringUtils.isNotBlank(data2)) {
                    MyData myData = new MyData();
                    myData.id = level2Id++;
                    myData.level = 2;
                    myData.parentId = level1Id - 1;
                    myData.data = data2;
                    myDatas.add(myData);
                }
                if (StringUtils.isNotBlank(data3)) {
                    MyData myData = new MyData();
                    myData.id = level3Id++;
                    myData.level = 3;
                    myData.parentId = level2Id - 1;
                    myData.data = data3.substring(3);
                    myDatas.add(myData);
                }


                System.out.println(data1 +
                        "==&&==" + data2 +
                        "==&&==" + data3);
            }

            int subjectId = subjectIds[idx++];
            myDatas.forEach(x -> {
                processMyData(x, fileName + ".txt", subjectId);
            });
        }
    }


    public void processMyData(MyData myData, String fileName, int subjectId) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ib_knowledge (id,parentId,deep,content,knowledgeSystemId,SUBJECT,learnSegment) VALUES(");
        sb.append(myData.id).append(",");
        sb.append(myData.parentId).append(",");
        sb.append(myData.level).append(",");
        sb.append("'").append(myData.data).append("',");
        sb.append("2," + subjectId + ",3);\n");
        try {
            FileUtils.write(new File("/home/liuyu/test/高中各科知识体系/" + fileName), sb.toString(), "UTF-8", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class MyData {
        public int id;
        public int level;
        public int parentId;
        public String data;
    }
}
