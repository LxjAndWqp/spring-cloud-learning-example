package com.lidong.flink;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.BatchTableEnvironment;

public class WordCountSql {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        BatchTableEnvironment tEnv = BatchTableEnvironment.create(env);
        DataSet<WC> input = env.fromElements(
                new WC("Hello", 1),
                new WC("zhisheng", 1),
                new WC("Hello", 1));

//        tEnv.registerDataSet("WordCount", input, "word, count");
//
//        Table table = tEnv.sqlQuery(
//                "SELECT word, SUM(count) as count FROM WordCount GROUP BY word");
//        DataSet<WC> result = tEnv.toDataSet(table, WC.class);
//        result.print();

        Table table = tEnv.fromDataSet(input);

        Table filtered = table
                .groupBy("word")
                .select("word, SUM(frequency) as count")
                .filter("count = 2");

        DataSet<WC> result = tEnv.toDataSet(filtered, WC.class);

        result.print();
    }

    public static class WC {
        public String word;//hello
        public long frequency;//1

        // public constructor to make it a Flink POJO
        public WC() {
        }

        public WC(String word, long frequency) {
            this.word = word;
            this.frequency = frequency;
        }

        @Override
        public String toString() {
            return "WC " + word + " " + frequency;
        }
    }
}
