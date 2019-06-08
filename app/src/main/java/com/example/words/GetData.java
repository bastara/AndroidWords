package com.example.words;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import com.example.words.db.DatabaseHelper;
import com.example.words.db.model.DataModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.example.words.Contract.Entry.TAG;


public class GetData extends AppCompatActivity {

    DatabaseHelper databaseHelper = App.getInstance()
                                       .getDatabaseInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_data_activity);
    }

    public void onclick(View v) {
        readFile();
    }

    void readFile() {

        DataModel model = new DataModel();

        StringBuilder buf = new StringBuilder();
        InputStream text;
        try {
            text = this.getAssets()
                       .open("test.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(text));
            String str;

            int position = 0;
            int count = 0;

            while ((str = in.readLine()) != null) {
                position = 0;
                count = 0;
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == ';' || i == str.length() - 1) {
                        switch (count) {
                            case 0:
                                model.setWord(str.substring(position, i));
                                position = i + 1;
                                count++;
                                break;
                            case 1:
                                model.setTranscription(str.substring(position, i));
                                position = i + 1;
                                count++;
                                break;
                            case 2:
                                model.setTranslation(str.substring(position, i));
                                position = i + 1;
                                count++;
                                break;
                            case 3:
                                model.setAssociation(str.substring(position, i));
                                position = i + 1;
                                count++;
                                break;
                        }
                    }
                }
                if (databaseHelper.getDataDao().getWords(model.getWord())==null) {
                    databaseHelper.getDataDao()
                                  .insertWord(model);
                }
                Log.d(TAG, "" + str);
                buf.append(str);

            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        InputStream text;
//        try {
//            text = this.getAssets()
//                       .open("test.txt");
//
//            BufferedInputStream bis = new BufferedInputStream(text);
//            ByteArrayOutputStream buf = new ByteArrayOutputStream();
//            int result = bis.read();
//            while(result != -1) {
//                if (result == 59) {
//                    Log.d(TAG, "" + buf.toString());
//                    buf.reset();
//                    result = bis.read();
//                    continue;
//                }
//                buf.write((byte) result);
//                result = bis.read();
////                Log.d(TAG, "" + (char)result);
//            }
//            Log.d(TAG, "" + buf.toString());
//            Log.d(TAG, "" + buf);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void onclickDeleteWords(View view) {
        databaseHelper.getDataDao()
                      .nukeTable();
    }
}
