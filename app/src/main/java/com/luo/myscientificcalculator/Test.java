package com.luo.myscientificcalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

import java.util.ArrayList;
import java.util.List;

public class Test extends Activity implements View.OnClickListener,ViewPager.OnPageChangeListener, View.OnLongClickListener {

    private SharedPreferences.Editor editor;
    private ViewPager viewPager;
    private ImageView img_cursor;
    private TextView tv_simple;
    private TextView tv_scientific;
    private TextView tv_matrix;

    private ArrayList<View> listViews;
    private int offset = 0;//移动条图片的偏移量
    private int currIndex = 0;//当前页面的编号
    private int bmpWidth;// 移动条图片的长度
    private int one = 0; //移动条滑动一页的距离
    private int two = 0; //滑动条移动两页的距离

    private View view1,view2,view3 ;


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

    /**
     * *******************************************    矩阵
     */
    //下拉栏
    private Spinner spinner_row_a, spinner_column_a,spinner_row_b, spinner_column_b;

    private TextView tv_result,tv_equal_ma,tv_add_ma,tv_sub_ma,tv_mult_ma,tv_sovle,tv_sovleTran,tv_clearAll,
            tv_A ,tv_B ,tv_analysisA,tv_analysisB;
    private EditText et_a,et_b,et_inputA,et_inputB;
    private String input_a,input_b;
    private List<String> row_a = new ArrayList<>();
    private List<String> column_a = new ArrayList<>();
    private List<String> row_b = new ArrayList<>();
    private List<String> column_b = new ArrayList<>();
    private static int num_row_a=1,num_row_b=1,num_column_a=1,num_column_b=1;//a b行列的数量
    private  int flag_ma = 0;                                                  //加减乘除的flag
    private double[][] result_ma,m,n,eigD,eigV,inverse,transpose;              // 结果
    private double rank,det;                                                //秩和行列式
    private static int num = 3;                                              //小数点保留位数
    private static boolean which_mode = false;                              //是否手动输入模式
    private static boolean isVibrate = false;                               //是否触摸反馈

    //输入框的id
    private LinearLayout linear_a1,linear_a2,linear_a3,linear_a4,linear_a5,
            linear_b1,linear_b2,linear_b3,linear_b4,linear_b5,linear_a,linear_b;
    static double id_a[][] = new double[5][5];
    static double id_b[][] = new double[5][5];
    static double data_a[][] = new double[5][5];
    static double data_b[][] = new double[5][5];
    Vibrator vibrator;


    private DrawerLayout drawerLayout;


    //获取小数点保留位数，以及是否触摸反馈
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("data_num", MODE_PRIVATE);
        num = preferences.getInt("num",2);
        which_mode = preferences.getBoolean("isChecked", false);
        isVibrate = preferences.getBoolean("isVibrate", false);


        //判断是哪种模式,并显示
        //which_mode == true ? View.GONE :View.VISIBLE
        linear_a.setVisibility(which_mode == true ? View.GONE : View.VISIBLE);
        linear_b.setVisibility(which_mode == true ? View.GONE : View.VISIBLE);
        et_inputA.setVisibility(which_mode == true ? View.VISIBLE : View.GONE);
        et_inputB.setVisibility(which_mode == true ? View.VISIBLE : View.GONE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
         //初始化
        initView();
        computeMatrix();


    }

    @Override
    public void onClick(View view){
        computeSimple(view.getId());
        computeScientific(view.getId());


        switch (view.getId()){
            case R.id.tv_simple:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_sicentific:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv_matrix:
                viewPager.setCurrentItem(2);
                break;


            case R.id.main_A:
                Vibrate();Unit(et_a,id_a);
                break;
            case R.id.main_B:
                Vibrate();Unit(et_b,id_b);
                break;

            case R.id.main_tv_add:
                Vibrate();
                flag_ma = 1;
                ChangeOperatorColor(flag_ma,tv_add_ma,tv_sub_ma,tv_mult_ma,tv_sovle,tv_sovleTran);break;
            case R.id.main_tv_sub:
                Vibrate();
                flag_ma = 2;
                ChangeOperatorColor(flag_ma,tv_add_ma,tv_sub_ma,tv_mult_ma,tv_sovle,tv_sovleTran);break;
            case R.id.main_tv_mult:
                Vibrate();
                flag_ma = 3;
                ChangeOperatorColor(flag_ma,tv_add_ma,tv_sub_ma,tv_mult_ma,tv_sovle,tv_sovleTran);break;
            case R.id.main_sovle:
                Vibrate();
                flag_ma = 4;
                ChangeOperatorColor(flag_ma,tv_add_ma,tv_sub_ma,tv_mult_ma,tv_sovle,tv_sovleTran);break;
            case R.id.main_sovleTran:
                Vibrate();
                flag_ma = 5;
                ChangeOperatorColor(flag_ma,tv_add_ma,tv_sub_ma,tv_mult_ma,tv_sovle,tv_sovleTran);break;

            case R.id.main_clearAll:
                Vibrate();
                flag_ma = 0;
                ChangeOperatorColor(flag_ma,tv_add_ma,tv_sub_ma,tv_mult_ma,tv_sovle,tv_sovleTran);

                spinner_column_a.setSelection(0);
                spinner_column_b.setSelection(0);
                spinner_row_a.setSelection(0);
                spinner_row_b.setSelection(0);

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        et_a = (EditText) findViewById((int)id_a[i][j]);
                        et_b = (EditText) findViewById((int)id_b[i][j]);
                        et_a.setText("0");
                        et_b.setText("0");
                    }
                }
                et_inputA.setText("");
                et_inputB.setText("");
                tv_result.setText("0  0\n0  0");break;



            case R.id.main_analysisA:
                Vibrate();
                    //把数据都放入data_a，b中,五行五列,此时还有好多0呢！！！需要处理！
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            et_a = (EditText) findViewById((int)id_a[i][j]);
                            data_a[i][j] = Double.parseDouble(et_a.getText().toString());
                        }
                    }
                    try {
                        //先处理数据ProData，切割数据
                        m = MyJama.ProData(data_a,num_row_a,num_column_a);

                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(Test.this,"输入错误！1", Toast.LENGTH_SHORT).show();
                    }

                    try{
                        Analysis(m);
                        break;
                    }catch (Exception e) {
                        Toast.makeText(Test.this,"输入有误...2",Toast.LENGTH_SHORT).show();
                        break;
                    }



            case R.id.main_analysisB:
                Vibrate();

                    //把数据都放入data_a，b中,五行五列,此时还有好多0呢！！！需要处理！
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            et_b = (EditText) findViewById((int)id_b[i][j]);
                            data_b[i][j] = Double.parseDouble(et_b.getText().toString());
                        }
                    }
                    try {
                        //先处理数据ProData，切割数据
                        n = MyJama.ProData(data_b,num_row_b,num_column_b);

                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(Test.this,"输入错误！1", Toast.LENGTH_SHORT).show();
                    }

                    try{
                        Analysis(n);
                        break;
                    }catch (Exception e) {
                        Toast.makeText(Test.this,"输入有误...2",Toast.LENGTH_SHORT).show();
                        break;
                    }

        }
    }







    @Override
    public void onPageSelected(int index) {
        Animation animation = null;
        switch (index) {
            case 0:
                tv_simple.setTextColor(Color.parseColor("#86C0EE"));
                tv_scientific.setTextColor(Color.parseColor("#000000"));
                tv_matrix.setTextColor(Color.parseColor("#000000"));
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, 0, 0, 0);
                }
                break;
            case 1:
                tv_scientific.setTextColor(Color.parseColor("#86C0EE"));
                tv_simple.setTextColor(Color.parseColor("#000000"));
                tv_matrix.setTextColor(Color.parseColor("#000000"));
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                }
                break;
            case 2:
                tv_scientific.setTextColor(Color.parseColor("#000000"));
                tv_simple.setTextColor(Color.parseColor("#000000"));
                tv_matrix.setTextColor(Color.parseColor("#86C0EE"));
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, two, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(one, two, 0, 0);
                }
                break;
        }
        currIndex = index;
        animation.setFillAfter(true);// true表示图片停在动画结束位置
        animation.setDuration(30); //设置动画时间为300毫秒
        img_cursor.startAnimation(animation);//开始动画
    }
    @Override
    public void onPageScrollStateChanged(int i) { }
    @Override
    public void onPageScrolled(int i, float v, int i1) { }



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
    public void initView(){

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tv_simple = (TextView) findViewById(R.id.tv_simple);
        tv_scientific = (TextView) findViewById(R.id.tv_sicentific);
        tv_matrix = (TextView) findViewById(R.id.tv_matrix);
        img_cursor = (ImageView) findViewById(R.id.img_cursor);

        //下划线动画的相关设置：
        bmpWidth = BitmapFactory.decodeResource(getResources(), R.drawable.m2).getWidth();// 获取图片宽度
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
        view1 = inflater.inflate(R.layout.simple, null);
        view2 = inflater.inflate(R.layout.scientific, null);
        view3 = inflater.inflate(R.layout.matrix, null);
        listViews = new ArrayList<View>();
        //添加想要切换的界面
        listViews.add(view1);
        listViews.add(view2);
        listViews.add(view3);
        viewPager.setAdapter(new MyPagerAdapter(listViews));
        viewPager.setCurrentItem(0);          //设置ViewPager当前页，从0开始算
        tv_simple.setTextColor(Color.parseColor("#86C0EE"));
        tv_simple.setOnClickListener(this);
        tv_scientific.setOnClickListener(this);
        tv_matrix.setOnClickListener(this);

        viewPager.addOnPageChangeListener(this);








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




        SharedPreferences preferences = getSharedPreferences("data_num", MODE_PRIVATE);
        num = preferences.getInt("num",2);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);


        //把输入框的地址全放入数组中
        id_a[0][0] = R.id.a_00;
        id_a[0][1] = R.id.a_01;
        id_a[0][2] = R.id.a_02;
        id_a[0][3] = R.id.a_03;
        id_a[0][4] = R.id.a_04;
        id_a[1][0] = R.id.a_10;
        id_a[1][1] = R.id.a_11;
        id_a[1][2] = R.id.a_12;
        id_a[1][3] = R.id.a_13;
        id_a[1][4] = R.id.a_14;
        id_a[2][0] = R.id.a_20;
        id_a[2][1] = R.id.a_21;
        id_a[2][2] = R.id.a_22;
        id_a[2][3] = R.id.a_23;
        id_a[2][4] = R.id.a_24;
        id_a[3][0] = R.id.a_30;
        id_a[3][1] = R.id.a_31;
        id_a[3][2] = R.id.a_32;
        id_a[3][3] = R.id.a_33;
        id_a[3][4] = R.id.a_34;
        id_a[4][0] = R.id.a_40;
        id_a[4][1] = R.id.a_41;
        id_a[4][2] = R.id.a_42;
        id_a[4][3] = R.id.a_43;
        id_a[4][4] = R.id.a_44;



        id_b[0][0] = R.id.b_00;
        id_b[0][1] = R.id.b_01;
        id_b[0][2] = R.id.b_02;
        id_b[0][3] = R.id.b_03;
        id_b[0][4] = R.id.b_04;
        id_b[1][0] = R.id.b_10;
        id_b[1][1] = R.id.b_11;
        id_b[1][2] = R.id.b_12;
        id_b[1][3] = R.id.b_13;
        id_b[1][4] = R.id.b_14;
        id_b[2][0] = R.id.b_20;
        id_b[2][1] = R.id.b_21;
        id_b[2][2] = R.id.b_22;
        id_b[2][3] = R.id.b_23;
        id_b[2][4] = R.id.b_24;
        id_b[3][0] = R.id.b_30;
        id_b[3][1] = R.id.b_31;
        id_b[3][2] = R.id.b_32;
        id_b[3][3] = R.id.b_33;
        id_b[3][4] = R.id.b_34;
        id_b[4][0] = R.id.b_40;
        id_b[4][1] = R.id.b_41;
        id_b[4][2] = R.id.b_42;
        id_b[4][3] = R.id.b_43;
        id_b[4][4] = R.id.b_44;

        linear_a1 = (LinearLayout) view3.findViewById(R.id.linear_a_1);
        linear_a2 = (LinearLayout) view3.findViewById(R.id.linear_a_2);
        linear_a3 = (LinearLayout) view3.findViewById(R.id.linear_a_3);
        linear_a4 = (LinearLayout) view3.findViewById(R.id.linear_a_4);
        linear_a5 = (LinearLayout) view3.findViewById(R.id.linear_a_5);

        linear_b1 = (LinearLayout) view3.findViewById(R.id.linear_b_1);
        linear_b2 = (LinearLayout) view3.findViewById(R.id.linear_b_2);
        linear_b3 = (LinearLayout) view3.findViewById(R.id.linear_b_3);
        linear_b4 = (LinearLayout) view3.findViewById(R.id.linear_b_4);
        linear_b5 = (LinearLayout) view3.findViewById(R.id.linear_b_5);


        linear_a = (LinearLayout) view3.findViewById(R.id.linear_a);
        linear_b = (LinearLayout) view3.findViewById(R.id.linear_b);

        et_inputA = (EditText) view3.findViewById(R.id.input_a);
        et_inputB = (EditText) view3.findViewById(R.id.input_b);



        tv_result = (TextView) view3.findViewById(R.id.main_tv_result);
        tv_add_ma =(TextView) view3.findViewById(R.id.main_tv_add);
        tv_sub_ma = (TextView) view3.findViewById(R.id.main_tv_sub);
        tv_mult_ma = (TextView) view3.findViewById(R.id.main_tv_mult);
        tv_equal_ma = (TextView) view3.findViewById(R.id.main_tv_equal);
        tv_sovle = (TextView) view3.findViewById(R.id.main_sovle);
        tv_sovleTran = (TextView) view3.findViewById(R.id.main_sovleTran);
        tv_clearAll = (TextView) view3.findViewById(R.id.main_clearAll);
        tv_A = (TextView) view3.findViewById(R.id.main_A);
        tv_B = (TextView) view3.findViewById(R.id.main_B);
        tv_analysisA = (TextView) view3.findViewById(R.id.main_analysisA);
        tv_analysisB = (TextView) view3.findViewById(R.id.main_analysisB);


        tv_add_ma.setOnClickListener(this);
        tv_sub_ma.setOnClickListener(this);
        tv_mult_ma.setOnClickListener(this);
        tv_sovle.setOnClickListener(this);
        tv_sovleTran.setOnClickListener(this);
        tv_clearAll.setOnClickListener(this);
        tv_analysisA.setOnClickListener(this);
        tv_analysisB.setOnClickListener(this);
        tv_B.setOnClickListener(this);
        tv_A.setOnClickListener(this);
        tv_A.setOnLongClickListener(this);
        tv_B.setOnLongClickListener(this);


        //设置下拉栏属性
        spinner_row_a = (Spinner) view3.findViewById(R.id.row_a);
        spinner_column_a = (Spinner) view3.findViewById(R.id.column_a);
        spinner_row_b = (Spinner) view3.findViewById(R.id.row_b);
        spinner_column_b = (Spinner) view3.findViewById(R.id.column_b);
        row_a.add("1行");
        row_a.add("2行");
        row_a.add("3行");
        row_a.add("4行");
        row_a.add("5行");
        column_a.add("1列");
        column_a.add("2列");
        column_a.add("3列");
        column_a.add("4列");
        column_a.add("5列");
        row_b.add("1行");
        row_b.add("2行");
        row_b.add("3行");
        row_b.add("4行");
        row_b.add("5行");
        column_b.add("1列");
        column_b.add("2列");
        column_b.add("3列");
        column_b.add("4列");
        column_b.add("5列");

        ArrayAdapter<String> adapter_row_a =
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,row_a);
        ArrayAdapter<String> adapter_column_a =
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,column_a);

        ArrayAdapter<String> adapter_row_b =
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,row_b);
        ArrayAdapter<String> adapter_column_b =
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,column_b);


        adapter_row_a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_row_a.setAdapter(adapter_row_a);
        adapter_column_a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_column_a.setAdapter(adapter_column_a);

        adapter_row_b.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_row_b.setAdapter(adapter_row_b);
        adapter_column_b.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_column_b.setAdapter(adapter_column_b);


        //下拉栏点击事件
        spinner_row_a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                num_row_a = position + 1;
                Check_ChangeColor();

//-----------------------------------------------------------------------------


                //控件的出现与消失
                //裁剪矩阵
                int[][] m = MyJama.FindId(id_a,num_row_a,num_column_a);
                EditText test;
                InputShow_linearlayout(num_column_a,linear_a1,linear_a2,linear_a3,linear_a4,linear_a5);

                //让控件全部GONE---初始化
                Input_gone(id_a);
                //让需要的控件VISIBLE
                Input_visible(m);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner_column_a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                num_column_a = position + 1;
                Check_ChangeColor();

//-----------------------------------------------------------------------------

                //控件的出现与消失

                int[][] m = MyJama.FindId(id_a,num_row_a,num_column_a);
                EditText test;
                InputShow_linearlayout(num_column_a,linear_a1,linear_a2,linear_a3,linear_a4,linear_a5);

                //让控件全部GONE---初始化
                Input_gone(id_a);
                //让需要的控件VISIBLE
                Input_visible(m);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner_row_b.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                num_row_b = position+1;

//-----------------------------------------------------------------------------

                //控件的出现与消失

                int[][] m = MyJama.FindId(id_b,num_row_b,num_column_b);
                EditText test;
                InputShow_linearlayout(num_column_b,linear_b1,linear_b2,linear_b3,linear_b4,linear_b5);

                //让控件全部GONE---初始化
                Input_gone(id_b);
                //让需要的控件VISIBLE
                Input_visible(m);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner_column_b.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                num_column_b = position+1;
                Check_ChangeColor();

//-----------------------------------------------------------------------------

                //控件的出现与消失
                int[][] m = MyJama.FindId(id_b,num_row_b,num_column_b);
                EditText test;
                InputShow_linearlayout(num_column_b,linear_b1,linear_b2,linear_b3,linear_b4,linear_b5);

                //让控件全部GONE---初始化
                Input_gone(id_b);
                //让需要的控件VISIBLE
                Input_visible(m);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



    }
    public void computeSimple(int id){
        switch (id){

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


        }
    }
    public void computeScientific(int id){
        switch (id){

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
    public void computeMatrix(){

/**
 *  //得出结果
 */
        tv_equal_ma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isVibrate){
                    vibrator.vibrate(10);
                }

                if (flag_ma == 0) {
                    Toast.makeText(Test.this,"还没有点运算符号呢！", Toast.LENGTH_SHORT).show();
                }else {


                        //把数据都放入data_a，b中,五行五列,此时还有好多0呢！！！需要处理！
                        // EditText et_a ,et_b;
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 5; j++) {
                                et_a = (EditText) findViewById((int)id_a[i][j]);
                                et_b = (EditText) findViewById((int)id_b[i][j]);
                                data_a[i][j] = Double.parseDouble(et_a.getText().toString());
                                data_b[i][j] = Double.parseDouble(et_b.getText().toString());
                            }
                        }
                        try {
                            //先处理数据ProData，切割数据
                            //计算结果；
                            result_ma = MyJama.getResult(MyJama.ProData(data_a,num_row_a,num_column_a), MyJama.ProData(data_b,num_row_b,num_column_b), flag_ma);
                            tv_result.setText(MyJama.output(result_ma,num).toString());


                        }catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(Test.this,"输入错误！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        });

    }
    //生成单位矩阵
    public void Unit(EditText et,double[][] id){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == j) {
                    et = (EditText) findViewById((int)id[i][j]);
                    et.setText("1");
                }
            }
        }
    }
    @Override
    public boolean onLongClick(View v) {
        return false;
    }


    //触摸反馈
    public void Vibrate(){
        if(isVibrate){
            vibrator.vibrate(10);
        }
    }
    //改变操作符颜色
    public void ChangeOperatorColor(int f, TextView add, TextView sub, TextView mult, TextView sovle, TextView sovleTran){

        switch (f){
            case 0:
                add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sovle.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sovleTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                break;
            case 1:
                add.setBackgroundColor(Color.parseColor("#86C0EE"));
                sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sovle.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sovleTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                break;
            case 2:
                add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sub.setBackgroundColor(Color.parseColor("#86C0EE"));
                mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sovle.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sovleTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                break;
            case 3:
                add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                mult.setBackgroundColor(Color.parseColor("#86C0EE"));
                sovle.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sovleTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                break;
            case 4:
                add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sovle.setBackgroundColor(Color.parseColor("#86C0EE"));
                sovleTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                break;
            case 5:
                add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sovle.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sovleTran.setBackgroundColor(Color.parseColor("#86C0EE"));
                break;
        }

    }
    //屏蔽不可进行的操作
    public void Check_ChangeColor(){
        if (num_row_a != num_row_b || num_column_a != num_column_b) {
            tv_add_ma.setBackgroundColor(Color.parseColor("#F3978F"));
            tv_sub_ma.setBackgroundColor(Color.parseColor("#F3978F"));
            tv_add_ma.setClickable(false);
            tv_sub_ma.setClickable(false);
        }else {
            tv_add_ma.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
            tv_sub_ma.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
            tv_add_ma.setClickable(true);
            tv_sub_ma.setClickable(true);
        }
        if (num_row_a != num_column_b || num_column_a != num_row_b ) {
            tv_mult_ma.setBackgroundColor(Color.parseColor("#F3978F"));
            tv_mult_ma.setClickable(false);
        }else {
            tv_mult_ma.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
            tv_mult_ma.setClickable(true);
        }
        //----------A*X=B----ac*cb=ab
        if (num_row_a  != num_row_b  ) {
            tv_sovle.setBackgroundColor(Color.parseColor("#F3978F"));
            tv_sovle.setClickable(false);
        }else {
            tv_sovle.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
            tv_sovle.setClickable(true);
        }
        //----------X*A=B----ac*cb=ab
        if (num_column_a  != num_column_b  ) {
            tv_sovleTran.setBackgroundColor(Color.parseColor("#F3978F"));
            tv_sovleTran.setClickable(false);
        }else {
            tv_sovleTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
            tv_sovleTran.setClickable(true);
        }
    }
    //输入控件的出现与消失
    public void InputShow_linearlayout(int num, LinearLayout l1, LinearLayout l2, LinearLayout l3, LinearLayout l4, LinearLayout l5){
        switch (num){
            case 1:
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.GONE);
                break;
            case 2:
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.VISIBLE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.GONE);
                break;
            case 3:
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.VISIBLE);
                l3.setVisibility(View.VISIBLE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.GONE);
                break;
            case 4:
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.VISIBLE);
                l3.setVisibility(View.VISIBLE);
                l4.setVisibility(View.VISIBLE);
                l5.setVisibility(View.GONE);
                break;
            case 5:
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.VISIBLE);
                l3.setVisibility(View.VISIBLE);
                l4.setVisibility(View.VISIBLE);
                l5.setVisibility(View.VISIBLE);
                break;


        }
    }
    //初始化控件，使之全部消失
    public void Input_gone(double[][] id){
        EditText test;
        for (int i = 0; i < id.length; i++) {
            for (int j = 0; j < id[i].length; j++) {
                test = (EditText) findViewById((int)id[i][j]);
                test.setVisibility(View.GONE);
            }
        }
    }
    //显示需要的空间
    public void Input_visible(int [][] id){
        EditText test;
        for (int i = 0; i < id.length; i++) {
            for (int j = 0; j < id[i].length; j++) {
                test = (EditText) findViewById(id[i][j]);
                test.setVisibility(View.VISIBLE);
            }
        }

    }

    //矩阵分析
    public void Analysis(double [][] m){

        Intent intent = new Intent(Test.this, Analysis.class);

        rank = MyJama.matrixRank(m);
        det = MyJama.matrixDet(m);
        transpose = MyJama.matrixTranspose(m);
        eigD = MyJama.matrixEigD(m);
        eigV = MyJama.matrixEigV(m);

        //二维变一维
        double[] tranOne = MyJama.TwotoOne(transpose);
        double[] eigDOne = MyJama.TwotoOne(eigD);
        double[] eigVOne = MyJama.TwotoOne(eigV);


        intent.putExtra("rank",rank);
        intent.putExtra("det", det);
        intent.putExtra("transpose", tranOne);
        intent.putExtra("eigD", eigDOne);
        intent.putExtra("eigV", eigVOne);

        if(det != 0){
            inverse = MyJama.matrixInverse(m);
            double[] inverseOne = MyJama.TwotoOne(inverse);
            intent.putExtra("inverse", inverseOne);
        }

        startActivity(intent);
    }


}
