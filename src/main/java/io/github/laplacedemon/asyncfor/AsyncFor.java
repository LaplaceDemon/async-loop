package io.github.laplacedemon.asyncfor;

import java.util.concurrent.CompletableFuture;

interface AsyncFor extends Runnable {
    
    @FunctionalInterface
    static interface Self {
        void apply(Self self);
    }
    
    public static void continueLoopInAsyncCallback(Self self) {
        self.apply(self);
    }
    
    public static void continueLoop(Self self) {
        CompletableFuture.runAsync(()->{
            self.apply(self);
        });
    }
    
	public static AsyncFor forloop(Self self) {
        return ()->{
            self.apply(self);
        };
    }
	
}