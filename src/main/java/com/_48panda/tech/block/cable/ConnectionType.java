package com._48panda.tech.block.cable;

import net.minecraft.util.StringRepresentable;

public enum ConnectionType implements StringRepresentable {
    NONE("none"),
    NORMAL("normal"),
    EXTRACT("extract"),
    INSERT("insert");
    
    private final String name;

    private ConnectionType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.getSerializedName();
    }

    public String getSerializedName() {
        return this.name;
    }

    public boolean isConnected() {
        return this != NONE;
    }
    public boolean isConnectedNormal() {return this == NORMAL;}
    public boolean isConnectedExtract() {return this == EXTRACT;}
    public boolean isConnectedInsert() {return this == INSERT;}
    public ConnectionType setConnected(boolean isConn) {
        return isConn ? (isConnected() ? this : NORMAL) : NONE;
    }
    
}
