import java.util.*;
import java.io.*;

public class Compress {
    static String vec = "";
    static HashMap <Character, Double> mp = new HashMap<Character,Double>();

    static HashMap<Character , String> table = new HashMap<Character,String>();
    static boolean find(String vec,char a)
    {
        for (int i=0;i<vec.length();i++)
        {
            if (a==vec.charAt(i))
                return true;
        }
        return false;
    }
    public static void huffSort(Vector<HuffmanNode> huffVec){
        for(int i =0 ; i<huffVec.size()-1 ; i++)
        {
            int minIndex = i;
            for (int j = i+1 ; j< huffVec.size() ; j++)
            {
                if( (huffVec.get(j)).prob <= (huffVec.get(minIndex)).prob )
                {
                    minIndex = j;
                }
            }

            HuffmanNode temp = huffVec.get(minIndex);
            huffVec.set(minIndex , huffVec.get(i));
            huffVec.set(i , temp);

        }
    }
    public static void main(String[] args) throws IOException {
        String s = "";

        try {
            File myObj = new File("D:\\DebugXXCODERProjects\\Standard Huffman\\Original File.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                s = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for (int i=0;i<s.length();i++)
        {
            try {
                if (!(find(vec,s.charAt(i))))
                {
                    vec+=s.charAt(i);
                }
            } catch (Exception e) {

                e.printStackTrace();
            }

        }
        for (int i=0;i<vec.length();i++)
        {
            double cnt=0.0;
            for (int j=0;j<s.length();j++)
            {
                if (vec.charAt(i)==s.charAt(j))
                    cnt++;
            }
            mp.put(vec.charAt(i),cnt/s.length());
        }

        Vector<HuffmanNode> huffVec = new Vector<HuffmanNode>();

        for (Map.Entry<Character,Double> entry : mp.entrySet())
        {
            HuffmanNode node = new HuffmanNode();
            node.prob = entry.getValue();
            node.name += entry.getKey();
            huffVec.add(node);
        }

        huffSort(huffVec);


        HuffmanNode root = null;



        while(huffVec.size() != 1)
        {
            HuffmanNode parent = new HuffmanNode();
            parent.right = huffVec.get(0);
            parent.left = huffVec.get(1);
            parent.name = parent.left.name +"+"+ parent.right.name;
            parent.prob = parent.left.prob + parent.right.prob;

            huffVec.remove(0);
            huffVec.remove(0);

            huffVec.add(parent);
            root = parent ;

            huffSort(huffVec);
        }

        HuffmanNode.setCodes(root);

        String output = "";

        for (int i =0 ; i<s.length() ; i++)
        {
            output+= table.get(s.charAt(i));
        }

        try {
            File myObj = new File("D:\\DebugXXCODERProjects\\Standard Huffman\\Compressed.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String outputTable = "";

        Set<Map.Entry<Character, String>> entries = table.entrySet();

        for(Map.Entry<Character, String> entry : entries ){
            outputTable += (String.valueOf(entry.getKey()) + '=' + String.valueOf(entry.getValue())) +',';
        }

        try {
            FileWriter myWriter = new FileWriter("D:\\DebugXXCODERProjects\\Standard Huffman\\Compressed.txt");

            myWriter.write(outputTable+' ');

            myWriter.write(output);

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



}