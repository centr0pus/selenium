package javaTest;

public class Topic_04_OS_Name  {
    public static void main(String[] args) {
        String ospath = System.getProperty("user.dir");

        System.out.println(ospath);

        String osName = System.getProperty("os.name");
        System.out.println(osName);

    }
}
