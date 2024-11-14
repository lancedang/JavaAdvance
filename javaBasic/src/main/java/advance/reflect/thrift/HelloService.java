// Copyright (C) 2020 Meituan
// All rights reserved
package advance.reflect.thrift;

/**
 * @author qiankai07
 * @version 1.0
 * Created on 11/12/20 11:36 AM
 **/
public class HelloService {
    public interface InnerIFace {
        void bye(String bye);
    }

    public static class StaticInnerClient{

    }
}