package com.simple.simpleweb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SimpleController {

    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    static DynamoDB dynamoDB = new DynamoDB(client);
    static String tableName = "simpleTable";

    @RequestMapping("/hello")
    public String hello(@RequestParam(value="name", required=false, defaultValue="Cloud 9 !!") String name, Model model) {
        model.addAttribute("name", name);

        Table table = dynamoDB.getTable(tableName);
        Item item = new Item().withPrimaryKey("memNo", "1000")
                    .withInt("Name", "skywing");
        table.putItem(item);

        return "hello";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        Table table = dynamoDB.getTable(tableName);

        QuerySpec spec = new QuerySpec()
                .withConsistentRead(true);

        ItemCollection<QueryOutcome> items = table.query(spec);
                System.out.println(items.toString());

        return "list";
    }
}
