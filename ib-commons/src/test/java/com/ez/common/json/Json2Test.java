package com.ez.common.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import org.junit.Test;

/**
 * ClassName: Json2Test <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-26 下午3:21 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class Json2Test {


    @Test
    public void test() throws Exception {

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteEnumUsingToString);
        fastJsonConfig.setDateFormat("yyyy-MM-dd");

        SerializeConfig serializeConfig = new SerializeConfig();//fastJsonConfig.getSerializeConfig();
        serializeConfig.put(Long.class, ToStringSerializer.instance);
        serializeConfig.put(long.class, ToStringSerializer.instance);
//        serializeConfig.put(long[].class, ToStringSerializer.instance);
        fastJsonConfig.setSerializeConfig(serializeConfig);


//        Object object, //
//        SerializeConfig config, //
//        SerializeFilter filter, //
//        SerializerFeature... features

        TestBean testBean = TestBean.builder().a(1L).b(2L).aa(new Long[]{1L,2L}).build();
        String json = JSON.toJSONString(testBean,fastJsonConfig.getSerializeConfig(),fastJsonConfig.getSerializeFilters(),fastJsonConfig.getSerializerFeatures());
        System.out.println(json);
    }


}