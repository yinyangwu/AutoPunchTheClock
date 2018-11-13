import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

/**
 * Desc:自动打卡程序 for 钉钉v4.5.10
 * <p>
 * Created by YoungWu on 2018/9/14.
 */
public class AutoPunchTheClock {

    public static void main(String args[]) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //第一步，在早上8点30分到8点55分随机构建一个初始的打卡时间
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINESE);
                Calendar specifiedCalendar = Calendar.getInstance();
                long startTime = specifiedCalendar.getTimeInMillis();
                Random random = new Random(startTime);
                int randomNumber = random.nextInt(25);
                int specifiedHour = 8;
                int specifiedMinute = randomNumber + 30;
                specifiedCalendar.set(Calendar.HOUR_OF_DAY, specifiedHour);
                specifiedCalendar.set(Calendar.MINUTE, specifiedMinute);
                long specifiedTime = specifiedCalendar.getTimeInMillis();
                if (startTime > specifiedTime) {
                    specifiedCalendar.add(Calendar.DAY_OF_MONTH, 1);
                }
                int specifiedDay = specifiedCalendar.get(Calendar.DAY_OF_MONTH);
                System.out.println("初始设定打卡时间：" + sdf.format(specifiedCalendar.getTime()));

                Calendar currentCalendar = Calendar.getInstance();
                while (true) {
                    //第二步，获取当前时间判断是不是等于打卡时间
                    long currentTime = System.currentTimeMillis();
                    currentCalendar.setTimeInMillis(currentTime);
                    System.out.println("当前时间：" + sdf.format(currentCalendar.getTime()));
                    int currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH);
                    int currentHour = currentCalendar.get(Calendar.HOUR_OF_DAY);
                    int currentMinute = currentCalendar.get(Calendar.MINUTE);
                    if (currentDay != specifiedDay || currentHour != specifiedHour || currentMinute != specifiedMinute) {
                        System.out.println("当前还不满足打卡要求");
                    } else {
                        try {
                            //第三步，如果满足打卡条件则执行打卡步骤
                            System.out.println("正在打卡...");
                            Process p1 = Runtime.getRuntime().exec("adb shell input tap 360 1216");
                            p1.waitFor();
                            Thread.sleep(3000);
                            Process p2 = Runtime.getRuntime().exec("adb shell input tap 270 842");
                            p2.waitFor();
                            Thread.sleep(10000);
                            Process p3 = Runtime.getRuntime().exec("adb shell input tap 360 525");
                            p3.waitFor();
                            Thread.sleep(10000);
                            Process p4 = Runtime.getRuntime().exec("adb shell input keyevent 4");
                            p4.waitFor();
                            Thread.sleep(3000);
                            Process p5 = Runtime.getRuntime().exec("adb shell input keyevent 4");
                            p5.waitFor();
                            Thread.sleep(3000);
                            Process p6 = Runtime.getRuntime().exec("adb shell input tap 70 1220");
                            p6.waitFor();
                            System.out.println("打卡成功");
                            //第四步，打卡成功后重置新的打卡时间
                            long resetTime = System.currentTimeMillis();
                            specifiedCalendar.setTimeInMillis(resetTime);
                            randomNumber = random.nextInt(25);
                            specifiedMinute = randomNumber + 30;
                            specifiedCalendar.set(Calendar.HOUR_OF_DAY, specifiedHour);
                            specifiedCalendar.set(Calendar.MINUTE, specifiedMinute);
                            specifiedCalendar.add(Calendar.DAY_OF_MONTH, 1);
                            specifiedDay = specifiedCalendar.get(Calendar.DAY_OF_MONTH);
                            System.out.println("已重置打卡时间：" + sdf.format(specifiedCalendar.getTime()));
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                            System.out.println("打卡失败");
                            break;
                        }
                    }
                    //第五步，睡眠30秒钟又循环判断当前时间
                    try {
                        Thread.sleep(30 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }).start();
    }
}
