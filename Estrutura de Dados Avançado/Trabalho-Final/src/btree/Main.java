package btree;

import java.io.RandomAccessFile;

import dtypes.DataType;
import dtypes.IntegerDT;
import dtypes.StringDT;

@SuppressWarnings("unused")
public class Main {
	public static void main(String[] args) throws Exception {
		String fileS = "dados.dat";
		StringDT keyDT = new StringDT(21);
		StringDT valDT = new StringDT(16);
		BTree<String, String> st = new BTree<String, String>(4,"dados.dat", keyDT, valDT);
		
		st.put("www.cs.princeton.edu", "128.112.136.12");
        st.put("www.cs.princeton.edu", "128.112.136.11");
        st.put("www.princeton.edu",    "128.112.128.15");
        st.put("www.yale.edu",         "130.132.143.21");
        st.put("www.simpsons.com",     "209.052.165.60");
        st.put("www.apple.com",        "17.112.152.32");
        st.put("www.amazon.com",       "207.171.182.16");
        st.put("www.ebay.com",         "66.135.192.87");
        st.put("www.cnn.com",          "64.236.16.20");
        st.put("www.google.com",       "216.239.41.99");
        st.put("www.nytimes.com",      "199.239.136.200");
        st.put("www.microsoft.com",    "207.126.99.140");
        st.put("www.dell.com",         "143.166.224.230");
        st.put("www.slashdot.org",     "66.35.250.151");
        st.put("www.espn.com",         "199.181.135.201");
        st.put("www.weather.com",      "63.111.66.11");
        st.put("www.yahoo.com",        "216.109.118.65");
        st.put("www.crateus.ufc.br",     "200.19.190.7");
        st.put("www.ufc.br",     		"200.17.41.185");
        
      for (String key : st.keys()) System.out.println(key);
      System.out.println(st.get("www.crateus.ufc.br"));
      
      
	}

}

//package btree;
//
//import java.io.File;
//import java.io.RandomAccessFile;
//
//import dtypes.DataType;
//import dtypes.IntegerDT;
//import dtypes.StringDT;
//
//@SuppressWarnings("unused")
//public class Main {
//	public static void main(String[] args) throws Exception {
//		String fileS = "dados.dat";
//		DataType<Integer> keyDT = new IntegerDT(); 
//		DataType<Integer>  valueDT = new IntegerDT();
//		File fl = new File(fileS);
//		BTree<Integer, Integer> bt = new BTree<Integer, Integer>(4, fileS, keyDT, valueDT);
//		
//		int k = 3;
//		
//		for (int i = 1; i <= k; i++) {
//			bt.put(i, i);
//		}
//		
//		System.out.println(bt.height());
//		
////		for(Integer k1 : bt.keys())
////			System.out.println(k1);
//		
////		for(int i = 1; i <= k; i++)
////			System.out.println(i + ": " + bt.get(i));
//		
//		RandomAccessFile raf = new RandomAccessFile("dados.dat", "rw");
//		raf.seek(4);
//		try {
//			System.out.println("start <-- Pointer " + raf.getFilePointer());
//			System.out.print("PAGE: IS LEAF: " + raf.readBoolean());
//			System.out.println(" <-- Pointer " + raf.getFilePointer());
//			System.out.print("PARENT OFFSET: " + raf.readLong()); 
//			System.out.println(" <-- Pointer " + raf.getFilePointer());
//			System.out.print("PAGE: NUM KEYS: " + raf.readInt());
//			System.out.println(" <-- Pointer " + raf.getFilePointer());
//			System.out.println();
//			System.out.print("CHILDREN 1: " + raf.readLong()); 
//			System.out.println(" <-- Pointer " + raf.getFilePointer());
//			System.out.print("PAGE: KEYS1: " + raf.readInt());
//			System.out.println(" <-- Pointer " + raf.getFilePointer());
//			System.out.print("PAGE: VALUE1: " + raf.readInt()); 
//			System.out.println(" <-- Pointer " + raf.getFilePointer());
//			System.out.println();
//			System.out.print("CHILDREN 2: " + raf.readLong()); 
//			System.out.println(" <-- Pointer " + raf.getFilePointer());
//			System.out.print("PAGE: KEYS2: " + raf.readInt());
//			System.out.println(" <-- Pointer " + raf.getFilePointer());
//			System.out.print("PAGE: VALUE2: " + raf.readInt()); 
//			System.out.println(" <-- Pointer " + raf.getFilePointer());
//			System.out.println();
//			System.out.print("CHILDREN 3: " + raf.readLong()); 
//			System.out.println(" <-- Pointer " + raf.getFilePointer());
//			System.out.print("PAGE: KEYS3: " + raf.readInt());
//			System.out.println(" <-- Pointer " + raf.getFilePointer());
//			System.out.print("PAGE: VALUE3: " + raf.readInt()); 
//			System.out.println(" <-- Pointer " + raf.getFilePointer());
//			System.out.println();
//			System.out.print("CHILDREN 4: " + raf.readLong()); 
//			System.out.println(" <-- Pointer " + raf.getFilePointer());
////			System.out.print("PAGE: KEYS4: " + raf.readInt());
////			System.out.println(" <-- Pointer " + raf.getFilePointer());
////			System.out.print("PAGE: VALUE4: " + raf.readInt()); 
////			System.out.println(" <-- Pointer " + raf.getFilePointer());
//		} catch (Exception e) {
//			System.out.println("Erro");
//		}
//		
//		raf.close();
////		System.out.println(bt.root.keys);
////		System.out.println(bt.root.children.get(0).keys);
//		
//	}
//}
//
//
