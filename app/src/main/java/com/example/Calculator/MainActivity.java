//TODO:
// 1. поиск по картинке
// 2. Страницы курсов
// 3. Страница Профиля
// 4. Подобрать прикольные картинки курсов (и добавить на них текст названия) (*добавить отображение цены курса на главную)
// 5. украсить главную страницу


package com.example.Calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

private RelativeLayout l_courses;
private EditText tag_search;
private EditText search;
private TextView androidStudioCurses_tag;
private TextView EnglishCurses_tag;
private TextView JavaCurses_tag;

private LinearLayout JavaCurses_layout;
private LinearLayout EnglishCurses_layout;
private LinearLayout AndroidStudioCurses_layout;

private ImageButton prof_btn;
private ImageButton courses_btn;
private ImageButton tests_btn;


public static SQLiteDatabase JavacoursesDB;
public static SQLiteDatabase AndroidStudiocoursesDB;
public static SQLiteDatabase EnglishcoursesDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        l_courses = (RelativeLayout) findViewById(R.id.l_courses);
        JavaCurses_layout = (LinearLayout) findViewById(R.id.JavaCourses);
        EnglishCurses_layout = (LinearLayout) findViewById(R.id.EnglishCourses);
        AndroidStudioCurses_layout = (LinearLayout) findViewById(R.id.androidStudioCurses);

        androidStudioCurses_tag = (TextView) findViewById(R.id.androidStudioCurses_tag);
        JavaCurses_tag = (TextView) findViewById(R.id.JavaCurses_tag);
        EnglishCurses_tag = (TextView) findViewById(R.id.EnglishCourses_tag);

        prof_btn = (ImageButton) findViewById(R.id.btn_profile);
        courses_btn = (ImageButton) findViewById(R.id.btn_courses);
        tests_btn = (ImageButton) findViewById(R.id.btn_tests);

        LinearLayout[] courses_layouts = new LinearLayout[l_courses.getChildCount()];
        courses_layouts[0] = AndroidStudioCurses_layout;
        courses_layouts[1] = EnglishCurses_layout;
        courses_layouts[2] = JavaCurses_layout;
        String[] tags = new String[l_courses.getChildCount()];

        tags[0] = androidStudioCurses_tag.getText().toString();
        tags[1] = EnglishCurses_tag.getText().toString();
        tags[2] = JavaCurses_tag.getText().toString();


        ImageView[] im_v_androidcourses = new ImageView[AndroidStudioCurses_layout.getChildCount()-1];
        ImageView[] im_v_englishcourses = new ImageView[EnglishCurses_layout.getChildCount()-1];
        ImageView[] im_v_javacourses = new ImageView[JavaCurses_layout.getChildCount()-1];

        //переход на активити курсов при нажатии на картинку курса
        for(int i =0;i < im_v_androidcourses.length;i++){
            im_v_androidcourses[i] = (ImageView) AndroidStudioCurses_layout.getChildAt(i+1);
            ImageView holder = im_v_androidcourses[i];
            im_v_androidcourses[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(MainActivity.this, CourseActivity.class);
                    myIntent.putExtra("id", holder.getId());
                    MainActivity.this.startActivity(myIntent);
                }
            });
        }
        for(int i =0;i < im_v_englishcourses.length;i++){
            im_v_englishcourses[i] = (ImageView) EnglishCurses_layout.getChildAt(i+1);
            im_v_englishcourses[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(MainActivity.this, CourseActivity.class);
                    //myIntent.putExtra("key", value); //Optional parameters
                    MainActivity.this.startActivity(myIntent);
                }
            });
        }
        for(int i =0;i < im_v_javacourses.length;i++){
            im_v_javacourses[i] = (ImageView) JavaCurses_layout.getChildAt(i+1);
            im_v_javacourses[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(MainActivity.this, CourseActivity.class);
                    //myIntent.putExtra("key", value); //Optional parameters
                    MainActivity.this.startActivity(myIntent);
                }
            });
        }


        tag_search = (EditText) findViewById(R.id.search_tag);
        search = (EditText) findViewById(R.id.search);


        tag_search.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                String search_str = tag_search.getText().toString();
                Pattern pattern = Pattern.compile(search_str,Pattern.CASE_INSENSITIVE);
                for(int i =0;i< tags.length;i++){
                    Matcher matcher = pattern.matcher(tags[i]);
                    if(!matcher.find() && !search_str.equals("")){
                        courses_layouts[i].setVisibility(View.GONE);
                    }
                    else if(search_str.equals("") || matcher.find()){
                        courses_layouts[i].setVisibility(View.VISIBLE);
                    }
                }
            }
        }
        );

        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_DOWN &&
                        (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    // сохраняем текст, введённый до нажатия Enter в переменную
                    String search_str = search.getText().toString();
                    return true;
                }
                return false;
            }
        }
        ); // доделать

        prof_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tests_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        courses_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}