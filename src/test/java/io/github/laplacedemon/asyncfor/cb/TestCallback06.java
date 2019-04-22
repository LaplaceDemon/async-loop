package io.github.laplacedemon.asyncfor.cb;

import java.util.function.Function;

public class TestCallback06 {
	
    @FunctionalInterface
    static interface IntFunc {
        int apply(int n);
    }
    
    @FunctionalInterface
    static interface SelfIntFunc {
        int apply(SelfIntFunc self, int n);
    }
    
    public static void main(String[] args) {
    	(
			(Function<SelfIntFunc, IntFunc>)
				self -> {
					return n -> {
						return self.apply(self, n);
					};
				}
		).apply(
			(self, n) -> n <= 0 ? 1 : n * self.apply(self, n - 1)
		).apply(10);
    	
 //   	((Function<SelfIntFunc, IntFunc>)(self -> n -> self.apply(self, n);)).apply((self, n) -> n <= 0 ? 1 : n * self.apply(self, n - 1)).apply(10);
    	System.out.println();   // Expect: 3628800
    }
}
