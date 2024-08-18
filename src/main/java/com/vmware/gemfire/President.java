package com.vmware.gemfire;

import java.io.Serializable;

public class President implements Serializable {
    private String name;

    public President(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

