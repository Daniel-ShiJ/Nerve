package com.kingnet.nerve.annotation;


import static com.kingnet.nerve.Nerve.FRAME;
import static com.kingnet.nerve.Nerve.MEMORY;

import androidx.annotation.IntDef;

/**
 * Author:Daniel.ShiJ
 * Date:2022/6/7 14:45
 * Description:数据类型的限制
 */
@IntDef(value={FRAME,MEMORY})
public @interface DataEnumDef {
}
