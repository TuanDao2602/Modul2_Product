package ra.bussiness.file;

import ra.bussiness.config.ShopConstant;

import java.io.*;
import java.util.List;

public class FileAll <T> {
    public List<T> readFromFile (String path){
        List<T> list = null;
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            file = new File(path);
            if (file.exists()){
                fis  = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                list = (List<T>) ois.readObject();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        return list;
    }
    public  boolean writeFromFile (List<T> list, String path){
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean returnData = true;
        try {
            file = new File(path);
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
        }catch (Exception e){
            returnData = false;
            e.printStackTrace();
        }finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        return returnData;
    }
}
