package com.kingnet.nerve.common.cache;

import com.kingnet.nerve.common.Enum.DataEnum;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Executor;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/20 17:02
 * Description:
 */
public interface ICacheManager<T> {
    String pathDir = "KY" + File.separator + "NerveCache" + File.separator;
    String pathAnalyzeDir = "KY" + File.separator + "Analyze" + File.separator;

    void pushData(DataEnum dataEnum,T t);

    boolean popData(DataEnum dataEnum);
}
