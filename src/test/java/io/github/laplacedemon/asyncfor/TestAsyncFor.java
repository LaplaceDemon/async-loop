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
    	// Synchronization method will not overflow
        Ref<Integer> x = new Ref<Integer>(0);
        AsyncFor.forloop((Self self)->{
            x.value++;
            System.out.println("loop:" + x.value);
            AsyncFor.continueLoop(self);
        }).run();
        
        Thread.sleep(100000000);
    }
    
    @Test
    public void testAsyncForNestedLoop() throws InterruptedException  {
        Ref<Integer> i = new Ref<Integer>(0);
        
        AsyncFor.forloop((Self self0) -> {
            i.value++;
            
            if(i.value>=10) {
                return;
            }
            
            CompletableFuture.runAsync(() -> {
                
                Ref<Integer> j = new Ref<Integer>(0);
                AsyncFor.forloop((Self self1)->{
                    j.value++;
                    CompletableFuture.runAsync(() -> {
                        if(j.value>=10) {
                            // loop
                            AsyncFor.continueLoop(self0);
                            return;
                        }
                        System.out.println(i.value + "*" +j.value + "=" + (i.value*j.value));
                        AsyncFor.continueLoop(self1);
                    });
                }).run();
            });
        }).run();
        
        Thread.sleep(100000000);
        /*
        for(int i = 0;i<10;i++) {
            for(int j = 0;j<10;j++) {
                System.out.println(i + "*" +j);
            }
        }
        */
    }
}
