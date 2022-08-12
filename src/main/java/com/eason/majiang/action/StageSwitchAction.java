package com.eason.majiang.action;

import com.eason.majiang.action.standard.StageSwitchActionType;

public class StageSwitchAction extends Action {
    private final String nextStageName;
    public StageSwitchAction(String nextStageName)
    {
        super(StageSwitchActionType.INSTANCE);
        this.nextStageName = nextStageName;
    }

    public String getNextStageName()
    {
        return nextStageName;
    }

    @Override
    public String toString()
    {
        return "[STAGE " + nextStageName + "]";
    }
}
