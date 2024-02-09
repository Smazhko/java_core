package three_bytes_array;


import three_bytes_array.exceptions.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/*
    Для хранения чисел от 0 до 3 требуется 2 БИТА информации
    00 - 0,
    01 - 1,
    10 - 2,
    11 - 3
    Для того, чтобы файл, в котором мы будем хранить данные занимал ТРИ БАЙТА,
    нам необходимо на каждую тройку чисел выбелить по 1 БАЙТУ.
    Формат INTEGER для этого НЕ ПОДХОДИТ, так как занимает 4 БАЙТА. ПОЭТОМУ ...
    Создаём массив из ТРЁХ элеметов, каждый - по 1 БАЙТУ, т.е. 4 пары по 2 бита.
    В первые три пары записываем наши числа 0, 1, 2 и 3. 4я пара - всегда нули.
    Конвертацию чисел [0..3] в куски байта, чтобы учесть порядок их расположения,
    будем проводить с использованием побитового сдвига ВЛЕВО на (i + 3) % 3 * 2 знаков
*/
public class TicTacArray {
    private static final int ARRLENGTH = 9;
    private static final int VALUESPERBYTE = 3;
    private int byteArrLength = ARRLENGTH / VALUESPERBYTE;

    private byte[] byteArray = new byte[byteArrLength];
    private int[] intArray = new int[ARRLENGTH];
    private int nextElem;

    public TicTacArray() {
        this.nextElem = 0;
    }

    public TicTacArray(int[] intArray) {
        try{
            if (intArray.length != ARRLENGTH)
                throw new WrongArraySizeException(Integer.toString(intArray.length));
            for (int i = 0; i < ARRLENGTH; i++) {
                if (intArray[i] < 0 || intArray[i] > 3)
                    throw new WrongArrayException(Arrays.toString(intArray));
            }
            for (int i = 0; i < ARRLENGTH; i++) {
                add(intArray[i]);
            }
        } catch (WrongArraySizeException | WrongArrayException e){
            printException(e);
        }
    }

    public void add(Integer value, Integer position) {
        try{
            if (position < 0 || position >= ARRLENGTH)
                throw new WrongArrayIndexException(value, position);
            if (value < 0 || value > 3)
                    throw new WrongValueException(value, position);
            intArray[position] = value;
            nextElem = position + 1;
        }
        catch (WrongValueException | WrongArrayIndexException e){
            printException(e);
        }
    }

    public void add(Integer value){
        try {
            if (nextElem >= ARRLENGTH)
                throw new FullArrayException(value.toString());
            add(value, nextElem);
        }
        catch (FullArrayException e) {
            printException(e);
        }
    }

    public int[] get(){
        return intArray;
    }



    private void convertToByte(){
        int byteValue = 0;
        int byteArrPos = 0;
        int temp = 0;
        int shift = 0;
        for (int i = 0; i < ARRLENGTH; i++) {
            byteArrPos = i / byteArrLength;
            shift = (i + 3) % 3 * 2;
            temp = intArray[i] << shift;

            if (i % 3 == 0)
                byteValue = 0;

            byteValue = byteValue + temp ;
            byteArray[byteArrPos] = (byte)byteValue;
            /* ===== ЛОГИ =========== */
            System.out.printf("int[i] = %1s (%2s) | сдвиг %8s (%2s) | итог %3s = %s %n",
                    intArray[i],
                    Integer.toBinaryString(intArray[i]),
                    Integer.toBinaryString(temp),
                    temp,
                    byteValue,
                    Integer.toBinaryString(byteValue));
            /**/
        }
    }

    public void saveToFile() {
        convertToByte();
        File outputFile = new File("byteArray");
        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            for (int i = 0; i < byteArrLength; i++) {
                outputStream.write(byteArray[i]);
                System.out.println("запись " + byteArray[i] + " в файл");
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void print() {
        System.out.println(Arrays.toString(intArray));
    }

    private void printException(Exception e) {
        System.out.println("\u001B[93m" + "<<  !  >> " + e.getMessage() + "\u001B[0m");
    }
}
