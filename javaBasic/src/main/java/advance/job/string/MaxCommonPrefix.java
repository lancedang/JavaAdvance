package advance.job.string;

public class MaxCommonPrefix {
    public static void main(String[] args) {
        String[] strings = {"hel","hello", "hel"};
        int index = findMaxCommPreIndex(strings);
        if (index == 0) {
            System.out.println("");
        } else {
            System.out.println(strings[0].substring(0, index));
        }

        String maxPreStr = findMaxPreStr(strings);
        System.out.println(maxPreStr);

    }

    public static int findMaxCommPreIndex(String[] strs) {
        int len = strs.length;

        if (len == 0) {
            System.out.println("empty");
            return 0;
        }

        int minLen = strs[0].length();

        for (String s : strs) {
            minLen = Math.min(minLen, s.length());
        }
        int i;
        for (i = 0; i < minLen; i++) {
            //依次取第一个字符串数组的字符
            char a = strs[0].charAt(i);

            boolean flag = false;

            //遍历剩余字符串
            int j = 1;
            while (j < len) {

                if (a == strs[j].charAt(i)) {
                    j++;
                }else {
                    break;
                }

            }

            if (j == len) {
                continue;
            }else {
                break;
            }
        }
        System.out.println(i);
        return i;
    }

    public static String findMaxPreStr(String[] strs) {
        int len = strs.length;

        if (len == 0) {
            return "";
        }

        int minLen = strs[0].length();

        for (String s : strs) {
            minLen = Math.min(minLen, s.length());
        }

        StringBuffer result = new StringBuffer("");

        for (int i = 0;i < minLen; i++) {
            char c = strs[0].charAt(i);

            int j = 1;
            while (j < len) {
                if (c == strs[j].charAt(i)) {
                    j++;
                } else {
                    break;
                }
            }

            if (j == len) {
                result.append(c);
            }else {
                break;
            }

        }

        return result.toString();

    }

}
