package com.example.words;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.example.words.Contract.Entry.DIR_SD;
import static com.example.words.Contract.Entry.TAG;
import static com.example.words.Contract.Entry.FILENAME_SD;


public class GetData extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_data_activity);
    }

    public void onclick(View v) {
        readFile();
    }


    void readFile() {


//        StringBuilder buf = new StringBuilder();
        InputStream text;
        try {
            text = this.getAssets()
                       .open("test.txt");
//            BufferedReader in = new BufferedReader(new InputStreamReader(text));
//            String str;
//
//            while ((str = in.readLine()) != null) {
//                Log.d(TAG, "" + str);
//                buf.append(str);
//
//            }
//
//            in.close();

            BufferedInputStream bis = new BufferedInputStream(text);
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            int result = bis.read();
            while(result != -1) {
                if (result == 59) {
                    Log.d(TAG, "" + buf.toString());
                    buf.reset();
                    result = bis.read();
                    continue;
                }
                buf.write((byte) result);
                result = bis.read();
//                Log.d(TAG, "" + (char)result);
            }
            Log.d(TAG, "" + buf.toString());
            Log.d(TAG, "" + buf);

        } catch (IOException e) {
            e.printStackTrace();
        }


//        InputStream is = getResources().openRawResource(R.raw.test);

//        try (FileInputStream fin = new FileInputStream(String.valueOf(getResources().openRawResource(R.raw.test)))) {
//            System.out.printf("File size: %d bytes \n", fin.available());
//
//            int i = -1;
//            while ((i = fin.read()) != -1) {
//
//                System.out.print((char) i);
//                Log.d(TAG, "" + (char) i);
//            }
//        } catch (IOException ex) {
//
//            System.out.println(ex.getMessage());
//        }


//
//            // проверяем доступность SD
////        if (!Environment.getExternalStorageState()
////                        .equals(
////                                Environment.MEDIA_MOUNTED)) {
////            Log.d(TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
////            return;
////        }
//        // получаем путь к SD
//        File sdPath = Environment.getExternalStorageDirectory();
//        // добавляем свой каталог к пути
//        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
//        // формируем объект File, который содержит путь к файлу
//        File sdFile = new File(sdPath, FILENAME_SD);
//        try {
//            // открываем поток для чтения
//            BufferedReader br = new BufferedReader(new FileReader(sdFile));
//            String str = "";
//            // читаем содержимое
//            while ((str = br.readLine()) != null) {
//                Log.d(TAG, str);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    }
}
