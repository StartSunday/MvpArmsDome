package com.sun.component.commonres.utils;

import com.sun.component.commonsdk.constant.Constant;
import com.sun.component.commonsdk.utils.SPUtils;

public class FaceUtlis {
    public static boolean getFaceIsOk(){
        boolean b = SPUtils.getInstance().getBoolean(Constant.FACEISOK,false);
        return b;
    }

}
