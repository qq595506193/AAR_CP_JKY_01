package com.example.aar_cp_jky_01.base;

public abstract class BaseMvpActivity<M extends IBaseModel, P extends BasePresenter> extends BaseActivity implements IBaseView {
    public M model;
    public P presenter;

    @Override
    protected void initData() {

        presenter = (P) initPresenter();
        if (presenter != null) {
            model = (M) presenter.getModel();
            if (model != null) {
                //绑定
                presenter.attach(model, this);

            }
        }

        init();
    }

    protected abstract void init();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            //解绑
            presenter.dettach();
        }
    }
}
