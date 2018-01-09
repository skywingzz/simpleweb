package com.simple.simpleweb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class SimpleController {

    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    static DynamoDB dynamoDB = new DynamoDB(client);
    static String tableName = "simpleTable";

    @RequestMapping("/insert")
    public String insert(@RequestParam(value="name", required=false, defaultValue="Cloud 9 !!") String name, Model model) {
        
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        int currentDate = Integer.parseInt(sdf.format(d));

        Table table = dynamoDB.getTable(tableName);
        Item item = new Item().withPrimaryKey("name", name)
                            .withInt("createDate", currentDate);

        table.putItem(item);

        model.addAttribute("name", name);
        return "hello";
    }

}
