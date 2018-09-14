import java.io.IOException;
import java.text.SimpleDateFormat;
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
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINESE);
                long startTime = System.currentTimeMillis();
                Random random = new Random(startTime);
                int randomNumber = random.nextInt(25);
                int specifiedHour = 8;
                int specifiedMinute = randomNumber + 30;
                boolean result = false;
                System.out.println("初始打卡时间:" + specifiedHour + ":" + specifiedMinute);

                while (true) {
                    //第二步，获取当前时间如果超过启动时间24小时则重置打卡结果
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - startTime > 24 * 60 * 60 * 1000) {
                        startTime = currentTime;
                        result = false;
                        System.out.println("超过24小时，已重置打卡结果");
                    }
                    if (!result) {
                        //第三步，如果未打卡则判断当前时间是不是等于打卡时间
                        String strCurrentTime = sdf.format(currentTime);
                        System.out.println("当前时间:" + strCurrentTime);
                        String currentTimeArray[] = strCurrentTime.split(":");
                        String hour = currentTimeArray[0];
                        String minute = currentTimeArray[1];
                        if (Integer.parseInt(hour) != specifiedHour || Integer.parseInt(minute) != specifiedMinute) {
                            System.out.println("当前还不满足打卡要求");
                        } else {
                            try {
                                //第四步，如果满足打卡条件则执行打卡步骤
                                System.out.println("当前已满足打卡要求");
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
                                result = true;
                                System.out.println("打卡成功");
                                //第五步，打卡成功后重置新的打卡时间
                                randomNumber = random.nextInt(25);
                                specifiedMinute = randomNumber + 30;
                                System.out.println("已重置打卡时间:" + specifiedHour + ":" + specifiedMinute);
                            } catch (IOException | InterruptedException e) {
                                e.printStackTrace();
                                break;
                            }
                        }
                    }
                    //第六步，如果打卡成功则睡眠10分钟，否则就再过10秒钟又循环判断当前时间
                    try {
                        if (result) {
                            Thread.sleep(10 * 60 * 1000);
                        } else {
                            Thread.sleep(10 * 1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }).start();
    }
}
