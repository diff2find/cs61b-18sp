public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int i = 0;
        while (i < 10) {
            
            x = x + i;
            i = i + 1;
            System.out.print(x + " ");
        }
        System.out.println();
    }
}