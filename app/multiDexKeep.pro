-keep class com.yice.card.app.** { *; }
-keep class com.jess.arms.** { *; }
-keep class com.yice.component.commonsdk.** { *; }
-keep class com.yice.component.commonres.** { *; }
-keep class com.alibaba.android.arouter.** { *; }
-keep class * implements com.yice.component.commonsdk.core.GlobalConfiguration { *; }
-keep class * implements com.jess.arms.integration.ConfigModule {*;}
-keep class * implements com.jess.arms.base.delegate.AppLifecycles {*;}