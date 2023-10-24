package me.surge.util;

public interface IThis<T> {

    default T _this() {
        return (T) this;
    }

}
