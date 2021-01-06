/**
 * Created by wzw on 2020/11/17.
 */
public class HaveFun {
    public static void main(String[] args) {
        for(int i=1;i<=10;i++){
            try {
                Thread.sleep(1500);
                switch (i){
                    case 3:
                        continue;
                    case 5:
                        i=1;
                        System.out.println("--------------------");
                }
                System.out.println("第"+i+"次Say:HelloWorld");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


