public class HuffmanNode {
     public double prob;
     public String name = "";
     public String code = "";
     public HuffmanNode left;
     public HuffmanNode right;
     public static void inOrder(HuffmanNode node) {
          if (node == null) { return; }
          inOrder(node.left);
          System.out.println(node.name + " " + node.code);
          inOrder(node.right);
     }
     public static void setCodes(HuffmanNode node){
          if(node == null)
          {return;}

          if(node.name.length() ==1)
          {
               Compress.table.put(node.name.charAt(0) ,node.code);
          }

          if(node.right != null)
          {
               node.right.code = node.code+'1';
          }

          if(node.left != null)
          {
               node.left.code = node.code + '0';
          }

          setCodes(node.left);
          setCodes(node.right);
     }

}
