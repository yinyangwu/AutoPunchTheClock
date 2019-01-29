# AutoPunchTheClock
Java实现自动打卡程序 for 钉钉v4.5.10

准备
------
1，把手机的开发者模式和USB调试开关都打开，并且设置不锁定屏幕，再确保已连上电脑<br>
2，把项目pull到本地，git clone https://github.com/yinyangwu/AutoPunchTheClock.git<br>
3，下载钉钉APP，当前项目是用v4.5.10，手机分辨率720*1280测试的，建议保持一致<br>
4，打开钉钉，登录成功后保持在主页面，对应底下的tab按钮是【消息】<br>
5，打开命令面板，进入项目所在本地目录，执行java AutoPunchTheClock即可自动进行打卡

实现方式
------
1，在早上8点30分到8点55分随机设定一个初始的打卡时间<br>
2，获取当前时间并且判断是不是到了设定的打卡时间<br>
3，如果满足打卡条件则执行打卡步骤<br>
4，打卡成功后重置新的打卡时间<br>
5，睡眠30秒钟又循环判断当前时间

实现原理
------
利用adb命令模拟触摸屏幕，常用的命令方式如下：

adb shell input tap \<x> \<y><br>
adb shell input keyevent \<key code number or name><br>
adb shell input swipe \<x1> \<y1> \<x2> \<y2><br>
adb shell input text \<string><br>

每个数字与keycode对应表如下：<br>

0 -->  "KEYCODE_UNKNOWN"<br>
1 -->  "KEYCODE_MENU"<br>
2 -->  "KEYCODE_SOFT_RIGHT"<br>
3 -->  "KEYCODE_HOME"<br>
4 -->  "KEYCODE_BACK"<br>
5 -->  "KEYCODE_CALL"<br>
6 -->  "KEYCODE_ENDCALL"<br>
7 -->  "KEYCODE_0"<br>
8 -->  "KEYCODE_1"<br>
9 -->  "KEYCODE_2"<br>
10 -->  "KEYCODE_3"<br>
11 -->  "KEYCODE_4"<br>
12 -->  "KEYCODE_5"<br>
13 -->  "KEYCODE_6"<br>
14 -->  "KEYCODE_7"<br>
15 -->  "KEYCODE_8"<br>
16 -->  "KEYCODE_9"<br>
17 -->  "KEYCODE_STAR"<br>
18 -->  "KEYCODE_POUND"<br>
19 -->  "KEYCODE_DPAD_UP"<br>
20 -->  "KEYCODE_DPAD_DOWN"<br>
21 -->  "KEYCODE_DPAD_LEFT"<br>
22 -->  "KEYCODE_DPAD_RIGHT"<br>
23 -->  "KEYCODE_DPAD_CENTER"<br>
24 -->  "KEYCODE_VOLUME_UP"<br>
25 -->  "KEYCODE_VOLUME_DOWN"<br>
26 -->  "KEYCODE_POWER"<br>
27 -->  "KEYCODE_CAMERA"<br>
28 -->  "KEYCODE_CLEAR"<br>
29 -->  "KEYCODE_A"<br>
30 -->  "KEYCODE_B"<br>
31 -->  "KEYCODE_C"<br>
32 -->  "KEYCODE_D"<br>
33 -->  "KEYCODE_E"<br>
34 -->  "KEYCODE_F"<br>
35 -->  "KEYCODE_G"<br>
36 -->  "KEYCODE_H"<br>
37 -->  "KEYCODE_I"<br>
38 -->  "KEYCODE_J"<br>
39 -->  "KEYCODE_K"<br>
40 -->  "KEYCODE_L"<br>
41 -->  "KEYCODE_M"<br>
42 -->  "KEYCODE_N"<br>
43 -->  "KEYCODE_O"<br>
44 -->  "KEYCODE_P"<br>
45 -->  "KEYCODE_Q"<br>
46 -->  "KEYCODE_R"<br>
47 -->  "KEYCODE_S"<br>
48 -->  "KEYCODE_T"<br>
49 -->  "KEYCODE_U"<br>
50 -->  "KEYCODE_V"<br>
51 -->  "KEYCODE_W"<br>
52 -->  "KEYCODE_X"<br>
53 -->  "KEYCODE_Y"<br>
54 -->  "KEYCODE_Z"<br>
55 -->  "KEYCODE_COMMA"<br>
56 -->  "KEYCODE_PERIOD"<br>
57 -->  "KEYCODE_ALT_LEFT"<br>
58 -->  "KEYCODE_ALT_RIGHT"<br>
59 -->  "KEYCODE_SHIFT_LEFT"<br>
60 -->  "KEYCODE_SHIFT_RIGHT"<br>
61 -->  "KEYCODE_TAB"<br>
62 -->  "KEYCODE_SPACE"<br>
63 -->  "KEYCODE_SYM"<br>
64 -->  "KEYCODE_EXPLORER"<br>
65 -->  "KEYCODE_ENVELOPE"<br>
66 -->  "KEYCODE_ENTER"<br>
67 -->  "KEYCODE_DEL"<br>
68 -->  "KEYCODE_GRAVE"<br>
69 -->  "KEYCODE_MINUS"<br>
70 -->  "KEYCODE_EQUALS"<br>
71 -->  "KEYCODE_LEFT_BRACKET"<br>
72 -->  "KEYCODE_RIGHT_BRACKET"<br>
73 -->  "KEYCODE_BACKSLASH"<br>
74 -->  "KEYCODE_SEMICOLON"<br>
75 -->  "KEYCODE_APOSTROPHE"<br>
76 -->  "KEYCODE_SLASH"<br>
77 -->  "KEYCODE_AT"<br>
78 -->  "KEYCODE_NUM"<br>
79 -->  "KEYCODE_HEADSETHOOK"<br>
80 -->  "KEYCODE_FOCUS"<br>
81 -->  "KEYCODE_PLUS"<br>
82 -->  "KEYCODE_MENU"<br>
83 -->  "KEYCODE_NOTIFICATION"<br>
84 -->  "KEYCODE_SEARCH"<br>
85 -->  "TAG_LAST_KEYCODE"<br>
