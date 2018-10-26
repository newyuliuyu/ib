package com.ez.common.json;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.*;

/**
 * ClassName: TestBean <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-26 下午3:23 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestBean {
    @JSONField(serializeUsing = ToStringSerializer.class)
    private long a;
    private Long[] aa;
    private Long b;
}
