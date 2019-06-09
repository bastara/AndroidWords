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
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

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

            fillDB(in);

//            while ((str = in.readLine()) != null) {
//                int position = 0;
//                int count = 0;
//                for (int i = 0; i < str.length(); i++) {
//                    if (str.charAt(i) == ';') {
//                        switch (count) {
//                            case 0:
//                                model.setWord(str.substring(position, i));
//                                position = i + 1;
//                                count++;
//                                break;
//                            case 1:
//                                model.setTranscription(str.substring(position, i));
//                                position = i + 1;
//                                count++;
//                                break;
//                            case 2:
//                                model.setTranslation(str.substring(position, i));
//                                position = i + 1;
//                                count++;
//                                break;
//                            case 3:
//                                model.setAssociation(str.substring(position, i));
//                                position = i + 1;
//                                count++;
//                        }
//                    }
//                    if (i == str.length() - 1) {
//                        switch (count) {
//                            case 2:
//                                model.setTranslation(str.substring(position, i + 1));
//                                position = i + 1;
//                                count++;
//                                break;
//                            case 3:
//                                model.setAssociation(str.substring(position, i + 1));
//                                position = i + 1;
//                                count++;
//                        }
//                    }
//
//                }
//                if (databaseHelper.getDataDao()
//                                  .getWords(model.getWord()) == null) {
//                    databaseHelper.getDataDao()
//                                  .insertWord(model);
//                }
//                Log.d(TAG, "" + str);
//                buf.append(str);
//
//            }
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

    private void fillDB(BufferedReader in) throws IOException {
        DataModel model = new DataModel();

        String str;

        while ((str = in.readLine()) != null) {
            int position = 0;
            int count = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == ';') {
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
                    }
                }
                if (i == str.length() - 1) {
                    switch (count) {
                        case 2:
                            model.setTranslation(str.substring(position, i + 1));
                            position = i + 1;
                            count++;
                            break;
                        case 3:
                            model.setAssociation(str.substring(position, i + 1));
                            position = i + 1;
                            count++;
                    }
                }

            }
            if (databaseHelper.getDataDao()
                              .getWords(model.getWord()) == null) {
                databaseHelper.getDataDao()
                              .insertWord(model);
            }
            Log.d(TAG, "" + str);
//            buf.append(str);

        }
    }

    public void onclickDeleteWords(View view) {
        databaseHelper.getDataDao()
                      .nukeTable();
//        databaseHelper.clearAllTables();
    }

    public void onclickWeb(View view) {

//        URL url = null;
//        try {
//            url = new URL("http://s150vla.storage.yandex.net/rdisk/9b9bf4533cbff75be932298ad489a4c0f08715f9c90185f237ae1d5d0ee3a0aa/5cfcae85/xEsgu8FwQNyma7vDiS0DjTVbAHnnt7aCELC2iEL2kvdRe_ouXeF764UvBdnYlVoP9rHQHgJ2Qiluvg5lVXi6wQ==?uid=116280676&filename=test.txt&disposition=attachment&hash=&limit=0&content_type=text%2Fplain&fsize=3739&hid=9846fcb4122da2761964a09a0f6bcdbc&media_type=document&tknv=v2&etag=8c6750470f33c9f534047baae985eb62&rtoken=80yDM4XKCkh3&force_default=yes&ycrid=na-5d2b94cfd6cf39ede48fb9a11582bd1f-downloader15h&ts=58ade9df4eb40&s=c89a0317f378bf24eb777927acbba25a7b9deafd95c707c139796c19981ea0f8&pb=U2FsdGVkX18KOrVyQh3jgLitYhU05WjVHLBMBcEUHMxI7r3uFDhUHijCtCKkADCPRA_MlYL6tpfNOWjrDXLr1Is3Y4MKmhbLtZiV28TtEsc");
////            url = new URL("https://getfile.dokpub.com/yandex/get/https://yadi.sk/d/eNyaKjPaVckbNw");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            InputStream inputStream = url.openConnection()
//                                         .getInputStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://s150vla.storage.yandex.net/rdisk/9b9bf4533cbff75be932298ad489a4c0f08715f9c90185f237ae1d5d0ee3a0aa/5cfcae85/xEsgu8FwQNyma7vDiS0DjTVbAHnnt7aCELC2iEL2kvdRe_ouXeF764UvBdnYlVoP9rHQHgJ2Qiluvg5lVXi6wQ==?uid=116280676&filename=test.txt&disposition=attachment&hash=&limit=0&content_type=text%2Fplain&fsize=3739&hid=9846fcb4122da2761964a09a0f6bcdbc&media_type=document&tknv=v2&etag=8c6750470f33c9f534047baae985eb62&rtoken=80yDM4XKCkh3&force_default=yes&ycrid=na-5d2b94cfd6cf39ede48fb9a11582bd1f-downloader15h&ts=58ade9df4eb40&s=c89a0317f378bf24eb777927acbba25a7b9deafd95c707c139796c19981ea0f8&pb=U2FsdGVkX18KOrVyQh3jgLitYhU05WjVHLBMBcEUHMxI7r3uFDhUHijCtCKkADCPRA_MlYL6tpfNOWjrDXLr1Is3Y4MKmhbLtZiV28TtEsc");
                    HttpsURLConnection c = (HttpsURLConnection) url.openConnection();
                    c.setRequestMethod("GET");
                    c.setReadTimeout(10000);
                    c.connect();
                    reader = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder buf = new StringBuilder();
                    String line;

                    fillDB(reader);
//                    while ((line = reader.readLine()) != null) {
//                        buf.append(line + "\n");
//                    }
                    Log.d(TAG, "" + buf.toString());
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }
        });
        thread.start();


    }
}
