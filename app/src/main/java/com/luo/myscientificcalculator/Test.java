package com.luo.myscientificcalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

import java.util.ArrayList;

public class Test extends Activity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private SharedPreferences.Editor editor;
    private ViewPager vpager_four;
    private ImageView img_cursor;
    private TextView tv_one;
    private TextView tv_two;
    private TextView tv_three;

    private ArrayList<View> listViews;
    private int offset = 0;//移动条图片的偏移量
    private int currIndex = 0;//当前页面的编号
    private int bmpWidth;// 移动条图片的长度
    private int one = 0; //移动条滑动一页的距离
    private int two = 0; //滑动条移动两页的距离


    private TextView tv_1,tv_2,tv_3,tv_4,tv_5,tv_6,tv_7,tv_8,tv_9,tv_0,tv_show,
            tv_point,tv_add,tv_sub,tv_div,tv_mult,tv_kuo_right,tv_clear,tv_backspace,tv_equal,tv_kuo_left;
    private TextView tv_1_pro,tv_2_pro,tv_3_pro,tv_4_pro,tv_5_pro,tv_6_pro,tv_7_pro,tv_8_pro,tv_9_pro,tv_0_pro,
            tv_show_pro, tv_point_pro,tv_add_pro,tv_sub_pro,tv_div_pro, tv_mult_pro,
            tv_clear_pro,tv_backspace_pro,tv_equal_pro,tv_kuo_left_pro,tv_kuo_right_pro,tv_pi,tv_e,tv_sin,tv_cos,tv_tan,tv_ln,tv_lg,tv_sqrt,
            tv_pow,tv_ping,tv_hitory;
    private static StringBuilder sb_show ,sb_result;
    private static int flag_kuo_left = 0,flag_kuo_right = 0,flag_result=0,flag_operator = 0;
    private Evaluator evaluator = new Evaluator();
    private String  result = "";
    private static StringBuilder sb_show_pro ,sb_result_pro;
    private static String flag = "";
    private static String flag_show = "";
    private static String [] all_op = new String[100];
    private static int index = -1;
    private  String simple_history_str = "";



    private static int flag_kuo_pro = 0,flag_result_pro=0;
    private Evaluator evaluator_pro = new Evaluator();
    private String  result_pro = "";
    public static final double PPP = Math.PI;
    public static final double EEE = Math.E;
    private static String operator = "";
    private static String flag_pro = "";
    private static int flag_kuo_left_pro = 0,flag_kuo_right_pro = 0;
    private static String [] all_op_pro = new String[100];
    private static String [] all_flag_pro = new String[100];
    private static int index_op = -1;
    private static int index_flag = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        vpager_four = (ViewPager) findViewById(R.id.vpager_four);
        tv_one = (TextView) findViewById(R.id.tv_one);
        tv_two = (TextView) findViewById(R.id.tv_two);
        tv_three = (TextView) findViewById(R.id.tv_three);
        img_cursor = (ImageView) findViewById(R.id.img_cursor);

        //下划线动画的相关设置：
        bmpWidth = BitmapFactory.decodeResource(getResources(), R.drawable.menu).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 3 - bmpWidth) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        img_cursor.setImageMatrix(matrix);// 设置动画初始位置
        //移动的距离
        one = offset * 2 + bmpWidth;// 移动一页的偏移量,比如1->2,或者2->3
        two = one * 2;// 移动两页的偏移量,比如1直接跳3
        //查找布局文件用LayoutInflater.inflate
        LayoutInflater inflater =getLayoutInflater();
        View view1 = inflater.inflate(R.layout.simple, null);
        View view2 = inflater.inflate(R.layout.scientific, null);
        View view3 = inflater.inflate(R.layout.matrix, null);
        listViews = new ArrayList<View>();
        //添加想要切换的界面
        listViews.add(view1);
        listViews.add(view2);
        listViews.add(view3);
        vpager_four.setAdapter(new MyPagerAdapter(listViews));
        vpager_four.setCurrentItem(0);          //设置ViewPager当前页，从0开始算

        tv_one.setOnClickListener(this);
        tv_two.setOnClickListener(this);
        tv_three.setOnClickListener(this);

        vpager_four.addOnPageChangeListener(this);




        sb_show = new StringBuilder();
        sb_result = new StringBuilder();
        tv_0 = (TextView) view1.findViewById(R.id.tv_zero);
        tv_1 = (TextView) view1.findViewById(R.id.tv_1);
        tv_2 = (TextView) view1.findViewById(R.id.tv_2);
        tv_3 = (TextView) view1.findViewById(R.id.tv_3);
        tv_4 = (TextView) view1.findViewById(R.id.tv_4);
        tv_5 = (TextView) view1.findViewById(R.id.tv_5);
        tv_6 = (TextView) view1.findViewById(R.id.tv_6);
        tv_7 = (TextView) view1.findViewById(R.id.tv_7);
        tv_8 = (TextView) view1.findViewById(R.id.tv_8);
        tv_9 = (TextView) view1.findViewById(R.id.tv_9);
        tv_point = (TextView) view1.findViewById(R.id.tv_point);
        tv_add = (TextView) view1.findViewById(R.id.tv_add);
        tv_sub = (TextView) view1.findViewById(R.id.tv_sub);
        tv_mult = (TextView) view1.findViewById(R.id.tv_mult);
        tv_div = (TextView) view1.findViewById(R.id.tv_div);
        tv_equal = (TextView) view1.findViewById(R.id.tv_equal);
        tv_backspace = (TextView) view1.findViewById(R.id.tv_backspace);
        tv_kuo_left = (TextView) view1.findViewById(R.id.tv_quo_left);
        tv_kuo_right = (TextView) view1.findViewById(R.id.tv_quo_right);
        tv_clear = (TextView) view1.findViewById(R.id.tv_clear);
        tv_show = (TextView) view1.findViewById(R.id.tv_show);
        tv_hitory = (TextView) view1.findViewById(R.id.tv_history);



        sb_show_pro = new StringBuilder();
        sb_result_pro = new StringBuilder();
        tv_0_pro = (TextView) view2.findViewById(R.id.tv_zero_pro);
        tv_1_pro = (TextView) view2.findViewById(R.id.tv_1_pro);
        tv_2_pro = (TextView) view2.findViewById(R.id.tv_2_pro);
        tv_3_pro = (TextView) view2.findViewById(R.id.tv_3_pro);
        tv_4_pro = (TextView) view2.findViewById(R.id.tv_4_pro);
        tv_5_pro = (TextView) view2.findViewById(R.id.tv_5_pro);
        tv_6_pro = (TextView) view2.findViewById(R.id.tv_6_pro);
        tv_7_pro = (TextView) view2.findViewById(R.id.tv_7_pro);
        tv_8_pro = (TextView) view2.findViewById(R.id.tv_8_pro);
        tv_9_pro = (TextView) view2.findViewById(R.id.tv_9_pro);
        tv_point_pro = (TextView) view2.findViewById(R.id.tv_point_pro);
        tv_add_pro = (TextView) view2.findViewById(R.id.tv_add_pro);
        tv_sub_pro = (TextView) view2.findViewById(R.id.tv_sub_pro);
        tv_mult_pro = (TextView) view2.findViewById(R.id.tv_mult_pro);
        tv_div_pro = (TextView) view2.findViewById(R.id.tv_div_pro);
        tv_equal_pro = (TextView) view2.findViewById(R.id.tv_equal_pro);
        tv_backspace_pro = (TextView) view2.findViewById(R.id.tv_backspace_pro);
        tv_kuo_left_pro = (TextView) view2.findViewById(R.id.tv_quo_left_pro);
        tv_kuo_right_pro = (TextView) view2.findViewById(R.id.tv_quo_right_pro);
        tv_clear_pro = (TextView) view2.findViewById(R.id.tv_clear_pro);
        tv_show_pro = (TextView) view2.findViewById(R.id.tv_show_pro);
        tv_pi = (TextView) view2.findViewById(R.id.tv_pi);
        tv_e= (TextView) view2.findViewById(R.id.tv_e);
        tv_sin= (TextView) view2.findViewById(R.id.tv_sin);
        tv_cos = (TextView) view2.findViewById(R.id.tv_cos);
        tv_tan = (TextView) view2.findViewById(R.id.tv_tan);
        tv_ln = (TextView) view2.findViewById(R.id.tv_ln);
        tv_lg = (TextView) view2.findViewById(R.id.tv_lg);
        tv_sqrt = (TextView) view2.findViewById(R.id.tv_sqrt);
        tv_pow = (TextView) view2.findViewById(R.id.tv_pow);
        tv_ping = (TextView) view2.findViewById(R.id.tv_ping);


        tv_show.setOnClickListener(this);
        tv_0.setOnClickListener(this);
        tv_1.setOnClickListener(this);
        tv_2.setOnClickListener(this);
        tv_3.setOnClickListener(this);
        tv_4.setOnClickListener(this);
        tv_5.setOnClickListener(this);
        tv_6.setOnClickListener(this);
        tv_7.setOnClickListener(this);
        tv_8.setOnClickListener(this);
        tv_9.setOnClickListener(this);
        tv_point.setOnClickListener(this);
        tv_add.setOnClickListener(this);
        tv_sub.setOnClickListener(this);
        tv_mult.setOnClickListener(this);
        tv_div.setOnClickListener(this);
        tv_clear.setOnClickListener(this);
        tv_kuo_left.setOnClickListener(this);
        tv_kuo_right.setOnClickListener(this);
        tv_backspace.setOnClickListener(this);
        tv_equal.setOnClickListener(this);
        tv_hitory.setOnClickListener(this);



       tv_1_pro.setOnClickListener(this);tv_2_pro.setOnClickListener(this);tv_3_pro.setOnClickListener(this);tv_4_pro.setOnClickListener(this);
        tv_5_pro.setOnClickListener(this);tv_6_pro.setOnClickListener(this);tv_7_pro.setOnClickListener(this);tv_8_pro.setOnClickListener(this);
        tv_9_pro.setOnClickListener(this);tv_0_pro.setOnClickListener(this);tv_add_pro.setOnClickListener(this);
        tv_show_pro.setOnClickListener(this); tv_point_pro.setOnClickListener(this);tv_sub_pro.setOnClickListener(this);
        tv_div_pro.setOnClickListener(this); tv_mult_pro.setOnClickListener(this);
        tv_clear_pro.setOnClickListener(this);tv_backspace_pro.setOnClickListener(this);tv_equal_pro.setOnClickListener(this);
        tv_kuo_left_pro.setOnClickListener(this);
        tv_kuo_right_pro.setOnClickListener(this);tv_pi.setOnClickListener(this);tv_e.setOnClickListener(this);
        tv_sin.setOnClickListener(this);tv_cos.setOnClickListener(this);tv_tan.setOnClickListener(this);
        tv_ln.setOnClickListener(this);tv_lg.setOnClickListener(this);tv_sqrt.setOnClickListener(this);
        tv_pow.setOnClickListener(this);tv_ping.setOnClickListener(this);

    }

    @Override
    public void onPageSelected(int index) {
        Animation animation = null;
        switch (index) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, 0, 0, 0);
                }
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                }
                break;
            case 2:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, two, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(one, two, 0, 0);
                }
                break;
        }
        currIndex = index;
        animation.setFillAfter(true);// true表示图片停在动画结束位置
        animation.setDuration(300); //设置动画时间为300毫秒
        img_cursor.startAnimation(animation);//开始动画
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }


    @Override
    public void onClick(View view){

        switch (view.getId()){
            case R.id.tv_one:
                vpager_four.setCurrentItem(0);
                break;
            case R.id.tv_two:
                vpager_four.setCurrentItem(1);
                break;
            case R.id.tv_three:
                vpager_four.setCurrentItem(2);
                break;




            case R.id.tv_zero:
                //是否进行过计算，然后初始化
                if (flag_result == 1) { clear(); }

                flag = "num";index++;all_op[index] = flag;
                tv_show.setText(sb_show.append("0"));
                sb_result.append("0");
                setShowSize(sb_show);
                break;
            case R.id.tv_1:
                if (flag_result == 1) { clear(); }
                flag = "num";index++;all_op[index] = flag;
                sb_result.append("1");tv_show.setText(sb_show.append("1"));
                setShowSize(sb_show);
                break;
            case R.id.tv_2:
                if (flag_result == 1) { clear(); }
                flag = "num";index++;all_op[index] = flag;sb_result.append("2");
                tv_show.setText(sb_show.append("2")); setShowSize(sb_show);
                break;
            case R.id.tv_3:
                if (flag_result == 1) { clear(); }
                flag = "num";
                index++;all_op[index] = flag;
                sb_result.append("3");
                tv_show.setText(sb_show.append("3")); setShowSize(sb_show);
                break;
            case R.id.tv_4:
                if (flag_result == 1) { clear(); }
                flag = "num";
                index++;all_op[index] = flag;
                sb_result.append("4");
                tv_show.setText(sb_show.append("4"));operator = ""; setShowSize(sb_show);
                break;
            case R.id.tv_5:
                if (flag_result == 1) { clear(); }
                flag = "num";
                index++;all_op[index] = flag;
                sb_result.append("5");
                tv_show.setText(sb_show.append("5")); setShowSize(sb_show);
                break;
            case R.id.tv_6:
                if (flag_result == 1) { clear(); }
                flag = "num";
                index++;all_op[index] = flag;
                sb_result.append("6");
                tv_show.setText(sb_show.append("6")); setShowSize(sb_show);
                break;
            case R.id.tv_7:
                if (flag_result == 1) { clear(); }
                flag = "num";
                index++;all_op[index] = flag;
                sb_result.append("7");
                tv_show.setText(sb_show.append("7")); setShowSize(sb_show);
                break;
            case R.id.tv_8:
                if (flag_result == 1) { clear(); }
                flag = "num";
                index++;all_op[index] = flag;
                sb_result.append("8");
                tv_show.setText(sb_show.append("8")); setShowSize(sb_show);
                break;
            case R.id.tv_9:
                if (flag_result == 1) { clear(); }
                flag = "num";
                index++;all_op[index] = flag;
                sb_result.append("9");
                tv_show.setText(sb_show.append("9")); setShowSize(sb_show);
                break;
            //------------------------------------------------------------


            case R.id.tv_add:
                ifNullAddZero();
                MyJeval.getOperator(sb_result,"+",all_op[index]);
                MyJeval.getOperator(sb_show,"+",all_op[index]);
                tv_show.setText(sb_show);flag = "+";index++; all_op[index] = flag;flag_result = 0; setShowSize(sb_show);
                break;
            case R.id.tv_sub:
                ifNullAddZero();
                MyJeval.getOperator(sb_result,"-",all_op[index]);
                MyJeval.getOperator(sb_show,"-",all_op[index]);
                tv_show.setText(sb_show);
                flag = "-";
                index++; all_op[index] = flag;
                flag_result = 0; setShowSize(sb_show);
                break;
            case R.id.tv_mult:
               ifNullAddZero();
                MyJeval.getOperator(sb_result,"*",all_op[index]);
                MyJeval.getOperator(sb_show,"×",all_op[index]);tv_show.setText(sb_show);
                flag = "*";
                index++; all_op[index] = flag;flag_result = 0; setShowSize(sb_show);
                break;
            case R.id.tv_div:
                ifNullAddZero();
                MyJeval.getOperator(sb_result,"/",all_op[index]);
                MyJeval.getOperator(sb_show,"÷",all_op[index]);tv_show.setText(sb_show);
                flag = "/";
                index++;all_op[index] = flag;
                flag_result = 0; setShowSize(sb_show);
                break;
            case R.id.tv_point:
                if (flag_result == 1) { clear(); }
                ifNullAddZero();
                flag = "num";flag_result = 0;sb_result.append(".");tv_show.setText(sb_show.append("."));
                index++;all_op[index] = flag;
                setShowSize(sb_show);
                break;

            case R.id.tv_clear:
                clear();
                 setShowSize(sb_show);
                break;
            case R.id.tv_backspace:

                if (sb_show.length() != 0 && sb_result.length() != 0) {
                    if(sb_result.charAt(sb_result.length()-1) =='(' ){
                        flag_kuo_left--;
                    }
                    if(sb_result.charAt(sb_result.length()-1) ==')' ){
                        flag_kuo_right--;
                    }
                    all_op[index] = "";
                    index--;


                    tv_show.setText(sb_show.deleteCharAt(sb_show.length()-1));
                    sb_result.deleteCharAt(sb_result.length()-1);
                    flag_result = 0; setShowSize(sb_show);
                }

                break;
            case R.id.tv_equal:
                try {
                    if (flag_kuo_left >flag_kuo_right) {
                        for (int i = 0; i < flag_kuo_left - flag_kuo_right; i++) {
                            sb_result.append(")");
                        }

                    }
                    result = evaluator.evaluate(sb_result.toString());

                    simple_history_str +=  sb_show.toString()+"#";

                    result = resultToInt(result);
                    tv_show.setText(result);
                    sb_result = new StringBuilder();
                    sb_show = new StringBuilder();
                    sb_result.append(result);
                    sb_show.append(result);
                    flag_result = 1;flag_kuo_left = 0;flag_kuo_right = 0;index = -1;flag = "num";all_op = new String[100];
                    for (int i = 0; i < sb_result.length(); i++) {
                        index++;all_op[index] = flag;
                    }


                } catch (EvaluationException ee) {
                    Toast.makeText(Test.this,"输入有误...", Toast.LENGTH_SHORT).show();
                }
                setShowSize(sb_show);
                break;
            case R.id.tv_quo_left:
                if (flag_result == 1) {clear(); }
                tv_show.setText(sb_show.append("("));
                sb_result.append("(");
                flag_result = 0;
                flag_kuo_left++; setShowSize(sb_show);
                break;
            case R.id.tv_quo_right:
                if (flag_result == 1) { clear(); }
                tv_show.setText(sb_show.append(")"));
                sb_result.append(")");
                flag_result = 0;
                flag_kuo_right++; setShowSize(sb_show);
                break;
            case R.id.tv_history:
                editor = getSharedPreferences("simple_history",MODE_PRIVATE).edit();
                editor.putString("str_history",simple_history_str);
                editor.apply();
                Intent intent = new Intent(Test.this,simple_history.class);
                startActivity(intent);
                //Toast.makeText(Test.this,simple_history_str, Toast.LENGTH_LONG).show();




/**
 *              科学计算
 * *********************************************************************
 */

            case R.id.tv_zero_pro:
                if (flag_result_pro == 1) { clear_pro(); }

                flag_pro = "num";
                index_flag++;all_flag_pro[index_flag] = flag_pro;

                tv_show_pro.setText(sb_show_pro.append("0"));
                sb_result_pro.append("0");setShowSize_pro(sb_show_pro);
                break;
            case R.id.tv_1_pro:
                if (flag_result_pro == 1) { clear_pro(); }

                flag_pro = "num"; index_flag++;all_flag_pro[index_flag] = flag_pro;
                sb_result_pro.append("1");
                tv_show_pro.setText(sb_show_pro.append("1"));setShowSize_pro(sb_show_pro);
                break;
            case R.id.tv_2_pro:
                if (flag_result_pro == 1) { clear_pro(); }
                flag_pro = "num"; index_flag++;all_flag_pro[index_flag] = flag_pro;
                sb_result_pro.append("2");
                tv_show_pro.setText(sb_show_pro.append("2"));setShowSize_pro(sb_show_pro);
                break;
            case R.id.tv_3_pro:
                if (flag_result_pro == 1) { clear_pro(); }


                flag_pro = "num"; index_flag++;all_flag_pro[index_flag] = flag_pro;
                sb_result_pro.append("3");
                tv_show_pro.setText(sb_show_pro.append("3"));setShowSize_pro(sb_show_pro);
                break;
            case R.id.tv_4_pro:
                if (flag_result_pro == 1) { clear_pro(); }
                flag_pro = "num"; index_flag++;all_flag_pro[index_flag] = flag_pro;
                sb_result_pro.append("4");
                tv_show_pro.setText(sb_show_pro.append("4"));setShowSize_pro(sb_show_pro);
                break;
            case R.id.tv_5_pro:
                if (flag_result_pro == 1) { clear_pro(); }
                flag_pro = "num"; index_flag++;all_flag_pro[index_flag] = flag_pro;
                sb_result_pro.append("5");
                tv_show_pro.setText(sb_show_pro.append("5"));setShowSize_pro(sb_show_pro);
                break;
            case R.id.tv_6_pro:
                if (flag_result_pro == 1) { clear_pro(); }
                flag_pro = "num";index_flag++;all_flag_pro[index_flag] = flag_pro;
                sb_result_pro.append("6");
                tv_show_pro.setText(sb_show_pro.append("6"));setShowSize_pro(sb_show_pro);
                break;
            case R.id.tv_7_pro:
                if (flag_result_pro == 1) { clear_pro(); }
                flag_pro = "num";index_flag++;all_flag_pro[index_flag] = flag_pro;
                sb_result_pro.append("7");
                tv_show_pro.setText(sb_show_pro.append("7"));setShowSize_pro(sb_show_pro);
                break;
            case R.id.tv_8_pro:
                if (flag_result_pro == 1) { clear_pro(); }
                flag_pro = "num";index_flag++;all_flag_pro[index_flag] = flag_pro;
                sb_result_pro.append("8");
                tv_show_pro.setText(sb_show_pro.append("8"));setShowSize_pro(sb_show_pro);
                break;
            case R.id.tv_9_pro:
                if (flag_result_pro == 1) { clear_pro(); }
                flag_pro = "num";index_flag++;all_flag_pro[index_flag] = flag_pro;
                sb_result_pro.append("9");
                tv_show_pro.setText(sb_show_pro.append("9"));setShowSize_pro(sb_show_pro);
                break;


/**
 * *******************************************************************
 */


            case R.id.tv_add_pro:
                ifNullAddZero_pro();

                flag_result_pro = 0;
                MyJeval.getOperator(sb_result_pro,"+",all_flag_pro[index_flag]);
                MyJeval.getOperator(sb_show_pro,"+",all_flag_pro[index_flag]);
                tv_show_pro.setText(sb_show_pro);
                flag_pro = "+";index_flag++;all_flag_pro[index_flag] = flag_pro;
                operator = "+";index_op++;all_op_pro[index_op] = operator;setShowSize_pro(sb_show_pro);
                break;
            case R.id.tv_sub_pro:
                ifNullAddZero_pro();
                flag_result_pro = 0;
                MyJeval.getOperator(sb_result_pro,"-",all_flag_pro[index_flag]);
                MyJeval.getOperator(sb_show_pro,"-",all_flag_pro[index_flag]);
                tv_show_pro.setText(sb_show_pro);
                flag_pro = "-"; operator = "-";
                index_flag++;all_flag_pro[index_flag] = flag_pro;
                index_op++;all_op_pro[index_op] = operator;setShowSize_pro(sb_show_pro);
                break;
            case R.id.tv_mult_pro:
                ifNullAddZero_pro();
                flag_result_pro = 0;
                MyJeval.getOperator(sb_result_pro,"*",all_flag_pro[index_flag]);
                MyJeval.getOperator(sb_show_pro,"*",all_flag_pro[index_flag]);
                tv_show_pro.setText(sb_show_pro);
                flag_pro = "*"; index_flag++;all_flag_pro[index_flag] = flag_pro;
                operator = "*";index_op++;all_op_pro[index_op] = operator;setShowSize_pro(sb_show_pro);
                break;
            case R.id.tv_div_pro:
                ifNullAddZero_pro();
                flag_result_pro = 0;
                MyJeval.getOperator(sb_result_pro,"/",all_flag_pro[index_flag]);
                MyJeval.getOperator(sb_show_pro,"/",all_flag_pro[index_flag]);
                tv_show_pro.setText(sb_show_pro);
                flag_pro = "/"; index_flag++;all_flag_pro[index_flag] = flag_pro;
                operator = "/";index_op++;all_op_pro[index_op] = operator;setShowSize_pro(sb_show_pro);
                break;
            case R.id.tv_point_pro:
                ifNullAddZero_pro();


                flag_pro = "num"; operator = ".";
                flag_result_pro = 0;
                sb_result_pro.append(".");
                tv_show_pro.setText(sb_show_pro.append("."));setShowSize_pro(sb_show_pro);
                break;

            case R.id.tv_clear_pro:
               clear_pro();setShowSize_pro(sb_show_pro);
                break;

                //TODO  有缺陷---试着把flag变成数组
            case R.id.tv_backspace_pro:
                if (sb_show_pro.length() != 0 && sb_result_pro.length() != 0) {

                    switch (sb_result_pro.charAt(sb_result_pro.length()-1)){
                        case '(':
                            flag_kuo_left_pro--;
                        case ')':
                            flag_kuo_right_pro--;

                    }
                    if(index_flag>=0 ){

                        all_flag_pro[index_flag] = "";
                        index_flag--;
                    }
                    if(index_op>=0){
                        all_op_pro[index_op] = "";index_op--;
                    }



                    tv_show_pro.setText(sb_show_pro.deleteCharAt(sb_show_pro.length()-1));
                    sb_result_pro.deleteCharAt(sb_result_pro.length()-1);
                    flag_result_pro = 0;


                    operator = all_op_pro[index_op];flag_pro = all_flag_pro[index_flag];
                }
                setShowSize_pro(sb_show_pro);
                break;
            case R.id.tv_equal_pro:
                try {
                    if (flag_kuo_left_pro >flag_kuo_right_pro) {
                        for (int i = 0; i < flag_kuo_left_pro - flag_kuo_right_pro; i++) {
                            sb_result_pro.append(")");
                        }

                    }
                    result_pro = evaluator_pro.evaluate(sb_result_pro.toString());
                    result_pro = resultToInt(result_pro);
                    tv_show_pro.setText(result_pro);
                    sb_result_pro = new StringBuilder();
                    sb_show_pro = new StringBuilder();
                    sb_result_pro.append(result_pro);
                    sb_show_pro.append(result_pro);
                    flag_result_pro = 1;
                    operator = "";
                    flag_pro = "num";

                    index_flag = -1;
                    all_flag_pro = new String[100];
                    for (int i = 0; i < sb_result_pro.length(); i++) {
                        index_flag++;all_flag_pro[index_flag] = flag_pro;
                    }
                    setShowSize_pro(sb_show_pro);

                } catch (EvaluationException ee) {
                    Toast.makeText(Test.this,"输入有误...", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.tv_quo_left_pro:
                flag_pro = "(";operator = "(";
                tv_show_pro.setText(sb_show_pro.append("("));
                sb_result_pro.append("(");
                flag_result_pro = 0;
                flag_kuo_left_pro++;
                setShowSize_pro(sb_show_pro);
                break;
            case R.id.tv_quo_right_pro:
                flag_pro = ")";operator = ")";
                tv_show_pro.setText(sb_show_pro.append(")"));
                sb_result_pro.append(")");
                flag_result_pro = 0;
                flag_kuo_right_pro++;setShowSize_pro(sb_show_pro);
                break;
            case R.id.tv_pi:
                if (flag_result_pro == 1) { clear_pro(); }
                flag_pro = "num";
                tv_show_pro.setText(sb_show_pro.append("π"));
                sb_result_pro.append(PPP);setShowSize_pro(sb_show_pro);
                break;

            case R.id.tv_e:
                if (flag_result_pro == 1) {
                   clear_pro();
                }
                flag_pro = "num";
                tv_show_pro.setText(sb_show_pro.append("e"));
                sb_result_pro.append(EEE);setShowSize_pro(sb_show_pro);
                break;


//TODO
            case R.id.tv_lg:

                break;
            case R.id.tv_tan:
                if (flag_result_pro == 1) {
                   clear_pro();
                }
                flag_pro = "(";
                tv_show_pro.setText(sb_show_pro.append("tan("));
                sb_result_pro.append("tan(");
                flag_kuo_left_pro++;setShowSize_pro(sb_show_pro);
                break;
            case R.id.tv_sin:
                if (flag_result_pro == 1) {
                   clear_pro();
                }
                flag_pro = "(";
                tv_show_pro.setText(sb_show_pro.append("sin("));
                sb_result_pro.append("sin(");
                flag_kuo_left_pro++;setShowSize_pro(sb_show_pro);
                break;
            case R.id.tv_cos:
                if (flag_result_pro == 1) {
                   clear_pro();
                }
                flag_pro = "(";
                tv_show_pro.setText(sb_show_pro.append("cos("));
                sb_result_pro.append("cos(");
                flag_kuo_left_pro++;setShowSize_pro(sb_show_pro);
                break;

            case R.id.tv_ln:
                if (flag_result_pro == 1) {
                    clear_pro();
                }
                flag_pro = "(";
                tv_show_pro.setText(sb_show_pro.append("log("));
                sb_result_pro.append("log(");
                flag_kuo_left_pro++;setShowSize_pro(sb_show_pro);
                break;
            case R.id.tv_sqrt:
                if (flag_result_pro == 1) {
                    clear_pro();
                }
                flag_pro = "(";
                tv_show_pro.setText(sb_show_pro.append("sqrt("));
                sb_result_pro.append("sqrt(");
                flag_kuo_left_pro++;setShowSize_pro(sb_show_pro);
                break;
                //TODO
            case R.id.tv_pow:
                flag_result_pro = 0;
                MyJeval.pow2(sb_result_pro,operator,flag_pro,flag_kuo_left_pro,flag_kuo_right_pro);
                MyJeval.pow2(sb_show_pro,operator,flag_pro,flag_kuo_left_pro,flag_kuo_right_pro);
                tv_show_pro.setText(sb_show_pro);
                flag_kuo_left_pro++;setShowSize_pro(sb_show_pro);
                break;
            case R.id.tv_ping:

                flag_result_pro = 0;
                MyJeval.pow2(sb_result_pro,operator,flag_pro,flag_kuo_left_pro,flag_kuo_right_pro);
                MyJeval.pow2(sb_show_pro,operator,flag_pro,flag_kuo_left_pro,flag_kuo_right_pro);
                sb_result_pro.append("2)");
                sb_show_pro.append("2)");
                tv_show_pro.setText(sb_show_pro);setShowSize_pro(sb_show_pro);
                //sb_result_pro.append("pow(");
                break;

        }
    }
    public void clear(){
        sb_show = new StringBuilder();
        sb_result = new StringBuilder();
        flag_kuo_left = 0;
        flag_kuo_right = 0;
        flag_result = 0;
        flag = "";
        all_op = new String[100];
        index = -1;
        tv_show.setText("");
    }
    public void ifNullAddZero(){
        if(index == -1){
            tv_show.setText("");
            index++; all_op[index] = "num";
            sb_result.append("0");
            sb_show.append("0");
        }
    }
    //如果结果是整数则取整
    public String resultToInt(String num){


        double number2 = Double.parseDouble(num);//類型轉換
        if(Math.round(number2)-number2 == 0){
            return String.valueOf((long)number2);
        }
        return String.valueOf(number2);
    }
    //动态显示结果
    public void setShowSize(StringBuilder show) {
        if (show.length()<9) {
            tv_show.setTextSize(80);
        }else if(show.length()<11){
            tv_show.setTextSize(60);
        }else if(show.length()<16){
            tv_show.setTextSize(40);
        }else {
            tv_show.setTextSize(30);
        }
    }
    public void setShowSize_pro(StringBuilder show) {
        if (show.length()<9) {
            tv_show_pro.setTextSize(80);
        }else if(show.length()<11){
            tv_show_pro.setTextSize(60);
        }else if(show.length()<16){
            tv_show_pro.setTextSize(40);
        }else {
            tv_show_pro.setTextSize(30);
        }
    }
    public void clear_pro(){
        tv_show_pro.setText("");
        sb_show_pro = new StringBuilder();
        sb_result_pro = new StringBuilder();
        flag_kuo_left_pro = 0;
        flag_kuo_right_pro = 0;
        flag_result_pro = 0;
        flag_pro = "";operator = "";
    }
    public void ifNullAddZero_pro(){
        if(index_flag == -1){
            index_flag++; all_flag_pro[index_flag] = "num";
            sb_result_pro.append("0");
            sb_show_pro.append("0");
        }
    }

    public void setTv_show(String flag){

    }
}
