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
public interface ICacheManager {
    String pathDir = "KY" + File.separator + "NerveCache" + File.separator;

    void pushData(DataEnum dataEnum, byte[] bytes);

    InputStream popData(DataEnum dataEnum);
}
