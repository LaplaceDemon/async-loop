package io.github.laplacedemon.asyncfor;

public class Ref<T> {
	public volatile T value;
    
    public Ref(T t) {
        super();
        this.value = t;
    }
}
