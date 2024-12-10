package org.example;

import java.util.ArrayList;
import java.util.List;

// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {
    public static void main(String[] args) {
        // 当文本光标位于高亮显示的文本处时按 Cmd+.，
        // 可查看 IntelliJ IDEA 对于如何修正该问题的建议。

        // 遍历一个list
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        for (String s : list) {
            System.out.println(s);
        }
        list.forEach(item -> {
            System.out.println(item);
        });

        list.forEach(item -> System.out.println(item));
        list.forEach(System.out::println);


    }
}