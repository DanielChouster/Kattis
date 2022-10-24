import java.util.Scanner;

public class ProblemC {
    static double calcDistance(long x[], long y[]) {
        double d = 0;
        for (int i = 0; i < x.length - 1; i++) {
            d += Math.sqrt((x[i] - x[i + 1]) * (x[i] - x[i + 1]) + (y[i] - y[i + 1]) * (y[i] - y[i + 1]));
        }
        return d;
    }

    public static void main(String[] args) {
        //Input data
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] split = s.split(" ");
        int n = Integer.parseInt(split[0]);
        int t = Integer.parseInt(split[1]);
        long x[] = new long[n];
        long y[] = new long[n];
        int ti[] = new int[n];
        for (int i = 0; i < n; i++) {
            s = in.nextLine();
            split = s.split(" ");
            x[i] = Integer.parseInt(split[0]);
            y[i] = Integer.parseInt(split[1]);
            ti[i] = Integer.parseInt(split[2]);
        }
        int lastSec = ti[n - 1];
        //Calculate for every sec
        long xA[] = new long[lastSec + 1];
        long yA[] = new long[lastSec + 1];
        xA[0] = x[0];
        yA[0] = y[0];
        //GPS last
        xA[lastSec] = x[n - 1];
        yA[lastSec] = y[n - 1];
        int pos = 0;
        for (int time = 1; time < lastSec; time++) {
            if (time > ti[pos])
                pos++;
            if (time == ti[pos]) {
                xA[time] = x[pos];
                yA[time] = y[pos];
                continue;
            }
            if (x[pos] == x[pos - 1]) {
                xA[time] = x[pos];
                yA[time] = y[pos - 1] + (time - ti[pos - 1]) * ((y[pos] - y[pos - 1]) / (ti[pos] - ti[pos - 1]));
            } else {
                long l = (time - ti[pos - 1]) / (ti[pos] - time);
                xA[time] = (long) ((x[pos - 1] + l * x[pos]) / (1 + l));
                yA[time] = (long) ((y[pos - 1] + l * y[pos]) / (1 + l));
            }
        }
        //Calculate GPS array
        int nGPS = lastSec / t + 1;
        if (lastSec % t > 0)
            nGPS++;
        long xGPS[] = new long[nGPS];
        long yGPS[] = new long[nGPS];
        int tGPS[] = new int[nGPS];
        int time = 0;
        for (int i = 1; i < nGPS - 1; i++) {
            time += t;
            tGPS[i] = time;
            xGPS[i] = xA[time];
            yGPS[i] = yA[time];
        }
        //GPS first
        xGPS[0] = x[0];
        yGPS[0] = y[0];
        tGPS[0] = ti[0];
        //GPS last
        xGPS[nGPS - 1] = x[n - 1];
        yGPS[nGPS - 1] = y[n - 1];
        tGPS[nGPS - 1] = lastSec;
        //Calculate distance
        double distActual = calcDistance(x, y);
        double distGPS = calcDistance(xGPS, yGPS);
        double result = Math.abs(((distActual - distGPS) / distActual) * 100);
        System.out.println("" + String.format("%.6f", result));
    }
}