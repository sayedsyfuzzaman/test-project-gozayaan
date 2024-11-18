#********************************Start rules for gson****************************************#
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}

-keepnames public class * extends androidx.fragment.app.Fragment
-keepnames public class * extends com.google.android.material.appbar.AppBarLayout.*
-keepnames abstract class com.google.android.material.appbar.HeaderBehavior
-keepclassmembers class com.google.android.material.appbar.HeaderBehavior {
    private java.lang.Runnable flingRunnable;
    android.widget.OverScroller scroller;
}
-keep class androidx.navigation** { *; }

-keep class * extends java.util.ListResourceBundle { *; }

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}
-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;
}

-keep class okhttp3.** { *; }
-dontwarn okhttp3.**

-keep class retrofit2.** { *; }
-dontwarn retrofit2.**

-keep class com.google.android.gms.** { *; }

-keepattributes LineNumberTable,SourceFile
-renamesourcefileattribute SourceFile
-keep public class * extends java.lang.Exception

-keepattributes InnerClasses -keep class **.R -keep class **.R$* { <fields>; }
-keepnames @dagger.hilt.android.lifecycle.HiltViewModel class * extends androidx.lifecycle.ViewModel
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation