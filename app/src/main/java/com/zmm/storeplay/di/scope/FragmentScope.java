package com.zmm.storeplay.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/30
 * Time:下午10:09
 */

@Scope
@Documented
@Retention(RUNTIME)
public @interface FragmentScope {
}
