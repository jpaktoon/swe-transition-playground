package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

//https://leetcode.com/problems/simplify-path/?envType=study-plan-v2&envId=top-interview-150
public class SimplifyPath {
    public static String simplifyPath(String path) {
//        String test = "/.././test//dd/";
//        String[] tests = test.split("/");
        String[] dirs = path.split("/");
        // '.' is current dir
        // '..' is up dir
        // '//' is single slash
        // Start from root - /
        Deque<String> cd = new ArrayDeque<>();
        // Only valid string '.', '..', [a-zA-Z0-9], '_'
        // Test input : /home/user/data/../documents/myfile.txt
        // Output : /home/user/documents/myfile.txt
        for (String dir: dirs) {
            if (!dir.isEmpty()) {
                switch (dir) {
                    case ".":
                        break;
                    case "..":
                        if (!cd.isEmpty()) cd.pop();
                        break;
                    default:
                        cd.push(dir);
                        break;
                }
            }
        }
        StringBuilder cPath = new StringBuilder();
        while (!cd.isEmpty()){
            cPath.insert(0, "/" + cd.pop());
        }

        if (cPath.isEmpty()) { // edge case
            cPath.append("/");
        }

        return cPath.toString();
    }

    public static void main(String[] args) {
        simplifyPath("/home/user/data/../documents/myfile.txt");
    }
}
