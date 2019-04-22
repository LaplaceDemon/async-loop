package io.github.laplacedemon.asyncfor.cb;


/**
 * https://blog.csdn.net/auntyellow/article/details/60763928
 */



public class TestCallback01 {
/*
 * 无法通过编译	
    @FunctionalInterface
    static interface IntFunc {
        int apply(int n);
    }
    
    public static void main(String[] args) {
        IntFunc factor = (int n) -> {
            return n <= 0 ? 1 : n * factor.apply(n - 1);
        };
        
        // 简写：
        IntFunc factor2 = n -> n <= 0 ? 1 : n * factor2.apply(n - 1);
        
        System.out.println(factor.apply(10)); // Expect: 3628800
    }
*/
}

