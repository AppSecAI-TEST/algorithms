package stacks;


import javafx.util.Pair;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by taalyko2 on 06/02/17.
 */
public class SimpleTextEditor {


    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        int to = Integer.valueOf(sc.nextLine());
        Pattern p = Pattern.compile("^([0-4]) *(.*)$");
        StringBuilder s = new StringBuilder();
        Stack<Pair<Integer, String>> operationStack = new Stack<>();
        for (int i = 0; i < to; i++) {
            Matcher m = p.matcher(sc.nextLine());

            if (m.matches()) {
                int operation = Integer.valueOf(m.group(1));
                switch (operation) {

                    case 1:
                        String str = m.group(2);
                        operationStack.add(new Pair<>(operation, str));
                        s.append(str);
                        break;
                    case 2:
                        int k = Integer.valueOf(m.group(2));
                        operationStack.add(new Pair<>(operation, s.substring(s.length() - k, s.length())));
                        s.delete(s.length() - k, s.length());
                        break;
                    case 3:
                        k = Integer.valueOf(m.group(2));
                        System.out.println(s.charAt(k - 1));
                        break;
                    case 4:
                        Pair par = operationStack.pop();
                        switch (Integer.valueOf(par.getKey().toString())) {
                            case 1:
                                String added = par.getValue().toString();
                                s.delete(s.length() - added.length(), s.length());
                                break;
                            case 2:
                                String removed = par.getValue().toString();
                                s.append(removed);
                                break;
                        }
                }

            }


        }

    }
}
