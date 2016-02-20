package com.smartdp.core.plugin;

/**
 * @author pengfenglong
 */
public abstract class AbstractPlugin implements Plugin {

    @Override
    public Boolean install() {
        return true;
    }

    @Override
    public Boolean uninstall() {
        return true;
    }

    @Override
    public Boolean upgrade() {
        return true;
    }
}
