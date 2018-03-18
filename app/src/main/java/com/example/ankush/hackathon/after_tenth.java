package com.example.ankush.hackathon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class after_tenth extends AppCompatActivity {
private String url="https://career.webindia123.com/career/options/careeroptionsafter10th.htm";

    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_tenth);
        linearLayout = (LinearLayout) findViewById(R.id.after_ten_scrollView);
        ListAsyncTask task = new ListAsyncTask();
        task.execute(url);
    }





    @SuppressLint("StaticFieldLeak")
    private  class ListAsyncTask extends AsyncTask<String, Void, ArrayList<data_with_link>> {




        @Override
        protected ArrayList<data_with_link> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
           // Bitmap mIcon11 = null;
            ArrayList<data_with_link> temp = new ArrayList<>();
           String initialUrl = "https://career.webindia123.com";

            try {


                Document doc = Jsoup.connect(urls[0]).get();
                // String title = doc.title();
                Elements elements = doc.body().select("*");
                Elements subcontainer = doc.select("div#subcontainer");
              //  Elements listCareer = doc.select("div.span_30 td");
                //Elements linkss = listCareer.select("a[href]");
                //Elements image =listCareer.select("img[src]");                //for image displaying
               // String urlForImage = image.attr("src");

             //   Elements s=doc.select("div#subcontainer h1");
                //temp.add(new data_with_link('a',s.text(),"head"));
                //InputStream in = new java.net.URL(initialUrl+urlForImage).openStream();

              //  mIcon11 = BitmapFactory.decodeStream(in);

              //  publishProgress(mIcon11); // calling progressupdate method

               /* for (Element link : linkss) {
                    //copiyng 4 url options available
                    temp.add(new data_with_link(link.text().charAt(0),link.text(), initialUrl + link.attr("href")));
                    Log.i("lol; ",temp.get(0).getTitle());

                }
                */
//check here

                for(Element e: elements ) {


                    Attributes attributes = e.attributes();
                    if(attributes.toString().equals("p"))
                        temp.add(new data_with_link('a', e.ownText(), ""));
                    else
                        temp.add(new data_with_link('a', e.ownText(), "null"));

                }



            } catch (IOException e) {
                Log.i("a","aa");
            }



            return temp;


        }
        /*
                 void publishProgress(Bitmap bitmap) {

                    imageView.setImageBitmap(bitmap);
                }
        */
        @Override
        protected void onPostExecute(ArrayList<data_with_link> data) {
            // Clear the adapter of previous earthquake data

            for(int i=0; i<data.size()  ;i++){
//check here

                if(data.get(i).getUrl().equals("null"))
                {
                    TextView textView= new TextView(getBaseContext());
                    textView.setId(i);
                    textView.setText(data.get(i).getTitle());
                    textView.setPadding(8,6,6,4);

                    textView.setTextSize(25);
                    textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.V));
                    linearLayout.addView(textView);

                }else
                if(data.get(i).getUrl().equals("small"))
                {
                    TextView textView= new TextView(getBaseContext());
                    textView.setId(i);
                    textView.setText(data.get(i).getTitle());
                    textView.setPadding(8,6,7,4);
                    textView.setTextSize(18);

                    textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.P));
                    linearLayout.addView(textView);


                }else
                if(data.get(i).getUrl().equals("")){    //check here
                    TextView textView= new TextView(getBaseContext());
                    textView.setId(i);
                    textView.setPadding(8,6,7,4);
                    textView.setText(data.get(i).getTitle());
                    textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.D));
                    linearLayout.addView(textView);
                }else
                {
                    final String url=data.get(i).getUrl();
                    TextView textView= new TextView(getBaseContext());
                    textView.setId(i);
                    textView.setTextSize(18);
                    textView.setPadding(8,4,0,4);
                    textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorPrimary));
                    String s=">>"+data.get(i).getTitle();
                    textView.setText(s);
                    linearLayout.addView(textView);
                    textView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Log.i("jdjshdh","asjbdkjkadbbhahb");
                            Intent intent = new Intent(getApplicationContext(), selectedCareerDetails.class);
                            intent.putExtra("url",url);
                            startActivity(intent);
                            // Log.i("TAG", "The index is" + index);
                        }
                    });

                    Log.i("ahghsjdga","ajshdjajh");

                }


            }


        }


    }




}



