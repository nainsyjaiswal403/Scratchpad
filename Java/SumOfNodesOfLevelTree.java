
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SumOfNodesOfLevelTree {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            int n = Integer.parseInt(br.readLine());
        }catch(IOException e){
//            String
        }
        
    }
}
class Node{
    int data;
    Node left,right;
    Node(int d){
        data = d;
        left = null;
        right = null;
    }
}
