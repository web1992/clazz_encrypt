#include <jni.h>
#include "app_cn_web1992_jni_HelloWorld.h"
#include <stdio.h>
JNIEXPORT void JNICALL Java_app_cn_web1992_jni_HelloWorld_DisplayHello
(JNIEnv *env, jobject obj)
{
	    	printf("From jni_helloWorldImpl.cpp :");
	        printf("Hello world ! \n");
		    return;
}
