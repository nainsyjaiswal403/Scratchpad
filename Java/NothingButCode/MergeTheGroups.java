package NothingButCode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class MergeTheGroups {
    static class Element {
        public int index;
        public int value;
        
        public Element(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == null) {
                return false;
            } else if(obj instanceof Element) {
                Element o = (Element) obj;
                if(o.value == value &&
                        o.index == index) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int hashCode() {
            int hash = 1009;
            hash = 31 * hash + this.index;
            hash = 31 * hash + this.value;
            return hash;
        }
        
        
    }
    
    static class Reader 
    { 
        final private int BUFFER_SIZE = 1 << 16; 
        private DataInputStream din; 
        private byte[] buffer; 
        private int bufferPointer, bytesRead; 
  
        public Reader() 
        { 
            din = new DataInputStream(System.in); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        }
  
        public String readLine() throws IOException 
        { 
            byte[] buf = new byte[64]; // line length 
            int cnt = 0, c; 
            while ((c = read()) != -1) 
            { 
                if (c == '\n') 
                    break; 
                buf[cnt++] = (byte) c; 
            } 
            return new String(buf, 0, cnt); 
        } 
  
        public int nextInt() throws IOException 
        { 
            int ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do
            { 
                ret = ret * 10 + c - '0'; 
            }  while ((c = read()) >= '0' && c <= '9'); 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public long nextLong() throws IOException 
        { 
            long ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public double nextDouble() throws IOException 
         
  
        private void fillBuffer() throws IOException 
        { 
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
            if (bytesRead == -1) 
                buffer[0] = -1; 
        } 
  
        private byte read() throws IOException 
        { 
            if (bufferPointer == bytesRead) 
                fillBuffer(); 
            return buffer[bufferPointer++]; 
        } 
  
        public void close() throws IOException 
        { 
            if (din == null) 
                return; 
            din.close(); 
        } 
    } 

    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        
        int n = r.nextInt();
        
        int[] a = new int[n];
        ArrayList<TreeSet<Element>> al = new ArrayList<>();
        for(int i = 0 ; i < n ; i++) {
            TreeSet<Element> t = new TreeSet<>((e1, e2) -> e1.value - e2.value);
            int temp = r.nextInt();
            t.add(new Element(temp, i));
            al.add(t);
            a[i] = temp;
        }
        
        int q = r.nextInt();
        
        while(q-- > 0) {
            int type = r.nextInt();
            
            if(type == 1) {
                int x = r.nextInt();
                int y = r.nextInt();
                Element X = new Element(a[x - 1], x);
                Element Y = new Element(a[y - 1], y);
                if(!al.get(x - 1).contains(Y)) {
                    al.get(x - 1).addAll(al.get(y - 1));
                    al.set(y - 1, al.get(x - 1));
                }
            } else {
                int x = r.nextInt();
                System.out.println(al.get(x - 1).last().value - al.get(x - 1).first().value);
            }
        }
    }
}
