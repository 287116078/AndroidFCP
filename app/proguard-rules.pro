# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#--------------------@注释 start------------------#
-dontwarn javax.lang.model.element.**
-keep class javax.lang.model.element.** {*;}
#--------------------@注释 end-------------------#



#--------------------junit start------------------#
-dontwarn android.test.**
-keep class android.test.** {*;}
#--------------------junit end-------------------#



#--------------------javax.naming start------------------#
-dontwarn javax.naming.**
-keep class javax.naming.** {*;}
#--------------------javax.naming end-------------------#



#-------------java.lang.management start-----------------#
-dontwarn java.lang.management.**
-keep class java.lang.management.** {*;}
#-------------java.lang.management end-------------------#



#--------------------ARoute start------------------#
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}

# 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider

# 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现
-keep class * implements com.alibaba.android.arouter.facade.template.IProvider
#--------------------ARoute end-------------------#



#--------------------EventBus start------------------#
-dontwarn org.greenrobot.eventbus.**
-keep class org.greenrobot.eventbus.** {*;}
#--------------------EventBus end-------------------#



#--------------------okgo & okhhtp start------------------#
#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

#okio
-dontwarn okio.**
-keep class okio.**{*;}
#--------------------okgo & okhhtp end-------------------#