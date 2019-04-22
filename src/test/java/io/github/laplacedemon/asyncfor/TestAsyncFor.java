package io.github.laplacedemon.asyncfor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.github.laplacedemon.asyncfor.AsyncFor.Self;

public class TestAsyncFor {
	@Test
    public void testAsyncFor() throws InterruptedException  {
        Ref<Integer> x = new Ref<Integer>(0);
        AsyncFor forloop = AsyncFor.forloop((Self self)->{
            x.value++;
            CompletableFuture.runAsync(() -> {
                System.out.println("loop:" + x.value);
                
                // loop
                AsyncFor.continueLoop(self);
            });
        });
        
        forloop.run();
        Thread.sleep(100000000);
    }
	
	@Test
    public void testAsyncForContinueLoop() throws InterruptedException  {
        Ref<Integer> x = new Ref<Integer>(0);
        AsyncFor forloop = AsyncFor.forloop((Self self)->{
            x.value++;
            CompletableFuture.runAsync(() -> {
                System.out.println("loop:" + x.value);
                
                // loop
                AsyncFor.continueLoop(self);
            });
        });
        
        forloop.run();
        Thread.sleep(100000000);
    }
	
	@Test
    public void testAsyncForBreak() throws InterruptedException  {
        Ref<Integer> x = new Ref<Integer>(0);
        AsyncFor forloop = AsyncFor.forloop((Self self)->{
            x.value++;
            
            // async method
            CompletableFuture.runAsync(() -> {
                System.out.println("loop:" + x.value);
                if(x.value >= 100) {
                	// async break
                	System.out.println("退出循环");
                	return ;
                }
                
                // loop
                AsyncFor.continueLoop(self);
            });
        });
        
        forloop.run();
        Thread.sleep(100000000);
    }
    
    @Test
    public void testSleep() throws InterruptedException  {
        
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        
        Ref<Integer> x = new Ref<Integer>(0);
        
        AsyncFor.forloop((Self self)->{
            x.value++;
            ses.schedule(() -> {
                System.out.println("loop:" + x.value);
                // loop
                if(x.value < 100) {
                	AsyncFor.continueLoop(self);
                }
            }, 10, TimeUnit.MILLISECONDS);
            
        }).run();
        
        Thread.sleep(100000000);
    }
    
    @Test
    public void testSyncLoop() throws InterruptedException  {
    	// 栈会溢出
        Ref<Integer> x = new Ref<Integer>(0);
        AsyncFor.forloop((Self self)->{
            x.value++;
            AsyncFor.continueLoop(self);
        }).run();
    }
}
