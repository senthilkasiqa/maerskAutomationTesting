package com.maersk.common.utilities;

import com.google.common.base.Function;
/**
 *
 * @param <O>
 * @param <T>
 */

public interface ExpectedCondition<O,T> extends Function<O, T> {}

