package com.example.Calculator;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class CourseActivity extends AppCompatActivity {
    private ImageView imv_course;

    private String id_of_course;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_layout);

        imv_course = (ImageView) findViewById(R.id.imv_courseim);


        Bundle arguments = getIntent().getExtras();
        id_of_course = arguments.get("id").toString();
        try {
            getEventsFromXML(this);
        } catch (XmlPullParserException e) {
            Log.e("error","Ошибка");
            throw new RuntimeException(e);
        } catch (IOException e) {
            Log.e("error","Ошибка");
            throw new RuntimeException(e);
        }

    }

    private String getEventsFromXML(Activity activity) throws XmlPullParserException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Resources res = activity.getResources();
        XmlResourceParser xmlResourceParser = res.getXml(R.xml.courses);
        xmlResourceParser.next();
        int eventType = xmlResourceParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {


            if (eventType == XmlPullParser.TEXT) {
                Log.e("error",xmlResourceParser.getText());
                Log.e("error",id_of_course);
            }
            eventType = xmlResourceParser.next();
        }
        return "";
    }
}
