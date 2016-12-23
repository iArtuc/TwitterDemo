package com.tretton.app.base;

public interface BasePresenter<T extends BaseView>
{
    void start(T view);
}
