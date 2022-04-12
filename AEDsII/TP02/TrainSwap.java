import java.io.*;
import java.util.*;
class TrainSwap{
    static int[] array;
    public static void gerar(int tam, String numeros){
        array = new int [tam];
        for(int i=0;i<tam;i++){
            String n ="";
            n+=numeros.charAt(i);
            array[i]=Integer.parseInt(n);
        }
    }
    public static void swap(int i, int j){
        int temp = array[i];
        array[i]=array[j];
        array[j]=temp;
    }
    public static int train(){
        int swaps=0;
        for(int i=1;i<array.length;i++){
            while(array[i]<array[i-1]){
                swap(i,i-1);
                swaps++;
            }
        }
        return swaps;
    }
    public static void main(String[] args){
        int n = MyIO.readInt();
        String num = MyIO.readLine();
        gerar(n,num);
        System.out.println(train());
    }
}