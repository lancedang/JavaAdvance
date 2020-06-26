// Copyright (C) 2020 Meituan
// All rights reserved
package util;

import java.io.File;
import java.io.FileWriter;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 读取json文件获取，增加、删除特定field后重新生成修改后的json文件
 * @author qiankai07
 * @version 1.0
 * Created on 1/15/20 11:38 AM
 **/
public class JsonTransfomfer {

    @Test
    public void json2Excel() throws Exception{

        String fileName = "union_loan_standard_data.json";
        String resultName = "result_" + "union_loan_standard_data.json";

        String jsonString = TestHelper.readSourceFile(fileName);
        JSONObject jsonObject = JSON.parseObject(jsonString);

        String arrayKey = "union_loan_internal_data";

        JSONArray array = jsonObject.getJSONArray(arrayKey);
        System.out.println(array.get(0));

        int size = array.size();
        for (int i = 0; i < size; i++) {
            JSONObject object = array.getJSONObject(i);
            object.remove("id");
            object.remove("dataSourceId");
            object.remove("calculationService");
            object.remove("sourceVariableName");
            object.remove("createUser");
            object.remove("expression");
            object.remove("addTime");
            object.remove("updateUser");
            object.remove("updateTime");
        }
        System.out.println("size=" + array.size());
        String requiredJson = JSON.toJSONString(array);
        File requiredJsonFile = new File(resultName);
        FileWriter fileWriter = new FileWriter(requiredJsonFile);
        fileWriter.write(requiredJson);
        fileWriter.close();

    }



}
