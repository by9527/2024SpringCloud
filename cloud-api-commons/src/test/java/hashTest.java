public class hashTest {

    public static void main(String[] args) {

        /**
         * 定义数组长度为2的整次幂，2^4
         */
        int	length  = 16;

        /**
         * 定义key，并计算k的hash值
         */
        String k = "China";
        int h = k.hashCode();

        /**
         * 分别使用两种方式计算在数组中的位置
         */
        int index1 = h % length;
        int index2 = h & (length - 1);

        /**
         * 验证结果
         */
        System.out.println(index1 == index2);
        System.out.println("index1的值为: " + index1);
        System.out.println("index2的值为: " + index2);

        /**
         * 结果为 true
         */
    }
}
