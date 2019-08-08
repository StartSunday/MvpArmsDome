package com.sun.component.commonres.utils

import com.sun.component.commonsdk.utils.SPUtils

/**
 * @作者 ${yongming}
 * @创建日期 2019/8/8 9:59
 * @说明
 */
class FetConfig {
    companion object{
        fun getLogin() = SPUtils.getInstance().getBoolean("islogin",false)
    }
}