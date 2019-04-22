package io.github.laplacedemon.asyncfor;

interface AsyncFor extends Runnable {
    
    @FunctionalInterface
    static interface Self {
        void apply(Self self);
    }
    
    public static void continueLoop(Self self) {
        self.apply(self);
    }
    
	public static AsyncFor forloop(Self self) {
        return ()->{
            self.apply(self);
        };
    }
	
}